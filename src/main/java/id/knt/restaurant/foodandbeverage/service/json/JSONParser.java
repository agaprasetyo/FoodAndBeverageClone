package id.knt.restaurant.foodandbeverage.service.json;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;

import id.knt.restaurant.foodandbeverage.database.model.Category;
import id.knt.restaurant.foodandbeverage.database.model.Menu;
import id.knt.restaurant.foodandbeverage.database.service.CategoryService;
import id.knt.restaurant.foodandbeverage.database.service.MenuService;

import org.json.*;

import android.content.Context;

/**
 * 
 * Parse json data then save to device local database
 * 
 * @author cindy.situmorang
 *
 */

public class JSONParser {
	private Context context;

	public JSONParser(Context context) throws JSONException,
			SQLException {
		this.context = context;
	}

	public void processJSONObject(String json) throws JSONException, SQLException {
		JSONObject jsonObj = new JSONObject(json.trim());
		Iterator<?> keys = jsonObj.keys();

		while (keys.hasNext()) 
		{
			String key = (String) keys.next();
			if (jsonObj.get(key) instanceof JSONObject) 
			{
				JSONObject child = jsonObj.getJSONObject(key);
				if (JSONConstant.CATEGORY.equals(key)) 
				{
					saveCategory(child);
				} 
				else if (JSONConstant.MENU.equals(key)) 
				{
					saveMenu(child);
				}
			} else if (jsonObj.get(key) instanceof JSONArray) 
			{
				JSONArray array = jsonObj.getJSONArray(key);
				if (JSONConstant.CATEGORY.equals(key)) 
				{
					for (int i = 0; i < array.length(); i++) 
					{
						saveCategory(array.getJSONObject(i));
					}

				} 
				else if (JSONConstant.MENU.equals(key)) 
				{
					for (int i = 0; i < array.length(); i++) 
					{
						saveMenu(array.getJSONObject(i));
					}
				} else 
				{
					throw new JSONException("Wrong data format");
				}
			} 
			else 
			{
				throw new JSONException("Wrong data format");
			}

		}
	}

	private void saveMenu(JSONObject child) throws JSONException, SQLException {
		Menu menu = new Menu();

		Integer id = child.getInt(JSONConstant.TAG_ID);
		String name = child.getString(JSONConstant.TAG_NAME);
		BigDecimal price = new BigDecimal(
				child.getDouble(JSONConstant.TAG_PRICE));
		String picture = child.getString(JSONConstant.TAG_PICTURE);
		String desc = child.getString(JSONConstant.TAG_DESCRIPTION);
		Boolean active = child.getBoolean(JSONConstant.TAG_ACTIVE);
		Boolean soldOut = child.getBoolean(JSONConstant.TAG_SOLD_OUT);
		Boolean newProduct = child.getBoolean(JSONConstant.TAG_NEW_PRODUCT);

		int categoryId1 = child.getInt(JSONConstant.TAG_CATEGORY1);
		int categoryId2 = child.getInt(JSONConstant.TAG_CATEGORY2);
		int categoryId3 = child.getInt(JSONConstant.TAG_CATEGORY3);

		CategoryService service = new CategoryService(context);

		menu.setId(id);
		menu.setName(name);
		menu.setPrice(price);
		menu.setPicture(picture);
		menu.setDescription(desc);
		menu.setActive(active);
		menu.setSoldOut(soldOut);
		menu.setNewProduct(newProduct);

		if (categoryId1 != -1)
			menu.setCategory1(service.getCategoryById(categoryId1));
		if (categoryId2 != -1)
			menu.setCategory1(service.getCategoryById(categoryId2));
		if (categoryId3 != -1)
			menu.setCategory1(service.getCategoryById(categoryId3));

		MenuService menuService = new MenuService(context);
		menuService.create(menu);
	}

	private void saveCategory(JSONObject child) throws JSONException,SQLException 
	{
		Category category = new Category();
		category.setId(child.getInt(JSONConstant.TAG_ID));
		category.setName(child.getString(JSONConstant.TAG_NAME));

		CategoryService service = new CategoryService(context);
		service.save(category);
	}

}
