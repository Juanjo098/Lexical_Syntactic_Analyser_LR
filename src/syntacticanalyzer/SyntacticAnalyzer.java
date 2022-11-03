package syntacticanalyzer;

import filemanagment.ReadSpreadsheet;
import java.util.Stack;
import data.Component;
import graphicinterface.CustomTableModel;
import java.util.StringTokenizer;
import java.util.Vector;
import semanticanalysis.SemanticAnalysis;

/**
 * Clase que se ocupa del anális sintáctico.
 *
 * @author juanj
 */
public class SyntacticAnalyzer {

    private final String[][] TABLE = ReadSpreadsheet.SpreadsheetTo2dArray();
    private final String[] ELEMENTS = ReadSpreadsheet.Elements();
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
    private String error, cell;
    private Stack<String> stack;
    private Vector<String> stackReg, input, action;
    private Component ant;

    private char act;
    private int row, column;
    private byte dataType = -1;

    public SyntacticAnalyzer() {
        stack = new Stack<>();
        stack.add("$");
        stack.add("0");
        stackReg = new Vector<>();
        input = new Vector<>();
        action = new Vector<>();
    }

    /**
     * Muestra el contenido de un Array 2D de Strings
     *
     * @param array
     * @deprecated
     */
    private void print2dArray(String[][] array) {
        for (String[] strings : array) {
            for (String string : strings) {
                System.out.print(string + "\t");
            }
            System.out.println("");
        }
    }

    /**
     * Muestra el contenido de un Array de Strings
     *
     * @param array
     * @deprecated
     */
    private void printArray(String[] array) {
        for (String string : array) {
            System.out.print(string + "\t");
        }
        System.out.println("");
    }

    /**
     * Análisis sintáctico
     * @param c Objeto instanciado de la clase Componente que contiene el token a analizar.
     * @param s Objeto instanciado de la clase SemanticAnalysis que es responsable del análisis semántico
     */
    public void syntacticAnalysis(Component c, SemanticAnalysis s, CustomTableModel t) {
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
                    t.addRow(getRow());
                    return;
                }

                if (cell.equals("acc")) {
                    action.add("Aceptado");
                    t.addRow(getRow());
                    return;
                }

                act = cell.charAt(0);
                cell = cell.substring(1, cell.length());

                if (act == 'r') {
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
                    
                    t.addRow(getRow());

                    continue;
                }

                if (act == 's') {
                    action.add("Desplazar: " + c.getToken() + " " + cell);
                    stack.add(c.getToken());
                    stack.add(cell);
                    s.analysis(c);
                    t.addRow(getRow());
                    return;
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                error = "Error sintactico";
                return;
            }
        }
    }

    /**
     * Determina si se encontró un error sintáctico.
     * @return true si hay un error | false: si no hay error
     */
    public boolean isError() {
        return error != null;
    }
    
    /**
     * Devuelve el mensaje de error
     * @return Mensaje de error
     */
    public String getError() {
        return error;
    }

    /**
     * Determina en que posición del arreglo se encuentra el token.
     * @param s Token
     * @return Posicion del token dentro del arreglo ELEMENTS
     */
    private int getColumnIndex(String s) {
        for (int i = 0; i < ELEMENTS.length; i++) {
            if (ELEMENTS[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna el valor en entero de la cima de la pila.
     * @return Valor de la cima de la pila.
     */
    private int getRowIndex() {
        return Integer.parseInt(stack.peek());
    }

    /**
     * Saca de la pila el doble de elementos de la producción
     * @param pops número de elementos de la producción
     */
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

    /**
     * Retorna el mensaje de error sintáctico dada la posición encontrada en la cima de la pila.
     * @param state cima de la pila 
     * @param c objeto instanciado de la clase Component que contienen la información del token.
     * @return 
     */
    private String getErrorMessage(int state, Component c) {
        return "Error sintáctico en la linea: " + c.getLine() + ". Se recibió un " + c.getToken() + " y se esperaba un: " + ERROR_MESSAGES[state];
    }

    /**
     * Retorna el contenido actual de la pila
     * @return Contenido de la pila.
     */
    private String getStackStatus() {
        String text = "";

        for (String string : stack) {
            text += string + " ";
        }

        return text;
    }

    public Vector<String> getStackReg() {
        return stackReg;
    }

    public Vector<String> getInput() {
        return input;
    }

    public Vector<String> getAction() {
        return action;
    }
    
    public Object[] getRow(){
        Object[] o = new Object[3];
        o[0] = stackReg.lastElement();
        o[1] = input.lastElement();
        o[2] = action.lastElement();
        return o;
    }

}
