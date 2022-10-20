package syntacticanalyzer;

import data.Component;
import filemanagment.ReadSpreadsheet;
import java.util.Stack;
import data.Component;
import java.util.StringTokenizer;
import java.util.Vector;

public class SyntacticAnalyzer {

    public Vector<String> getStackReg() {
        return stackReg;
    }

    public Vector<String> getInput() {
        return input;
    }

    public Vector<String> getAction() {
        return action;
    }

    private final String[][] TABLE = ReadSpreadsheet.SpreadsheetTo2dArray();
    private final String[] ELEMENTS = ReadSpreadsheet.ReadTable();
    private final String[] PRODUCTIONS = ReadSpreadsheet.Productions();
    private final String[] NO_TERMINALS = ReadSpreadsheet.NoTerminals();
    // <editor-fold defaultstate="collapsed" desc="Error Messages">      
    private final String[] ERROR_MESSAGES = new String[]{
        "id, int, float, char",
        "",
        "id",
        "",
        "id",
        "id",
        "id",
        "=",
        ",, ;",
        "id, (, num",
        "",
        "id",
        "id, int, float",
        ";",
        ";, +, -",
        ";, +, -, *, /",
        ";, +, -, *, /",
        "id, (, num",
        ";, +, -, *, /",
        ",, ;",
        "",
        "",
        ";",
        "id, (, num",
        "id, (, num",
        ";, +, -",
        "id, (, num",
        "id, (, num",
        ")",
        "+, -, )",
        "+, -, *, /, )",
        "+, -, *, /, )",
        "id, (, num",
        "+, -, *, /, )",
        "",
        ";, +, -",
        ";, +, -",
        ";, +, -, *, /",
        ";, +, -, *, /",
        ";, +, -, *, /",
        ")",
        "id, (, num",
        "id, (, num",
        "+, -, )",
        "id, (, num",
        "id, (, num",
        ")",
        ";",
        ";",
        ";, +, -",
        ";, +, -",
        "+, -, )",
        "+, -, )",
        "+, -, *, /, )",
        "+, -, *, /, ",
        "+, -, *, /, ",
        ")",
        ")",
        "+, -, )",
        "+, -, )",};
    // </editor-fold>

    private int row, column;

    private String error, cell;
    private Stack<String> stack;
    private Vector<String> stackReg, input, action;

    public SyntacticAnalyzer() {
        stack = new Stack<>();
        stack.add("0");
        stackReg = new Vector<>();
        input = new Vector<>();
        action = new Vector<>();
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

                row = getRowIndex();
                column = getColumnIndex(c.getToken());
                cell = TABLE[row][column];
                
                stackReg.add(getStackStatus());
                input.add(c.getToken());

                if (cell.equals("")) {
                    action.add("ERROR");
                    error = getErrorMessage(row, c);
                    return;
                }

                if (cell.equals("acc")) {
                    action.add("Aceptado");
                    return;
                }

                if (cell.startsWith("r")) {
                    cell = cell.substring(1, cell.length());
                    int pro = Integer.parseInt(cell);
                    int elements, tope;

                    action.add("Reducir " + NO_TERMINALS[pro] + " -> " + PRODUCTIONS[pro]);
                    
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
                    cell = cell.substring(1, cell.length());
                    action.add("Desplazar: " + c.getToken() + " " + cell);
                    stack.add(c.getToken());
                    stack.add(cell);
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

    /**
     * @deprecated
     */
    private void showStack() {
        System.out.print("Pila: ");
        for (String string : stack) {
            System.out.print(string + " ");
        }
        System.out.println("");
    }

    private String getErrorMessage(int state, Component c) {
        return "Error sintáctico en la linea: " + c.getLine() + ". Se esperaba un: " + ERROR_MESSAGES[state];
    }
    
    private String getStackStatus(){
        String text = "";
        
        for (String string : stack)
            text += string + " ";
        
        return text;
    }
}
