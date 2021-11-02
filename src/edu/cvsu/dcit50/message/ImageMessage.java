package edu.cvsu.dcit50.message;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import javax.imageio.ImageIO;

/**
 *
 * @author rlvillacarlos
 */
public final class ImageMessage extends Message{
    private Path filePath;
    private Path file;
    private BufferedImage image;
    
    public ImageMessage(String sender, String receiver) {
        super(sender, receiver);
    }
    
    //Annotation
    @Override
    public boolean setContent(String content) {
        try {
            Path filePath = Paths.get(content);
            String[] filenameParts = filePath.getFileName().toString().split("\\.");
            String extension = filenameParts.length > 1 ?
                    "." + filenameParts[filenameParts.length -1 ] :
                    "";
            
            this.filePath =  Files.createTempFile("tmp_" + Long.toString(System.nanoTime()),
                                                    extension);
            this.file = filePath.getFileName();
            Files.copy(filePath.toAbsolutePath(), this.filePath, StandardCopyOption.REPLACE_EXISTING);
            this.image = ImageIO.read(Files.newInputStream(this.filePath, 
                                        StandardOpenOption.READ));
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public String getContentAsHTML() {
        return this.getContent(this.image.getHeight(), this.image.getWidth());
    }
    
    public String getContent(int height, int width) {
        return "<img src=\"" + this.filePath + "\" " + 
               "height=\""+ height + " " +
               "width=\""+ width + " " +
               "alt=\"" + this.file + "\">";
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
}
