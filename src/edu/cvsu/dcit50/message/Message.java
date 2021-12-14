package edu.cvsu.dcit50.message;

/**
 *
 * @author rlvillacarlos
 */
public abstract class Message {
    private final String sender;
    
    private final String receiver;
    
    public Message(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }
    
    public abstract boolean setContent(String content);
    
    public String getContentAsHTML(){
        return "";
    }
        
}
