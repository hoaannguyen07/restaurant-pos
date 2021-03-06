package view;

import javax.swing.table.DefaultTableModel;

// @author ashraf

@SuppressWarnings("serial")
public class Model extends DefaultTableModel {
 
    public Model() {
        super(Constants.CART, Constants.TABLE_HEADER);
    }
 
}
