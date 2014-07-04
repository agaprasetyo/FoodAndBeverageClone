package id.knt.restaurant.foodandbeverage.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import id.knt.restaurant.foodandbeverage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angga.prasetiyo on 5/7/2014.
 */
public class PagerPictureFoodAdapter extends PagerAdapter {
    private Context context;
    private int[] listPict;
    private LayoutInflater inflater;

    public PagerPictureFoodAdapter(Context context, int[] listPict){
        this.listPict = listPict;
        this.context = context;
    }
    @Override
    public int getCount() {
        return this.listPict.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == ((LinearLayout) o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        ImageView imageView;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.image_detail_food_item, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.imageViewDetail);

        imageView.setImageResource(listPict[position]);
        ((ViewPager) container).addView(rootView);
        return  rootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);

    }
}
