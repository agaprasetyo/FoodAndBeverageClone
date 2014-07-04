package id.knt.restaurant.foodandbeverage.ui.fragment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jess.ui.TwoWayAdapterView;
import com.jess.ui.TwoWayGridView;

import android.content.Intent;
import android.widget.Toast;
import id.knt.restaurant.foodandbeverage.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import id.knt.restaurant.foodandbeverage.database.model.Category;
import id.knt.restaurant.foodandbeverage.database.model.Menu;
import id.knt.restaurant.foodandbeverage.database.service.MenuService;
import id.knt.restaurant.foodandbeverage.ui.activity.ActivityItemDetail;
import id.knt.restaurant.foodandbeverage.ui.activity.DetailMenuActivity;
import id.knt.restaurant.foodandbeverage.ui.adapter.GridViewMenuAdapter;
import id.knt.restaurant.foodandbeverage.ui.adapter.HorizontalGridViewAdapter;

/**
 * Created by angga.prasetiyo on May 9, 2014
 */
public class FragmentDetailMenu extends Fragment 
{
	/**
	 * Service
	 */
	private MenuService menuService;
	private List<Menu> list = new ArrayList<Menu>();

	/**
	 * View
	 */
	private TwoWayGridView gridViewMenu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		try 
		{
			menuService = new MenuService(getActivity());
			list = menuService.getAll(Integer.MIN_VALUE, Integer.MAX_VALUE);
		} 
		catch (SQLException ex) 
		{
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
		}

		View view = inflater.inflate(R.layout.fragment_detail_menu, container,false);

		gridViewMenu = (TwoWayGridView) view.findViewById(R.id.horz_gridview);
		gridViewMenu.setAdapter(new HorizontalGridViewAdapter(getActivity(), gridViewMenu,list));
		setHasOptionsMenu(true);
        gridViewMenu.setOnItemClickListener(new OnClickMenu());
		return view;
	}

	public void setView(Category category) 
	{
		try {
			list = menuService.getMenuByCategory(category);
		} catch (SQLException ex) {
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
		}
		gridViewMenu.setAdapter(new HorizontalGridViewAdapter(getActivity(), gridViewMenu,list));
//		gridViewMenu.invalidateViews();

	}

    private class OnClickMenu implements com.jess.ui.TwoWayAdapterView.OnItemClickListener {
        @Override
        public void onItemClick(TwoWayAdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getActivity(), String.valueOf(position)+" Clicked", Toast.LENGTH_LONG).show();
        }
    }
}
