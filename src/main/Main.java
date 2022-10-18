package main;

import filemanagment.ReadSpreadsheet;
import graphicinterface.Window;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        String cad[][] = ReadSpreadsheet.SpreadsheetTo2dArray();
        for (String[] strings : cad) {
            for (String string : strings) {
                System.out.println(string + "\n");
            }
            System.out.println("");
        }
        
        if (!(new File("src\\lexicalanalysis\\Lexer.java").exists()))
            JFlex.Main.generate(new File("src\\lexicalanalysis\\Lexer.flex"));
        new Window().setVisible(true);
    }
}
