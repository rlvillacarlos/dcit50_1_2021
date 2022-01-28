package edu.cvsu.dcit50.expression;

/**
 *
 * @author Acer
 */
public class Variable extends Expression{
    private final String name;
    private Integer value;

    public Variable(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
    
    public Variable(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }
    
    public void setValue(Integer value) {
        this.value = value;
    }
    
    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}
