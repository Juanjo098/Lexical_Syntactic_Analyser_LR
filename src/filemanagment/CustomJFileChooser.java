package filemanagment;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase para crear un JFileChooser con el filtro para abrir archivos con la extenci�n ".sg".
 * 
 * @author Juan Jos� Silva L�pez
 * @author Arturo Guzm�n �vila
 * @version 1.0
 */
public class CustomJFileChooser extends JFileChooser{

    public final int APPROVE_OPTION = JFileChooser.APPROVE_OPTION;
    
    public CustomJFileChooser(){
        setFileFilter(new FileNameExtensionFilter("SG file (*.sg)", "sg"));
    }
}
