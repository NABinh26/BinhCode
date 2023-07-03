

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.*;
import java.lang.*;

public class CarList extends ArrayList<Car>{
    BrandList brandList;

    public CarList() {
        super();
    }
    
    
    public CarList(BrandList bList){
        this.brandList = bList;
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
                    //Format: C01, B7-2018, red, F12345, E12345
                    line = line.trim();
                    if(line.length()>0){
                        StringTokenizer stk = new StringTokenizer(line, ",");
                        String carID = stk.nextToken().trim().toUpperCase();
                        String brandID = stk.nextToken().trim().toUpperCase();
                        String color = stk.nextToken().trim();
                        String frameID = stk.nextToken().trim().toUpperCase();
                        String engineID = stk.nextToken().trim().toUpperCase();
                        
                        Brand b = brandList.get(brandList.searchID(brandID));
                        this.add(new Car(carID, b, color, frameID, engineID));
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
    
    public boolean saveToFile(String filename){
        try{//open text file for writing chars
            FileWriter fw = new FileWriter(filename);
            PrintWriter pw = new PrintWriter(fw);
            //Writing products to file
            for (Car c : this) 
                pw.println(c.toString());
                
            // All brand are stored in file
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    public int searchCarID(String carID){
        int N = this.size();
        for(int i = 0; i < N; i++){
            if(this.get(i).getCarID().equals(carID)){
                return i;
            }    
        }
        return -1;
    }    
        
    public int searchFrame (String fID){
        int N = this.size();
        for(int i = 0; i < N; i++){
            if(this.get(i).getFrameID().equals(fID)) return i;
        }return -1;
    }    
    
    public int searchEngine (String eID){
        int N = this.size();
        for(int i = 0;i < N; i++){
            if(this.get(i).getEngineID().equals(eID)) return i;
        }return -1;    
    }
    
    public void addCar(){
        
        String carID, color, frameID, engineID;
        boolean Duplicated ;
        boolean Format = true;
        
        System.out.println("Add new Car");
        do{
            do{
                System.out.println("Enter new CarID: ");
                carID = sc.nextLine().toUpperCase().trim();
                if(searchCarID(carID) >= 0){
                    System.out.println("CarID is duplicated.");
                    Duplicated = true;
                }else Duplicated = false;                
            }while(Duplicated == true);                
        }while (carID.equals(""));
        
        Menu menu = new Menu();
        System.out.println("Choose Brand: ");
        Brand b = (Brand)menu.ref_getChoice(brandList);
        
        do{
            System.out.println("Enter color: ");
            color = sc.nextLine();
        }while(color.equals(""));
        
        do{
            do{
                System.out.println("Enter FrameID (Format: F00000): ");
                frameID = sc.nextLine().toUpperCase().trim();
                if(searchFrame(frameID) >= 0){
                    Duplicated = true;
                }else{
                    if(!frameID.matches("[F][0-9]{5}"))
                        Format = false;
                    else Format = true;
                }
            }while(Duplicated == true || Format == false);
        }while(frameID.equals(""));
        
        do{
            do{
                System.out.println("Enter EngineID (Format: E00000): ");
                engineID = sc.nextLine().toUpperCase().trim();
                if(searchEngine(engineID) >= 0){
                    Duplicated = true;
                }else{
                    if(!engineID.matches("[E][0-9]{5}"))
                        Format = false;
                    else Format = true;
                }
            }while(Duplicated == true || Format == false);
        }while(engineID.equals(""));
        
        this.add(new Car(carID, b, color, frameID, engineID));
        System.out.println("New Car has been add.");
    }
    
    public void printBasedBrandName(){
        System.out.print("Enter Brand name: ");
        String aPartOfBrandName = sc.nextLine();       
        int count = 0;
        for(int i = 0; i < this.size(); i++){
            Car c = this.get(i);            
            if(c.getBrand().getBrandName().contains(aPartOfBrandName)){                
                System.out.println(c.screenString());
                count++;
            }    
        }
        if(count == 0) 
            System.out.println("No car is detected.");
    }
    
    public boolean removeCar(){
        // remove car base on ID
        String CarID;
        System.out.println("Enter CarID to remove: ");
        CarID = sc.nextLine().toUpperCase();
        int Pos = searchCarID(CarID);
        if(Pos < 0){
            System.out.println("Not found!");
            return false;
        }else remove(Pos);
        System.out.println("CarID:"+CarID+" has been remove.");
        return true;
    }
    
    public boolean updateCar(){
        //update base on ID
        String CarID;
        Menu menu = new Menu();
        String newColor, newFrameID, newEngineID;
        boolean Duplicated = false ;
        boolean Format = true;
        System.out.println("Enter CarID to update: ");
        CarID = sc.nextLine().toUpperCase();
        int Pos = searchCarID(CarID);
        if(Pos < 0){
            System.out.println("Not found!");
        }else{
            System.out.println("Choose Brand: ");
            Brand b = (Brand)menu.ref_getChoice(brandList);
                    
            do{
                System.out.println("Enter color: ");
                newColor = sc.nextLine();
            }while(newColor.equals(""));
        
            do{
                do{
                    System.out.println("Enter new FrameID (Format: F00000): ");
                    newFrameID = sc.nextLine().toUpperCase().trim();
                    if(searchFrame(newFrameID) >= 0){
                        Duplicated = true;
                    }else{
                        if(!newFrameID.matches("[F][0-9]{5}"))
                            Format = false;
                        else Format = true;
                    }
                }while(Duplicated == true || Format == false);
            }while(newFrameID.equals(""));
        
            do{
                do{
                    System.out.println("Enter EngineID (Format: E00000): ");
                    newEngineID = sc.nextLine().toUpperCase().trim();
                    if(searchEngine(newEngineID) >= 0){
                        Duplicated = true;
                    }else{
                        if(!newEngineID.matches("[E][0-9]{5}"))
                            Format = false;
                        else Format = true;
                    }
                }while(Duplicated == true || Format == false);
            }while(newEngineID.equals(""));
            
            this.get(searchCarID(CarID)).setBrand(b);
            this.get(searchCarID(CarID)).setColor(newColor);
            this.get(searchCarID(CarID)).setFrameID(newFrameID);
            this.get(searchCarID(CarID)).setEngineID(newEngineID);
            System.out.println("Car ID:"+CarID+" has been update.");
        }
        
        
        return true;
    }
    
    public void listCar(){
        //Listing cars in ascending order of brand names
        Collections.sort(this);
        for (Car c: this){
            System.out.println(c.screenString());
        }           
    }
}
