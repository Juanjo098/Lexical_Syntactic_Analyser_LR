package data;

import lexicalanalysis.Tokens;

public class Component {
    private int line;
    private Tokens type;
    private String token;
    private Object value;

    public Component(int line, Tokens type, String token, Object value) {
        this.line = line;
        this.type = type;
        this.token = token;
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public Tokens getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    public Object getValue() {
        return value;
    }
}
