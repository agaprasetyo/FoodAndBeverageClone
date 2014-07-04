package id.knt.restaurant.foodandbeverage.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/*
 * table: rate
 * field: id, menu, star, comment
 */
@DatabaseTable(tableName = "rate")
public class Rate implements KeyModel<Integer> {

	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
	private Integer id;
	@DatabaseField(columnName = "menu", foreign = true, canBeNull = false)
	private Menu menu;
	@DatabaseField(columnName = "star", canBeNull = false)
	private Integer star;
	@DatabaseField(columnName = "comment", canBeNull = true)
	private String comment;

	@Override
	public Integer getId() {
		return id;
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

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
