package view;

import java.math.RoundingMode;
import java.sql.*; // to do all SQL commands
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;

public class DataHelper {
	public Connection conn;
	public String last_name;
	public String first_name;
	public String id;
	public String password;
	Vector<Vector<String>> menu_list; // [0] = id || [1] = name || [2] = price || [3] = availability
	Vector<Vector<String>> ingredients_list; // [0] = key || [1] = name || [2] = price
	Cart_Helper cart_helper;
	
	DataHelper()
	{
		//Building the connection
	     try {
	        //Class.forName("org.postgresql.Driver");
	        conn = DriverManager.getConnection(
	          "jdbc:postgresql://csce-315-db.engr.tamu.edu/db907_group9_project2",
	           dbSetup.user, dbSetup.pswd);
	     } catch (Exception e) {
	        e.printStackTrace();
	        System.err.println(e.getClass().getName() + ": " + e.getMessage());
	        System.exit(0);
	     }//end try catch
	     System.out.println("Opened database successfully");
	     
	     last_name = "";
	     first_name = "";
	     id = "";
	     password = "";
	     menu_list = new Vector<Vector<String>>();
	     ingredients_list = new Vector<Vector<String>>();
	     cart_helper = new Cart_Helper();
	}
	
	DataHelper (Connection conn)
	{
		this.conn = conn;
		System.out.println("Transfer of Connection Successfully");
		
		last_name = "";
		first_name = "";
		id = "";
		password = "";
		menu_list = new Vector<Vector<String>>();
		ingredients_list = new Vector<Vector<String>>();
		cart_helper = new Cart_Helper();
	}
	
	
	
	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the cart_helper
	 */
	public Cart_Helper getCart_helper() {
		return cart_helper;
	}

	// add new person to the system
	@SuppressWarnings("deprecation")
	int add_new_person(JTextField l_name, JTextField f_name, JTextField cust_id, JPasswordField password)
	{
		// create & execute a sql statement (first object then the statement that will be put into that object)
		// sql stmt: INSERT INTO customer(lastname, firstname, id, password) VALUES ("last_name", "first_name", "cust_id", "cust_password");
		// done in try/catch statement in case database cannot be accessed and errors present itself
		try
		{
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String sql_stmt = "INSERT INTO customer(lastname, firstname, id, password) VALUES (\"" + l_name.getText() + "\", \""
								+ f_name.getText() + "\", \"" + cust_id.getText() + "\", \"" + password.getText() + "\")";
			
			System.out.println("Executing Statement: " + sql_stmt);
			
			stmt.executeQuery(sql_stmt);
			System.out.println("Added " + f_name.getText() + " " + f_name.getText() + " to the customer data table successfully!");
		} catch (Exception e)
		{
			System.out.println(e);
			System.out.println("Error adding to customer Datatable.");
			return 0; // tells GUI that it was unsuccessful in putting customer in database
		}
		
		// update information of person using the system
		this.last_name = l_name.getText();
		this.first_name = f_name.getText();
		this.id = cust_id.getText();
		this.password = password.getText();
		return 1; // query was successful
	}
	
	// get the 5 most recent orders from a person (must have signed in or signed up already so already have customer information already
	void retrieve_recent_orders()
	{
		/* 
		 * create & execute a sql statement (first object then the statement that will be put into that object)
		 * sql stmt:
		 * SELECT customer.lastname, customer.firstname, o.customerid, o.date, o.totalcost, o.n_items,
		 * m1.name AS "entree", m2.name AS "side", m3.name AS "beverage", m4.name AS "dessert"
		 * FROM orders as o
		 * FROM OUTER JOIN customer ON o.customerid = customer.id
		 * LEFT JOIN menu m1 ON o.entrees = m1.id
		 * LEFT JOIN menu m2 ON o.sides = m2.id
		 * LEFT JOIN menu m3 ON o.beverages = m3.id
		 * LEFT JOIN menu m4 ON o.desserts = m4.id
		 * WHERE customer.lastname LIKE 'last_name%'
		 * AND customer.firstname LIKE 'first_name%'
		 * ORDER BY o.date DESC LIMIT 5;
		 * 
		 * done in try/catch statement in case database cannot be accessed and errors present itself
		 */
		
		try
		{
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String sql_stmt = 
			 "SELECT customer.lastname, customer.firstname, o.customerid, o.date, o.totalcost, o.n_items, " +
			 "m1.name AS \"entree\", m2.name AS \"side\", m3.name AS \"beverage\", m4.name AS \"dessert\" " +
			 "FROM orders as o " +
			 "FROM OUTER JOIN customer ON o.customerid = customer.id " +
			 "LEFT JOIN menu m1 ON o.entrees = m1.id " +
			 "LEFT JOIN menu m2 ON o.sides = m2.id " +
			 "LEFT JOIN menu m3 ON o.beverages = m3.id " +
			 "LEFT JOIN menu m4 ON o.desserts = m4.id " +
			 "WHERE customer.lastname LIKE '" + this.last_name + "%' " +
			 "AND customer.firstname LIKE '" + this.first_name + "%' " +
			 "ORDER BY o.date DESC LIMIT 5";
					
			
			System.out.println("Executing Statement: " + sql_stmt);
			
			stmt.executeQuery(sql_stmt);
			
			System.out.println("Successfully retrieved " + this.first_name + " " + this.last_name + "'s 5 most recent orders!");
		} catch (Exception e)
		{
			System.out.println(e);
			System.out.println("Error querying from orders data table.");
			// tells GUI that it was unsuccessful in putting customer in database
		}
		 
	}
	
