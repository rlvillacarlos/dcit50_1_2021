package edu.cvsu.dcit50.hangman;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rlvillacarlos
 */
public class Hangman {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        Scanner in = new Scanner(System.in);
        
        WordSource wordSource = null;
        
        System.out.println("Where do you want to get the words?:");
        System.out.println("   [0] User Input");
        System.out.println("   [1] Database");
        System.out.println("   [2] None, bye");
        System.out.print("Choice: ");
        
        int choice = in.nextInt();
        
        if(in.hasNextLine()){
            in.nextLine();
        }
        
        switch(choice){
            case 0 -> { //User Input
                wordSource = createUserWords();
            }
            case 1 -> { // Database
                wordSource = new DBWordSource("jdbc:sqlite:hangman.db","dataset");
            }
        }
        
        if(wordSource != null){
            while(true){
                startGame(wordSource);
                System.out.print("Play again (y/n)?: ");
                String cont = in.nextLine().trim().toLowerCase();
                
                if(!cont.equals("y")){
                    break;
                }
            }
            wordSource.close();  
        }
        
    }
    
    static ArrayWordSource createUserWords(){
        Scanner in = new Scanner(System.in);
        List<String> topicWords = new ArrayList<>();
        
        while(true){
            System.out.println("Enter a topic (blank line to stop)");
            System.out.print(">> ");
            String topic = in.nextLine().trim().toLowerCase();
            if(topic.isEmpty()){
                break;
            }
            System.out.printf("Enter the words for the topic \"%s\" (blank line to stop)%n",
                                topic.toUpperCase());
            while(true){
                System.out.print(">> ");
                String word = in.nextLine().trim().toLowerCase();
                if(word.isEmpty()){
                    break;
                }
                topicWords.add(String.format("%s:%s", topic,word));
            }
        }
        
        return new ArrayWordSource(topicWords);
    }
    
    static void startGame(WordSource wordSource){
        Scanner in = new Scanner(System.in);
        System.out.println("Choose a topic:");
        
        List<String> topics = wordSource.getTopics();
        
        for(int i = 0; i < topics.size(); i++){
            System.out.printf("   [%d]%s%n",i,topics.get(i));
        }
        
        System.out.print("Topic: ");
        String topic = topics.get(in.nextInt());
        in.nextLine();
        
        wordSource.setTopic(topic);
        
        play(wordSource.getRandomWord(), 8);      
    }
    
    static void play(Word word, int lives){
        Scanner in = new Scanner(System.in);
        
        String selectedTopic = word.topic.toUpperCase();
        
        String selectedWord = word.value.replaceAll("", "").toUpperCase();
        
        char[] question = constructQuestion(selectedWord);
        
        int numOfBlanks = question.length;
        
        int currentLives = lives;
        
        List<Character> guesses = new ArrayList<>();
        
        while(currentLives > 0 && numOfBlanks > 0){
            
            System.out.printf("Topic: %s%n",selectedTopic);
            
            System.out.printf("Lives: %d%n",currentLives);
            
            printQuestion(question);
            
            if(!guesses.isEmpty()){
                System.out.print("Guesses: ");
                printGuesses(guesses);
            }
            
            System.out.print("\n> ");                
            
            String input = in.nextLine().strip().toUpperCase();
            
            char guess;
            if(!input.isEmpty()){
                guess = input.charAt(0);
                if(!Character.isLetter(guess) || guesses.contains(guess)){
                    continue;
                }
                
                guesses.add(guess);
                int success = checkGuess(selectedWord, question, guess);
                
                if(success > 0){
                    numOfBlanks -= success;
                }else{
                    currentLives--;
                }
            }
            
            System.out.println("");
        }
        
        if(currentLives == 0){
            System.out.printf("Sorry! The word is %s.%n", selectedWord);
        }else{
            System.out.printf("Congratulations! You've guessed the word %s.%n", selectedWord);
        }
        
    }
    
    private static char[] constructQuestion(String word){
        char[] blanks = word.toCharArray();
        
        for(int i = 0; i < blanks.length; i++){
            blanks[i] = (blanks[i] >= 'A' && blanks[i] <= 'Z') ? '_' : blanks[i];
        }
        
        return blanks;
    }
    
    private static void printQuestion(char[] question){
        for(char c:question){
            System.out.printf("%c ", c);
        }
        System.out.println("");
    }
    
    private static void printGuesses(List<Character> guesses){
        for(char c: guesses){
            System.out.printf("%s ",c);
        }
        System.out.println("");
    }
    
    private static int checkGuess(String word, char[] question, char guess){
        int pos = -1;
        int success = 0;
        
        while((pos = word.indexOf(guess, pos + 1)) != -1){
            question[pos] = guess;
            success++;
        }
        
        return success;
    }
}
