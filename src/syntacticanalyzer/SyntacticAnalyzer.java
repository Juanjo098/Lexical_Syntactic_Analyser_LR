package syntacticanalyzer;

import data.Component;
import filemanagment.ReadSpreadsheet;
import java.util.Stack;
import data.Component;
import java.util.StringTokenizer;

public class SyntacticAnalyzer {

    private final String[][] TABLE = ReadSpreadsheet.SpreadsheetTo2dArray();
    private final String[] ELEMENTS = ReadSpreadsheet.ReadTable();
    private final String[] PRODUCTIONS = ReadSpreadsheet.Productions();
    private final String[] NO_TERMINALS = ReadSpreadsheet.NoTerminals();

    private int row, column;

    private String error, cell;
    private Stack<String> stack, input, actions;

    public SyntacticAnalyzer() {
        stack = new Stack<>();
        stack.add("0");
        input = new Stack<>();
        actions = new Stack<>();
    }

    private void print2dArray(String[][] array) {
        for (String[] strings : array) {
            for (String string : strings) {
                System.out.print(string + "\t");
            }
            System.out.println("");
        }
    }

    private void printArray(String[] array) {
        for (String string : array) {
            System.out.print(string + "\t");
        }
        System.out.println("");
    }

    public void syntacticAnalysis(Component c) {
        while (true) {
            try {
                showStack();

                row = getRowIndex();
                column = getColumnIndex(c.getToken());
                cell = TABLE[row][column];

                if (cell == "") {
                    error = "Error sintactico";
                    return;
                }
                
                if (cell.equals("acc")) {
                    return;
                }

                if (cell.startsWith("r")) {
                    cell = cell.substring(1, cell.length());
                    int pro = Integer.parseInt(cell);
                    int elements, tope;

                    StringTokenizer st = new StringTokenizer(PRODUCTIONS[pro], " ");
                    elements = st.countTokens();

                    if (elements == 1 && st.nextToken().equals("")) {
                        elements = 0;
                    }

                    popStack(elements);
                    tope = Integer.parseInt(stack.peek());

                    stack.add(NO_TERMINALS[pro]);
                    stack.add(TABLE[tope][getColumnIndex(NO_TERMINALS[pro])]);
                    
                    continue;
                }

                if (cell.startsWith("s")) {
                    stack.add(c.getToken());
                    stack.add(cell.substring(1, cell.length()));
                    return;
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                error = "Error sintactico";
            }
        }
    }

    public boolean isError() {
        return error != null;
    }

    public String getError() {
        return error;
    }

    private int getColumnIndex(String s) {
        for (int i = 0; i < ELEMENTS.length; i++) {
            if (ELEMENTS[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    private int getRowIndex() {
        return Integer.parseInt(stack.peek());
    }

    private void popStack(int pops) {
        for (int i = 0; i < (pops * 2); i++) {
            stack.pop();
        }
    }

    private void showStack() {
        System.out.print("Pila: ");
        for (String string : stack) {
            System.out.print(string + " ");
        }
        System.out.println("");
    }
}
