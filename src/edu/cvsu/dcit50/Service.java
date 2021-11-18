package edu.cvsu.dcit50;

/**
 *
 * @author rlvillacarlos
 */
public class Service implements Rateable{
    private final String name;
    private boolean available;
    private int rating;
    private int raterCount;

    public Service(String name) {
        this.name = name;
        this.available = true;
    }

    public boolean isAvailable() {
        return this.available;
    }

    @Override
    public String getRatee() {
        return this.name;
    }
    
    @Override
    public void addRating(int rate) {
        this.rating += rate;
        this.raterCount++;
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
