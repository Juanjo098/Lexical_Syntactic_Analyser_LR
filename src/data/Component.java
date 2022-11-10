package data;

/**
 * Clase dedicada a guardar un elemento de la tabla de símbolos.
 * @author Juan José Silva López
 * @author Arturo Guzmán Ávila
 * 
 * @version 1.0
 */
public class Component {
    private int line;
    private String type, name, token;
    private Object value;

    /**
     * @param line Linea
     * @param type Tipo
     * @param name Nombre
     * @param token Tipo de token
     * @param value Valor
     */
    public Component(int line, String type, String name, String token, Object value) {
        this.line = line;
        this.type = type;
        this.name = name;
        this.token = token;
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public String getType() {
        return type;
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

    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Muesta el tipo, el token y el nombre de un elmemento de la tabla de símbolos.
     */
    public void showInfo(){
        System.out.println(type + "\t" + token + "\t" + name);
    }
}
