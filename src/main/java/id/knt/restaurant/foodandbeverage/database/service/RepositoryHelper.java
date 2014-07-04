package id.knt.restaurant.foodandbeverage.database.service;

import id.knt.restaurant.foodandbeverage.database.DbConstant;
import id.knt.restaurant.foodandbeverage.database.model.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class RepositoryHelper extends OrmLiteSqliteOpenHelper {

	private static final String TAG = RepositoryHelper.class.getName();

	private Dao<Order, Integer> order;
	private Dao<OrderItem, Integer> orderItem;
	private Dao<Bill, Integer> bill;
	private Dao<Table, Integer> table;
	private Dao<Rate, Integer> rate;
	private Dao<Menu, Integer> menu;
	private Dao<Category, Integer> category;

	public RepositoryHelper(Context context) {
		super(context, DbConstant.DATABASE_NAME, null,
				DbConstant.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database,
			ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Category.class);
			TableUtils.createTable(connectionSource, Menu.class);
			TableUtils.createTable(connectionSource, Order.class);
			TableUtils.createTable(connectionSource, OrderItem.class);
			TableUtils.createTable(connectionSource, Table.class);
			TableUtils.createTable(connectionSource, Rate.class);
			TableUtils.createTable(connectionSource, Bill.class);

		} catch (java.sql.SQLException ex) {
			Log.d(TAG, ex.getMessage());
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase database,
			ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, Category.class, true);
			TableUtils.dropTable(connectionSource, Menu.class, true);
			TableUtils.dropTable(connectionSource, Order.class, true);
			TableUtils.dropTable(connectionSource, OrderItem.class, true);
			TableUtils.dropTable(connectionSource, Table.class, true);
			TableUtils.dropTable(connectionSource, Rate.class, true);
			TableUtils.dropTable(connectionSource, Bill.class, true);
			onCreate(database, connectionSource);
		} catch (java.sql.SQLException ex) {
			Log.d(TAG, ex.getMessage());
		}
	}

	@Override
	public void close() {
		super.close();
		this.order = null;
		this.orderItem = null;
		this.table = null;
		this.rate = null;
		this.bill = null;
		this.menu = null;
		this.category = null;
	}

	public Dao<Order, Integer> getOrderDao() throws java.sql.SQLException {
		if (order == null)
			order = getDao(Order.class);
		return order;
	}

	public Dao<OrderItem, Integer> getOrderItemDao()
			throws java.sql.SQLException {
		if (orderItem == null)
			orderItem = getDao(OrderItem.class);
		return orderItem;
	}

	public Dao<Table, Integer> getTableDao() throws java.sql.SQLException {
		if (table == null)
			table = getDao(Table.class);
		return table;
	}

	public Dao<Bill, Integer> getBillDao() throws java.sql.SQLException {
		if (bill == null)
			bill = getDao(Bill.class);
		return bill;
	}

	public Dao<Rate, Integer> getRateDao() throws java.sql.SQLException {
		if (rate == null)
			rate = getDao(Rate.class);
		return rate;
	}

	public Dao<Menu, Integer> getMenuDao() throws java.sql.SQLException {
		if (menu == null)
			menu = getDao(Menu.class);
		return menu;
	}

	public Dao<Category, Integer> getCategoryDao() throws java.sql.SQLException {
		if (category == null)
			category = getDao(Category.class);
		return category;
	}

}
