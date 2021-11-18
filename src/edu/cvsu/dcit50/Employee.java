package edu.cvsu.dcit50;

/**
 *
 * @author rlvillacarlos
 */
public class Employee extends User implements Rateable{
    public final String id;
    private int rating;
    private int raterCount;

    public Employee(String id, String name, String email) {
        super(name, email);
        this.id = id;
    }
    
    @Override
    public void addRating(int rate) {
        this.rating += rate;
        this.raterCount++;
    }

    @Override
    public String getRatee() {
        return this.getName();
    }

    
    @Override
    public int getRaterCount() {
        return this.raterCount; 
    }

    @Override
    public int getTotalRating() {
        return this.rating;
    }
}
