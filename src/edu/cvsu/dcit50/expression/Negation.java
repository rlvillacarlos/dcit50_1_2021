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
public class Negation extends UnaryOperation{

    public Negation(Expression operand){
        super(operand);
    }
    
    @Override
    public Integer getValue() {
       Integer operandValue = this.operand.getValue();
       
       if(operandValue != null){
           operandValue = -operandValue;
       }
       
       return operandValue;
    }

    @Override
    public String toString() {
        String operandString = this.operand.toString();
        
        if(operandString.startsWith("-")){
            return operandString.substring(1);
        }
        return "-" + this.operand.toString();
    }
    
}
