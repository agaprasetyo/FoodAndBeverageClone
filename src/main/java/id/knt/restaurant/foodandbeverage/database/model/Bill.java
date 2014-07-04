package id.knt.restaurant.foodandbeverage.database.model;

import java.math.BigDecimal;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * table : bill
 * field : id, order, total, tax, service_charge, note, active
 * @author cindy.situmorang
 *
 */

@DatabaseTable(tableName = "bill")
public class Bill implements KeyModel<Integer> {

	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
	private Integer id;
	@DatabaseField(columnName = "order", foreign = true, canBeNull = false)
	private Order order;
	@DatabaseField(columnName = "total", dataType = DataType.BIG_DECIMAL, canBeNull = false)
	private BigDecimal total;
	@DatabaseField(columnName = "tax", canBeNull = false)
	private BigDecimal tax;
	@DatabaseField(columnName = "service_charge", canBeNull = false)
	private BigDecimal serviceCharge;
	@DatabaseField(columnName = "note", canBeNull = true)
	private String note;
	@DatabaseField(columnName = "active", canBeNull = false, defaultValue = "true")
	protected Boolean active;

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;

	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
