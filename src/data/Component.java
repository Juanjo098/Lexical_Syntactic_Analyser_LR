package data;

import lexicalanalysis.Tokens;

public class Component {
    private int line;
    private String name, token;
    private Object value;

    public Component(int line, String name, String token, Object value) {
        this.line = line;
        this.name = name;
        this.token = token;
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public Object getValue() {
        return value;
    }
    
}
