package edu.cvsu.dcit50.inventory;

/**
 *
 * @author rlvillacarlos
 */
public class InventoryManager {
    //Array to store individual single products
    private SingleProduct singleProducts[] = new SingleProduct[1];
    //Array to store individual boxed products
    private BoxedProduct boxedProducts[] = new BoxedProduct[1];
    //Array to store each brands
    private String brands[] = new String[1];
    //Array to store the number of single products of each brand
    private int brandSinglesCount[] = new int[1];
    //Array to store the number of boxed products of each brand
    private int brandBoxesCount[] = new int[1];
    //Current position in the singleProducts array where a new product can be added
    private int vacantSingleIndex = 0;
    //Current position in the boxedProducts array where a new product can be added
    private int vacantBoxedIndex = 0;
    //Current position in the brands array where a new brand can be added
    private int vacantBrandIndex = 0;
    
    
    public void add(SingleProduct p){
        //Add one single product
        this.add(p, 1);
    }
    
    public void add(SingleProduct p, int quantity){
        //Add the single products individually at the next vacant position in the array
        for(int i = 1; i <= quantity; i++){
            //If the array is full, them resize to make room
            if(vacantSingleIndex == singleProducts.length){
                resizeSingleProducts();
            }
            //Add the boxed product in the next vacant position
            singleProducts[vacantSingleIndex] = p;
            
            //Update the next vacant position
            vacantSingleIndex++;
            
            //Update the number of single products for the product's brand
            incrementSingleEntryForBrand(p.getBrand());            
        }
    }

    public void add(BoxedProduct p){
        //Add one boxed product
        this.add(p, 1);
    }
    
    public void add(BoxedProduct p, int quantity){
        //Add the boxed products individually at the next vacant position in the array
        for(int i = 1; i <= quantity; i++){
            //If the array is full, them resize to make room
            if(vacantBoxedIndex == boxedProducts.length){
                resizeBoxedProducts();
            }
            
            //Add the boxed product in the next vacant position
            boxedProducts[vacantBoxedIndex] = p;
            
            //Update the next vacant position
            vacantBoxedIndex++;
            
            //Update the number of boxes for the product's brand
            incrementBoxedEntryForBrand(p.getBrand());            
        }
    }
    
    public String[] getBrands(){
        String tmp[] = new String[vacantBrandIndex];
        System.arraycopy(brands, 0, tmp, 0, vacantBrandIndex);
        return tmp;
    }

    public BoxedProduct[] getBoxes(String brand){
        //Find the position of the given brand in the array of brands
        int brandIndex = findIndexOfBrand(brand);
        
        //If the brand does not exists, return an empty array
        if(brandIndex == -1){
            return new BoxedProduct[0];
        }
        
        //Get the number of boxes for the given brand
        int numBoxesOfBrand = brandBoxesCount[brandIndex];
        
        //Create an array that will hold all the boxes for the given brand
        BoxedProduct tmp[] = new BoxedProduct[numBoxesOfBrand];
        
        //Vacant position to place the box for the given brand
        int vacantPos = 0;
        
        //Search the array of all boxed products and filter based on the given brand
        for(int i = 0; i < vacantBoxedIndex; i++){
            if(brand.equals(boxedProducts[i].getBrand())){
                tmp[vacantPos] = boxedProducts[i];
                vacantPos++;
            }
        }
        
        //Return the created array holding all boxed product of the given brand
        return tmp;
    }
    
    public SingleProduct[] getSingles(String brand){
        //Find the position of the given brand in the array of brands
        int brandIndex = findIndexOfBrand(brand);
        
        //If the brand does not exists, return an empty array
        if(brandIndex == -1){
            return new SingleProduct[0];
        }
        
        //Get the number of single products for the given brand
        int numSinglesOfBrand = brandSinglesCount[brandIndex];
        
        //Create an array that will hold all the single prodcuts for the given brand
        SingleProduct tmp[] = new SingleProduct[numSinglesOfBrand];
        
        //Vacant position to place the box for the given brand
        int vacantPos = 0;
        
        //Search the array of all single products and filter based on the given brand
        for(int i = 0; i < vacantSingleIndex; i++){
            if(brand.equals(singleProducts[i].getBrand())){
                tmp[vacantPos] = singleProducts[i];
                vacantPos++;
            }
        }
        
        //Return the created array holding all single product of the given brand
        return tmp;
    }
    
    //---The private methods below are for maintaining all the arrays used in the class--//
    
    
    private void incrementSingleEntryForBrand(String brand){
        int brandIndex = addBrand(brand);
        brandSinglesCount[brandIndex]++;
    }
    
    private void incrementBoxedEntryForBrand(String brand){
        int brandIndex = addBrand(brand);
        brandBoxesCount[brandIndex]++;
    }
    
    //Add a new brand if it does not exists. Then return the index of the brand
    private int addBrand(String brand){
        int brandIndex = findIndexOfBrand(brand);
        
        if(brandIndex == -1){
            if(vacantBrandIndex == brands.length){
                resizeBrands();
            }
            
            brands[vacantBrandIndex] = brand;
            brandSinglesCount[vacantBrandIndex] = 0;
            brandBoxesCount[vacantBrandIndex] = 0;
            
            brandIndex = vacantBrandIndex;
            
            vacantBrandIndex++;
        }
        
        return brandIndex;
    }
    
    private int findIndexOfBrand(String brand){
        for(int i = 0; i < vacantBrandIndex; i++){
            if(brands[i].equals(brand)){
                return i;
            }
        }
        return -1;
    }
    
    private void resizeBrands(){
        String tmpBrands[] = new String[brands.length * 2];
        System.arraycopy(brands, 0, tmpBrands, 0, brands.length);
        brands = tmpBrands;
        
        int tmpEntries[] = new int[brandSinglesCount.length * 2];
        System.arraycopy(brandSinglesCount, 0, tmpEntries, 0, brandSinglesCount.length);
        brandSinglesCount = tmpEntries;
        
        tmpEntries = new int[brandBoxesCount.length * 2];
        System.arraycopy(brandBoxesCount, 0, tmpEntries, 0, brandBoxesCount.length);
        brandBoxesCount = tmpEntries;
        
    }
    
    private void resizeSingleProducts(){
        SingleProduct tmpSingleProducts[] = new SingleProduct[singleProducts.length * 2];
        System.arraycopy(singleProducts, 0, tmpSingleProducts, 0, singleProducts.length);
        singleProducts = tmpSingleProducts;       
    }
    
    private void resizeBoxedProducts(){
        BoxedProduct tmpBoxedProducts[] = new BoxedProduct[boxedProducts.length * 2];
        System.arraycopy(boxedProducts, 0, tmpBoxedProducts, 0, boxedProducts.length);
        boxedProducts = tmpBoxedProducts;       
    }
}
