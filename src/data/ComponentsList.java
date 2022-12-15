package data;

import java.util.ArrayList;

/**
 * Clase responsable de guardar los elementos de la tabla de símbolos.
 * @author Juan José Silva López
 * @author Arturo Guzmán Ávila
 * @version 1.0
 */
public class ComponentsList {

    private ArrayList<Component> componentsList;

    public ComponentsList() {
        componentsList = new ArrayList<>();
    }

    /**
     * A?adir el componente a la tabla.
     * @param c Componente a a?adir
     * @return true: si el componente se a?adió | false: si el componente no se a?adió
     */
    public boolean addComponent(Component c) {
        if (!isAlreadyDeclared(c)) {
            componentsList.add(c);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determina si el nombre del componente ya se encuentra almacenado en la tabla.
     * @param c Componente a comparar.
     * @return true: si el componente existe dentro de la tabla | false: si el componente no existe dentro de la tabla 
     */
    public boolean isAlreadyDeclared(Component c) {
        for (Component component : componentsList) {
            if (component.getName().equals(c.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determina en qué linea se encuentra el nombre del id repetido.
     * @param idName
     * @return Linea donde se declaró el identificador.
     */
    public int repitedPosition(String idName) {
        int line = 0;
        for (Component component : componentsList) {
            if (component.getName().equals(idName)) {
                line = component.getLine();
            }
        }
        return line;
    }

    /**
     * Muestra el contenido de la tabla de símbolos.
     */
    public void showListContent() {
        if (componentsList.size() != 0) {
            for (Component component : componentsList) {
                component.showInfo();
            }
        }
    }
    
    /**
     * Regresa el tipo de dato del id buscado.
     * @param c Componente buscando en la lista.
     * @return String con el tipo de dato del id buscado.
     */
    public String getIdType(Component c){
        for (Component component : componentsList) {
            if(c.getName().equals(component.getName()))
                return component.getType();
        }
        return null;
    }
    
    public Component getComponent(String name){
        for (Component component : componentsList) {
            if(component.getName().equals(name))
                return component;
        }
        return null;
    }
}
