package id.knt.restaurant.foodandbeverage.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * table : category
 * field : id, name
 * @author cindy.situmorang
 *
 */
@DatabaseTable(tableName = "category")
public class Category implements KeyModel<Integer>{
	
	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
	private Integer id;
	@DatabaseField(columnName = "name", canBeNull = false)
	private String name;
	
	public Category(){
	}

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
