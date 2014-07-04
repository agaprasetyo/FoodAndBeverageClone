package id.knt.restaurant.foodandbeverage.database.service;

import id.knt.restaurant.foodandbeverage.database.model.*;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import android.content.Context;

public class MenuService extends BaseService{
	
	private final Dao<Menu, Integer> menuDao;

	public MenuService(Context context) 
	{
		super(context, MenuService.class);
		try {
			this.menuDao = repositoryHelper.getMenuDao();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void create(Menu menu) throws SQLException
	{
		QueryBuilder<Menu, Integer> qb = this.menuDao.queryBuilder();
		int size = qb.where().eq("id", menu.getId()).or().eq("name", menu.getName()).query().size();
		if(size == 0)
			this.menuDao.create(menu);
		else 
			this.menuDao.update(menu);
	}
	
	public List<Menu> getMenuByCategory(Category category) throws SQLException
	{
		QueryBuilder<Menu, Integer> qb = this.menuDao.queryBuilder();
		return qb.where().eq("category_1", category).or().eq("category_2", category).or().eq("category_3", category).query();
	}
	
	public List<Menu> getAll(long from, long to) throws SQLException
	{	
		return this.menuDao.queryBuilder().offset( from ).limit( to ).query();
	}
	
	public Menu getMenu(Integer id) throws SQLException
	{
		return this.menuDao.queryForId(id);
	}
	
}
