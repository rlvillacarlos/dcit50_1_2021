/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cvsu.dcit50.expression;

/**
 *
 * @author Acer
 */
public class Multiplication extends BinaryOperation{

    public Multiplication(Expression leftOperand, Expression rightOperand) {
        super(leftOperand, rightOperand);
    }
    
    @Override
    public Integer getValue() {
        Integer l = this.getLeftOperand().getValue();
        Integer r = this.getRightOperand().getValue();
        Integer v = null;
        
        if(!(l == null || r == null)){
            v = l * r;
        }
        
        return v;
    }

    @Override
    public String toString() {
        return "(" + this.getLeftOperand() + "*" + this.getRightOperand() + ")";
    }
    
}
