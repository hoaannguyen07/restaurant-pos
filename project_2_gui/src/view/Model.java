package view;

import javax.swing.table.DefaultTableModel;

// @author ashraf

@SuppressWarnings("serial")
public class Model extends DefaultTableModel {
 
    public Model() {
        super(MenuQuery.MENU, MenuQuery.TABLE_HEADER);
    }
 
}
