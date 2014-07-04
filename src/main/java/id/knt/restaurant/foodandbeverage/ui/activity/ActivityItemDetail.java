package id.knt.restaurant.foodandbeverage.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;
import id.knt.restaurant.foodandbeverage.R;
import id.knt.restaurant.foodandbeverage.service.proxy.AsyncCallback;

/**
 * Created by angga.prasetiyo on 5/13/2014.
 */
public class ActivityItemDetail extends Activity implements AsyncCallback{

	private SlidingPaneLayout mSlidingLayout;
	private ActionBar mActionBar;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_food_activity);
		mActionBar = getActionBar();
		mSlidingLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane_layout_item);
		mSlidingLayout.setPanelSlideListener(new SliderListener());
		mSlidingLayout.openPane();

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

		getFragmentManager().findFragmentById(R.id.right_pane)
				.setHasOptionsMenu(true);
		getFragmentManager().findFragmentById(R.id.left_pane)
				.setHasOptionsMenu(false);

	}

	private void panelOpened() {
		mActionBar.setHomeButtonEnabled(false);
		mActionBar.setDisplayHomeAsUpEnabled(false);

		if (mSlidingLayout.isSlideable()) {
			getFragmentManager().findFragmentById(R.id.right_pane)
					.setHasOptionsMenu(false);
			getFragmentManager().findFragmentById(R.id.left_pane)
					.setHasOptionsMenu(true);
		} else {
			getFragmentManager().findFragmentById(R.id.right_pane)
					.setHasOptionsMenu(true);
			getFragmentManager().findFragmentById(R.id.left_pane)
					.setHasOptionsMenu(false);
		}
	}

	private class FirstLayoutListener implements
			ViewTreeObserver.OnGlobalLayoutListener {
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

//	@Override
//	public void onListCategoriesClick(View view, int position) {
//		getActionBar().setTitle(FragmentListMenu.items[position]);
//		mSlidingLayout.closePane();
//	}

	@Override
	public void onPostExecute(String data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPreExecute(String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doInBackground(String data) {
		// TODO Auto-generated method stub
		
	}
}