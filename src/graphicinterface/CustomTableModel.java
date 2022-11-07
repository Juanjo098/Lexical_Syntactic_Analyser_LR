package graphicinterface;

import javax.swing.table.DefaultTableModel;

/**
 * Clase extendida de DefaultTableModel que se personaliz� para mostrar el proceso del analizador sint�ctico.
 * @author Juan Jos� Silva L�pez
 * @version 1.0
 */
public class CustomTableModel extends DefaultTableModel{
    public CustomTableModel() {
        this.addColumn("Stack");
        this.addColumn("Input");
        this.addColumn("Actions");
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
}
