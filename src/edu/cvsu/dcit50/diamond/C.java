package edu.cvsu.dcit50.diamond;

/**
 *
 * @author rlvillacarlos
 */
public interface C extends A{

    String getName();
    
    @Override
    default String getTag(){
        return "C";
    }
    
}
