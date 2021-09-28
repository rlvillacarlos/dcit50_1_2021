package edu.cvsu.dcit50;

/**
 *
 * @author rlvillacarlos
 */
public class Poll {
    String question = "";
    String choices[] = new String[2];
    int votes[] = new int[2];
    int choicesCount = 0;
    int currentVotes = 0;
    int maxVotes;
    int lead = -1;
    
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getChoices() {
        String tmp[] = new String[choicesCount];
        
        System.arraycopy(this.choices, 0, tmp, 0, choicesCount);
        return tmp;
    }

    public int getChoicesCount(){
        return this.choicesCount;
    }
    
    public boolean addChoice(String newChoice) {
        
        if (this.findChoice(newChoice) != -1){
            return false;
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

    public boolean hasChoice(String choice){
        return this.findChoice(choice) != -1;
    }
    
    public int getMaxVotes() {
        return maxVotes;
    }

    public void setMaxVotes(int maxVotes) {
        this.maxVotes = maxVotes;
    }
    
    public boolean vote(String choice){
        
        if(!this.isActive()){
            return false;
        }
        
        int choiceIndex = this.findChoice(choice);
        
        if(choiceIndex == -1){
            return false;
        }
        
        this.votes[choiceIndex]++;
        this.currentVotes++;
        
        if(this.lead == -1 || this.votes[choiceIndex] > this.votes[this.lead]) {
            this.lead = choiceIndex;
        }
        
        return true;
    }
    
    public boolean isActive(){
        return this.isReady() && currentVotes < maxVotes;
    }
    
    public boolean isReady(){
        return !this.question.isBlank() && this.choicesCount >= 2;
    }
    
    public String getWinner(){
        return this.isActive() ? null : this.choices[this.lead]; 
    }
 
    private int findChoice(String choice){
        for (int i = 0; i < choicesCount; i++) {
            if (choices[i].compareToIgnoreCase(choice) == 0) {
                return i;
            }
        }
        return -1;
    }
    
}
