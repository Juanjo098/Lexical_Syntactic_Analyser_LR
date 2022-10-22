package semanticanalysis;

import data.Component;
import data.ComponentsList;

public class SemanticAnalysis {

    private byte dataType;

    private String token, error;
    private ComponentsList list;

    public SemanticAnalysis() {
        list = new ComponentsList();
    }
    
    public void analysis(Component c) {
        token = c.getToken();
        setDataType(token);
        
        
        if (dataType != -1 && token.equals("id")) {
            if (!list.addComponent(c)) {
                error = "Error semántico linea: " + c.getLine() + ". Ya hay un id con el nombre de " + c.getName() + " en la linea " + list.repitedPosition(c.getName());
            }
            list.showListContent();
            return;
        }
    }

    private void setDataType(String type) {
        if (type.equals("int")) {
            dataType = 0;
            return;
        }
        if (type.equals("float")) {
            dataType = 1;
            return;
        }
        if (type.equals("float")) {
            dataType = 2;
            return;
        }
        if (type.equals(",") | type.equals("id")) {
            return;
        }
        dataType = -1;
    }

    public String getError() {
        return error;
    }
}
