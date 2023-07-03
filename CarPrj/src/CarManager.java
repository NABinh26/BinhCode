

import java.util.ArrayList;

import java.util.Scanner;



public class CarManager {

    public static void main(String[] args){
        ArrayList<String> ops  = new ArrayList<>();
        ops.add("List all brands");
        ops.add("Add a new brand");
        ops.add("Search a brand based on its ID");
        ops.add("Update a brand");
        ops.add("Save brands to the file, named brands.txt");
        ops.add("List all cars in ascending order of brand names");
        ops.add("List cars based on a part of an input brand name");
        ops.add("Add a car");
        ops.add("Remove a car based on its ID");
        ops.add("Update a car based on its ID");
        ops.add("Save cars to file, named cars.txt");
        int choice = 0;
        
        BrandList bList = new BrandList();
        bList.loadFromFile("Brands.txt");
        CarList cList = new CarList(bList);
        cList.loadFromFile("Cars.txt");
        
        

        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        do{
            System.out.println("\nCar managing Program");
            choice = menu.int_getChoice(ops);
            switch(choice){
                case 1:                    
                    bList.listBrands();
                    //List all brands
                    break;
                case 2:
                    //add new brand
                    bList.addBrands();
                    break;
                case 3:
                    System.out.println("Enter ID: ");
                    String bID = sc.nextLine();
                    int index = bList.searchID(bID);
                            
                    if(index == -1)
                        System.out.println("Not found");                   
                    else{
                        System.out.println("Brand result is shown.");
                        System.out.println(bList.get(bList.searchID(bID)));
                    } 
                        
                   // Search a brand based on its ID
                    break;
                case 4:
                    bList.updateBrand();
                    
                    //Update a brand
                    break;
                case 5:
                    bList.saveToFile("Brands.txt");
                    System.out.println("New brands has been save to file.");
                    //Save brands to the file
                    break;
                case 6:
                    //List all cars in ascending order of brand names
                    cList.listCar();
                    break;
                case 7:
                    //List cars based on a part of an input brand name
                    cList.printBasedBrandName();
                    
                    break;    
                case 8:
                    //Add a car
                    cList.addCar();
                    break;
                case 9:
                    //Remove a car based on its ID
                    cList.removeCar();
                    cList.saveToFile("Cars.txt");
                    break;
                case 10:
                    //Update a car based on its ID
                    cList.updateCar();
                            
                    break;
                case 11:
                    //Save cars to file
                    cList.saveToFile("Cars.txt");
                    System.out.println("New car has been save to file.");
                    break;    
                default:
                    System.out.println("Bye!");
            }
        }while(choice > 0 && choice < 12);
    


    }
}
