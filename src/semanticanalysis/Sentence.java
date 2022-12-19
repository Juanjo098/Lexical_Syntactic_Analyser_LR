/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semanticanalysis;

/**
 *
 * @author juanj
 */
public class Sentence {
    private boolean closed;
    private int number;
    private String type;

    public Sentence(boolean closed, int number, String type) {
        this.closed = closed;
        this.number = number;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
