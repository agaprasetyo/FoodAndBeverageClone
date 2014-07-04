package id.knt.restaurant.foodandbeverage.database.service;

import id.knt.restaurant.foodandbeverage.database.model.OrderItem;
import java.sql.SQLException;
import com.j256.ormlite.dao.Dao;
import android.content.Context;

public class OrderItemService extends BaseService{
	
	private final Dao<OrderItem, Integer> orderItemDao;

	public OrderItemService(Context context) {
		super(context, OrderItemService.class);
		try {
			this.orderItemDao = repositoryHelper.getOrderItemDao();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
