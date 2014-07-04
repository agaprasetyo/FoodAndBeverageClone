package id.knt.restaurant.foodandbeverage.ui.activity;

import com.ptr.folding.FoldingPaneLayout;
import id.knt.restaurant.foodandbeverage.R;
import id.knt.restaurant.foodandbeverage.ui.UIConstant;
import id.knt.restaurant.foodandbeverage.ui.customview.TypefaceSpan;
import id.knt.restaurant.foodandbeverage.ui.fragment.FragmentDetailMenuByCategory;
import id.knt.restaurant.foodandbeverage.ui.listener.FragmentMenuListener;
import id.knt.restaurant.foodandbeverage.database.model.Menu;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;

/**
 * Show detail menu Field : Picture, name, price, description
 * 
 * @author cindy.situmorang
 * 
 */
public class ActivityDetailMenu extends Activity implements FragmentMenuListener, OnGlobalLayoutListener {
	/**
	 * Field
	 */
	private FoldingPaneLayout mSlidingLayout;
	private ActionBar mActionBar;
	private int idMenu;
	private int restTextId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * Get id menu
		 */
		this.idMenu = getIntent().getIntExtra(UIConstant.KEY_EXTRA_MENU_ID, 1);
		this.restTextId = getIntent().getIntExtra(UIConstant.KEY_EXTRA_TEXTURE_ID, 1);
		
		/**
		 * Set content view
		 */
		setContentView(R.layout.activity_detail_menu);

		/**
		 * Set action bar title
		 */
		SpannableString titleBar = new SpannableString(getResources()
				.getString(R.string.app_name));
		titleBar.setSpan(new TypefaceSpan(this, "KGChasingCars.ttf"), 0,
				titleBar.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		mActionBar = getActionBar();
		mActionBar.setTitle(titleBar);
		
		mSlidingLayout = (FoldingPaneLayout) findViewById(R.id.sliding_pane_detail_menu_layout);
		mSlidingLayout.setPanelSlideListener(new SliderListener());
		mSlidingLayout.closePane();

		mSlidingLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
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
	
	/**
	 * This panel slide listener updates the action bar accordingly for each
	 * panel state.
	 */
	private class SliderListener extends SlidingPaneLayout.SimplePanelSlideListener {
		@Override
		public void onPanelOpened(View panel) {
			panelOpened();
		}

		@Override
		public void onPanelClosed(View panel) {
			panelClosed();
		}

		@Override
		public void onPanelSlide(View view, float v) {}
	}

	/**
	 * This global layout listener is used to fire an event after first layout
	 * occurs and then it is removed. This gives us a chance to configure parts
	 * of the UI that adapt based on available space after they have had the
	 * opportunity to measure and layout.
	 */

	@Override
	public void onGlobalLayout() {

		if (mSlidingLayout.isSlideable() && !mSlidingLayout.isOpen()) {
			panelClosed();
		} else {
			panelOpened();
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
			mSlidingLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
		else 
			mSlidingLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
	}

	private void panelClosed() {
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);

		getFragmentManager().findFragmentById(R.id.detail_menu_by_category_pane).setHasOptionsMenu(true);
		getFragmentManager().findFragmentById(R.id.menu_by_catgory_pane).setHasOptionsMenu(false);

	}

	private void panelOpened() {
		mActionBar.setHomeButtonEnabled(false);
		mActionBar.setDisplayHomeAsUpEnabled(false);

		if (mSlidingLayout.isSlideable()) {
			getFragmentManager().findFragmentById(R.id.detail_menu_by_category_pane).setHasOptionsMenu(false);
			getFragmentManager().findFragmentById(R.id.menu_by_catgory_pane).setHasOptionsMenu(true);
		} else {
			getFragmentManager().findFragmentById(R.id.detail_menu_by_category_pane).setHasOptionsMenu(true);
			getFragmentManager().findFragmentById(R.id.menu_by_catgory_pane).setHasOptionsMenu(false);
		}
	}

	@Override
	public void onMenuClick(Menu menu) {
		SpannableString titleBar = new SpannableString(menu.getName());
	    titleBar.setSpan(new TypefaceSpan(this, "KGChasingCars.ttf"), 0, titleBar.length(),
	            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		getActionBar().setTitle(titleBar);
		((FragmentDetailMenuByCategory) getFragmentManager().findFragmentById(R.id.detail_menu_by_category_pane)).setView(menu);
		
	}

	@Override
	public int getMenuId() {
		return this.idMenu;
	}

	@Override
	public int getTextureId() {
		return this.restTextId;
	}
}
