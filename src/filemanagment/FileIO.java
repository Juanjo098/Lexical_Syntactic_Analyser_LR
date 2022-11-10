package filemanagment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;

/**
 * Clase para leer y escribir archivos.
 * 
 * @author Juan José Silva López
 * @author Arturo Guzmán Ávila
 * @version 1.0
 */
public class FileIO {
    
    /**
     * Lee el contenido de un archivo.
     * @param file Archivo a leer.
     * @return Contenido del archivo
     */
    public String readFile(File file){
        String cont = "", line;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))){
            
            while ((line = reader.readLine()) != null)
                cont += line + "\n";
            
            if (cont == "")
                return "";
            
            return cont.substring(0, cont.length() - 1);
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "The file couldn't be open.", "READING ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /**
     * Escribe un archivo
     * @param file Archivo a escribir
     * @param text Texto a escribir en el archivo
     * @return true: si se escribió correctamente el archivo | false: si no se pudo escribir el archivo
     */
    public boolean writeFile(File file, String text){
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            writer.write(text);
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "The file couldn't be saved.\nDetails: " + e.getMessage(), "WRITING ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
