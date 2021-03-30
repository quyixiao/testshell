package tsh.entity;

public class TVar {
    private String name;
    private Object value;
    private String kind;

    public TVar() {

    }

    public TVar(String name) {
        this.name = name;
    }

    public TVar(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public TVar(String name, Object value, String kind) {
        this.name = name;
        this.value = value;
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", kind='" + kind + '\'' +
                '}';
    }
}
