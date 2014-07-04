package id.knt.restaurant.foodandbeverage.database.service;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import android.content.Context;
import id.knt.restaurant.foodandbeverage.database.model.Order;

;
public class OrderService extends BaseService {

	private final Dao<Order, Integer> orderDao;

	public OrderService(Context context) {
		super(context, OrderService.class);
		try {
			this.orderDao = repositoryHelper.getOrderDao();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
