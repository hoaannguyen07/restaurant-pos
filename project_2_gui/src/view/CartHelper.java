package view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class CartHelper {
	/**
	 * CONSTANTS USED TO KNOW WHICH TYPE OF FOOD IT IS
	 */
	private final int DONT_ADD = 0;
	private final int REGULAR = 1;
	private final int EXTRA = 2;

	private Map<String, Item_Info> menu_map; // map database menu item key to its name & price
	private Map<String, Item_Info> ingredients_map; // map database ingredient item key to its name & price
	private Map<String, Vector<String>> cart;
	private Double total_cost;
	private Double cur_customizing_item_price;
	private String cur_menu_item_key;
	private String cur_ingredient_item_key;

	CartHelper() {
		this.menu_map = new HashMap<String, Item_Info>();
		this.ingredients_map = new HashMap<String, Item_Info>();
		this.cart = new HashMap<String, Vector<String>>();

		this.total_cost = 0.0;
		this.cur_customizing_item_price = 0.0;

		this.cur_menu_item_key = "";
		this.cur_ingredient_item_key = "";
	}

	/**
	 * @return the menu_map
	 */
	public Map<String, Item_Info> getMenu_map() {
		return menu_map;
	}

	/**
	 * @return the ingredient_map
	 */
	public Map<String, Item_Info> getIngredients_map() {
		return ingredients_map;
	}

	/**
	 * @return the cart
	 */
	public Map<String, Vector<String>> getCart() {
		return cart;
	}

	/**
	 * @return the total_cost
	 */
	public Double getTotal_cost() {
		return total_cost;
	}

	/**
	 * @return the cur_customizing_item_price
	 */
	public Double getCur_customizing_item_price() {
		return cur_customizing_item_price;
	}

	/**
	 * @return the cur_menu_item_key
	 */
	public String getCur_menu_item_key() {
		return cur_menu_item_key;
	}

	/**
	 * @return the cur_ingredient_item_key
	 */
	public String getCur_ingredient_item_key() {
		return cur_ingredient_item_key;
	}

	/**
	 * @param menu_map the menu_map to set
	 */
	public void setMenu_map(Map<String, Item_Info> menu_map) {
		this.menu_map = menu_map;
	}

	/**
	 * @param ingredient_map the ingredient_map to set
	 */
	public void setIngredients_map(Map<String, Item_Info> ingredient_map) {
		this.ingredients_map = ingredient_map;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(Map<String, Vector<String>> cart) {
		this.cart = cart;
	}

	/**
	 * @param total_cost the total_cost to set
	 */
	public void setTotal_cost(Double total_cost) {
		this.total_cost = total_cost;
	}

	/**
	 * @param cur_customizing_item_price the cur_customizing_item_price to set
	 */
	public void setCur_customizing_item_price(Double cur_customizing_item_price) {
		this.cur_customizing_item_price = cur_customizing_item_price;
	}

	/**
	 * @param cur_menu_item_key the cur_menu_item_key to set
	 */
	public void setCur_menu_item_key(String cur_menu_item_key) {
		this.cur_menu_item_key = cur_menu_item_key;
	}

	/**
	 * @param cur_ingredient_item_key the cur_ingredient_item_key to set
	 */
	public void setCur_ingredient_item_key(String cur_ingredient_item_key) {
		this.cur_ingredient_item_key = cur_ingredient_item_key;
	}

	void update_menu_map(Vector<Vector<String>> menu_list)
	{
		this.menu_map = new HashMap<String, Item_Info>(); // delete whole map to start fresh
		for(int i = 0; i < menu_list.size(); i++)
		{
			Vector<String> cur_item_info = menu_list.elementAt(i);
			this.menu_map.put(cur_item_info.elementAt(0), new Item_Info(cur_item_info.elementAt(2), cur_item_info.elementAt(3)));
		}
	}
	
	void update_ingredients_map(Vector<Vector<String>> ingredients_list)
	{
		this.ingredients_map = new HashMap<String, Item_Info>(); // delete whole map to start fresh
		for(int i = 0; i < ingredients_list.size(); i++)
		{
			Vector<String> cur_item_info = ingredients_list.elementAt(i);
			this.ingredients_map.put(cur_item_info.elementAt(0), new Item_Info(cur_item_info.elementAt(2), cur_item_info.elementAt(3)));
		}
	}
	
	/**
	 * have not finalized the current order so no money has been added to total_cost
	 * used in ingredient menu when customer clicks "back" instead of save changes
	 */
	void delete_cur_menu_item_from_cart()
	{
		cart.remove(cur_menu_item_key);
		
		// check if key is still in map and throw error accordingly
		if (cart.containsKey(cur_menu_item_key))
		{
			System.out.println("ERROR: Was NOT able to delete item " + cur_menu_item_key + " from the table");
		}
		else
		{
			// goes back to menu screen to select items again so reset cur_menu_item_key, cur_ingredients_item_key, and cur_customizing_item_price
			setCur_menu_item_key("");
			setCur_ingredient_item_key("");
			setCur_customizing_item_price(0.0);
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
