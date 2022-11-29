package semanticanalysis;

import data.Component;
import data.ComponentsList;
import java.util.Stack;

/**
 * Clase que realiza el an�lisis sem�ntico.
 *
 * @author Juan Jos� Silva L�pez
 * @author Arturo Guzm�n �vila
 * @version 1.0
 */
public class SemanticAnalysis {

    private final int[] OPERATOR_PRIORITY = new int[]{0, 0, 1, 1};
    private final String[] DATA_TYPE_LIST = new String[]{"int", "float", "char"};
    private final String[] MATH_OPERATORS = new String[]{"+", "-", "*", "/"};
    private final int[][] OPERATION_TABLE = new int[][]{new int[]{0, 1, -1}, new int[]{1, 1, -1}, new int[]{-1, -1, -1}};
    private final boolean[][] ASIGNATION_TABLE = new boolean[][]{new boolean[]{true, false, false}, new boolean[]{true, true, false}, new boolean[]{false, false, true}};
    private final String FLOAT_REGEX = "^\\-?[0-9]+\\.[0-9]+$";
    private final String INT_REGEX = "^\\-?[0-9]+$";
    private String midCode;

    private byte dataType;

    private String error;
    private ComponentsList list;
    private Component past, var;
    private VarList varList;

    public SemanticAnalysis() {
        list = new ComponentsList();
        varList = new VarList();
        midCode = "";
    }

    /**
     * Ejecuci�n del an�lisis sem�ntico.
     *
     * @param c Objeto de la clase Component que contiene el token a analizar.
     */
    public void analysis(Component c, Stack<String> semanticStack) {
        setDataType(c.getToken());

        if (dataType != -1 && c.getToken().equals("id")) {
            c.setType(DATA_TYPE_LIST[dataType]);
            if (!list.addComponent(c)) {
                error = "Error sem�ntico linea: " + c.getLine() + ". La variable " + c.getName() + " ya fue declarada previamente.";
            }
            midCode += DATA_TYPE_LIST[dataType] + " " + c.getName() + ";\n";
            past = c;
            return;
        }

        if (c.getToken().equals("id") && !list.isAlreadyDeclared(c)) {
            error = "Error sem�ntico linea: " + c.getLine() + ". La variable " + c.getName() + " no ha sido declarada.";
            past = c;
            return;
        }

        if (dataType == -1 && c.getToken().equals("id")) {
            semanticStack.push(list.getIdType(c));
            if (var != null) {
                int index;
                if ((index = varList.addVar(c.getName())) != -1) {
                    midCode += varList.get(index).getVar() + " = " + varList.get(index).getValue() + ";\n";
                }
            }
            past = c;
            return;
        }

        if (c.getToken().equals("num")) {

            if (c.getName().matches(INT_REGEX)) {
                semanticStack.push("int");
            }
            if (c.getName().matches(FLOAT_REGEX)) {
                semanticStack.push("float");
            }
            int index;
            
            if ((index = varList.addVar(c.getName())) != -1) {
                midCode += varList.get(index).getVar() + " = " + varList.get(index).getValue() + ";\n";
            }
            
            past = c;
            return;
        }

        if (c.getToken().equals("=") && past.getToken().equals("id")) {
            var = past;
            return;
        }

        if (c.getName().equals(";") && (var != null)) {
            midCode += var.getName() + " = v2;";
            var = null;
            past = c;
            return;
        }

    }

    /**
     * Proceso para realizar operaciones arit�ticas empleando la pila sem�ntica
     *
     * @param c componente recibido.
     * @param semanticStack pila de an�lisis sem�ntico
     * @param prod producciones a reducir
     */
    public void stackProcess(Component c, Stack<String> semanticStack, Stack<String> operators, String[] prod) {
        if (dataType == -1) {
            byte op = getOperation(prod);
            String operator = getOperator(prod);
            // op = 0: Asignaci�n
            // op = 0: Operaci�n arim�tica

            if (op != -1) {
                operatorsProcess(operator, operators);
            }

            switch (op) {
                case 0:
                    if (!getAssigntionType(semanticStack)) {
                        error = "Error semantico en la linea " + c.getLine() + ". No se puede asignar.";
                    }
                    return;
                case 1:
                    String result = getResultType(semanticStack);
                    semanticStack.push(result);

                    if (semanticStack.peek() == null) {
                        error = "Error semantico en la linea " + c.getLine() + ". Tipos de datos incompatibles.";
                    }
                    return;
            }
        }
    }

    /**
     * Determina el tipo de operaci�n a realizar
     *
     * @param prods producciones a reducir
     * @return 0: Asignaci�n | 1: Operacci�n matem�tica | -1: No se realiza
     * movimiento en la pila sem�ntica
     */
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
     * Determina si se detect� un error sem�ntico.
     *
     * @return true: se encontr� error | false: no se encontr� error
     */
    public boolean isError() {
        return error != null;
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

    /**
     * Determina a que posici�n del arreglo corresponde el tipo de dato
     * ingresado
     *
     * @param t Tipo de dato
     * @return Posici�n de tipo de dato
     */
    private int getDataTypePosition(String t) {
        for (int i = 0; i < DATA_TYPE_LIST.length; i++) {
            if (t.equals(DATA_TYPE_LIST[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Determina el resultado de una operaci�n aritm�tica entre los dos �ltimos
     * elementos de la pila sem�ntica
     *
     * @param semanticStack
     * @return
     */
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

    /**
     * Determina si se puede realizar una asignaci�n entre los dos �ltimos
     * elementos de la pila sem�ntica
     *
     * @param semanticStack
     * @return true: se puede asignar | false: si no se puede asignar
     */
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

    private String getOperator(String prods[]) {
        for (String prod : prods) {
            if (prod.equals("=")) {
                return prod;
            }
            for (String mo : MATH_OPERATORS) {
                if (prod.equals(mo)) {
                    return prod;
                }
            }
        }
        return null;
    }

    private int getOperatorPriority(String op) {
        for (int i = 0; i < MATH_OPERATORS.length; i++) {
            if (MATH_OPERATORS[i].equals(op)) {
                return OPERATOR_PRIORITY[i];
            }
        }
        return -1;
    }

    private void operatorsProcess(String op, Stack<String> operators) {
        if (operators.peek().equals("$")) {
            operators.push(op);
            return;
        }

        if (op.equals("=")) {
            while (!operators.peek().equals("$")) {
                operators.pop();
            }
            return;
        }

        if (getOperatorPriority(operators.peek()) >= getOperatorPriority(op)) {
            operators.pop();
            operators.push(op);
            return;
        }
    }

    public String getMidCode() {
        return midCode;
    }

}
