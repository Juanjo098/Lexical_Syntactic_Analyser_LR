package graphicinterface;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel{

    public CustomTableModel(Vector<String> stack, Vector<String> input, Vector<String> actions) {
        this.addColumn("Stack");
        this.addColumn("Input");
        this.addColumn("Actions");
        
        for (int i = 0; i < actions.size(); i++) {
            Object fila[] = new Object[3];
            fila[0] = stack.get(i);
            fila[1] = input.get(i);
            fila[2] = actions.get(i);
            this.addRow(fila);
        }
        
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
    
    
}
