package id.knt.restaurant.foodandbeverage.ui.activity;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;
import android.widget.Toast;
import com.ptr.folding.FoldingPaneLayout;
import id.knt.restaurant.foodandbeverage.R;
import id.knt.restaurant.foodandbeverage.database.model.Category;
import id.knt.restaurant.foodandbeverage.service.proxy.AsyncCallback;
import id.knt.restaurant.foodandbeverage.ui.UIConstant;
import id.knt.restaurant.foodandbeverage.ui.customview.TypefaceSpan;
import id.knt.restaurant.foodandbeverage.ui.fragment.FragmentDetailMenu;
import id.knt.restaurant.foodandbeverage.ui.listener.FragmentCategoryListener;

/**
 * Created by angga.prasetiyo on 5/6/2014.
 */
public class ActivityMainMenu extends Activity implements AsyncCallback, FragmentCategoryListener {

	private FoldingPaneLayout mSlidingLayout;
	private ActionBar mActionBar;
	private TextView tvPromoLine;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
 
		SpannableString titleBar = new SpannableString(getResources().getString(R.string.app_name));
	    titleBar.setSpan(new TypefaceSpan(this, "KGChasingCars.ttf"), 0, titleBar.length(),
	            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		mActionBar = getActionBar();
		mActionBar.setTitle(titleBar);
		
		tvPromoLine = (TextView) findViewById(R.id.tv_promo_line);
		tvPromoLine.setText(UIConstant.MARQUE_PROMO_TEXT);
		tvPromoLine.setSelected(true);
		
		
		mSlidingLayout = (FoldingPaneLayout) findViewById(R.id.sliding_pane_layout);
		mSlidingLayout.setPanelSlideListener(new SliderListener());
		mSlidingLayout.closePane();

		mSlidingLayout.getViewTreeObserver().addOnGlobalLayoutListener(
				new FirstLayoutListener());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*
		 * The action bar up action should open the slider if it is currently
		 * closed, as the left pane contains content one level up in the
		 * navigation hierarchy.
		 */
		if (item.getItemId() == android.R.id.home && !mSlidingLayout.isOpen()) {
			mSlidingLayout.openPane();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private Fragment getFragmentById(int id) {
		return getFragmentManager().findFragmentById(id);
	}

	/**
	 * This panel slide listener updates the action bar accordingly for each
	 * panel state.
	 */
	private class SliderListener extends
			SlidingPaneLayout.SimplePanelSlideListener {
		@Override
		public void onPanelOpened(View panel) {
			Toast.makeText(panel.getContext(), "Opened", Toast.LENGTH_SHORT)
					.show();

			panelOpened();
		}

		@Override
		public void onPanelClosed(View panel) {
			Toast.makeText(panel.getContext(), "Closed", Toast.LENGTH_SHORT)
					.show();

			panelClosed();
		}

		@Override
		public void onPanelSlide(View view, float v) {
		}
	}

	private void panelClosed() {
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);

		getFragmentManager().findFragmentById(R.id.detail_pane)
				.setHasOptionsMenu(true);
		getFragmentManager().findFragmentById(R.id.menu_pane)
				.setHasOptionsMenu(false);

	}

	private void panelOpened() {
		mActionBar.setHomeButtonEnabled(false);
		mActionBar.setDisplayHomeAsUpEnabled(false);

		if (mSlidingLayout.isSlideable()) {
			getFragmentManager().findFragmentById(R.id.detail_pane)
					.setHasOptionsMenu(false);
			getFragmentManager().findFragmentById(R.id.menu_pane)
					.setHasOptionsMenu(true);
		} else {
			getFragmentManager().findFragmentById(R.id.detail_pane)
					.setHasOptionsMenu(true);
			getFragmentManager().findFragmentById(R.id.menu_pane)
					.setHasOptionsMenu(false);
		}
	}

	/**
	 * This global layout listener is used to fire an event after first layout
	 * occurs and then it is removed. This gives us a chance to configure parts
	 * of the UI that adapt based on available space after they have had the
	 * opportunity to measure and layout.
	 */

	private class FirstLayoutListener implements OnGlobalLayoutListener {
		@Override
		public void onGlobalLayout() {

			if (mSlidingLayout.isSlideable() && !mSlidingLayout.isOpen()) {
				panelClosed();
			} else {
				panelOpened();
			}
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				mSlidingLayout.getViewTreeObserver()
						.removeOnGlobalLayoutListener(this);
			} else {
				mSlidingLayout.getViewTreeObserver()
						.removeGlobalOnLayoutListener(this);
			}
		}
	}

	@Override
	public void onPostExecute(String data) {}

	@Override
	public void onPreExecute(String data) {}

	@Override
	public void doInBackground(String data) {}

	@Override
	public void onListMenuClick(List<?> list, int position, int type) {
		if(UIConstant.LIST_CATEGORY == type){
			@SuppressWarnings("unchecked")
			List<Category> fragmentList = (List<Category>) list;
			
			SpannableString titleBar = new SpannableString(fragmentList.get(position).getName());
		    titleBar.setSpan(new TypefaceSpan(this, "KGChasingCars.ttf"), 0, titleBar.length(),
		            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			getActionBar().setTitle(titleBar);
			((FragmentDetailMenu) getFragmentById(R.id.detail_pane)).setView(fragmentList.get(position));
		}
		
		mSlidingLayout.closePane();
	}

}