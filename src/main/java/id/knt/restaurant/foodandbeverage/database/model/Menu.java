package id.knt.restaurant.foodandbeverage.database.model;

import java.math.BigDecimal;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * table: menu field: id, name, price, picture, description, active, category_1,
 * category_2, category_3
 * 
 * @author cindy.situmorang
 * 
 */
@DatabaseTable(tableName = "menu")
public class Menu implements KeyModel<Integer> {

	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
	private Integer id;
	@DatabaseField(columnName = "name", canBeNull = false)
	private String name;
	@DatabaseField(columnName = "price", canBeNull = false)
	private BigDecimal price;
	@DatabaseField(columnName = "picture", canBeNull = false)
	private String picture;
	@DatabaseField(columnName = "description", canBeNull = true)
	private String description;
	@DatabaseField(columnName = "new_product", canBeNull = false, defaultValue = "false")
	protected Boolean newProduct;
	@DatabaseField(columnName = "sold_out", canBeNull = false, defaultValue = "false")
	protected Boolean soldOut;
	@DatabaseField(columnName = "active", canBeNull = false, defaultValue = "true")
	protected Boolean active;
	@DatabaseField(columnName = "category_1", foreign = true, canBeNull = false)
	private Category category1;
	@DatabaseField(columnName = "category_2", foreign = true, canBeNull = true)
	private Category category2;
	@DatabaseField(columnName = "category_3", foreign = true, canBeNull = true)
	private Category category3;

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(Boolean newProduct) {
		this.newProduct = newProduct;
	}

	public Boolean getSoldOut() {
		return soldOut;
	}

	public void setSoldOut(Boolean soldOut) {
		this.soldOut = soldOut;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Category getCategory1() {
		return category1;
	}

	public void setCategory1(Category category1) {
		this.category1 = category1;
	}

	public Category getCategory2() {
		return category2;
	}

	public void setCategory2(Category category2) {
		this.category2 = category2;
	}

	public Category getCategory3() {
		return category3;
	}

	public void setCategory3(Category category3) {
		this.category3 = category3;
	}
}
