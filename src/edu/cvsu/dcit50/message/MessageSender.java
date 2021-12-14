package edu.cvsu.dcit50.message;

import java.util.Scanner;

/**
 *
 * @author rlvillacarlos
 * 
 */
public class MessageSender {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Sender: ");
        String sender = in.nextLine();
        
        System.out.print("Receiver: ");
        String receiver = in.nextLine();
        
        showMenu();
        int choice = in.nextInt();
        in.nextLine();
        
        switch(choice){
            case 1 -> { //Text Message
                TextMessage txt = new TextMessage(sender, receiver);
                System.out.print("Message: ");
                txt.setContent(in.nextLine());
                printMessageDetails(txt);
            }
            case 2 -> { // Link Message
                LinkMessage link = new LinkMessage(sender, receiver);
                System.out.print("Link: ");
                link.setContent(in.nextLine());
                printMessageDetails(link);
            }
            case 3 -> { //Image Message
                ImageMessage img = new ImageMessage(sender, receiver);
                System.out.print("Image Path: ");
                img.setContent(in.nextLine());
                printMessageDetails(img);
            }
        }
    }

    static void printMessageDetails(Message msg){
        System.out.println("--Message--");
        System.out.printf("Sender: %s%n", msg.getSender());
        System.out.printf("Receiver: %s%n", msg.getReceiver());
        System.out.printf("Message: %s%n", msg.getContentAsHTML());
    }
    
    static void showMenu(){
        System.out.println("What do you want to send?");
        System.out.println("\t[1] Text Message\n\t[2]Link\n\t[3]Image");
        System.out.print("Choice: ");
    }
}