	@SuppressWarnings("deprecation")
//	 account verification for manager sign in
	int verify_manager(JTextField username, JPasswordField password)
	{
		/* 
		 * create & execute a sql statement (first object then the statement that will be put into that object)
		 * sql stmt:
		 * SELECT * FROM manager WHERE manager.username LIKE 'username' AND manager.password LIKE 'password';
		 * done in try/catch statement in case database cannot be accessed and errors present itself
		 * 
		 * return values:
		 * 0 - error
		 * 1 - incorrect username or password
		 * 2 - successful sign in
		 */
		
		try
		{
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			
			String sql_stmt = "SELECT * FROM manager WHERE manager.username LIKE '" + username.getText() + "' AND manager.password LIKE '" + password.getText() + "'";
			System.out.println("Executing Statement: " + sql_stmt);
			
			ResultSet result = stmt.executeQuery(sql_stmt);
			
			String user_first_name = "";
			String user_last_name = "";
			while(result.next())
			{
				user_first_name = result.getString("firstname");
				user_last_name = result.getString("lastname"); 
			}
			System.out.println("Finished Executing Statement: " + sql_stmt);
			// if there is nothing in the string, then there are no username and password that matches.
			if (user_first_name.equals("") && user_last_name.equals(""))
			{
				return 1;
			} else // username & password match so we have a customer
			{
				// update name and id of person (using SQL queries)
							
				this.first_name = user_first_name;
				this.last_name = user_last_name;
				this.id = username.getText();
				this.password = password.getText();
//				this.password = String.valueOf(password.getPassword());
				System.out.println("Manager " + this.first_name + " " + this.last_name + " has logged into the system!");
				return 2;
			}

		} catch (Exception e)
		{
			System.out.println(e);
			System.out.println("Error finding manager in manager data table.");
			return 0; // tells GUI that it was unsuccessful in putting customer in database
		}
	}
	
	@SuppressWarnings("deprecation")
	// account verification for customer
	int verify_customer(JTextField username, JPasswordField password)
	{
		/* 
		 * create & execute a sql statement (first object then the statement that will be put into that object)
		 * sql stmt:
		 * SELECT * FROM customer WHERE customer.id LIKE 'id' AND customer.password LIKE 'password';
		 * done in try/catch statement in case database cannot be accessed and errors present itself
		 * 
		 * return values:
		 * 0 - error
		 * 1 - incorrect username or password
		 * 2 - successful sign in
		 */
		
		try
		{
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			
			String sql_stmt = "SELECT * FROM customer WHERE customer.id LIKE '" + username.getText() + "' AND customer.password LIKE '" + password.getText() + "'";
			System.out.println("Executing Statement: " + sql_stmt);
			
			ResultSet result = stmt.executeQuery(sql_stmt);
			
			String user_first_name = "";
			String user_last_name = "";
			while(result.next())
			{
				user_first_name = result.getString("firstname");
				user_last_name = result.getString("lastname"); 
			}
			System.out.println("Finished Executing Statement: " + sql_stmt);
			// if there is nothing in the string, then there are no username and password that matches.
			if (user_first_name.equals("") && user_last_name.equals(""))
			{
				return 1;
			} else // username & password match so we have a customer
			{
				// update name and id of person (using SQL queries)
							
				this.first_name = user_first_name;
				this.last_name = user_last_name;
				this.id = username.getText();
				this.password = password.getText();
//				this.password = String.valueOf(password.getPassword());
				System.out.println("Customer " + this.first_name + " " + this.last_name + " has logged into the system!");
				return 2;
			}

		} catch (Exception e)
		{
			System.out.println(e);
			System.out.println("Error finding customer in customer data table.");
			return 0; // tells GUI that it was unsuccessful in putting customer in database
		}
	}

	// find number of visits % 25 (b/c 25 is our max 
	int get_num_visits()
	{
		/* 
		 * create & execute a sql statement (first object then the statement that will be put into that object)
		 * sql stmt:
		 * SELECT "Rewards".visit_num FROM "Rewards" WHERE "Rewards".customerid='id';
		 * done in try/catch statement in case database cannot be accessed and errors present itself
		 * 
		 * 
		 */
		int num_visits = 0;
		
		try
		{
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String sql_stmt = "SELECT \"Rewards\".visit_num FROM \"Rewards\" WHERE \"Rewards\".customerid='" + this.id + "'";
			
			System.out.println("Executing Statement: " + sql_stmt);
			
			ResultSet result = stmt.executeQuery(sql_stmt);
			
			if (result.next())
			{
				num_visits = result.getInt("visit_num");
			}
			
			return num_visits;
		} catch (Exception e)
		{
			System.out.println(e);
			System.out.println("Error adding to manager Datatable.");
			return 0; // tells GUI that it was unsuccessful in putting customer in database
		}
	}

