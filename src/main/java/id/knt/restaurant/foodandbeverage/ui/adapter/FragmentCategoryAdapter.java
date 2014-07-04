package id.knt.restaurant.foodandbeverage.ui.adapter;

import java.util.List;
import id.knt.restaurant.foodandbeverage.database.model.Category;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import id.knt.restaurant.foodandbeverage.R;

public class FragmentCategoryAdapter extends ArrayAdapter<Category> {

	private List<Category> list;
	private LayoutInflater inflater = null;
	private Context context;

	public FragmentCategoryAdapter(Context context, List<Category> list) {
		super(context, R.layout.row_adapter_category_fragment, list);

		this.list = list;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
	}
	
	@Override
	public Category getItem(int position) {
		return list.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (list.size() <= 0) {
			return view;
		}
		if (view == null) {
			view = inflater.inflate(R.layout.row_adapter_category_fragment, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.textLabel = (TextView) view.findViewById(R.id.tv_name_category_acf);
			view.setTag(viewHolder);
		}
		
		final Category item = getItem(position);
		
		ViewHolder viewHolder = (ViewHolder)view.getTag();
		Typeface type = Typeface.createFromAsset(context.getAssets(),"font/BABYBLOC.TTF"); 
		viewHolder.textLabel.setTypeface(type);
		
		viewHolder.textLabel.setText(item.getName());
		
		return view;
	}
	
	private static class ViewHolder {
		private TextView textLabel;
	}

}
