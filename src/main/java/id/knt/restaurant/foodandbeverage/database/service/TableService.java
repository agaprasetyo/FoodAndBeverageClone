package id.knt.restaurant.foodandbeverage.database.service;

import id.knt.restaurant.foodandbeverage.database.model.Table;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

import android.content.Context;

public class TableService extends BaseService{
	private Dao<Table, Integer> tableDao;

	public TableService(Context context) {
		super(context, TableService.class);
		try {
			this.tableDao = repositoryHelper.getTableDao();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
