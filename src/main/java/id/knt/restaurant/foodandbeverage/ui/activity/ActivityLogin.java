package id.knt.restaurant.foodandbeverage.ui.activity;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import id.knt.restaurant.foodandbeverage.R;
import id.knt.restaurant.foodandbeverage.service.json.JSONParser;
import id.knt.restaurant.foodandbeverage.service.json.JSONConstant;
import id.knt.restaurant.foodandbeverage.service.proxy.AssetAsyncTask;
import id.knt.restaurant.foodandbeverage.service.proxy.ProxyAsyncTask;
import id.knt.restaurant.foodandbeverage.service.proxy.AsyncCallback;
import id.knt.restaurant.foodandbeverage.ui.UIConstant;
import id.knt.restaurant.foodandbeverage.ui.customview.TypefaceSpan;

/**
 * Created by angga.prasetiyo on 5/6/2014.
 */
public class ActivityLogin extends Activity implements AsyncCallback {
	private Button btnCheckin;
	private Button btnTestAdmin;
	private EditText eTextCheckin;
	private ActionBar mActionBar;
	private TextView textViewLabel;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);

		SpannableString titleActionBar = new SpannableString(getResources()
				.getString(R.string.app_name));
		titleActionBar.setSpan(new TypefaceSpan(this, "KGChasingCars.ttf"), 0,
				titleActionBar.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		mActionBar = getActionBar();
		mActionBar.setTitle(titleActionBar);

		eTextCheckin = (EditText) findViewById(R.id.et_checkin);
		btnCheckin = (Button) findViewById(R.id.btn_checkin);
		btnTestAdmin = (Button) findViewById(R.id.btn_test_admin);
		textViewLabel = (TextView)findViewById(R.id.tv_app_name_login_activity);
		
		Typeface type = Typeface.createFromAsset(getAssets(),"font/KGChasingCars.ttf"); 
		textViewLabel.setTypeface(type, Typeface.BOLD);
		textViewLabel.setText(getResources().getText(R.string.app_name));

		btnCheckin.setOnClickListener(new OnClickCheckin());
		btnTestAdmin.setOnClickListener(new OnClickTestAdmin());

	}

	private class OnClickCheckin implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			goToMainMenu();
		}
	}

	private void goToMainMenu() {
		new AssetAsyncTask(ActivityLogin.this)
				.execute(JSONConstant.ASSET_JSON_DATA);
	}

	// =============================
	// Link intent Test Admin Here
	// =============================
	private class OnClickTestAdmin implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			new ProxyAsyncTask(ActivityLogin.this)
					.execute("https://www.google.co.id/");
		}
	}

	@Override
	public void onPostExecute(String data) {
		try {
			JSONParser parser = new JSONParser(this);
			parser.processJSONObject(data);
		} catch (JSONException ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		} catch (SQLException ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}

		Intent intent = new Intent(this, ActivityMainMenu.class);
		startActivity(intent);

	}

	@Override
	public void onPreExecute(String data) {
		Toast.makeText(this, data, Toast.LENGTH_LONG).show();

	}

	@Override
	public void doInBackground(String data) {
		Toast.makeText(this, data, Toast.LENGTH_LONG).show();

	}
}