package id.knt.restaurant.foodandbeverage.ui.recycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import id.knt.restaurant.foodandbeverage.R;
import id.knt.restaurant.foodandbeverage.ui.activity.ActivityMainMenu;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by angga.prasetiyo on 5/6/2014.
 */
public class DashboardActivity extends Activity {
    private Button btnToMenu;
    private ImageView imageView;
    private static final int[] IMAGES = new int[]{R.drawable.sample, R.drawable.sample2, R.drawable.sample3
            , R.drawable.sample4};
    Timer timer;
    TimerTask task;
    public int currentimageindex = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        imageView = (ImageView) findViewById(R.id.imageViewDashboard);
        final Handler handler = new Handler();
        final Runnable updateResults = new Runnable() {
            @Override
            public void run() {
                AnimatedSlideShow();
            }
        };
        int delay = 1000; // delay for 1 sec.

        int period = 4000; // repeat every 4 sec.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                handler.post(updateResults);

            }

        }, delay, period);

        btnToMenu = (Button) findViewById(R.id.btnToMenu);
        btnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityMainMenu.class);
                startActivity(intent);
            }
        });
    }

    private void AnimatedSlideShow() {
        imageView.setImageResource(IMAGES[currentimageindex % IMAGES.length]);
        currentimageindex++;
        Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.custom_anim);

        imageView.startAnimation(rotateimage);
    }
}