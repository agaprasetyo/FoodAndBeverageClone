package id.knt.restaurant.foodandbeverage.ui.adapter;

import id.knt.restaurant.foodandbeverage.R;
import id.knt.restaurant.foodandbeverage.database.model.Menu;
import id.knt.restaurant.foodandbeverage.service.util.ImageLoader;
import id.knt.restaurant.foodandbeverage.ui.UIConstant;
import id.knt.restaurant.foodandbeverage.ui.activity.ActivityDetailMenu;
import id.knt.restaurant.foodandbeverage.ui.adapter.GridViewMenuAdapter.ViewHolder;
import id.knt.restaurant.foodandbeverage.ui.customview.SquareImageView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import com.jess.ui.TwoWayAbsListView;
import com.jess.ui.TwoWayGridView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HorizontalGridViewAdapter extends BaseAdapter {

	private Context context;
	private List<Menu> list;

	// HorzGridView stuff
	private final int childLayoutResourceId = R.layout.row_grid_view_menu;
	private int itemPadding;
	private int itemWidth;
	private int itemHeight;
	private TwoWayGridView horzGridView;
	public ImageLoader imageLoader;

	public HorizontalGridViewAdapter(Context context, TwoWayGridView horzGridView, List<Menu> data){
		this.context = context;
		this.list = data;
		this.horzGridView = horzGridView; 
		this.imageLoader=new ImageLoader(context);
		//Get dimensions from values folders; note that the value will change
		//based on the device size but the dimension name will remain the same
		Resources res = context.getResources();
		this.itemPadding = (int) res.getDimension(R.dimen.grid_child_padding);
		this.itemWidth = (int) res.getDimension(R.dimen.grid_child_width);
		this.itemHeight = (int) res.getDimension(R.dimen.grid_child_height);
		
		
		//HorzGridView size not established yet, so need to set it using a viewtreeobserver
		ViewTreeObserver vto = this.horzGridView.getViewTreeObserver();
		
		OnGlobalLayoutListener onGlobalLayoutListener = new OnGlobalLayoutListener() {
			
			@SuppressWarnings("deprecation")
			@SuppressLint("NewApi")
			@Override
			public void onGlobalLayout() {
				
				
				//First use the gridview height and width to determine child values
				int rowHeight = itemHeight ;//- (2*itemPadding);
				int numRows = (int)((float)(HorizontalGridViewAdapter.this.horzGridView.getHeight()/rowHeight));
				
				
				HorizontalGridViewAdapter.this.horzGridView.setNumRows(numRows);
				HorizontalGridViewAdapter.this.horzGridView.setRowHeight(rowHeight);
				HorizontalGridViewAdapter.this.horzGridView.setColumnWidth(rowHeight);
				
				//Then remove the listener
				ViewTreeObserver vto = HorizontalGridViewAdapter.this.horzGridView.getViewTreeObserver();
				
				if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
					vto.removeOnGlobalLayoutListener(this);
				}else{
					vto.removeGlobalOnLayoutListener(this);
				}
				
				
				
			}
		};
		
		vto.addOnGlobalLayoutListener(onGlobalLayoutListener);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHandler viewHolder = null;

		if (view == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			view = inflater.inflate(childLayoutResourceId, parent, false);
			viewHolder = new ViewHandler();
			viewHolder.tvNameMenu = (TextView) view.findViewById(R.id.tv_name_menu_grid_view);
			viewHolder.tvFlagMenu = (TextView) view.findViewById(R.id.tv_flag_menu_grid_view);
			viewHolder.ivPictureMenu = (SquareImageView) view.findViewById(R.id.iv_picture_menu_grid_view);
			viewHolder.llFlagMenu = (LinearLayout) view.findViewById(R.id.ll_flag_menu_grid_view);
			viewHolder.flMenu = (FrameLayout) view.findViewById(R.id.fl_menu_grid_view);
			viewHolder.llMenu = (LinearLayout) view.findViewById(R.id.ll_menu_grid_view);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHandler) view.getTag();
		}

		Animation animation = AnimationUtils
				.loadAnimation(context, R.anim.bounce);

		final Menu menu = list.get(position);
		

		int rowHeight = itemHeight - (2*itemPadding);
		int rowWidth = itemWidth - (2*itemPadding);
		TwoWayAbsListView.LayoutParams lp 
			= new TwoWayAbsListView.LayoutParams(rowWidth, rowHeight);
		viewHolder.flMenu.setLayoutParams(lp);
		
		Typeface type = Typeface.createFromAsset(context.getAssets(),"font/BiCoLoRsSt.ttf"); 
		viewHolder.tvNameMenu.setTypeface(type);
		viewHolder.tvNameMenu.setText(menu.getName());
		imageLoader.DisplayImage(menu.getPicture(), viewHolder.ivPictureMenu);

		if (menu.getNewProduct() || menu.getSoldOut()) {
			viewHolder.llFlagMenu.setVisibility(View.VISIBLE);

			if (menu.getNewProduct())
				viewHolder.tvFlagMenu.setText(UIConstant.TAG_NEW_PRODUCT);
			if (menu.getSoldOut())
				viewHolder.tvFlagMenu.setText(UIConstant.TAG_SOLD_OUT);

		}
		
		// Random background
		Random random = new Random();
		
		int bgInt = random.nextInt((UIConstant.AVAILABLE_ROW_COLOR - 1)+1)+1;
		final int restTextId;
		
		switch (bgInt) {
		case 1:
			restTextId =  R.drawable.rounder_corner_1;
			break;
		case 2:
			restTextId = R.drawable.rounder_corner_2;
			break;
		case 3:
			restTextId = R.drawable.rounder_corner_3;
			break;
		case 4:
			restTextId = R.drawable.rounder_corner_4;
			break;
		case 5:
			restTextId = R.drawable.rounder_corner_5;
			break;
		case 6:
			restTextId = R.drawable.rounder_corner_6;
			break;
		default:
			restTextId = R.drawable.rounder_corner;
			break;
		}
		viewHolder.flMenu.setBackgroundResource(restTextId);
		viewHolder.llMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) 
			{
				Intent intent = new Intent(context, ActivityDetailMenu.class);
				intent.putExtra(UIConstant.KEY_EXTRA_MENU_ID, menu.getId());
				intent.putExtra(UIConstant.KEY_EXTRA_TEXTURE_ID, restTextId);
				context.startActivity(intent);
			}
		});
		
		view.startAnimation(animation);
		animation = null;
		return view;
	}

	class ViewHandler {
		TextView tvNameMenu;
		TextView tvFlagMenu;
		ImageView ivPictureMenu;
		LinearLayout llFlagMenu;
		LinearLayout llMenu;
		FrameLayout flMenu;
	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public Menu getItem(int position) {

		return list.get(position);
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

}
