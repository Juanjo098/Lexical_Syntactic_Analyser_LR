package semanticanalysis;

import data.Component;
import data.ComponentsList;
import java.util.Stack;

/**
 * Clase que realiza el análisis semántico.
 *
 * @author Juan José Silva López
 * @version 1.0
 */
public class SemanticAnalysis {

    private final String[] DATA_TYPE_LIST = new String[]{"int", "float", "char"};
    private final String[] MATH_OPERATORS = new String[]{"+", "-", "*", "/"};
    private final int[][] OPERATION_TABLE = new int[][]{new int[]{0, 1, -1}, new int[]{1, 1, -1}, new int[]{-1, -1, -1}};
    private final boolean[][] ASIGNATION_TABLE = new boolean[][]{new boolean[]{true, false, false}, new boolean[]{true, true, false}, new boolean[]{false, false, true}};

    private byte dataType;

    private String error;
    private ComponentsList list;
    private Component past, var;

    public SemanticAnalysis() {
        list = new ComponentsList();
    }

    /**
     * Ejecución del análisis semántico.
     *
     * @param c Objeto de la clase Component que contiene el token a analizar.
     */
    public void analysis(Component c, Stack<String> semanticStack) {
        setDataType(c.getToken());

        if (dataType != -1 && c.getToken().equals("id")) {
            c.setType(DATA_TYPE_LIST[dataType]);
            if (!list.addComponent(c)) {
                error = "Error semántico linea: " + c.getLine() + ". La variable " + c.getName() + " ya fue declarada previamente.";
                return;
            }
        }

        if (c.getToken().equals("id") && !list.isAlreadyDeclared(c)) {
            error = "Error semántico linea: " + c.getLine() + ". La variable " + c.getName() + " no ha sido declarada.";
            return;
        }

        if (dataType == -1 && c.getToken().equals("id")) {
            semanticStack.push(list.getIdType(c));
        }

        past = c;

        if (c.getToken().equals("=") && past.getToken().equals("id")) {
            var = past;
            return;
        }

    }

    public void stackProcess(Component c, Stack<String> semanticStack, String[] prod) {
        if (dataType == -1) {
            byte op = getOperation(prod);

            switch (op) {
                case 0:
                    if (!getAssigntionType(semanticStack)){
                        error = "Error semantico en la linea " + c.getLine() + ". No se puede asignar.";
                    }
                    return;
                case 1:
                    String result = getResultType(semanticStack);
                    semanticStack.push(result);
                    
                    if (semanticStack.peek() == null){
                        error = "Error semantico en la linea " + c.getLine() + ". Tipos de datos incompatibles.";
                    }
                    return;
            }
        }
    }

    public byte getOperation(String[] prods) {
        for (String prod : prods) {
            if (prod.equals("=")) {
                return 0;
            }
        }

        for (String prod : prods) {
            for (String op : MATH_OPERATORS) {
                if (prod.equals(op)) {
                    return 1;
                }
            }
        }

        return -1;

    }

    /**
     * Determina el tipo de dato que se va a declarar o si se ha interrumpido la
     * declaración de los mismos.
     *
     * @param type Token recivido
     */
    private void setDataType(String type) {
        if (type.equals(",") || type.equals("id")) {
            return;
        }

        for (int i = 0; i < DATA_TYPE_LIST.length; i++) {
            if (type.equals(DATA_TYPE_LIST[i])) {
                dataType = (byte) i;
                return;
            }
        }

        dataType = -1;
    }

    /**
     * Retorna el error semántico encontrado.
     *
     * @return
     */
    public String getError() {
        return error;
    }

    /**
     * Muestra el contenido de la tabla de símbolos.
     */
    public void showTableContent() {
        list.showListContent();
    }

    private int getDataTypePosition(String t) {
        for (int i = 0; i < DATA_TYPE_LIST.length; i++) {
            if (t.equals(DATA_TYPE_LIST[i])) {
                return i;
            }
        }
        return -1;
    }

    private String getResultType(Stack<String> semanticStack) {
        try {
            int row, column;
            row = getDataTypePosition(semanticStack.pop());
            column = getDataTypePosition(semanticStack.pop());
            return DATA_TYPE_LIST[OPERATION_TABLE[row][column]];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private boolean getAssigntionType(Stack<String> semanticStack) {
        try {
            int row, column;
            column = getDataTypePosition(semanticStack.pop());
            row = getDataTypePosition(semanticStack.pop());
            return ASIGNATION_TABLE[row][column];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

}
