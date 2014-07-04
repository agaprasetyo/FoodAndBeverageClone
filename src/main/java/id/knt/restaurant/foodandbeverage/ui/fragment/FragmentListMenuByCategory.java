package id.knt.restaurant.foodandbeverage.ui.fragment;

import id.knt.restaurant.foodandbeverage.R;
import id.knt.restaurant.foodandbeverage.database.model.Category;
import id.knt.restaurant.foodandbeverage.database.model.Menu;
import id.knt.restaurant.foodandbeverage.database.service.MenuService;
import id.knt.restaurant.foodandbeverage.ui.adapter.FragmentCategoryAdapter;
import id.knt.restaurant.foodandbeverage.ui.adapter.ListViewListMenuAdapter;
import id.knt.restaurant.foodandbeverage.ui.listener.FragmentMenuListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentListMenuByCategory extends Fragment implements AdapterView.OnItemClickListener {

	private ListView listView;
	private FragmentMenuListener activityListener;
	private List<Menu> list = new ArrayList<Menu>();
	private MenuService menuService;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		try
		{
			menuService = new MenuService(getActivity());
			Menu menu = menuService.getMenu(activityListener.getMenuId());
			list.clear();
			
			// Get menu by category1
			if(menu.getCategory1() != null)
			{
				List<Menu> listMenuCategory1 = menuService.getMenuByCategory(menu.getCategory1()); 
				list.addAll(listMenuCategory1);
			}
			
			// Get menu by category2
			if(menu.getCategory2() != null)
			{
				List<Menu> listMenuCategory2 = menuService.getMenuByCategory(menu.getCategory2());
				list.addAll(listMenuCategory2);
			}
			
			// Get menu by category2
			if(menu.getCategory3() != null)
			{
				List<Menu> listMenuCategory3 = menuService.getMenuByCategory(menu.getCategory3());
				list.addAll(listMenuCategory3);
			}
		}
		catch (SQLException ex)
		{
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
		}
		
		/**
		 * Set Root View
		 */
		ListViewListMenuAdapter adapter = new ListViewListMenuAdapter(getActivity(), R.layout.row_adapter_menu_by_category_fragment, list);
		View rootView = inflater.inflate(R.layout.fragment_list_menu_by_category, container,false);
		listView = (ListView) rootView.findViewById(R.id.lv_menu_by_category_fragment);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
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
	public void onDetach() {
		super.onDetach();
		activityListener = null;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub

	}

}
