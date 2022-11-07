package semanticanalysis;

import data.Component;
import data.ComponentsList;
import java.util.Stack;

/**
 * Clase que realiza el an�lisis sem�ntico.
 *
 * @author Juan Jos� Silva L�pez
 * @version 1.0
 */
public class SemanticAnalysis {

    private final String[] DATA_TYPE_LIST = new String[]{"int", "float", "char"};
    private final String[] MATH_OPERATORS = new String[]{"+", "-", "*", "/"};
    private final int[][] OPERATION_TABLE = new int[][]{new int[]{0, 1, -1}, new int[]{1, 1, -1}, new int[]{0, 1, -1}};
    private final boolean[][] ASIGNATION_TABLE = new boolean[][]{new boolean[]{true, false, false}, new boolean[]{true, false, false}, new boolean[]{false, false, true}};

    private byte dataType;

    private String error;
    private ComponentsList list;
    private Component past, var;

    public SemanticAnalysis() {
        list = new ComponentsList();
    }

    /**
     * Ejecuci�n del an�lisis sem�ntico.
     *
     * @param c Objeto de la clase Component que contiene el token a analizar.
     */
    public void analysis(Component c, Stack<String> semanticStack, String[] prods) {
        setDataType(c.getToken());

        if (dataType != -1 && c.getToken().equals("id")) {
            c.setType(DATA_TYPE_LIST[dataType]);
            if (!list.addComponent(c)) {
                error = "Error sem�ntico linea: " + c.getLine() + ". La variable " + c.getName() + " ya fue declarada previamente.";
                return;
            }
        }

        if (c.getToken().equals("id") && !list.isAlreadyDeclared(c)) {
            error = "Error sem�ntico linea: " + c.getLine() + ". La variable " + c.getName() + " no ha sido declarada.";
            return;
        }
        
        past = c;
        
        if (c.getToken().equals("=") && past.getToken().equals("id")){
            var = past;
        }

    }
    
    /**
     * Determina el tipo de dato que se va a declarar o si se ha interrumpido la
     * declaraci�n de los mismos.
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
     * Retorna el error sem�ntico encontrado.
     *
     * @return
     */
    public String getError() {
        return error;
    }

    /**
     * Muestra el contenido de la tabla de s�mbolos.
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
    
    private int getOperationTableValue(String first, String second){
        try {
            int row, column;
            row = getDataTypePosition(first);
            column = getDataTypePosition(second);
            return OPERATION_TABLE[row][column];
        } catch (ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

}
