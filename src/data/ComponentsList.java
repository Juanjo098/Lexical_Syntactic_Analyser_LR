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
            if (component.getName().equals(c.getName()))
                return true;
        }
        return false;
    }
    
    public int repitedPosition(String idName){
        for (Component component : componentsList) {
            if (component.getName().equals(idName))
                return component.getLine();
        }
        return -1;
    }
    
    public void showListContent(){
        if (componentsList.size() != 0)
        for (Component component : componentsList) {
            System.out.print(component.getName() + " ");
        }
        System.out.println("");
    }
}
