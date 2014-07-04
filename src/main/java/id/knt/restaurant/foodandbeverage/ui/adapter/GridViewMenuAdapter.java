package id.knt.restaurant.foodandbeverage.ui.adapter;

import id.knt.restaurant.foodandbeverage.R;
import id.knt.restaurant.foodandbeverage.database.model.Menu;
import id.knt.restaurant.foodandbeverage.service.util.ImageLoader;
import id.knt.restaurant.foodandbeverage.ui.UIConstant;
import id.knt.restaurant.foodandbeverage.ui.customview.SquareImageView;

import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.app.Activity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GridViewMenuAdapter extends ArrayAdapter<Menu> {
	private Context context;
	private List<Menu> list;
	private int layout;
    public ImageLoader imageLoader; 

	public GridViewMenuAdapter(Context context, int layout, List<Menu> list) {
		super(context, layout, list);
		this.context = context;
		this.layout = layout;
		this.list = list;
		this.imageLoader=new ImageLoader(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder viewHolder = null;

		if (view == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			view = inflater.inflate(layout, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.tvNameMenu = (TextView) view.findViewById(R.id.tv_name_menu_grid_view);
			viewHolder.tvFlagMenu = (TextView) view.findViewById(R.id.tv_flag_menu_grid_view);
			viewHolder.ivPictureMenu = (SquareImageView) view.findViewById(R.id.iv_picture_menu_grid_view);
			viewHolder.llFlagMenu = (LinearLayout) view.findViewById(R.id.ll_flag_menu_grid_view);
			viewHolder.flMenu = (FrameLayout) view.findViewById(R.id.fl_menu_grid_view);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		Animation animation = AnimationUtils
				.loadAnimation(context, R.anim.move);

		Menu menu = list.get(position);
		
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
		
		switch (bgInt) {
		case 1:
			viewHolder.flMenu.setBackgroundResource(R.drawable.rounder_corner_1);
			break;
		case 2:
			viewHolder.flMenu.setBackgroundResource(R.drawable.rounder_corner_2);
			break;
		case 3:
			viewHolder.flMenu.setBackgroundResource(R.drawable.rounder_corner_3);
			break;
		case 4:
			viewHolder.flMenu.setBackgroundResource(R.drawable.rounder_corner_4);
			break;
		case 5:
			viewHolder.flMenu.setBackgroundResource(R.drawable.rounder_corner_5);
			break;
		case 6:
			viewHolder.flMenu.setBackgroundResource(R.drawable.rounder_corner_6);
			break;
		default:
			viewHolder.flMenu.setBackgroundResource(R.drawable.rounder_corner);
			break;
		}
		
		view.startAnimation(animation);
		animation = null;
		return view;
	}

	static class ViewHolder {
		TextView tvNameMenu;
		TextView tvFlagMenu;
		ImageView ivPictureMenu;
		LinearLayout llFlagMenu;
		FrameLayout flMenu;
	}

}