	Boolean update_num_visits()
	{
		/* 
		 * create & execute a sql statement (first object then the statement that will be put into that object)
		 * sql stmt:
		 * UPDATE "Rewards" SET visit_num = visit_num + 1 WHERE "Rewards".customerid LIKE 'id';
		 * done in try/catch statement in case database cannot be accessed and errors present itself
		 * 
		 * 
		 */
		
		try
		{
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String check_visits_sql_stmt = "SELECT \"Rewards\".visit_num FROM \"Rewards\" WHERE \"Rewards\".customerid LIKE '" + this.id + "%'";
			String update_visits_sql_stmt = "UPDATE \"Rewards\" SET visit_num = visit_num + 1 WHERE \"Rewards\".customerid LIKE '" + this.id + "'";
			
			System.out.println("Executing Statement: " + check_visits_sql_stmt);
			System.out.println("Executing Statement: " + update_visits_sql_stmt);
			System.out.println("Executing Statement: " + check_visits_sql_stmt);
			
			// make sure that the database updates by check visit_num before & after updates
			ResultSet num_visit_results_before = stmt.executeQuery(check_visits_sql_stmt);
			stmt.executeQuery(update_visits_sql_stmt);
			ResultSet num_visit_results_after = stmt.executeQuery(check_visits_sql_stmt);
			
			int visits_before = Integer.parseInt(num_visit_results_before.getString("visit_num"));
			int visits_after = Integer.parseInt(num_visit_results_after.getString("visit_num"));
			
			if (visits_after - visits_before != 1)
			{
				System.out.println("Error updating number of visits for " + this.first_name + " " + this.last_name);
				return false;
			} 
			System.out.println("Successfully updated number of visits for " + this.first_name + " " + this.last_name);
			
		} catch (Exception e)
		{
			System.out.println(e);
			System.out.println("Error updating number of visits for a customer in Rewards data table.");
			return false; // tells GUI that it was unsuccessful in putting customer in database
		}
		
		return true;
	}
	
	Vector<Vector<String>> get_menu_data()
	{
		// every time front end asks for menu, need to query menu from database and use it to update
		// the menu map in cart_helper to keep everything in synced, menu-wise
		query_menu();
		System.out.println("Finished Menu Query");
		cart_helper.update_menu_map(menu_list);
		System.out.println("Finished Updating Menu Map in CartHelper");
		return menu_list;
	}
	
	void query_menu()
	{
		Vector<Vector<String>> menu = new Vector<Vector<String>>();

		try
		{
			Statement stmt = this.conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String sql_stmt = "SELECT * from menu";
			
			System.out.println("Executing Statement: " + sql_stmt);
			ResultSet result = stmt.executeQuery(sql_stmt);
			
			while(result.next())
			{
				DecimalFormat df2 = new DecimalFormat("#.###");
				df2.setRoundingMode(RoundingMode.UP);
				
				// create vector to record information on one row to then be added to menu_list [0] = id || [1] = name || [2] = price || [3] = availability
				Vector<String> cur_item = new Vector<String>(); 
				// get name and price of food item
				String food_id = result.getString("id");
				String food_name = result.getString("name");
				String food_price = result.getString("price"); 
				String availability = result.getString("available");
				
				if (food_price.length() > 4 && food_price.contains(".")) {
					food_price = food_price.substring(0, food_price.indexOf(".") + 3);
				}
				else if (food_price.chars().filter(ch -> ch == '.').count() == 0) { // no decimals in the value, then add decimals
					food_price += ".00";
				}
				
//				food_price = df2.format(Double.parseDouble(food_price)).toString();
//				C
//				if (food_price.chars().filter(ch -> ch == '.').count() == 0) { // no decimals in the value, then add decimals
//					food_price += ".00";
//				} else if (food_price.charAt(food_price.length() - 2).equals('.'))
				
				cur_item.addElement(food_id);
				cur_item.addElement(food_name);
				cur_item.addElement(food_price);
				cur_item.addElement(availability);
				
				
				menu.addElement(cur_item);
				
			}
			
		} catch (Exception e)
		{
			System.out.println(e);
			System.out.println("Error getting menu data table");
		}
		
		this.menu_list = menu;
	}
	
	Vector<Vector<String>> get_ingredients_data()
	{
		query_ingredients();
		cart_helper.update_ingredients_map(ingredients_list);
		return ingredients_list;
	}
	
	void query_ingredients()
	{
		Vector<Vector<String>> ingredients = new Vector<Vector<String>>();

		try
		{
			
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String sql_stmt = "SELECT * from ingredients";
			
			System.out.println("Executing Statement: " + sql_stmt);
			ResultSet result = stmt.executeQuery(sql_stmt);
			
			while(result.next())
			{
				Vector<String> cur_item = new Vector<String>(); // [0] = key || [1] = name || [2] = price
				// get name and price of food item
				String ingredient_key = result.getString("key");
				String ingredient_name = result.getString("name");
				String ingredient_price = result.getString("price");
				
				if (ingredient_price.length() > 4 && ingredient_price.contains(".")) {
					ingredient_price = ingredient_price.substring(0, ingredient_price.indexOf(".") + 3);
				}
				else if (ingredient_price.chars().filter(ch -> ch == '.').count() == 0) { // no decimals in the value, then add decimals
					ingredient_price += ".00";
				}
				
				cur_item.addElement(ingredient_key);
				cur_item.addElement(ingredient_name);
				cur_item.addElement(ingredient_price);
				// put all info pertaining to item into the menu list
				ingredients.addElement(cur_item);
			
			}
			
		} catch (Exception e)
		{
			System.out.println("Error querying information from Ingredients Data Table.");
		}
		
		System.out.println(ingredients);
		this.ingredients_list = ingredients;
	}
	
