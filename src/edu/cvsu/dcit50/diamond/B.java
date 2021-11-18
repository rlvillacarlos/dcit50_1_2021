package edu.cvsu.dcit50.diamond;

/**
 *
 * @author rlvillacarlos
 */
public interface B extends A{

    int getID();
    
    @Override
    default String getTag(){
        return "B";
    }
    
}
