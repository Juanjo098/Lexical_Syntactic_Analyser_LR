package semanticanalysis;

import java.util.LinkedList;

/**
 *
 * @author juanj
 */
public class Var{
    private String var, value, type;
    private boolean inUse;

    public Var(String var, String value, String type, boolean inUse) {
        this.var = var;
        this.value = value;
        this.type = type;
        this.inUse = inUse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

}
