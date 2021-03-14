package view;

/**
 * 
 * @author hoaan
 * used to keep name and price of item
 */
public class Item_Info {
	private String name;
	private Double price;
	
	Item_Info() {
		this.name = "";
		this.price = 0.0;
	}//end empty constructor
	
	Item_Info(String item_name, Double item_price) {
		this.name = item_name;
		this.price = item_price;
	}//end constructor

	/**
	 * @return the name
	 */
	public String get_name() {
		return name;
	}//end get name

	/**
	 * @return the price
	 */
	public Double get_price() {
		return price;
	}//end get price

	/**
	 * @param name the name to set
	 */
	public void set_name(String name) {
		this.name = name;
	}//end set name

	/**
	 * @param price the price to set
	 */
	public void set_price(Double item_price) {
		this.price = item_price;
	}//end set price

	@Override
	public String toString() {
		return "Item_Info [name = " + name + ", price = " + price + "]";
	}//end toString	
}//end class