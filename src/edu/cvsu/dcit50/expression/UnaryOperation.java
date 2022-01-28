package edu.cvsu.dcit50.expression;

/**
 *
 * @author Acer
 */
public abstract class UnaryOperation extends Expression{
    protected Expression operand;

    public UnaryOperation(Expression operand) {
        this.operand = operand;
    }

    public Expression getOperand() {
        return operand;
    }

    public void setOperand(Expression operand) {
        this.operand = operand;
    }
    
}
