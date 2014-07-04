package id.knt.restaurant.foodandbeverage.ui.fragment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import id.knt.restaurant.foodandbeverage.R;
import id.knt.restaurant.foodandbeverage.database.model.Category;
import id.knt.restaurant.foodandbeverage.database.service.CategoryService;
import id.knt.restaurant.foodandbeverage.ui.UIConstant;
import id.knt.restaurant.foodandbeverage.ui.adapter.FragmentCategoryAdapter;
import id.knt.restaurant.foodandbeverage.ui.listener.FragmentCategoryListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.AdapterView;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by angga.prasetiyo on May 9, 2014
 */
public class FragmentListMenu extends Fragment implements  AdapterView.OnItemClickListener {
	
	private ListView listView;
	private FragmentCategoryListener fragmentListener;
	private List<Category> list = new ArrayList<Category>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		try {
			CategoryService categoryService = new CategoryService(getActivity());
			list = categoryService.getAllCategory(Integer.MIN_VALUE, Integer.MAX_VALUE);
		} catch (SQLException ex) {
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
		}
		FragmentCategoryAdapter adapter = new FragmentCategoryAdapter(getActivity(), list);

		View rootView = inflater.inflate(R.layout.fragment_menu, container,false);
		listView = (ListView) rootView.findViewById(R.id.list_menu_fragment);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		setHasOptionsMenu(true);
		return rootView;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.list, menu);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try
		{
			fragmentListener = (FragmentCategoryListener) activity;
		}
		catch(Exception ex)
		{
			Toast.makeText(activity, ex.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		fragmentListener = null;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CategoryService categoryService = new CategoryService(getActivity());
        Category category = new Category();
        int idCategory = 1;
		fragmentListener.onListMenuClick(list, position, UIConstant.LIST_CATEGORY);
	}
}
