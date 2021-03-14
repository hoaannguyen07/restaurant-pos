package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Cart_Helper {
	/**
	 * CONSTANTS USED TO KNOW WHICH TYPE OF FOOD IT IS
	 */
	private final int REGULAR = 1;
	private final int EXTRA = 2;

	private Map<String, Item_Info> menu_map; // map database menu item key to its name & price
	private Map<String, Item_Info> ingredients_map; // map database ingredient item key to its name & price
	private Map<String, Vector<String>> cart;
	
	//variables to use for CartHelper
	private Double total_cost;
	private Double cur_customizing_item_price;
	private String cur_menu_item_key;
	private String cur_ingredient_item_key;

	Cart_Helper() {
		this.menu_map = new HashMap<String, Item_Info>();
		this.ingredients_map = new HashMap<String, Item_Info>();
		this.cart = new HashMap<String, Vector<String>>();

		this.total_cost = 0.0;
		this.cur_customizing_item_price = 0.0;

		this.cur_menu_item_key = "";
		this.cur_ingredient_item_key = "";
	}//end constructor

	/**
	 * @return the menu_map
	 */
	public Map<String, Item_Info> get_menu_map() {
		return menu_map;
	}//end get menu map

	/**
	 * @return the ingredient_map
	 */
	public Map<String, Item_Info> get_ingredients_map() {
		return ingredients_map;
	}//end get ingredients map

	/**
	 * @return the cart
	 */
	public Map<String, Vector<String>> get_cart() {
		return cart;
	}//end get cart

	/**
	 * @return the total_cost
	 */
	public Double get_total_cost() {
		return total_cost;
	}//end total cost

	/**
	 * @return the cur_customizing_item_price
	 */
	public Double get_cur_customizing_item_price() {
		return cur_customizing_item_price;
	}//end get current cuztomizing item price

	/**
	 * @return the cur_menu_item_key
	 */
	public String get_cur_menu_item_key() {
		return cur_menu_item_key;
	}//end get cur menu item key

	/**
	 * @return the cur_ingredient_item_key
	 */
	public String get_cur_ingredient_item_key() {
		return cur_ingredient_item_key;
	}//end get cur ingredient item key

	/**
	 * @param menu_map the menu_map to set
	 */
	public void set_menu_map(Map<String, Item_Info> menu_map) {
		this.menu_map = menu_map;
	}//end set menu map

	/**
	 * @param ingredient_map the ingredient_map to set
	 */
	public void set_ingredients_map(Map<String, Item_Info> ingredient_map) {
		this.ingredients_map = ingredient_map;
	}//end set ingredients map

	/**
	 * @param cart the cart to set
	 */
	public void set_cart(Map<String, Vector<String>> cart) {
		this.cart = cart;
	}//end set cart

	/**
	 * @param total_cost the total_cost to set
	 */
	public void set_total_cost(Double total_cost) {
		this.total_cost = total_cost;
	}//end set total cost

	/**
	 * @param cur_customizing_item_price the cur_customizing_item_price to set
	 */
	public void set_cur_customizing_item_price(Double cur_customizing_item_price) {
		this.cur_customizing_item_price = cur_customizing_item_price;
	}//end set cur customizing item pricce

	/**
	 * @param cur_menu_item_key the cur_menu_item_key to set
	 */
	public void set_cur_menu_item_key(String cur_menu_item_key) {
		this.cur_menu_item_key = cur_menu_item_key;
	}//end set cur menu item key

	/**
	 * @param cur_ingredient_item_key the cur_ingredient_item_key to set
	 */
	public void set_cur_ingredient_item_key(String cur_ingredient_item_key) {
		this.cur_ingredient_item_key = cur_ingredient_item_key;
	}//end set cur ingredient item key
	
	/*
	 * returns the name of the item
	 */
	public String get_menu_item_name(String menu_item_id) {
		// check for errors that might happen when getting name from map
		if (!menu_map.containsKey(menu_item_id)) {
			System.out.println("get_menu_item_name() called but an error occured");
			System.out.println("Menu item id [" + menu_item_id + "] is not in menu map");
			return "";
		}//end if
		return menu_map.get(menu_item_id).get_name();
	}//end get menu item name
	
	/*
	 * gets the price of a menu item
	 */
	public Double get_menu_item_price(String menu_item_id) {
		// check for errors that might happen when getting name from map
		if (!menu_map.containsKey(menu_item_id)) {
			System.out.println("get_menu_item_name() called but an error occured");
			System.out.println("Menu item id [" + menu_item_id + "] is not in menu map");
			return -1.0;
		}//end if
		return menu_map.get(menu_item_id).get_price();
	}//end get menu item price
	
	/*
	 * returns the name of an ingredient
	 */
	public String get_ingredient_item_name(String ingredient_item_id) {
		// check for errors that might happen when getting name from map
		if (!ingredients_map.containsKey(ingredient_item_id)) {
			System.out.println("get_ingredient_item_name() called but an error occured");
			System.out.println("Ingredient item id [" + ingredient_item_id + "] is not in ingredient map");
			return "";
		}//end if
		return ingredients_map.get(ingredient_item_id).get_name();
	}//end get ingredient item name
	
	/*
	 * gets the price of an ingredient
	 */
	public Double get_ingredient_item_price(String ingredient_item_id) {
		// check for errors that might happen when getting name from map
		if (!ingredients_map.containsKey(ingredient_item_id)) {
			System.out.println("get_ingredient_item_name() called but an error occured");
			System.out.println("Ingredient item id [" + ingredient_item_id + "] is not in ingredient map");
			return -1.0;
		}//end if
		return ingredients_map.get(ingredient_item_id).get_price();
	}//end get ingredient item price
	
	/**
	 * @param menu_list
	 */
	void update_menu_map(Vector<Vector<String>> menu_list) {
		this.menu_map = new HashMap<String, Item_Info>(); // delete whole map to start fresh
		for(int i = 0; i < menu_list.size(); i++) {
			Vector<String> cur_item_info = menu_list.elementAt(i);
			String price = cur_item_info.elementAt(2);
			if (price.length() > 4 && price.contains(".")) {
				price = price.substring(0, price.indexOf(".") + 3);
			} else if (price.chars().filter(ch -> ch == '.').count() == 0) { // no decimals in the value, then add decimals
				price += ".00";
			}//end if/else if
			
			this.menu_map.put(cur_item_info.elementAt(0), new Item_Info(cur_item_info.elementAt(1), Double.parseDouble(price)));
		}//end for
	}//end update menu map
	
	/**
	 * @param ingredients_list
	 */
	void update_ingredients_map(Vector<Vector<String>> ingredients_list) {
		this.ingredients_map = new HashMap<String, Item_Info>(); // delete whole map to start fresh
		for(int i = 0; i < ingredients_list.size(); i++) {
			Vector<String> cur_item_info = ingredients_list.elementAt(i);
			
			String price = cur_item_info.elementAt(2);
			if (price.length() > 4 && price.contains(".")) {
				price = price.substring(0, price.indexOf(".") + 3);
			} else if (price.chars().filter(ch -> ch == '.').count() == 0) { // no decimals in the value, then add decimals
				price += ".00";
			}//end if/else-if
	
			this.ingredients_map.put(cur_item_info.elementAt(0), new Item_Info(cur_item_info.elementAt(1), Double.parseDouble(price)));
		}//end for
	}//end update ingredients map
	
	/**
	 * have not finalized the current order so no money has been added to total_cost
	 * used in ingredient menu when customer clicks "back" instead of save changes
	 */
	void delete_cur_menu_item_from_cart() {
		if (!cart.containsKey(cur_menu_item_key)) {
			System.out.println("delete_cur_menu_item_from_cart() called but an error occured");
			System.out.println("Menu item id [" + cur_menu_item_key + "] is not in cart");
			return;
		}//end if
		
		cart.remove(cur_menu_item_key);
		
		// check if key is still in map and throw error accordingly
		if (cart.containsKey(cur_menu_item_key)) {
			System.out.println("ERROR: Was NOT able to delete item " + cur_menu_item_key + " from the table");
		} else {
			System.out.println("Selected menu item key [" + cur_menu_item_key + "] (" 
					+ menu_map.get(cur_menu_item_key).get_name() + ") has been removed from the cart");
			System.out.println("New Cart:\n" + cart.toString());
		}//end if/else-if
	}//end delete cur menu item from cart
	
	/**
	 * reset cur_menu_item_key, cur_ingredient_item_key, and cur_customizing_item_price to get ready
	 * for customer's next selection of food item
	 */
	void prep_for_next_menu_item() {
		set_cur_menu_item_key("");
		set_cur_ingredient_item_key("");
		set_cur_customizing_item_price(0.0);
	}//end prep for next menu item
	
	/**
	 * @param menu_item_id
	 * delete an item from the cart's menu page
	 */
	void delete_menu_item_from_cart(String menu_item_id) {
		System.out.println("");
		System.out.println("Attempting to delete [" + menu_item_id + "] from the cart using delete_menu_item_from_cart()");
		// error checks to make sure that parameter is valid to not have the system throw errors down the line
		if (!menu_map.containsKey(menu_item_id)) {
			System.out.println("Cannot delete menu item id [" + menu_item_id + "] from cart because it does not exist in the Menu Map");
			return;
		} //end if
		if (!cart.containsKey(menu_item_id)) {
			System.out.println("Menu item id [" + menu_item_id + "] is not in cart");
			return;
		}//end if
		
		// find the total cost of the item order (item price + ingredients price)
		Double deducting_price = 0.0;
		
		deducting_price += menu_map.get(menu_item_id).get_price();
		Vector<String> item_ingredients = cart.get(menu_item_id);
		for(int i = 0; i < item_ingredients.size(); i++) {
			String cur_ingredient = "";
			// 'X' stands for extra, and is not part of ingredient key
			Character cur_char = item_ingredients.elementAt(i).charAt(0);
			if (cur_char.equals('X')) {
				cur_ingredient = item_ingredients.elementAt(i).substring(1, item_ingredients.elementAt(i).length()); 
					// take out the first character leaves with the id
			} else {
				cur_ingredient = item_ingredients.elementAt(i);
			}//end if/else
			
			if (!ingredients_map.containsKey(cur_ingredient)) {
				System.out.println("Cannot delete ingredient item id [" + cur_ingredient + "] from cart because it does not exist in the Ingredients Map");
				return;
			} else {
				deducting_price += ingredients_map.get(cur_ingredient).get_price();
			}//end if/else
		}//end for
		
		cart.remove(menu_item_id);
		if (!cart.containsKey(menu_item_id)) {
			System.out.println("Menu item id [" + menu_item_id + "] if successfully deleted from cart");
		}//end if
		
		System.out.println("Total cost was $" + total_cost);
		total_cost -= deducting_price;
		System.out.println("Total cost is now $" + total_cost);
	}//end delete menu item from cart
	
	/**
	 * @param option
	 * add the current ingredient to an item in the cart
	 */
	void add_cur_ingredient(int option) {
		// error checks to make sure that parameter is valid to not have the system throw errors down the line
		if (!menu_map.containsKey(cur_menu_item_key)) {
			System.out.println("Cannot find menu item id [" + cur_menu_item_key + "] in Menu Map");
			return;
		}//end if
		if (!ingredients_map.containsKey(cur_ingredient_item_key)) {
			System.out.println("Cannot find ingredient item id [" + cur_ingredient_item_key + "] in Ingredients Map");
			return;
		}// end if
		if (!cart.containsKey(cur_menu_item_key)) {
			System.out.println("Menu item id [" + cur_menu_item_key + "] is not in cart");
			return;
		}// end if
		
		// add new ingredients to ingredients list of each menu item and update the price
		if (option == REGULAR) {
			Vector<String> cur_item_ingredients = cart.get(cur_menu_item_key);
			cur_item_ingredients.addElement(cur_ingredient_item_key);
			cart.put(cur_menu_item_key, cur_item_ingredients);
			
			System.out.println("Added ingredient [" + cur_ingredient_item_key + "] as customization to menu item [" + cur_menu_item_key + "] in cart");
			
			System.out.println("The previous menu item price is $" + cur_customizing_item_price);
			cur_customizing_item_price += ingredients_map.get(cur_ingredient_item_key).get_price();
			System.out.println("The current menu item price is $" + cur_customizing_item_price + " after the addition");	
		} else if (option == EXTRA) {
			Vector<String> cur_item_ingredients = cart.get(cur_menu_item_key);
			cur_item_ingredients.addElement("X" + cur_ingredient_item_key);
			cart.put(cur_menu_item_key, cur_item_ingredients);
			
			System.out.println("Added ingredient [X" + cur_ingredient_item_key + "] as customization to menu item [" + cur_menu_item_key + "] in cart");
			
			System.out.println("The previous menu item price is $" + cur_customizing_item_price);
			cur_customizing_item_price += ingredients_map.get(cur_ingredient_item_key).get_price();
			System.out.println("The current menu item price is $" + cur_customizing_item_price + " after the addition");
		}//end if/else-if
	}//end add_cur_ingredient 
	
	/*
	 * adds the current menu item to the cart
	 */
	void add_cur_menu_item() {
		// error checks to make sure that parameter is valid to not have the system throw errors down the line
		if (!menu_map.containsKey(cur_menu_item_key)) {
			System.out.println("Cannot find menu item id [" + cur_menu_item_key + "] in Menu Map");
			return;
		}//end if
		
		// because adding menu item is a precursor to customizing that ingredient, make sure cur_customizing_item_price is reset to 0
		if (Math.abs(cur_customizing_item_price - 0.0) >= 0.000001) {
			System.out.println("Menu Item Key [" + cur_menu_item_key + "] has been been added to the card due to error in cur_customizing_item_price. cur_customizing_item_price is $" 
					+ cur_customizing_item_price + ". Please remember to reset it when finishing with the last item");
			return;
		}//end if
		
		cart.put(cur_menu_item_key, new Vector<String> ());
		System.out.println("Current Cart outputted in add_cur_menu_item in CartHelper:\n" + cart);
		System.out.println("Menu Item key [" + cur_menu_item_key + "] (" + menu_map.get(cur_menu_item_key).get_name() + ") has been added to the cart");
		
		cur_customizing_item_price += menu_map.get(cur_menu_item_key).get_price();
		System.out.println("The current item is priced at $" + cur_customizing_item_price);
	}//end add cur menu item
	
	/*
	 * adds the cost of an item to the total cost of the items in the cart so far
	 */
	void add_item_cost_to_tot_cost(){
		total_cost += cur_customizing_item_price;
	}//end add item cost to total cost
	
	/*
	 * adds the menu item from the rewards page to the cart.
	 */
	void add_menu_item_as_free(String menu_item_id)	{
		// error checks to make sure that parameter is valid to not have the system throw errors down the line
		if (!menu_map.containsKey(cur_menu_item_key)) {
			System.out.println("Cannot find menu item id [" + cur_menu_item_key + "] in Menu Map");
			return;
		}//end if
		
		// because adding menu item is a precursor to customizing that ingredient, make sure cur_customizing_item_price is reset to 0
		if (Math.abs(cur_customizing_item_price - 0.0) >= 0.000001) {
			System.out.println("Menu Item Key [" + cur_menu_item_key + "] has been been added to the card due to error in cur_customizing_item_price. cur_customizing_item_price is $" 
					+ cur_customizing_item_price + ". Please remember to reset it when finishing with the last item");
			return;
		}//end if
		
		cart.put(cur_menu_item_key, new Vector<String> ());
		System.out.println("Current Cart outputted in add_cur_menu_item in CartHelper:\n" + cart);
		System.out.println("Menu Item key [" + cur_menu_item_key + "] (" + menu_map.get(cur_menu_item_key).get_name() + ") has been added to the cart");
	}//end add menu item as free
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Vector<String>> hi = new HashMap<String, Vector<String>>();
		System.out.println(hi);
		
		hi.put("sucks", new Vector<String>());
		System.out.println(hi);
		
		String s = "XE3";
		System.out.println("s: " + s);
		s = s.substring(1, s.length());
		System.out.println("s: " + s);
		
		String t = s;
		t = "123";
		System.out.println("s: " + s);
		System.out.println("t: " + t);
	}//end main

	@Override
	public String toString() {
		return "CartHelper \n[menu_map=" + menu_map + ", \ningredients_map=" + ingredients_map + ", \ncart=" + cart
				+ ", \ntotal_cost=" + total_cost + ", \ncur_customizing_item_price=" + cur_customizing_item_price
				+ ", \ncur_menu_item_key=" + cur_menu_item_key + ", \ncur_ingredient_item_key=" + cur_ingredient_item_key
				+ "\n]";
	}//end toString
}//end class