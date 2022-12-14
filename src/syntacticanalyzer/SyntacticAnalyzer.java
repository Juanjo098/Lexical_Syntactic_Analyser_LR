package syntacticanalyzer;

import filemanagment.ReadSpreadsheet;
import java.util.Stack;
import data.Component;
import graphicinterface.CustomTableModel;
import java.util.StringTokenizer;
import semanticanalysis.SemanticAnalysis;

/**
 * Clase que se ocupa del an?lis sint?ctico.
 *
 * @author Juan Jos? Silva L?pez
 * @author Arturo Guzm?n ?vila
 */
public class SyntacticAnalyzer {

    private final String[][] TABLE = ReadSpreadsheet.SpreadsheetTo2dArray();
    private final String[] ELEMENTS = ReadSpreadsheet.Elements();
    private final String[] PRODUCTIONS = ReadSpreadsheet.Productions();
    private final String[] NO_TERMINALS = ReadSpreadsheet.NoTerminals();
    // <editor-fold defaultstate="collapsed" desc="Error Messages">      
    private final String[] ERROR_MESSAGES = new String[]{
        "program",
        "",
        "id",
        "id, endProgram, int, float, char, if, while, print",
        "endProgram",
        "id, endProgram, if, while, print",
        "id",
        "id",
        "id",
        "id",
        "",
        "endProgram",
        "(",
        "(",
        "(",
        "=",
        ",, ;",
        "id, int, float, char, if, endIf, while, print",
        "id",
        "id, int, float, char, if, while, endWhile, print",
        "id",
        "id, num",
        "id, (, num, read",
        "id, endProgram, if, while, print",
        "id",
        "id, endProgram, int, float, char, if, while, print",
        "endIf",
        "id, if, endIf, while, print",
        "id",
        "<, <=, >, >=, ==, !=",
        "endWhile",
        "id, if, while, endWhile, print",
        "id",
        "<, <=, >, >=, ==, !=",
        ")",
        ",, )",
        ",, )",
        ",, )",
        ";",
        ";, +, -",
        ";, +, -, *, /",
        ";, +, -, *, /",
        "id, num, read",
        ";, +, -, *, /",
        "(",
        ",, ;",
        "id, endProgram, if, while, print",
        "id, endProgram, if, while, print",
        "endIf",
        "(",
        "(",
        "(",
        "=",
        ",, ;",
        "id",
        "id",
        "id",
        "id",
        "id",
        "id",
        "id",
        "id, endProgram, if, while, print",
        "endWhile",
        "(",
        "(",
        "(",
        "=",
        ",, ;",
        "id",
        ";",
        ")",
        "id",
        "id, num",
        "id, endProgram, if, while, print",
        ";",
        "id, ), num, read",
        "id, ), num, read",
        ";, +, -",
        "id, (, num, read",
        "id, (, num, read",
        ")",
        "+, -, )",
        "+, -, *, /, )",
        "+, -, *, /, )",
        "id, (, num, read",
        "+, -, *, /, )",
        "(",
        "id, num",
        "id, endProgram, if, while, print",
        "endProgram",
        "id, int, float, char, if, endIf, while, print",
        "id",
        "id, int, float, char, if, while, endWhile, print",
        "id",
        "id, num",
        "id, (, num, read",
        "id, if, endIf, while, print",
        "id",
        "id, int, float, char, if, endIf, while, print",
        ")",
        "endProgram",
        "id, int, float, char, if, endIf, while, print",
        "id, int, float, char, if, while, endWhile, print",
        "id",
        "id, num",
        "id, (, num, read",
        "id, if, while, endWhile, print",
        "id",
        "id, int, float, char, if, while, endWhile, print",
        ")",
        "id, endProgram, if, while, print",
        ",, )",
        "endProgram",
        ";, +, -",
        ";, +, -",
        ";, +, -, *, /",
        ";, +, -, *, /",
        ";, +, -, *, /",
        ")",
        "id, (, num, read",
        "id, (, num, read",
        "+, -, )",
        "id, (, num, read",
        "id, (, num, read",
        ")",
        "id, num",
        ")",
        ")",
        ")",
        "endIf",
        "<, <=, >, >=, ==, !=",
        "endWhile",
        "<, <=, >, >=, ==, !=",
        ")",
        ";",
        ",, ;",
        "id, if, endIf, while, print",
        "id, int, float, char, if, endIf, while, print",
        "endIf",
        "endWhile",
        "<, <=, >, >=, ==, !=",
        ")",
        ";",
        ",, ;",
        "id, if, while, endWhile, print",
        "id, int, float, char, if, while, endWhile, print",
        "endProgram",
        ")",
        ";",
        ";",
        ";, +, -",
        ";, +, -",
        "+, -, )",
        "+, -, ) ",
        "+, -, *, /, )",
        "+, -, *, /, )",
        "+, -, *, /, )",
        ")",
        "+, -, *, /, )",
        "id, if, endIf, while, print",
        "id",
        "id, if, endIf, while, print",
        "id",
        ";",
        "id, if, endIf, while, print",
        "id, if, endIf, while, print",
        "id",
        ";",
        "id, if, endIf, while, print",
        "id, if, endIf, while, print",
        ")",
        ")",
        "+, -, )",
        "+, -, )",
        "+, -, *, /, )",
        "endIf",
        ")",
        "endIf",
        ")",
        "id, if, endIf, while, print",
        "endIf",
        "endWhile",
        "endWhile",
        ")",
        "id, if, endIf, while, print",
        "endWhile",
        "id, int, float, char, if, endIf, while, print",
        "id, int, float, char, if, endIf, while, endWhile, print",
        "endIf",
        "id, int, float, char, if, while, endWhile, print",
        "endWhile",
        };
    // </editor-fold>
    private String error, cell;
    private Stack<String> stack, semanticStack, operators, postfixNotation;
    private Object[] rowTable;

