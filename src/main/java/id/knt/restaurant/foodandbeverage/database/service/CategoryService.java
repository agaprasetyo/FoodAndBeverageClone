package id.knt.restaurant.foodandbeverage.database.service;


import java.sql.SQLException;
import java.util.List;

import id.knt.restaurant.foodandbeverage.database.model.Category;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import android.content.Context;

public class CategoryService extends BaseService{
	
	private final Dao<Category, Integer> category;

	public CategoryService(Context context) {
		super(context, CategoryService.class);
		try {
			this.category = repositoryHelper.getCategoryDao();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void save(Category category) throws SQLException{
		QueryBuilder<Category, Integer> qb = this.category.queryBuilder();
		int size = qb.where().eq("id", category.getId()).or().eq("name", category.getName()).query().size();
		if(size == 0)
			this.category.create(category);
		else 
			this.category.update(category);
	}
	
	public Category getCategoryById(Integer id) throws SQLException{
		return this.category.queryForId(id);
	}
	
	public List<Category> getAllCategory(long start, long to) throws SQLException{
		return this.category.queryBuilder().offset(start).limit(to).query();
	}

}
