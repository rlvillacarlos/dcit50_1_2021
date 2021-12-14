package edu.cvsu.dcit50.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author rlvillacarlos
 */
public class ArrayWordSource extends WordSource{
    private final Random rnd = new Random(System.nanoTime());
    private List<List<String>> topicWords = new ArrayList<>();
    
    public ArrayWordSource(String topicWords[]) {
        for(String topicWord: topicWords){
            String parts[] = topicWord.split(":");
            this.addTopicWord(parts[0], parts[1]);
        }
        
    }
    
    public ArrayWordSource(List<String> topicWords) {
        for(String topicWord: topicWords){
            String parts[] = topicWord.split(":");
            this.addTopicWord(parts[0], parts[1]);
        }
    }

    @Override
    public Word getRandomWord() {
        int topicIndex; 
        int wordIndex; 
        
        if(!this.topicFilter.equals("")){
            topicIndex = this.topics.indexOf(this.topicFilter);
        }else{
            topicIndex = rnd.nextInt(this.topics.size());
        }
        
        if(topicIndex == -1){
            return null;
        }
        
        String topic = this.topics.get(topicIndex);
        
        List<String> topicWords = this.topicWords.get(topicIndex);
        
        wordIndex = rnd.nextInt(topicWords.size());
        
        String word = topicWords.get(wordIndex);
        
        return new Word(topic, word);
        
    }

    @Override
    public void close() {
        super.close();
        this.topicWords.clear();
        this.topicWords = null;
        
    }
    
    private boolean addTopicWord(String topic, String word){
        int index = this.topics.indexOf(topic);
        
        if(index == -1){
            this.topics.add(topic);
            this.topicWords.add(new ArrayList<>());
            index = topics.size() - 1;
        }
        
        List<String> words = this.topicWords.get(index);
        
        if(!words.contains(word)){
            words.add(word);
            return true;
        }
        return false;    
    }
}
