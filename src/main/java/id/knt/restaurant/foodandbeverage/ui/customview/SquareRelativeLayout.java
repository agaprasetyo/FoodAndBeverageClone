package id.knt.restaurant.foodandbeverage.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by angga.prasetiyo on 5/21/2014.
 */
public class SquareRelativeLayout extends RelativeLayout {
    public SquareRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SquareRelativeLayout(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int hight = getMeasuredWidth();
        setMeasuredDimension(hight, hight);

    }
}
