package edu.cvsu.dcit50.expression;

/**
 *
 * @author Acer
 */
public class Constant extends Expression{
    private final Integer value;

    public Constant(Integer value) {
        this.value = value;
    }
    
    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
    
}
