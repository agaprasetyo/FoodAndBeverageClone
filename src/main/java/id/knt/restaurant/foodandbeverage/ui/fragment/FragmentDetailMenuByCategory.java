package id.knt.restaurant.foodandbeverage.ui.fragment;

import java.sql.SQLException;

import id.knt.restaurant.foodandbeverage.R;
import id.knt.restaurant.foodandbeverage.database.model.Menu;
import id.knt.restaurant.foodandbeverage.database.service.MenuService;
import id.knt.restaurant.foodandbeverage.service.util.ImageLoader;
import id.knt.restaurant.foodandbeverage.ui.listener.FragmentMenuListener;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentDetailMenuByCategory extends Fragment
{
	private MenuService menuService;
	private FragmentMenuListener activityListener;
	private Menu menu;
	private ImageView ivPictureMenu;
	private TextView tvNameMenu;
	private TextView tvPriceMenu;
	private ImageLoader imageLoader;
	private LinearLayout llMenuDetail;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		try 
		{
			menuService = new MenuService(getActivity());
			menu = menuService.getMenu(activityListener.getMenuId());
			imageLoader = new ImageLoader(getActivity());
		} 
		catch (SQLException ex) 
		{
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
		}
		
		/**
		 * Set Root View
		 */
		View rootView = inflater.inflate(R.layout.fragment_detail_menu_by_category, container,false);
		
		/**
		 * Get Views
		 */
		llMenuDetail = (LinearLayout) rootView.findViewById(R.id.ll_menu_fdmbc);
		ivPictureMenu = (ImageView) rootView.findViewById(R.id.iv_picture_menu_fdmbc);
		tvNameMenu = (TextView) rootView.findViewById(R.id.tv_name_menu_fdmbc);
		tvPriceMenu = (TextView) rootView.findViewById(R.id.tv_price_menu_fdmbc);
		
		/**
		 * Set Views value
		 */
		
		tvNameMenu.setText(menu.getName());
		tvPriceMenu.setText(String.valueOf(menu.getPrice()));
		
		try
		{
			imageLoader.DisplayImage(menu.getPicture(), ivPictureMenu);
			llMenuDetail.setBackgroundResource(activityListener.getTextureId());
		}
		catch (Exception ex)
		{
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
		}
		
		setHasOptionsMenu(true);
		
		return rootView;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try
		{
			activityListener = (FragmentMenuListener) activity;
		}
		catch (Exception ex)
		{
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public void onDetach() 
	{
		super.onDetach();
		activityListener = null;
	}
	
	@Override
	public void onCreateOptionsMenu(android.view.Menu menu, MenuInflater inflater) 
	{
		inflater.inflate(R.menu.list, menu);
	}
	
	public void setView(Menu menu)
	{
		if(menu != null)
		{
			/**
			 * Set Views value
			 */
			
			tvNameMenu.setText(menu.getName());
			tvPriceMenu.setText(String.valueOf(menu.getPrice()));
			
			try
			{
				imageLoader.DisplayImage(menu.getPicture(), ivPictureMenu);
			}
			catch (Exception ex)
			{
				Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
			}
		}
	}
}