	String getPrice(String item) {
		String price = "";
		try {
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String sql_stmt = "SELECT price FROM menu WHERE name = '" + item + "'";
			System.out.println("Executing Statement: " + sql_stmt);
			
			ResultSet result = stmt.executeQuery(sql_stmt);
			while(result.next()) {
				price = result.getString("price");
				if (price.length() > 4 && price.contains(".")) {
					price = price.substring(0, price.indexOf(".") + 3);
				}
				else if (price.chars().filter(ch -> ch == '.').count() == 0) { // no decimals in the value, then add decimals
					price += ".00";
				}
			}
			
			System.out.println("Updated item " + item + " in database to " + price);
			
			
			return price;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error encountered when getting price");
			return price;
		}
	}
	
	Boolean changePrice(String item, String priceChangeTxt) {
		if (priceChangeTxt.charAt(0) == '$') {
			priceChangeTxt = priceChangeTxt.substring(1);
		}
		System.out.println(priceChangeTxt);
		
		try {
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String sql_stmt = "UPDATE menu SET price = '" + priceChangeTxt + "' WHERE name = '" + item + "'";
			System.out.println("Executing Statement: " + sql_stmt);
			
			stmt.executeUpdate(sql_stmt);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error encountered when changing price");
			return false;
		}
		
	}
	
