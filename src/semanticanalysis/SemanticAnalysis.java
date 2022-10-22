package semanticanalysis;

import data.Component;
import data.ComponentsList;

/**
 * Clase que realiza el análisis semántico.
 * @author Juan José Silva López
 * @version 1.0
 */
public class SemanticAnalysis {

    private String[] DATA_TYPE_LIST = new String[]{"int", "float", "char"};
    
    private byte dataType;

    private String error;
    private ComponentsList list;
    private Component past;

    public SemanticAnalysis() {
        list = new ComponentsList();
    }

    /**
     * Ejecución del análisis semántico.
     * @param c Objeto de la clase Component que contiene el token a analizar.
     */
    public void analysis(Component c) {
        setDataType(c.getToken());
        
        if (dataType != -1 && c.getToken().equals("id")){
            c.setType(DATA_TYPE_LIST[dataType]);
            if (!list.addComponent(c)){
                error = "Error semántico linea: " + c.getLine() + ". La variable " + c.getName() + " ya fue declarada previamente.";
                return;
            }
        }

        if (c.getToken().equals("id") && !list.isAlreadyDeclared(c)) {
            error = "Error semántico linea: " + c.getLine() + ". La variable " + c.getName() + " no ha sido declarada.";
        }
    }

    /**
     * Determina el tipo de dato que se va a declarar o si se ha interrumpido la declaración de los mismos.
     * @param type Token recivido
     */
    private void setDataType(String type) {
        if (type.equals(",") || type.equals("id"))
            return;
        
        for (int i = 0; i < DATA_TYPE_LIST.length; i++) {
            if (type.equals(DATA_TYPE_LIST[i])){
                dataType = (byte) i;
                return;
            }
        }
        
        dataType = -1;
    }

    /**
     * Retorna el error semántico encontrado.
     * @return 
     */
    public String getError() {
        return error;
    }
    
    /**
     * Muestra el contenido de la tabla de símbolos.
     */
    public void showTableContent(){
        list.showListContent();
    }
}
