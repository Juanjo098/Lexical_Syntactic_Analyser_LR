package main;

public class Main {

    public static void main(String[] args) {
        String[][] table = filemanagment.ReadSpreadsheet.SpreadsheetTo2dArray();
        for (String[] strings : table) {
            for (String string : strings) {
                System.out.print(string + "\t");
            }
            System.out.println("");
        }
    }
}
