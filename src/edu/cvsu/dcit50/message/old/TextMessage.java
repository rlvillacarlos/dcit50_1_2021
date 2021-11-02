package edu.cvsu.dcit50.message.old;

/**
 *
 * @author rlvillacarlos
 */
public class TextMessage {

    private final String sender;
    
    private final String receiver;
    
    protected String content;
    
    public TextMessage(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }
    
    public boolean setContent(String content) {
        this.content = content;
        return true;
    }

    public String getContentAsHTML() {
        return "<p>" + this.content + "</p>";
    }
    
}
