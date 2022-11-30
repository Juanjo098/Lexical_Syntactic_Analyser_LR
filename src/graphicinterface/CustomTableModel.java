package graphicinterface;

import javax.swing.table.DefaultTableModel;

/**
 * Clase extendida de DefaultTableModel que se personaliz� para mostrar el proceso del analizador sint�ctico.
 * @author Juan Jos� Silva L�pez
 * @author Arturo Guzm�n �vila
 * @version 1.0
 */
public class CustomTableModel extends DefaultTableModel{
    public CustomTableModel() {
        this.addColumn("Stack");
        this.addColumn("Input");
        this.addColumn("Actions");
        this.addColumn("Semantic");
        this.addColumn("Operators");
        this.addColumn("Postfix notation");
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
}
