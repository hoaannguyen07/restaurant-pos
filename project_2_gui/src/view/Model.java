package view;

import javax.swing.table.DefaultTableModel;

// @author ashraf

@SuppressWarnings("serial")
public class Model extends DefaultTableModel {
	

    public Model() {
        super(DataHelper.get_menu, TABLE_HEADER);
    }
    
	/*
	 * Variables:
	 * Item name: the name of a food item
	 * Item type: entree, beverage, side, or dessert
	 * Price: how much the item costs
	 */
    public static final Object[] TABLE_HEADER = { 
    		"Item Name", "Item Type", "Price"
    };
 
}
