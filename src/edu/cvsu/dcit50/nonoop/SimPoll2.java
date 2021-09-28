package edu.cvsu.dcit50.nonoop;

import java.util.Scanner;

/**
 *
 * @author rlvillacarlos
 */
public class SimPoll2 {

    static String question = "";
    static String choices[] = new String[2];
    static int votes[] = new int[2];
    static int choicesCount = 0;
    static int currentVotes = 0;
    static int maxVotes;
    static int lead = -1;

    public static void main(String[] args) {
        setUpPoll();
        
        startPoll();
        
        showPollResult();
    }
    
    private static void setUpPoll() {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Poll question:");
        question = in.nextLine().trim();
        
        System.out.println("Choices: (min. 2)");
        String newChoice;
        
        while(true){
            System.out.printf("   Choice %d: ", choicesCount + 1);
            newChoice = in.nextLine().trim();
            
            if(newChoice.isBlank()){
                if(choicesCount >= 2){
                    break;                
                }
            }else{
                addChoice(newChoice);
            }
        }
        
        System.out.printf("Maximum votes:");
        maxVotes = in.nextInt();
        in.nextLine();
    }

    private static void startPoll() {
        Scanner in = new Scanner(System.in);
        
        while(currentVotes < maxVotes){
            printPoll();
            
            System.out.printf("Choice:");
            String choice = in.nextLine().trim();
            
            if(!choice.isBlank()){
                //Try to add the given choice just in case it is not existing
                addChoice(choice);
                
                //Search the index of the given choice
                int choiceIndex = findChoice(choice);
                
                //Increment the vote of the selected choice
                votes[choiceIndex]++;
                
                //Increment the number of casted votes
                currentVotes++;
                
                //Update the index of the choice with highest votes
                if(lead == -1 || votes[choiceIndex] > votes[lead]){
                    lead = choiceIndex;
                }
            }
        }
    }

    private static void showPollResult() {
        System.out.println("\n\n--------POLL RESULT--------");
        System.out.printf("Winner is %s%n", choices[lead]);
    }

    private static boolean addChoice(String newChoice) {
        
        //Check if new choice has not been added before
        for (int i = 0; i < choicesCount; i++) {
            if (choices[i].compareToIgnoreCase(newChoice) == 0) {
                return false;
            }
        }

        //Resize choices array if full
        if (choicesCount == choices.length) {
            String tmpChoices[] = new String[choicesCount * 2];
            int tmpVotes[] = new int[choicesCount * 2];
            System.arraycopy(choices, 0, tmpChoices, 0, choicesCount);
            System.arraycopy(votes, 0, tmpVotes, 0, choicesCount);
            choices = tmpChoices;
            votes = tmpVotes;
        }
        
        //Add new choice
        choices[choicesCount] = newChoice;
        choicesCount++;
         
        return true;
    }
    
    private static int findChoice(String choice) {
        //Find the index of the given choice
        for (int i = 0; i < choicesCount; i++) {
            if (choices[i].compareToIgnoreCase(choice) == 0) {
                return i;
            }
        }
        
        return -1;
    }
    
    private static void printPoll(){
        System.out.println("\n\n--------POLL--------");
        System.out.println(question);
        System.out.println("Please select your choice (Choice not in the list will be added):");
        for (int i = 0; i < choicesCount; i++) {
            System.out.printf("   [] %s%n", choices[i]);
        }
    }
    
}
