package edu.cvsu.dcit50;

import java.util.Scanner;

/**
 *
 * @author rlvillacarlos
 */
public class RatingApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Employee emp1 = new Employee("2021-0-001", "Employee 1", "employee1@compnay.com");
        Service service1 = new Service("Service 1");
        
        showFeedbackForm(emp1);
        showFeedbackForm(emp1);
        showFeedbackForm(service1);
        showFeedbackForm(service1);
        
        showRating(emp1);
        showRating(service1);
    }
    
    private static void showFeedbackForm(Rateable rateable){
        Scanner in = new Scanner(System.in);
        
        System.out.printf("Please rate %s (1 - Lowest, 5 - Highest)%n ", rateable.getRatee());
        System.out.print("Rating: ");
        
        int r = in.nextInt();
        
        if(r >= 1 && r <= 5){
            rateable.addRating(r);
        }
        
        System.out.println("");
    }
    
    private static void showRating(Rateable rateable){
        System.out.printf("--Rating for %s--%n", rateable.getRatee());
        System.out.printf("\tTotal Rating: %d%n", rateable.getTotalRating());
        System.out.printf("\tNumber of Raters: %d%n", rateable.getRaterCount());
        System.out.printf("\tAverage Rating: %.2f%n%n", rateable.getAverageRating());
    }
}
