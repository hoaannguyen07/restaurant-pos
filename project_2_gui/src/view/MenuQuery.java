package view;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

//@author ashraf_sarhan
public class MenuQuery {
 
    public static final Object[] TABLE_HEADER = { 
    		"Item Name", "Price"
    };
    
    public MenuQuery() {
    	Statement stmt = conn.createStatement();
    	String sqlMenuStatement = "SELECT menu.name, menu.price from menu";
    	ResultSet result = stmt.executeQuery(sqlMenuStatement);
    	
    	String menu_item, item_price; 
    	
    	String[][] menu_vect;
    	
    	int i = 0;
    	
    	while (result.next()) {
            menu_item = result.getString("name");
            item_price = result.getString("price");
            menu_vect[i][0] = menu_item;
            menu_vect[i][1] = item_price;
            i++;    
        }
    }
 
    public static final Object[][] MENU = { 
    		//make into a 2D vector
    		//customer can add stuff to cart,
    		//will carry out throughout whole process
    		
    		//previous orders screen will still have cart due to this.
    		
    		//everything inside of here will stay here until the order is up
    		
    };
     
}
