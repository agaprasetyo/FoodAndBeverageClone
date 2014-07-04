package id.knt.restaurant.foodandbeverage.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * table: order_item
 * field: id, menu, order, amount
 * @author cindy.situmorang
 *
 */
@DatabaseTable(tableName = "order_item")
public class OrderItem implements KeyModel<Integer> {

	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
	private Integer id;
	@DatabaseField(columnName = "menu", foreign = true, canBeNull = false)
	private Menu menu;
	@DatabaseField(columnName = "order", foreign = true, canBeNull = false)
	private Order order;
	@DatabaseField(columnName = "amount", canBeNull = false)
	private Integer amount;

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
