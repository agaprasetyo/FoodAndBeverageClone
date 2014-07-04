package id.knt.restaurant.foodandbeverage.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * table: order
 * field: id, table, note, active
 * @author cindy.situmorang
 *
 */
@DatabaseTable(tableName = "order")
public class Order implements KeyModel<Integer> {

	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
	private Integer id;
	@DatabaseField(columnName = "table", foreign = true, canBeNull = false)
	private Table table;
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

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
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
