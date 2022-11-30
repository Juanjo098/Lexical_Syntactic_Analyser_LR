/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semanticanalysis;

import java.util.Stack;

/**
 *
 * @author juanj
 */
public class VarList extends Stack<Var> {

    public int addVar(String value) {
        int index;
        
        if ((index = alreadyExist(value)) != -1) {
            return index;
        }
        
        if ((index = findFreePosition()) != -1) {
            Var var = this.get(index);
            var.setValue(value);
            this.set(index, var);
            return index;
        }

        this.push(new Var("v" + (this.size() + 1), value, true));
        return this.size() - 1;
    }

    public void changeState(String var){
        for (int i = 0; i < this.size(); i++) {
            Var get = this.get(i);
            if (get.getVar().equals(var)){
                get.setInUse(false);
                this.set(i, get);
            }
        }
    }
    
    public void freeAll(){
        for (int i = 0; i < this.size(); i++) {
            Var get = this.get(i);
            get.setInUse(false);
            this.set(i, get);
        }
    }
    
    private int alreadyExist(String value) {
        for (int i = 0; i < this.size(); i++) {
            Var v = this.get(i);
            if (v.getValue().equals(value) && !v.isInUse()) {
                return i;
            }
        }
        return -1;
    }

    private int findFreePosition() {
        for (int i = 0; i < this.size(); i++) {
            Var get = this.get(i);
            if (!this.get(i).isInUse()) {
                get.setInUse(true);
                this.set(i, get);
                return i;
            }
        }
        return -1;
    }
}
