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
	
	/**
	 * 
	 * @param menu_list
	 * STILL HAVE TO TEST
	 */
	void update_menu_map(Vector<Vector<String>> menu_list)
	{
		this.menu_map = new HashMap<String, Item_Info>(); // delete whole map to start fresh
		for(int i = 0; i < menu_list.size(); i++)
		{
			Vector<String> cur_item_info = menu_list.elementAt(i);
			this.menu_map.put(cur_item_info.elementAt(0), new Item_Info(cur_item_info.elementAt(1), Double.parseDouble(cur_item_info.elementAt(2))));
		}
	}
	
	/**
	 * 
	 * @param ingredients_list
	 * STILL HAVE TO TEST
	 */
	void update_ingredients_map(Vector<Vector<String>> ingredients_list)
	{
		this.ingredients_map = new HashMap<String, Item_Info>(); // delete whole map to start fresh
		for(int i = 0; i < ingredients_list.size(); i++)
		{
			Vector<String> cur_item_info = ingredients_list.elementAt(i);
			this.ingredients_map.put(cur_item_info.elementAt(0), new Item_Info(cur_item_info.elementAt(1), Double.parseDouble(cur_item_info.elementAt(2))));
		}
	}
	
	/**
	 * have not finalized the current order so no money has been added to total_cost
	 * used in ingredient menu when customer clicks "back" instead of save changes
	 * 
	 * STILL HAVE TO TEST
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
			System.out.println("Selected menu item key [" + cur_menu_item_key + "] (" + menu_map.get(cur_menu_item_key).getName() + ") has been removed from the cart");
			System.out.println("New Cart:\n" + cart.toString());
		}
	}
	
	/**
	 * reset cur_menu_item_key, cur_ingredient_item_key, and cur_customizing_item_price to get ready
	 * for customer's next selection of food item
	 * STILL HAVE TO TEST
	 */
	void prep_for_next_menu_item()
	{
		setCur_menu_item_key("");
		setCur_ingredient_item_key("");
		setCur_customizing_item_price(0.0);
	}
	
	/**
	 * 
	 * @param menu_item_id
	 * STILL HAVE TO TEST
	 */
	void delete_menu_item_from_cart(String menu_item_id)
	{
		// error checks to make sure that parameter is valid to not have the system throw errors down the line
		if (!menu_map.containsKey(menu_item_id))
		{
			System.out.println("Cannot delete menu item id [" + menu_item_id + "] from cart because it does not exist in the Menu Map");
			return;
		}
		if (!cart.containsKey(menu_item_id))
		{
			System.out.println("Menu item id [" + menu_item_id + "] is not in cart");
			return;
		}
		
		// find the total cost of the item order (item price + ingredients price)
		double deducting_price = 0.0;
		
		deducting_price += menu_map.get(menu_item_id).getPrice();
		Vector<String> item_ingredients = cart.get(menu_item_id);
		for(int i = 0; i < item_ingredients.size(); i++)
		{
			String cur_ingredient = "";
			// 'X' stands for extra, and is not part of ingredient key
			for(int j = 0; j < item_ingredients.elementAt(i).length(); j++)
			{
				Character cur_char = item_ingredients.elementAt(i).charAt(j);
				if (!cur_char.equals('X')) 
				{
					cur_ingredient += cur_char;
				}
			}
			
			if (!ingredients_map.containsKey(cur_ingredient))
			{
				System.out.println("Cannot delete ingredient item id [" + cur_ingredient + "] from cart because it does not exist in the Ingredients Map");
				return;
			}
			else
			{
				deducting_price += ingredients_map.get(cur_ingredient).getPrice();
			}
		}
		
		cart.remove(menu_item_id);
		if (!cart.containsKey(menu_item_id))
		{
			System.out.println("Menu item id [" + menu_item_id + "] if successfully deleted from cart");
		}
		
		System.out.println("Total cost was $" + total_cost);
		total_cost -= deducting_price;
		System.out.println("Total cost is now $" + total_cost);
	}
	
	/**
	 * 
	 * @param option
	 * STILL NEED TO TEST
	 */
	void add_cur_ingredient(int option)
	{
		// error checks to make sure that parameter is valid to not have the system throw errors down the line
		if (!menu_map.containsKey(cur_menu_item_key))
		{
			System.out.println("Cannot find menu item id [" + cur_menu_item_key + "] in Menu Map");
			return;
		}
		if (!ingredients_map.containsKey(cur_ingredient_item_key))
		{
			System.out.println("Cannot find ingredient item id [" + cur_ingredient_item_key + "] in Ingredients Map");
			return;
		}
		if (!cart.containsKey(cur_menu_item_key))
		{
			System.out.println("Menu item id [" + cur_menu_item_key + "] is not in cart");
			return;
		}
		
		// add new ingredients to ingredients list of each menu item and update the price
		if (option == REGULAR)
		{
			Vector<String> cur_item_ingredients = cart.get(cur_menu_item_key);
			cur_item_ingredients.addElement(cur_ingredient_item_key);
			cart.put(cur_menu_item_key, cur_item_ingredients);
			
			System.out.println("Added ingredient [" + cur_ingredient_item_key + "] as customization to menu item [" + cur_menu_item_key + "] in cart");
			
			System.out.println("The previous menu item price is $" + cur_customizing_item_price);
			cur_customizing_item_price += ingredients_map.get(cur_ingredient_item_key).getPrice();
			System.out.println("The current menu item price is $" + cur_customizing_item_price + " after the addition");
			
		} else if (option == EXTRA)
		{
			Vector<String> cur_item_ingredients = cart.get(cur_menu_item_key);
			cur_item_ingredients.addElement("X" + cur_ingredient_item_key);
			cart.put(cur_menu_item_key, cur_item_ingredients);
			
			System.out.println("Added ingredient [X" + cur_ingredient_item_key + "] as customization to menu item [" + cur_menu_item_key + "] in cart");
			
			System.out.println("The previous menu item price is $" + cur_customizing_item_price);
			cur_customizing_item_price += ingredients_map.get(cur_ingredient_item_key).getPrice();
			System.out.println("The current menu item price is $" + cur_customizing_item_price + " after the addition");
		}
	}
	
	void add_cur_menu_item()
	{
		// error checks to make sure that parameter is valid to not have the system throw errors down the line
		if (!menu_map.containsKey(cur_menu_item_key))
		{
			System.out.println("Cannot find menu item id [" + cur_menu_item_key + "] in Menu Map");
			return;
		}
		
		// because adding menu item is a precursor to customizing that ingredient, make sure cur_customizing_item_price is reset to 0
		if (Math.abs(cur_customizing_item_price - 0.0) >= 0.000001)
		{
			System.out.println("Menu Item Key [" + cur_menu_item_key + "] has been been added to the card due to error in cur_customizing_item_price. cur_customizing_item_price is $" 
					+ cur_customizing_item_price + ". Please remember to reset it when finishing with the last item");
			return;
		}
		
		cart.put(cur_menu_item_key, new Vector<String> ());
		System.out.println("Current Cart outputted in add_cur_menu_item in CartHelper:\n" + cart);
		System.out.println("Menu Item key [" + cur_menu_item_key + "] (" + menu_map.get(cur_menu_item_key).getName() + ") has been added to the cart");
		
		cur_customizing_item_price += menu_map.get(cur_menu_item_key).getPrice();
		System.out.println("The current item is priced at $" + cur_customizing_item_price);
	}
	
	void add_item_cost_to_tot_cost()
	{
		total_cost += cur_customizing_item_price;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Vector<String>> hi = new HashMap<String, Vector<String>>();
		System.out.println(hi);
		
		hi.put("sucks", new Vector<String>());
		System.out.println(hi);
	}

	@Override
	public String toString() {
		return "CartHelper \n[menu_map=" + menu_map + ", \ningredients_map=" + ingredients_map + ", \ncart=" + cart
				+ ", \ntotal_cost=" + total_cost + ", \ncur_customizing_item_price=" + cur_customizing_item_price
				+ ", \ncur_menu_item_key=" + cur_menu_item_key + ", \ncur_ingredient_item_key=" + cur_ingredient_item_key
				+ "\n]";
	}



}
