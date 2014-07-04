package id.knt.restaurant.foodandbeverage.ui.adapter;

import java.util.List;
import id.knt.restaurant.foodandbeverage.database.model.Menu;
import id.knt.restaurant.foodandbeverage.service.util.ImageLoader;
import id.knt.restaurant.foodandbeverage.ui.adapter.GridViewMenuAdapter.ViewHolder;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import id.knt.restaurant.foodandbeverage.R;

public class FragmentMenuAdapter extends ArrayAdapter<Menu> {

	private List<Menu> list;
	private LayoutInflater inflater = null;
	private Context context;
	public ImageLoader imageLoader;

	public FragmentMenuAdapter(Context context, List<Menu> list) {
		super(context, R.layout.row_adapter_menu_by_category_fragment, list);

		this.list = list;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		
		this.imageLoader=new ImageLoader(context);
	}
	
	@Override
	public Menu getItem(int position) {
		return list.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder viewHolder = null;
		
		if (view == null) {
			view = inflater.inflate(R.layout.row_adapter_category_fragment, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.tvNameMenu = (TextView) view.findViewById(R.id.tv_name_category_acf);
			view.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.move);
		
		final Menu item = getItem(position);
		
		Typeface type = Typeface.createFromAsset(context.getAssets(),"font/BABYBLOC.TTF"); 
		viewHolder.tvNameMenu.setTypeface(type);
		
		viewHolder.tvNameMenu.setText(item.getName());
		
		return view;
	}
	
	private static class ViewHolder {
		TextView tvNameMenu;
		TextView tvFlagMenu;
		ImageView ivPictureMenu;
		LinearLayout llFlagMenu;
		FrameLayout flMenu;
	}

}
