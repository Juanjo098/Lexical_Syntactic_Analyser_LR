/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filemanagment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;

public class FileIO {
    
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
