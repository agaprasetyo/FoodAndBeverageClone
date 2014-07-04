package id.knt.restaurant.foodandbeverage.database.service;

import java.sql.SQLException;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import id.knt.restaurant.foodandbeverage.database.model.Rate;

public class RateService extends BaseService{
	
	private final Dao<Rate, Integer> rateDao;

	public RateService(Context context) {
		super(context, RateService.class);
		try {
			this.rateDao = repositoryHelper.getRateDao();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
