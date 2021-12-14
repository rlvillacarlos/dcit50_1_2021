package edu.cvsu.dcit50.hangman;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rlvillacarlos
 */
public abstract class WordSource {
    protected List<String> topics = new ArrayList<>();
    protected String topicFilter = "";
    
    
    public void setTopic(String topic){
        this.topicFilter = topic == null ? "" : topic.strip();
    }

    public List<String> getTopics() {
        return new ArrayList<>(this.topics);
    }
    
    public void close(){
        this.topics.clear();
        this.topics = null;
    } 
    
    abstract public Word getRandomWord();
}
