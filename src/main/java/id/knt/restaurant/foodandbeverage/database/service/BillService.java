package id.knt.restaurant.foodandbeverage.database.service;

import java.sql.SQLException;
import id.knt.restaurant.foodandbeverage.database.model.Bill;
import com.j256.ormlite.dao.Dao;
import android.content.Context;

public class BillService extends BaseService {
	private final Dao<Bill, Integer> billDao;

	public BillService(Context context) {
		super(context, BillService.class);
		try {
			this.billDao = repositoryHelper.getBillDao();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
