package id.knt.restaurant.foodandbeverage.ui.listener;

import id.knt.restaurant.foodandbeverage.database.model.Menu;

public interface FragmentMenuListener {
	void onMenuClick(Menu menu);
	int getMenuId();
	int getTextureId();
}
