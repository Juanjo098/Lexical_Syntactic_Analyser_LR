package main;

import graphicinterface.Window;
import java.io.File;

/**
 * Clase que muestra el entorno gr�fico.
 * @author Juan Jos� Silva L�pez
 * @author Arturo Guzm�n �vila
 * @version 1.0
 */
public class Main {
    
    /**
     * M�todo principal
     * @param args 
     */
    public static void main(String[] args) {
        if (!(new File("src\\lexicalanalysis\\Lexer.java").exists()))
            JFlex.Main.generate(new File("src\\lexicalanalysis\\Lexer.flex"));
        new Window().setVisible(true);
    }
}
