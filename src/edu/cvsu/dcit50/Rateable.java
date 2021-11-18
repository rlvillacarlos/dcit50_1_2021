package edu.cvsu.dcit50;

/**
 *
 * @author rlvillacarlos
 */
public interface Rateable {
    String getRatee();
    
    void addRating(int rate);
    
    int getRaterCount();
    
    int getTotalRating();
    
    default float getAverageRating(){
        return this.getTotalRating() / (float)this.getRaterCount();
    }
}
