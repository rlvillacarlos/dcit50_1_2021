package edu.cvsu.dcit50.message;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rlvillacarlos
 */
public class LinkMessage extends TextMessage {
    
    protected URL link;
    
    public LinkMessage(String sender, String receiver) {
        super(sender, receiver);
    }

    @Override
    public boolean setContent(String content) {
        return this.setContent(content, content);
    }
    
    public boolean setContent(String content, String url) {
        if(!super.setContent(content)){
            return false;
        }
        
        try {
            this.link = new URL(url);
            return true;
        } catch (MalformedURLException ex) {
            return false;
        }
    }

    @Override
    public String getContentAsHTML() {
        if(this.link != null){
            return "<a href=\"" + this.link + "\">" + this.content + "</a>";
        }
        
        return super.getContentAsHTML();
    }
    
    public final URL getLink() {
        return this.link;
    }
    
}
