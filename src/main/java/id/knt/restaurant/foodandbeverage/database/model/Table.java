package id.knt.restaurant.foodandbeverage.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * table: table
 * field: id, name
 * @author cindy.situmorang
 *
 */
@DatabaseTable(tableName = "table")
public class Table implements KeyModel<Integer>{
	
	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
	private Integer id;
	@DatabaseField(columnName = "name")
	private String name;

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