    private char act;
    private int row, column;

    public SyntacticAnalyzer() {
        stack = new Stack<>();
        stack.add("$");
        stack.add("0");
        semanticStack = new Stack<>();
        semanticStack.push("$");
        operators = new Stack<>();
        operators.push("$");
        postfixNotation = new Stack<>();
        postfixNotation.push("$");
        rowTable = new Object[6];
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
     * An?lisis sint?ctico
     *
     * @param c Objeto instanciado de la clase Componente que contiene el token
     * a analizar.
     * @param s Objeto instanciado de la clase SemanticAnalysis que es
     * responsable del an?lisis sem?ntico
     */
    public void syntacticAnalysis(Component c, SemanticAnalysis s, CustomTableModel t) {
        while (true) {
            try {
                row = getRowIndex();
                column = getColumnIndex(c.getToken());
                cell = TABLE[row][column];

                rowTable[0] = getStackStatus(stack);
                rowTable[1] = c.getToken();

                if (cell.equals("")) {
                    rowTable[2] = "ERROR";
                    rowTable[3] = getStackStatus(semanticStack);
                    rowTable[4] = getStackStatus(operators);
                    rowTable[5] = getStackStatus(postfixNotation);

                    error = getErrorMessage(row, c);
                    t.addRow(rowTable);
                    return;
                }

                if (cell.equals("acc")) {
                    rowTable[2] = "Aceptado";
                    rowTable[3] = getStackStatus(semanticStack);
                    rowTable[4] = getStackStatus(operators);
                    rowTable[5] = getStackStatus(postfixNotation);
                    t.addRow(rowTable);
                    return;
                }

                act = cell.charAt(0);
                cell = cell.substring(1, cell.length());

                if (act == 'r') {
                    int pro = Integer.parseInt(cell);
                    int cont = 0, elements, tope;

                    rowTable[2] = "Reducir " + NO_TERMINALS[pro] + " -> " + PRODUCTIONS[pro];
                    rowTable[3] = getStackStatus(semanticStack);
                    rowTable[4] = getStackStatus(operators);
                    rowTable[5] = getStackStatus(postfixNotation);;

                    StringTokenizer st = new StringTokenizer(PRODUCTIONS[pro], " ");
                    String[] prods = new String[st.countTokens()];

                    while (st.hasMoreTokens()) {
                        prods[cont++] = st.nextToken();
                    }

                    elements = prods.length;

                    if (elements == 1 && prods[0].equals("")) {
                        elements = 0;
                    }

                    popStack(elements);
                    tope = Integer.parseInt(stack.peek());

                    stack.add(NO_TERMINALS[pro]);
                    stack.add(TABLE[tope][getColumnIndex(NO_TERMINALS[pro])]);

                    s.semanticStackProcess(c, semanticStack, operators, postfixNotation, prods);

                    t.addRow(rowTable);

                    if (s.getError() != null) {
                        return;
                    }

                    continue;
                }

                if (act == 's') {
                    rowTable[2] = "Desplazar: " + c.getToken() + " " + cell;
                    rowTable[3] = getStackStatus(semanticStack);
                    rowTable[4] = getStackStatus(operators);
                    rowTable[5] = getStackStatus(postfixNotation);;

                    stack.add(c.getToken());
                    stack.add(cell);
                    s.analysis(c, semanticStack, operators, postfixNotation);

                    t.addRow(rowTable);
                    return;
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                error = "Error sintactico";
                return;
            }
        }
    }

    /**
     * Determina si se encontr? un error sint?ctico.
     *
     * @return true si hay un error | false: si no hay error
     */
    public boolean isError() {
        return error != null;
    }

    /**
     * Devuelve el mensaje de error
     *
     * @return Mensaje de error
     */
    public String getError() {
        return error;
    }

    /**
     * Determina en que posici?n del arreglo se encuentra el token.
     *
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
     *
     * @return Valor de la cima de la pila.
     */
    private int getRowIndex() {
        return Integer.parseInt(stack.peek());
    }

    /**
     * Saca de la pila el doble de elementos de la producci?n
     *
     * @param pops n?mero de elementos de la producci?n
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
     * Retorna el mensaje de error sint?ctico dada la posici?n encontrada en la
     * cima de la pila.
     *
     * @param state cima de la pila
     * @param c objeto instanciado de la clase Component que contienen la
     * informaci?n del token.
     * @return
     */
    private String getErrorMessage(int state, Component c) {
        return "Error sint?ctico en la linea: " + c.getLine() + ". Se recibi? un " + c.getToken() + " y se esperaba un: " + ERROR_MESSAGES[state];
    }

    /**
     * Retorna el contenido actual de la pila
     *
     * @return Contenido de la pila.
     */
    private String getStackStatus(Stack<String> stack) {
        String text = "";

        for (String string : stack) {
            text += string + " ";
        }

        return text;
    }

}
