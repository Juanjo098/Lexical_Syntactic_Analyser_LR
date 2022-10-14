/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filemanagment;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class that returns a JFileChooser to open or save files with extension "sg".
 * 
 * @author Juan José Silva López
 * @version 1.0
 */
public class CustomJFileChooser extends JFileChooser{

    public final int APPROVE_OPTION = JFileChooser.APPROVE_OPTION;
    
    public CustomJFileChooser(){
        setFileFilter(new FileNameExtensionFilter("SG file (*.sg)", "sg"));
    }
}
