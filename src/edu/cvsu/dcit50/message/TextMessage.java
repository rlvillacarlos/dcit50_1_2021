package edu.cvsu.dcit50.message;

/**
 *
 * @author rlvillacarlos
 */
public class TextMessage extends Message{
    protected String content;
    
    public TextMessage(String sender, String receiver) {
        super(sender, receiver);
    }
    
    @Override
    public boolean setContent(String content) {
        this.content = content;
        return true;
    }

    @Override
    public String getContentAsHTML() {
        return "<p>" + this.content + "</p>";
    }
    
}
