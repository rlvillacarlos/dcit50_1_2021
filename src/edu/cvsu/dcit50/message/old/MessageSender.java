package edu.cvsu.dcit50.message.old;

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
        
        String content;
        TextMessage msg = null;
        switch(choice){
            case 1 -> { 
                msg = new TextMessage(sender, receiver);
                System.out.print("Message: ");
                content = in.nextLine();
                msg.setContent(content);
            }
            case 2 -> { 
                msg = new LinkMessage(sender, receiver);
                System.out.print("Link: ");
                content = in.nextLine();
                msg.setContent(content);
            }
        }
        
        if(msg != null){
            System.out.println("--Message--");
            System.out.printf("Sender: %s%n", msg.getSender());
            System.out.printf("Receiver: %s%n", msg.getReceiver());
            System.out.printf("Message: %s%n", msg.getContentAsHTML());
        }
    }
    
    static void showMenu(){
        System.out.println("What do you want to send?");
        System.out.println("\t[1] Text Message\n\t[2]Link");
        System.out.print("Choice: ");
    }
}
