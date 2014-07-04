package id.knt.restaurant.foodandbeverage.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by angga.prasetiyo on 5/13/2014.
 */
public class SquareImageView extends ImageView {

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SquareImageView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int hight = getMeasuredWidth();
        setMeasuredDimension(hight, hight);

    }
}
