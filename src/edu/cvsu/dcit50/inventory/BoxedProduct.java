package edu.cvsu.dcit50.inventory;

/**
 *
 * @author rlvillacarlos
 */
public class BoxedProduct {
    private final String brand;
    private final int quantity;

    public BoxedProduct(String brand, int qunatity) {
        this.brand = brand;
        this.quantity = qunatity;
    }

    public String getBrand() {
        return brand;
    }

    public int getQuantity() {
        return quantity;
    }
    
    
}
