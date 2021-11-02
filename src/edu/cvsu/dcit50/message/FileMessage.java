package edu.cvsu.dcit50.message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rlvillacarlos
 */
public class FileMessage extends Message{
    protected Path filePath;
    protected Path file;
    
    public FileMessage(String sender, String receiver) {
        super(sender, receiver);
    }

    @Override
    public boolean setContent(String content) {
        return this.setContent(Paths.get(content));
    }
    
    
    public boolean setContent(Path filePath) {
        try {
            String[] filenameParts = filePath.getFileName().toString().split(".");
            String extension = filenameParts.length > 1 ?
                    filenameParts[filenameParts.length -1 ] :
                    "";
            
            this.filePath =  Files.createTempFile("tmp_" + Long.toString(System.nanoTime()),
                                                    extension);
            this.file = filePath.getFileName();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(FileMessage.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public String getContentAsHTML() {
        return "<a href=\"" + this.filePath + "\">" + this.file + "</a>";
    }


    public Path getFileName() {
        return filePath.getFileName();
    }
    
}
