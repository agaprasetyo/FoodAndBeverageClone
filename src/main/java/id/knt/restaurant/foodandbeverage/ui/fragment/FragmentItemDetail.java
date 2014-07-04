package id.knt.restaurant.foodandbeverage.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import id.knt.restaurant.foodandbeverage.R;
import id.knt.restaurant.foodandbeverage.ui.adapter.PagerPictureFoodAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by angga.prasetiyo on 5/13/2014.
 */
public class FragmentItemDetail extends Fragment {
    private Activity context;

    private ViewPager viewPager;
    private PagerPictureFoodAdapter adapter;
    List listPicts = new ArrayList();
    private static final int[] IMAGE_ID = new int[]{
            R.drawable.sample,
            R.drawable.sample2,
            R.drawable.sample3,
            R.drawable.sample4
    };

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_item, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager_detail_item);
        Collections.addAll(listPicts, IMAGE_ID);
        adapter = new PagerPictureFoodAdapter(getActivity(), IMAGE_ID);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer(){
            @Override
            public void transformPage(View page, float position) {
                // do transformation here
                page.setRotationY(position * -70);
            }
        });
        return rootView;
    }
}