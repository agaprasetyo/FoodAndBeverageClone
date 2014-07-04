package id.knt.restaurant.foodandbeverage.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import id.knt.restaurant.foodandbeverage.R;
import id.knt.restaurant.foodandbeverage.service.proxy.AsyncCallback;

/**
 * Created by angga.prasetiyo on 5/6/2014.
 */
public class LoginActivity extends Activity implements AsyncCallback, View.OnClickListener {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

    }

    @Override
    public void onClick(View v) {
    }

	@Override
	public void onPostExecute(String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPreExecute(String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doInBackground(String data) {
		// TODO Auto-generated method stub
		
	}
}