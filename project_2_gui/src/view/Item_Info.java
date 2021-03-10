package view;

/**
 * 
 * @author hoaan
 * used to keep name and price of item
 */
public class Item_Info {
	private String name;
	private String price;
	
	Item_Info()
	{
		this.name = "";
		this.price = "";
	}
	
	Item_Info(String item_name, String item_price)
	{
		this.name = item_name;
		this.price = item_price;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item_Info [name = " + name + ", price = " + price + "]";
	}
	
	
	
}
