package view;

import java.sql.*; // to do all SQL commands
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

public class DataHelper {
	public Connection conn;
	public String last_name;
	public String first_name;
	public String id;
	public String password;
	Vector<Vector<String>> cart;
	
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
	}
	
	DataHelper (Connection conn)
	{
		this.conn = conn;
		System.out.println("Transfer of Connection Successfully");
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
			
			ResultSet result = stmt.executeQuery(sql_stmt);
			
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
	int num_visits_left_to_reward()
	{
		/* 
		 * create & execute a sql statement (first object then the statement that will be put into that object)
		 * sql stmt:
		 * SELECT "Rewards".visit_num FROM "Rewards" WHERE "Rewards".customerid LIKE 'id%';
		 * done in try/catch statement in case database cannot be accessed and errors present itself
		 * 
		 * 
		 */
		
		try
		{
			Statement stmt = conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String sql_stmt = "SELECT \"Rewards\".visit_num FROM \"Rewards\" WHERE \"Rewards\".customerid LIKE '" + this.id + "%'";
			
			System.out.println("Executing Statement: " + sql_stmt);
			
			ResultSet result = stmt.executeQuery(sql_stmt);
			
			int num_visits = Integer.parseInt(result.getString("visit_num"));
			
			return 5 - (num_visits % 5);
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
		Vector<Vector<String>> menu_list = new Vector<Vector<String>>();

		try
		{
			Statement stmt = this.conn.createStatement(); // statement object
			// create the actual statement to populate the statement object
			String sql_stmt = "SELECT * from menu";
			
			System.out.println("Executing Statement: " + sql_stmt);
			ResultSet result = stmt.executeQuery(sql_stmt);
			
			while(result.next())
			{
				// create vector to record information on one row to then be added to menu_list [0] = id || [1] = name || [2] = price || [3] = availability
				Vector<String> cur_item = new Vector<String>(); 
				// get name and price of food item
				String food_id = result.getString("id");
				String food_name = result.getString("name");
				String food_price = result.getString("price");
				String availability = result.getString("available");
				
				cur_item.addElement(food_id);
				cur_item.addElement(food_name);
				cur_item.addElement(food_price);
				cur_item.addElement(availability);
				
				
				menu_list.addElement(cur_item);
				
			}
			
		} catch (Exception e)
		{
			System.out.println(e);
			System.out.println("Error getting menu data table");
		}
		return menu_list;
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
			
			stmt.executeQuery(sql_stmt);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error encountered when changing price");
			return false;
		}
		
	}
	
}
