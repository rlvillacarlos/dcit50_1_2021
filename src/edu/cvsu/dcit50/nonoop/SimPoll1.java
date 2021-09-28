package edu.cvsu.dcit50.nonoop;

import java.util.Scanner;

/**
 *
 * @author rlvillacarlos
 */
public class SimPoll1 {
    public static void main(String[] args) {
        String question;
        String choices[] = new String[2];
        int votes[] = new int[2];
        int choicesCount = 0;
        int currentVotes = 0;
        int maxVotes;
        int lead = -1;
        
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
                //Check if new choice has not been added before
                boolean isExisting = false;
                for (int i = 0; i < choicesCount; i++) {
                    if (choices[i].compareToIgnoreCase(newChoice) == 0) {
                        isExisting = true;
                        break;
                    }
                }
                
                //Add if new choice is not yet existing
                if(!isExisting){
                    //Resize the array if there are no space left
                    if(choicesCount == choices.length){
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
                }
            }
        }
        
        System.out.printf("Maximum votes:");
        maxVotes = in.nextInt();
        in.nextLine();
        
        while(currentVotes < maxVotes){
            System.out.println("\n\n--------POLL--------");
            System.out.println(question);
            System.out.println("Please select your choice (Choice not in the list will be added):");
            for(int i = 0; i < choicesCount; i++){
                System.out.printf("   [] %s%n",choices[i]);
            }
            System.out.print("Choice:");
            String choice = in.nextLine().trim();
            
            if(!choice.isBlank()){
                //Search if input is one of the choices and get its index
                int choiceIndex = -1;
                for (int i = 0; i < choicesCount; i++){
                    if(choices[i].equalsIgnoreCase(choice)){
                        choiceIndex = i;
                        break;
                    }
                }
                
                //If the choice is not found, add it to the array
                if(choiceIndex == -1){
                    //Resize the array if there are no space left
                    if (choicesCount == choices.length) {
                        String tmpChoices[] = new String[choicesCount * 2];
                        int tmpVotes[] = new int[choicesCount * 2];
                        System.arraycopy(choices, 0, tmpChoices, 0, choicesCount);
                        System.arraycopy(votes, 0, tmpVotes, 0, choicesCount);
                        choices = tmpChoices;
                        votes = tmpVotes;
                    }
                    //Add new choice
                    choices[choicesCount] = choice;
                    choiceIndex = choicesCount;
            
                    choicesCount++;
                }
                
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
        System.out.println("\n\n--------POLL RESULT--------");
        System.out.printf("Winner is %s%n", choices[lead]);
    }
}
