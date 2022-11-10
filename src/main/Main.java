package main;

import graphicinterface.Window;
import java.io.File;

/**
 * Clase que muestra el entorno gráfico.
 * @author Juan José Silva López
 * @author Arturo Guzmán Ávila
 * @version 1.0
 */
public class Main {
    
    /**
     * Método principal
     * @param args 
     */
    public static void main(String[] args) {
        if (!(new File("src\\lexicalanalysis\\Lexer.java").exists()))
            JFlex.Main.generate(new File("src\\lexicalanalysis\\Lexer.flex"));
        new Window().setVisible(true);
    }
}