	Boolean changeAvailability(String item, Boolean available) {
		try {
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String available_str = "true";
			if (!available) {
				available_str = "false";
			}
			String sql_stmt = "UPDATE menu SET available = '" + available_str + "' WHERE name = '" + item + "'";
			System.out.println("Executing Statement: " + sql_stmt);
			
			stmt.executeUpdate(sql_stmt);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	Boolean getAvailability(String item) {
		try {
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String available_str = "true";
			String sql_stmt = "SELECT available FROM menu WHERE name = '" + item + "'";
			System.out.println("Executing Statement: " + sql_stmt);
			
			ResultSet result = stmt.executeQuery(sql_stmt);
			while(result.next()) {
				available_str = result.getString("available");
				System.out.println(available_str);
			}
			
			if (available_str.equals("t")) {
				System.out.println("Found true");
				return true;
			}
			else {
				return false;
			}
			
			
		} catch (Exception e) {
			return true;
		}
	}
	
	String getItemID(String item_name) {
		try {
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String id = "";
			String sql_stmt = "SELECT id FROM menu WHERE name = '" + item_name + "'";
			System.out.println("Executing Statement: " + sql_stmt);
			
			ResultSet result = stmt.executeQuery(sql_stmt);
			while(result.next()) {
				id = result.getString("id");
			}
			
			return id;
			
		} catch (Exception e) {
			System.out.println(e);
			return "";
		}
	}
	
	String getItemName(String item_id) {
		try {
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String id = "";
			String sql_stmt = "SELECT name FROM menu WHERE id = '" + item_id + "'";
			System.out.println("Executing Statement: " + sql_stmt);
			
			ResultSet result = stmt.executeQuery(sql_stmt);
			while(result.next()) {
				id = result.getString("name");
			}
			
			return id;
			
		} catch (Exception e) {
			System.out.println(e);
			return "";
		}
	}
	
	String getIngredientID(String ingredient_name) {
		try {
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String id = "";
			String sql_stmt = "SELECT key FROM ingredients WHERE name = '" + ingredient_name + "'";
			System.out.println("Executing Statement: " + sql_stmt);
			
			ResultSet result = stmt.executeQuery(sql_stmt);
			while(result.next()) {
				id = result.getString("key");
			}
			
			return id;
			
		} catch (Exception e) {
			System.out.println(e);
			return "";
		}
	}
	
	String getIngredientName(String ingredient_id) {
		try {
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String id = "";
			String sql_stmt = "SELECT name FROM ingredients WHERE key = '" + ingredient_id + "'";
			System.out.println("Executing Statement: " + sql_stmt);
			
			ResultSet result = stmt.executeQuery(sql_stmt);
			while(result.next()) {
				id = result.getString("name");
			}
			
			return id;
			
		} catch (Exception e) {
			System.out.println(e);
			return "";
		}
	}
	
	Boolean addNewCustomer(String first, String last, String user, String pass) {
		try {
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String count = "";
			String sql_stmt = "SELECT count(id) FROM customer WHERE id = '" + user + "'";
			System.out.println("Executing Statement: " + sql_stmt);
			
			ResultSet result = stmt.executeQuery(sql_stmt);
			while(result.next()) {
				count = result.getString("count");
				System.out.println(count);
			}
			
			// is username is not found, add them to customer table
			if (count.equals("0") && !first.equals("") && !last.equals("") && !pass.equals("")) {
				sql_stmt = "INSERT INTO customer (id, password, firstname, lastname) VALUES ('" + user + "', '" + pass + "', '" + first + "', '" + last + "');";
				System.out.println("Executing Statement: " + sql_stmt);
				
				stmt.executeUpdate(sql_stmt);
				
				this.last_name = last;
				this.first_name = first;
				this.id = user;
				this.password = pass;
				return true;
			}
			else {
				return false;
			}
			
		} catch (Exception e) {
			System.out.println("Query Failed");
			return false;
		}
	}
	
	Boolean verifyPayment(JTextField card_num, JTextField security_code, int card_type, String card_carrier, String expiration_date) {
		Boolean card_type_sql;
		String cardnumber = "";
//		String type;
//		int securitycode = -1;
		
		try {
			Statement stmt = conn.createStatement();
			
			//determining card type
			if (card_type == 0) { //it's a credit card
				card_type_sql = false;
			} else { //it's a debit card
				card_type_sql = true;
			}
			
			String sql_stmt = "SELECT * FROM cardinfo WHERE cardinfo.credit_debit IS '" 
					+ card_type_sql
					+ "' AND cardinfo.cardnumber LIKE '" 
					+ card_num.getText() 
					+ "' AND cardinfo.type LIKE '"
					+ card_carrier.toUpperCase()
					+ "' AND cardinfo.expiration LIKE'"
					+ expiration_date
					+ "' AND cardinfo.securitycode LIKE '"
					+ security_code.getText() + "'";
			System.out.println("Executing Statement: " + sql_stmt);
			ResultSet result = stmt.executeQuery(sql_stmt);
			
			while(result.next()) {
				card_type_sql = result.getBoolean("credit_debit");
				cardnumber = result.getString("cardnumber"); 
//				type = result.getString("type");
//				securitycode = result.getInt("securitycode");
			}
			System.out.println("Finished Executing Statement: " + sql_stmt);
			
			if (cardnumber.equals("")) { //no card number is all you need to check to verify if no card is there
				return false;
			} else { // the card exists!		
				System.out.println("Card is valid!");
				return true;
			}
			
		} catch (Exception e) {
			return false;
		}
	}
	
	Boolean createPayment(JTextField card_num, JTextField security_code, int card_type, String card_carrier, String expiration_date) {
		Boolean card_type_sql;
		
		try {
			Statement stmt = conn.createStatement();
			
			//determining card type
			if (card_type == 0) { //it's a credit card
				card_type_sql = true;
			} else { //it's a debit card
				card_type_sql = false;
			}
			
			//INSERT INTO cardinfo(credit_debit, cardnumber, type, expiration, securitycode, customerid)
			//VALUES ('<T/F>', '<card_num>', '<type>', '<expiration>', <security_code_int>, '<customerid>');
			
			String sql_stmt = "INSERT INTO cardinfo(credit_debit, cardnumber, type, expiration, securitycode, customerid) VALUES (" 
					+ card_type_sql
					+ ", '" 
					+ card_num.getText() 
					+ "', '"
					+ card_carrier.toUpperCase()
					+ "', '"
					+ expiration_date
					+ "', "
					+ security_code.getText() 
					+ ", '"
					+ getId()
					+ "')";
			System.out.println("Executing Statement: " + sql_stmt);
			stmt.executeQuery(sql_stmt);	
			System.out.println("Finished Executing Statement: " + sql_stmt);
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @param menu_item_id
	 * at this point in time, used for transition from customer menu to ingredients menu
	 * set current menu item key to param to know which menu item the added ingredients will belong to
	 * reset the current item price to 0.0 before adding in the item to the cart along with its customizations
	 */
	void choose_menu_item_to_customize(String menu_item_id) {
		cart_helper.set_cur_menu_item_key(menu_item_id);
		cart_helper.set_cur_customizing_item_price(0.0);
		cart_helper.add_cur_menu_item();
	}//end choose menu item to customize
	
	void choose_ingredient_item_to_customize(String ingredient_item_id) {
		cart_helper.set_cur_ingredient_item_key(ingredient_item_id);
	}
	
	void delete_cur_menu_item()
	{
		cart_helper.delete_cur_menu_item_from_cart();
		cart_helper.prep_for_next_menu_item();
	}
	
	void delete_menu_item(String menu_item_id)
	{
		cart_helper.delete_menu_item_from_cart(menu_item_id);
	}
	
	void reset_cart_ingredient_id() {
		cart_helper.set_cur_ingredient_item_key("");
	}
	
	// add ingredient into cart and delete ingredient key because this is used right before
	// going to ingredient screen for the customer to choose another customization
	void add_cur_ingredient_as_customization(int option) {
		cart_helper.add_cur_ingredient(option);
		reset_cart_ingredient_id();
	}
	
	void add_free_item_to_cart(String menu_item_id) {
		cart_helper.set_cur_menu_item_key(menu_item_id);
		cart_helper.set_cur_customizing_item_price(0.0);
		cart_helper.add_menu_item_as_free(menu_item_id);
		reset_cart_ingredient_id();
	}//end add free item to cart
	
	/**
	 * everything has been added to cart previously. to finalize the customized item,
	 * only the cost of that customized item needs to be added to the total cost
	 * after adding item into cart, need to reset item menu key, ingr. key, and cur 
	 * item cost to get ready for next item (all in prep for next menu item function)
	 */
	void add_cur_customized_menu_item()
	{
		cart_helper.add_item_cost_to_tot_cost();
		cart_helper.prep_for_next_menu_item();
		System.out.println("Current Cart:\n" + cart_helper.get_cart());
	}
	
	Map<String, Vector<String>> convertOrder(String order) 
    { 
	Map<String, Vector<String>> hm  = new HashMap<String, Vector<String>>(); 
	Vector<String> empty_string = new Vector<String>();

	String[] split_entrees = order.split("(:)");

	for(int i = 0; i < split_entrees.length; i++) { 
	    if(split_entrees[i].length() == 2 || split_entrees[i].length() == 3) { 
		// only has the entree and no ingredient 
		hm.put(split_entrees[i], empty_string);
	    } else {
		// need to parse the string 
		String[] split_ingred = split_entrees[i].split(";");
		Vector<String> ingredients = new Vector<String>();
		for(int j = 1; j < split_ingred.length; j++) { 
		    ingredients.add(split_ingred[j]);
		}
		hm.put(split_ingred[0], ingredients);
	    }
	}

	// Uncomment for debugging
	// for (Map.Entry<String, Vector<String>> me : hm.entrySet()) { 
	//     System.out.println(me.getKey() + "->" + me.getValue());
	// }

	return hm;
    }

	Map<String, Vector<String>> prevOrder() {
		Map<String, Vector<String>> prevOrderMap = new HashMap<String, Vector<String>>(); 
		try {
			Statement stmt = conn.createStatement();
			
			String entree, side, beverage, dessert;
			Vector<String> entrees = new Vector<String>();
			Vector<String> sides = new Vector<String>();
			Vector<String> beverages = new Vector<String>();
			Vector<String> desserts = new Vector<String>();
			
			//INSERT INTO card(credit_debit, cardnumber, type, expiration, securitycode, customerid)
			//VALUES ('<T/F>', '<card_num>', '<type>', '<expiration>', <security_code_int>, '<customerid>');
			
			String where = "WHERE customer.lastname LIKE '" + this.last_name.toUpperCase() + "%' ";
			String and_str = "AND customer.firstname LIKE '" + this.first_name.toUpperCase() + "%' ";
			String sql_stmt = 
					"SELECT m1.name AS entree, m2.name AS side, m3.name AS beverage, m4.name AS dessert " + 
					"FROM orders " + 
					"FULL OUTER JOIN customer ON orders.customerid = customer.id " + 
					"FULL OUTER JOIN menu m1 ON orders.entrees = m1.id " + 
					"FULL OUTER JOIN menu m2 ON orders.sides = m2.id " + 
					"FULL OUTER JOIN menu m3 ON orders.beverages = m3.id " + 
					"FULL OUTER JOIN menu m4 ON orders.desserts = m4.id " + 
					 where + and_str + 
					"ORDER BY orders.date DESC " + 
					"LIMIT 5";
			ResultSet result = stmt.executeQuery(sql_stmt);	
			System.out.println("Finished Executing Statement: " + sql_stmt);
			
			while (result.next()) {
				entree = result.getString("entree");
				entrees.add(entree);
				
				side = result.getString("side");
				sides.add(side);
				
				beverage = result.getString("beverage");
				beverages.add(beverage);
				
				dessert = result.getString("dessert");
				desserts.add(dessert);
			}
			
			prevOrderMap.put("Entrees", entrees);
			prevOrderMap.put("Sides", sides);
			prevOrderMap.put("Beverages", beverages);
			prevOrderMap.put("Desserts", desserts);
			
			return prevOrderMap;
			
		} catch (Exception e) {
			System.out.println("Failed to execute previous order.");
		}
		return null;
	}
	
	Vector<Vector<String>> getOrders(Calendar startDate, Calendar endDate) {
		String startYear = Integer.toString(startDate.get(Calendar.YEAR));
		String startMonth = Integer.toString(startDate.get(Calendar.MONTH) + 1);
		String startDay = Integer.toString(startDate.get(Calendar.DATE));
		String endYear = Integer.toString(endDate.get(Calendar.YEAR));
		String endMonth = Integer.toString(endDate.get(Calendar.MONTH) + 1);
		String endDay = Integer.toString(endDate.get(Calendar.DATE));
		
		if (startMonth.length() < 2) {
			startMonth = "0" + startMonth;
		}
		if (startDay.length() < 2) {
			startDay = "0" + startDay;
		}
		if (endMonth.length() < 2) {
			endMonth = "0" + endMonth;
		}
		if (endDay.length() < 2) {
			endDay = "0" + endDay;
		}
		
		Vector<Vector<String>> orders = new Vector<Vector<String>>();
		
		try {
			Statement stmt = conn.createStatement();
			
			String sql_stmt = "SELECT * FROM orders WHERE date BETWEEN '" + startYear + startMonth + startDay + "' and '" + endYear + endMonth + endDay + "'";
			System.out.println(sql_stmt);
			ResultSet result = stmt.executeQuery(sql_stmt);	
			
			while (result.next()) {
				Vector<String> row = new Vector<String>();
				row.add(result.getString("totalcost"));
				row.add(result.getString("entrees"));
				row.add(result.getString("sides"));
				row.add(result.getString("beverages"));
				row.add(result.getString("desserts"));
				row.add(result.getString("n_items"));
				orders.add(row);
			}
			
			return orders;
			
		} catch (Exception e) {
			System.out.println("Failed");
		}
		
		return null;
	}
	
	Vector<Vector<String>> compile_cart_for_display()
	{
		Vector<Vector<String>> cart_vector = new Vector<Vector<String>>();
		
		Map<String, Vector<String>> cart_map = cart_helper.get_cart();
		
		// put entrees first, then sides, dessert, and beverages
		for(Map.Entry<String, Vector<String>> item : cart_map.entrySet())
		{
			String item_key = item.getKey();
			Character menu_item_first_char = item_key.charAt(0);
			if (menu_item_first_char.equals('E'))
			{
				Vector<String> cur_customized_item_with_names = create_cur_item_info_display(item);
				cart_vector.addElement(cur_customized_item_with_names);
			}
		}
		
		for(Map.Entry<String, Vector<String>> item : cart_map.entrySet())
		{
			String item_key = item.getKey();
			Character menu_item_first_char = item_key.charAt(0);
			if (menu_item_first_char.equals('S'))
			{
				Vector<String> cur_customized_item_with_names = create_cur_item_info_display(item);
				cart_vector.addElement(cur_customized_item_with_names);
			}
		}
		
		for(Map.Entry<String, Vector<String>> item : cart_map.entrySet())
		{
			String item_key = item.getKey();
			Character menu_item_first_char = item_key.charAt(0);
			if (menu_item_first_char.equals('D'))
			{
				Vector<String> cur_customized_item_with_names = create_cur_item_info_display(item);
				cart_vector.addElement(cur_customized_item_with_names);
			}
		}
		
		for(Map.Entry<String, Vector<String>> item : cart_map.entrySet())
		{
			String item_key = item.getKey();
			Character menu_item_first_char = item_key.charAt(0);
			if (menu_item_first_char.equals('B'))
			{
				Vector<String> cur_customized_item_with_names = create_cur_item_info_display(item);
				cart_vector.addElement(cur_customized_item_with_names);
			}
		}
		
		return cart_vector;
	}
	
	// USES getItemName
	Vector<String> create_cur_item_info_display(Map.Entry<String, Vector<String>> item)
	{
		Vector<String> cur_item = new Vector<String>();
		
		String item_name = getItemName(item.getKey());
		cur_item.addElement(item_name);
		
		Vector<String> cur_item_customizations = item.getValue();
		String customization_string = "";
		
		for(String i : cur_item_customizations)
		{
			// if there's nothing in the string yet then don't add "," before ingredient name
			// otherwise add it because there's a preceding item in the customization
			if (!customization_string.equals(""))
			{
				customization_string += ", ";
			}
			
			// add the word Extra if thats what the customization calls for
			Character ingr_item_first_char = i.charAt(0);
			String ingr_id = i;
			if (ingr_item_first_char.equals('X'))
			{
				customization_string += "Extra ";
				// remove the first character in the ingr_item_first_char because it's not part
				// of the ingredient id and ingredient id is needed to get ingredient name
				ingr_id = ingr_id.substring(1, ingr_id.length());
			}
			String ingr_name = cart_helper.get_ingredient_item_name(ingr_id);
			customization_string += ingr_name;
		}
		
		cur_item.addElement(customization_string);
		
		return cur_item;
	}
	
	// USES getItemName
	Map<String, Integer> getOrderAmounts(String type) { 
		HashMap<String, Integer> orderAmounts = new HashMap<String, Integer>();
		try {
			Statement stmt = conn.createStatement();
			String number = ""; 
			String menu_item = "";
			int limit = 0;
			if(type == "E") { 
				number = type; 
				limit = 7;
				menu_item  = "entrees";
			} else if (type == "S") { 
				number = type;
				limit = 4;
				menu_item  = "sides";
			} else if (type == "B") { 
				number = type; 
				limit = 4;
				menu_item  = "beverages";
			} else if (type == "D") { 
				number = type;
				limit = 2;
				menu_item  = "desserts";
			}
			int num = 1, sum = 0;
			for(int i = 0; i < limit; i++) { 
				number += String.valueOf(num);
				String sqlStatement = "SELECT sum(case when " + menu_item + " LIKE '%" + number + "%' then 1 else 0 end) FROM orders "
						+ "where date >= '2020-01-01'";
				ResultSet rs = stmt.executeQuery(sqlStatement); 
				while(rs.next()) { 
					sum = rs.getInt("sum");
				}
				orderAmounts.put(number, sum);
				num++;
				number = type;
			}
			
			// used to sort the hashmap
			// ANY INQUIRIES CAN BE DROPPED AT THIS WEBSITE: https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/ 
			List<Map.Entry<String,Integer>> list = new LinkedList<Map.Entry<String, Integer>>(orderAmounts.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() { 
					public int compare(
							Map.Entry<String, Integer> 	item1,
							Map.Entry<String, Integer> item2)
					{
						return(item1.getValue()).compareTo(item2.getValue());
					}});
			HashMap<String, Integer> result = new LinkedHashMap<String, Integer>();
			for(Map.Entry<String, Integer> me: list) { 
				result.put(me.getKey(), me.getValue());
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orderAmounts;
		
	}
	
	Vector<Vector<String>> trending_options(String type) { 
		System.out.println("Type: " + type);
		Vector<Vector<String>> ordered_trending_items = new Vector<Vector<String>>(); 
		
//		if(!type.equals("E") || !type.equals("S") || !type.equals("D") || !type.equals("B")) { 
//			System.out.println("Proper input was not put in..."); 
//			System.out.println("Auto choosing entree...");
//			type = "E";
//		}
		
		Map<String, Integer> trending_map = getOrderAmounts(type);
		
		for(Map.Entry<String, Integer> item : trending_map.entrySet())
		{
			Vector<String> cur_item = new Vector<String>();
			String item_name = getItemName(item.getKey());
			String num_ordered = item.getValue().toString();
			
			cur_item.addElement(item_name);
			cur_item.addElement(num_ordered);
			
			ordered_trending_items.addElement(cur_item);
		}
		
		return ordered_trending_items;
	}
	
	// USES getItemName
	Vector<Vector<String>> trending_options_for_rec(String type)
	{
		System.out.println("Type: " + type);
		Vector<Vector<String>> ordered_trending_items = new Vector<Vector<String>>(); 
		
//		if(!type.equals("E") || !type.equals("S") || !type.equals("D") || !type.equals("B")) { 
//			System.out.println("Proper input was not put in..."); 
//			System.out.println("Auto choosing entree...");
//			type = "E";
//		}
		
		Map<String, Integer> trending_map = getOrderAmounts(type);
		
		for(Map.Entry<String, Integer> item : trending_map.entrySet())
		{
			Vector<String> cur_item = new Vector<String>();
			String item_name = getItemName(item.getKey()); 
			Double item_price = cart_helper.get_menu_item_price(item.getKey());
			
			cur_item.addElement(item_name);
			cur_item.addElement(item_price.toString());
			
			ordered_trending_items.addElement(cur_item);
		}
		
		return ordered_trending_items;
	}
	
	void writeOrdertoDatabase() { 
		int order_id = 0; 
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "SELECT COUNT(id) FROM orders"; 
			ResultSet rs = stmt.executeQuery(sqlStatement);
			while(rs.next()) { 
				order_id = rs.getInt("count") + 1;
			}
			Map<String, Vector<String>> cart_map = cart_helper.get_cart();
			String date = "'";
			date += java.time.LocalDate.now().toString() + "'";
			// Need to parse the string for the entrees and stuff still
			String entrees = "'";
			String sides = "'";
			String bev = "'";
			String desserts = "'";
			
			for(Entry<String, Vector<String>> me : cart_map.entrySet()) {
				if(me.getKey().charAt(0) == 'E') {
					String hold = "";
					hold += me.getKey() + ":";		
					for(int i = 0; i < me.getValue().size(); i++) { 
						if(i == me.getValue().size() -1) {
							hold += me.getValue().elementAt(i) + ":";
						} else {
							hold += me.getValue().elementAt(i) + ";";
						}
					}
					entrees += hold;
				} else if(me.getKey().charAt(0) == 'S') { 
					String hold = "";
					hold += me.getKey() + ":";	
					for(int i = 0; i < me.getValue().size(); i++) { 
						if(i == me.getValue().size() -1) {
							hold += me.getValue().elementAt(i) + ":";
						} else {
							hold += me.getValue().elementAt(i) + ";";
						}
					}
					sides += hold;
				} else if(me.getKey().charAt(0) == 'B') { 
					String hold = "";
					hold += me.getKey() + ":";	
					for(int i = 0; i < me.getValue().size(); i++) { 
						if(i == me.getValue().size() -1) {
							hold += me.getValue().elementAt(i) + ":";
						} else {
							hold += me.getValue().elementAt(i) + ";";
						}
					}
					bev += hold;
				} else if(me.getKey().charAt(0) == 'D') { 
					String hold = "";
					hold += me.getKey() + ":";	
					for(int i = 0; i < me.getValue().size(); i++) { 
						if(i == me.getValue().size() -1) {
							hold += me.getValue().elementAt(i) + ":";
						} else {
							hold += me.getValue().elementAt(i) + ";";
						}
					}
					desserts += hold;
				}
			}
			
			if(entrees.equals("'")) { 
				entrees = null;
			} else {
				entrees = entrees.substring(0, entrees.length()-1);
				entrees += "'";
			}
			
			if(sides.equals("'")) { 
				sides = null;
			} else {
				sides = sides.substring(0, sides.length()-1);
				sides += "'";
			} 
			
			if(bev.equals("'")) { 
				bev = null;
			} else {
				bev = bev.substring(0, bev.length()-1);
				bev += "'";
			} 
			
			if(desserts.equals("'")) { 
				desserts = null;
			} else {
				desserts = desserts.substring(0, desserts.length()-1);
				desserts += "'";
			} 
			
			//INSERT INTO orders VALUES (<order_id>, <customer_id>, <date>, <total_cost>,
				//<number_of_ordered_items>, <entrees>, <sides>, <beverages>, <desserts>)
			
			sqlStatement = "INSERT INTO orders VALUES (" + order_id + ", '" + getId() + "' ," + date + "," 
					+ cart_helper.get_total_cost() + "," + cart_map.size() + "," + entrees + "," + sides + "," 
					+ bev + "," + desserts + ")";
			System.out.println("I hope this works: " + sqlStatement);
			stmt.execute(sqlStatement);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void closeConnection() { 
		try {
			conn.close();
			System.out.println("Successfully closed connection...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
