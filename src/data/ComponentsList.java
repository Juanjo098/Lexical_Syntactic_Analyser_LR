package data;

import java.util.ArrayList;

public class ComponentsList {
    private ArrayList<Component> componentsList;

    public ComponentsList() {
        componentsList = new ArrayList<>();
    }
    
    public boolean addComponent(Component c){
        if (!isRepited(c)){
            componentsList.add(c);
            return true;
        }
        else
            return false;
    }
    
    private boolean isRepited(Component c){
        for (Component component : componentsList) {
            if (component.getToken().equals(c.getToken()))
                return true;
        }
        return false;
    }
}
