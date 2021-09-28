package edu.cvsu.dcit50;

import java.util.Scanner;

/**
 *
 * @author rlvillacarlos
 */
public class SimPoll {
    
    
    public static void main(String[] args) {
        Poll poll = new Poll();
        
        setUpPoll(poll);
        
        startPoll(poll);
        
        showPollResult(poll);
    }
    
    private static void setUpPoll(Poll poll) {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Poll question:");
        poll.setQuestion(in.nextLine().trim());
        
        System.out.println("Choices: (min. 2)");
        String newChoice;
        
        while(true){
            System.out.printf("   Choice %d: ", poll.getChoicesCount() + 1);
            newChoice = in.nextLine().trim();
            
            if(newChoice.isBlank()){
                if(poll.isReady()){
                    break;                
                }
            }else{
                poll.addChoice(newChoice);
            }
        }
        
        System.out.printf("Maximum votes:");
        poll.setMaxVotes(in.nextInt());
        in.nextLine();
    }

    private static void startPoll(Poll poll) {
        Scanner in = new Scanner(System.in);
        
        while(poll.isActive()){
            printPoll(poll);
            
            System.out.print("Choice: ");
            String choice = in.nextLine().trim();
            
            //Create new choice and select it
            if(!choice.isBlank()){
                //Try to add the choice in case it is not yet existing
                poll.addChoice(choice);
            }
            
            poll.vote(choice);
        }
    }

    private static void showPollResult(Poll poll) {
        System.out.println("\n\n--------POLL RESULT--------");
        System.out.printf("Winner is %s%n", poll.getWinner());
    }

    private static void printPoll(Poll poll){
        System.out.println("\n\n--------POLL--------");
        System.out.println(poll.getQuestion());
        System.out.println("Please select your choice (Choice not in the list will be added):");
        for (String choice: poll.getChoices()) {
            System.out.printf("   [] %s%n", choice);
        }
    }
    
}
