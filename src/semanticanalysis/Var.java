package semanticanalysis;

import java.util.LinkedList;

/**
 *
 * @author juanj
 */
public class Var{
    private String var, value;
    private boolean inUse;

    public Var(String var, String value, boolean inUse) {
        this.var = var;
        this.value = value;
        this.inUse = inUse;
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
