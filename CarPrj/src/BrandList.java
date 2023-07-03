

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.*;
import java.lang.*;


public class BrandList extends ArrayList<Brand>{
    public BrandList(){
        super();
    }
    Scanner sc = new Scanner(System.in);
    public boolean loadFromFile(String filename){
        
        File file = new File(filename);
        if(!file.exists()){
            System.out.println("File is not exists!");
            return false;
        }else{
            try{
                FileReader fr = new FileReader(file);//open lile for reading chars
                BufferedReader bf = new BufferedReader(fr);// for reading line
                String line;// a line will be read a line from file
                while((line = bf.readLine())!=null){
                    //Format: B7-2018, BMW 730Li (2018), Harman Kardon: 3.749
                    line = line.trim();
                    if(line.length()>0){
                        StringTokenizer stk = new StringTokenizer(line, ",:");
                        String brandID = stk.nextToken().trim();
                        String brandName = stk.nextToken().trim();
                        String soundBrand = stk.nextToken().trim();
                        double price = Double.parseDouble(stk.nextToken().trim());                       
                        this.add(new Brand(brandID, brandName, soundBrand, price));
                    }
                }
                bf.close();
                fr.close();
            }catch (Exception e) {
                e.getStackTrace();           
            }
        }
        
        return true;
    }
    
    public boolean saveToFile(String filename) {
        try{//open text file for writing chars
            FileWriter fw = new FileWriter(filename);
            PrintWriter pw = new PrintWriter(fw);
            //Writing products to file
            for (Brand brand : this) 
                pw.println(brand);
                
            // All brand are stored in file
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    

    
    
    public int searchID(String bID){
        
        int N = this.size();
        for(int i = 0; i < N; i++){
            if(this.get(i).getBrandID().equals(bID.toUpperCase()))
                return i;
        }        
        return -1;
    }
    
    
    public Brand getUserChoice() {
        Menu menu = new Menu();
        return (Brand) menu.ref_getChoice(this);
    }
    
    public void addBrands(){        
        String newBrandID;
        String newBrandName;
        String newBrandSound;
        double price;
        boolean Duplicated;
        
        
        do{
            do{
                System.out.println("Enter new BrandID: ");
                newBrandID = sc.nextLine().toUpperCase().trim();
                if(searchID(newBrandID) >= 0){
                    System.out.println("ID is duplicated.");
                    Duplicated = true;
                }else Duplicated = false;                
            }while(Duplicated == true);                
        }while (newBrandID.equals(""));
        
        
        
        do{
            System.out.println("Enter new Brand Name: ");
            newBrandName = sc.nextLine().trim();
        }while(newBrandName.equals(""));
        
        
        do{
            System.out.println("Enter new Sound Brand: ");
            newBrandSound = sc.nextLine().trim();
        }while(newBrandSound.equals(""));
        
        
        do{
            System.out.println("Enter new price: ");
            price = Double.parseDouble(sc.nextLine());
        }while(price < 0);
        
        this.add(new Brand(newBrandID, newBrandName, newBrandSound, price));
        System.out.println("Add complete.");
    }    
    
    public void updateBrand(){
        System.out.println("Enter brand ID you want to update: ");
        String bID = sc.nextLine();
        int Pos = searchID(bID);
        if(Pos < 0){
            System.out.println("Not found!");
        }else {
            String newBrandName, newBrandSound;
            double price;
        
            do{
                System.out.println("Enter new Brand Name: ");
                newBrandName = sc.nextLine().trim();
            }while(newBrandName.equals(""));
        
        
            do{
                System.out.println("Enter new Sound Brand: ");
                newBrandSound = sc.nextLine().trim();
            }while(newBrandSound.equals(""));
        
        
            do{
                System.out.println("Enter new price: ");
                price = Double.parseDouble(sc.nextLine());
            }while(price < 0);
            this.get(Pos).setBrandName(newBrandName);
            this.get(Pos).setSoundBrand(newBrandSound);
            this.get(Pos).setPrice(price);
            System.out.println("Brand ID "+ bID+ " has been update");
        }
        
    
    }
    
    public void listBrands(){                
        if (this.size() == 0) {
            System.out.println("Empty list!");            
        }
        System.out.println("\nBrand list");
        System.out.println("-----------------------------------------");
        for (Brand x: this)
            System.out.println(x.toString());
        }
    }

