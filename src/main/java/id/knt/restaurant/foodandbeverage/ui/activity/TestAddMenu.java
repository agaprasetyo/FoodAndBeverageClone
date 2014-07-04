package id.knt.restaurant.foodandbeverage.ui.activity;

import id.knt.restaurant.foodandbeverage.R;
import id.knt.restaurant.foodandbeverage.database.model.Menu;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class TestAddMenu extends Activity implements View.OnClickListener{
	// ==================================
	// FIELDS
	// =====================================
	private AutoCompleteTextView typeMenuAdd;
	private AutoCompleteTextView categoryAdd;
	private EditText nameMenuAdd;
	private EditText priceMenuAdd;
	private EditText descriptionMenuAdd;
	private Button buttonAdd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_add_menu);
		
		// Get view
		typeMenuAdd = (AutoCompleteTextView) findViewById(R.id.typeMenuAdd);
		categoryAdd = (AutoCompleteTextView) findViewById(R.id.categoryMenuAdd);
		nameMenuAdd = (EditText) findViewById(R.id.nameMenuNameAdd);
		priceMenuAdd = (EditText) findViewById(R.id.priceMenuAdd);
		descriptionMenuAdd = (EditText) findViewById(R.id.descriptionMenuAdd);
		buttonAdd = (Button) findViewById(R.id.addMenu);
		buttonAdd.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.addMenu:
			save();
			break;

		default:
			break;
		}
	}
	
	private void save(){
		
	}

}
