package main;

import filemanagment.ReadSpreadsheet;
import graphicinterface.Window;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (!(new File("src\\lexicalanalysis\\Lexer.java").exists()))
            JFlex.Main.generate(new File("src\\lexicalanalysis\\Lexer.flex"));
        new Window().setVisible(true);
    }
}
