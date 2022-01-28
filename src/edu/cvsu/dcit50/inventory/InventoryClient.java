package edu.cvsu.dcit50.inventory;

import java.util.Scanner;

/**
 *
 * @author rlvillacarlos
 */
public class InventoryClient {
    static Scanner in = new Scanner(System.in);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        startInventory(manager);
        showReport(manager);
    }
 
    private static void startInventory(InventoryManager manager){
    menuLoop: while(true){
            showMenu();
            int choice = in.nextInt();
            in.nextLine();
            
            switch(choice){
                case 1 -> { addSingleProduct(manager); }
                case 2 -> { addBoxedProduct(manager); }
                case 3 -> { System.out.println(); break menuLoop; }
            }
            
        }
    }
    
    private static void addSingleProduct(InventoryManager manager){
        System.out.print("Brand: ");
        String brand = in.nextLine().trim();
        System.out.print("Quantity: ");
        int quantity = in.nextInt();
        in.nextLine();
        System.out.println();
        manager.add(new SingleProduct(brand), quantity);
    }

    private static void addBoxedProduct(InventoryManager manager){
        System.out.print("Brand: ");
        String brand = in.nextLine().trim();
        System.out.print("Items in Box: ");
        int pieces = in.nextInt();
        in.nextLine();
        System.out.print("Quantity: ");
        int quantity = in.nextInt();
        in.nextLine();
        System.out.println();
        manager.add(new BoxedProduct(brand, pieces), quantity);
    }
    
    private static void showReport(InventoryManager manager){
        System.out.println("-Inventory Report-\n");
        
        for(String brand: manager.getBrands()){
            SingleProduct[] singleProducts = manager.getSingles(brand);
            BoxedProduct[] boxedProducts = manager.getBoxes(brand);
            int pieces = singleProducts.length;
            
            for(BoxedProduct product:boxedProducts){
                pieces += product.getQuantity();
            }
            
            System.out.println(brand);
            System.out.printf("\tSingles: %d%n", singleProducts.length);
            System.out.printf("\tBoxes: %d%n", boxedProducts.length);
            System.out.printf("\tTotal Pieces: %d%n%n", pieces);
        }
    }
    
    private static void showMenu(){
        System.out.print("""
            Options:
            \t[1] Add Single Product
            \t[2] Add Box Product
            \t[3] Exit
            Choice: """
        );
    }
}
