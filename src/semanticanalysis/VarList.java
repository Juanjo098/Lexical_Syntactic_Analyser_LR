/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semanticanalysis;

import data.Component;
import java.util.Stack;

/**
 *
 * @author juanj
 */
public class VarList extends Stack<Var> {

    private final String FLOAT_REGEX = "^\\-?[0-9]+\\.[0-9]+$";
    private final String INT_REGEX = "^\\-?[0-9]+$";

    public int addVar(String v, Component t) {
        int index;
        String value, type;

        if (t != null) {
            type = t.getType();
            value = t.getName();
        } else {
            value = v;
            type = determineType(v);
        }

        if ((index = alreadyExist(value, type)) != -1) {
            return index;
        }

        if ((index = findFreePosition(type)) != -1) {
            Var var = this.get(index);
            var.setValue(value);
            this.set(index, var);
            return index;
        }

        this.push(new Var("v" + (this.size() + 1), value, type, true));
        return this.size() - 1;
    }

    private String determineType(String v) {
        if (v.matches(INT_REGEX)) {
            return "int";
        }
        if (v.matches(FLOAT_REGEX)) {
            return "float";
        }
        return null;
    }

    public void changeState(String var) {
        for (int i = 0; i < this.size(); i++) {
            Var get = this.get(i);
            if (get.getVar().equals(var)) {
                get.setInUse(false);
                this.set(i, get);
            }
        }
    }
    
    public Var getVar(String id){
        for (Var v : this) {
            if (v.getVar().equals(id)){
                return v;
            }
        }
        return null;
    }

    public void freeAll() {
        for (int i = 0; i < this.size(); i++) {
            Var get = this.get(i);
            get.setInUse(false);
            this.set(i, get);
        }
    }

    private int alreadyExist(String value, String type) {
        for (int i = 0; i < this.size(); i++) {
            Var v = this.get(i);
            if (v.getValue().equals(value) && !v.isInUse() && v.getType().equals(type)) {
                return i;
            }
        }
        return -1;
    }

    private int findFreePosition(String type) {
        for (int i = 0; i < this.size(); i++) {
            Var get = this.get(i);
            if (!this.get(i).isInUse() && get.getType().equals(type)) {
                get.setInUse(true);
                this.set(i, get);
                return i;
            }
        }
        return -1;
    }
}
