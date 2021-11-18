package edu.cvsu.dcit50.diamond;

/**
 *
 * @author rlvillacarlos
 */
public class D implements B,C{
    int id;
    String name; 

    public D(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String getTag() {
        //Alternatively C.super.getTag()
        return "D"; //C.super.getTag();
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }
    
}
