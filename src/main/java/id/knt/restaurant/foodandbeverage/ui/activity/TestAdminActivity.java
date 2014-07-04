package id.knt.restaurant.foodandbeverage.ui.activity;

import id.knt.restaurant.foodandbeverage.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestAdminActivity extends Activity implements View.OnClickListener {
	private Button addMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.temp_admin_menu);
		addMenu = (Button) findViewById(R.id.addMenu);
		addMenu.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.addMenu:
			Intent intent = new Intent(this, TestAddMenu.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		
	}

}
