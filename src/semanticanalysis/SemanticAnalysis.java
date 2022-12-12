package semanticanalysis;

import data.Component;
import data.ComponentsList;
import java.util.Stack;

/**
 * Clase que realiza el análisis semántico.
 *
 * @author Juan José Silva López
 * @author Arturo Guzmán Ávila
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
    private final String STRING_REGEX = "^\\\".*\\\"$";
    private boolean isProgram, isSentence, isPrint;
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
     * Ejecución del análisis semántico.
     *
     * @param c Objeto de la clase Component que contiene el token a analizar.
     */
    public void analysis(Component c, Stack<String> semanticStack, Stack<String> operators, Stack<String> postfixNotation) {
        setDataType(c.getToken());
        setProgramState(c.getToken());
        setSentenceState(c.getToken());
        setPrintState(c.getToken());

        if (c.getToken().equals("program")) {
            midCode += c.getName() + " ";
            return;
        }
        
        if (c.getToken().equals("endProgram")) {
            midCode += c.getName();
            return;
        }
        
        if (c.getToken().equals("id") && isProgram) {
            midCode += c.getName() + "\n";
            isProgram = false;
            return;
        }
        
        if (dataType != -1 && c.getToken().equals("id")) {
            c.setType(DATA_TYPE_LIST[dataType]);
            if (!list.addComponent(c)) {
                error = "Error semántico linea: " + c.getLine() + ". La variable " + c.getName() + " ya fue declarada previamente.";
            }
            midCode += DATA_TYPE_LIST[dataType] + " " + c.getName() + ";\n";
            past = c;
            return;
        }

        if (c.getToken().equals("id") && !list.isAlreadyDeclared(c)) {
            error = "Error semántico linea: " + c.getLine() + ". La variable " + c.getName() + " no ha sido declarada.";
            past = c;
            return;
        }
        
        if (c.getToken().equals("print")) {
            return;
        }
        
        if (c.getToken().equals("if") || c.getToken().equals("while")) {
            midCode += c.getName() + " ";
            return;
        }

        if (isSentence) {
            if (c.getToken().equals(")")) {
                midCode += c.getName() + "\n";
                isProgram = false;
            }
            midCode += c.getName() + " ";
            return;
        }

        if (isPrint) {
            if (c.getToken().equals(")")) {
                isPrint = false;
                return;
            }
            if (c.getToken().equals("id") || c.getToken().equals("num")){
                midCode += "print(" + c.getName() + ");\n";
            }
            return;
        }

        if (c.getToken().equals(";") && postfixNotation.size() > 1) {
            if (!getAssigntionType(semanticStack)) {
                error = "Error semantico en la linea " + c.getLine() + ". No se puede asignar.";
                return;
            }
            while (!operators.peek().equals("$")) {
                postfixNotation.push(operators.pop());
            }
            generateMidcode(postfixNotation);
            return;
        }

        if (dataType == -1 && c.getToken().equals("id")) {
            semanticStack.push(list.getIdType(c));
            postfixNotation.add(c.getName());
            past = c;
            return;
        }

        if (var != null && c.getToken().equals("id")) {
            past = c;
            postfixNotation.add(c.getName());
            return;
        }

        if (c.getToken().equals("num")) {

            if (c.getName().matches(INT_REGEX)) {
                semanticStack.push("int");
            }
            if (c.getName().matches(FLOAT_REGEX)) {
                semanticStack.push("float");
            }
            
            postfixNotation.add(c.getName());
            past = c;
            return;
        }

        if (c.getToken().equals("=") && past.getToken().equals("id")) {
            past = c;
            var = past;
            return;
        }

        past = c;
        switch (isMathOperator(c.getToken())) {
            case 0:
                if (operators.peek().equals("(") || operators.peek().equals("$")) {
                    operators.push(c.getToken());
                    return;
                }

                while (!(operators.peek().equals("(") || operators.peek().equals("$"))) {
                    if (getOperatorPriority(operators.peek()) >= getOperatorPriority(c.getToken())) {
                        postfixNotation.push(operators.pop());
                        continue;
                    }
                    operators.push(c.getToken());
                    return;
                }
            case 1:
                operators.add(c.getToken());
                return;
            case 2:
                String peak;
                while (true) {
                    peak = operators.pop();

                    if (peak.equals("$")) {
                        error = "Error sintáctico en la línea " + c.getLine() + ". Falta paréntesis que abre.";
                        return;
                    }

                    if (!peak.equals("(")) {
                        postfixNotation.push(peak);
                        continue;
                    }

                    return;
                }
            case 3:
                String top3;
                while (!operators.peek().equals("$")) {
                    top3 = operators.pop();
                    if (top3.equals("(")) {
                        error = "Error sintáctico en la línea " + c.getLine() + ". Falta un paréntesis que cierra.";
                        return;
                    }
                    postfixNotation.push(top3);
                }
        }
    }

    private void setProgramState(String t) {
        if (t.equals("program")) {
            isProgram = true;
        }
    }

    private void setSentenceState(String t) {
        if (t.equals("if") || t.equals("while")) {
            isSentence = true;
        }
    }

    private void setPrintState(String t) {
        if (t.equals("print")) {
            isPrint = true;
        }
    }

    private void generateMidcode(Stack<String> postfixNotation) {
        int index;
        String peek, assing, tmps = "";
        Stack<String> tmp = new Stack<>(), op = new Stack<>();
        tmp.push("$");
        while (!postfixNotation.peek().equals("$")) {
            tmp.push(postfixNotation.pop());
        }
        assing = tmp.pop();

        if (tmp.size() == 2) {
            midCode += assing + " = " + tmp.pop() + ";\n";
            return;
        }

        while (!tmp.peek().equals("$")) {
            peek = tmp.pop();
            if (isMathOperator(peek) != 0) {
                index = varList.addVar(peek);
                op.push(varList.get(index).getVar());
                midCode += varList.get(index).getVar() + " = " + peek + ";\n";
                continue;
            }
            String value1 = op.pop();
            String value2 = op.pop();
            varList.changeState(value1);
            op.push(value2);
            midCode += value2 + " = " + value2 + " " + peek + " " + value1 + ";\n";
        }

        varList.freeAll();
        midCode += assing + " = " + op.pop() + ";\n";
        var = null;
    }

    /**
     * Proceso para realizar operaciones aritéticas empleando la pila semántica
     *
     * @param c componente recibido.
     * @param semanticStack pila de análisis semántico
     * @param prod producciones a reducir
     */
    public void semanticStackProcess(Component c, Stack<String> semanticStack, Stack<String> operators, Stack<String> postfixNotation, String[] prod) {
        if (dataType == -1) {
            byte op = getOperation(prod);
            String operator = getOperator(prod);
            // op = 0: Asignación
            // op = 1: Operación arimética

            switch (op) {
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
     * Determina el tipo de operación a realizar
     *
     * @param prods producciones a reducir
     * @return 0: Asignación | 1: Operacción matemática | -1: No se realiza
     * movimiento en la pila semántica
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
     * Determina si se detectó un error semántico.
     *
     * @return true: se encontró error | false: no se encontró error
     */
    public boolean isError() {
        return error != null;
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

    /**
     * Determina a que posición del arreglo corresponde el tipo de dato
     * ingresado
     *
     * @param t Tipo de dato
     * @return Posición de tipo de dato
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
     * Determina el resultado de una operación aritmética entre los dos últimos
     * elementos de la pila semántica
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
     * Determina si se puede realizar una asignación entre los dos últimos
     * elementos de la pila semántica
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

    private void operatorsProcess(String op, Stack<String> operators, Stack<String> postfixNotation) {

    }

    private byte isMathOperator(String token) {
        for (String mo : MATH_OPERATORS) {
            if (token.equals(mo)) {
                return 0;
            }
        }
        if (token.equals("(")) {
            return 1;
        }
        if (token.equals(")")) {
            return 2;
        }
        if (token.equals(";")) {
            return 3;
        }

        return -1;
    }

    public String getMidCode() {
        return midCode;
    }

    public String getDeclarations() {
        String tmps = "";
        for (Var v : varList) {
            tmps += "#declare " + v.getVar() + ";\n";
        }
        return tmps;
    }

}
