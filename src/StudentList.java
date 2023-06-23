/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ngô Bình
 */

import java.util.Scanner;
import java.util.*;
import java.lang.*;

public class StudentList extends ArrayList<Student>{
    public StudentList(){
        super();
    }
    
    public Student search(String code){
        code = code.trim().toUpperCase();
        for(int i=0;i<this.size();i++){
            if(this.get(i).getCode().equals(code))
                return this.get(i);
        }
        return null;
    }
    
    private boolean isCodeDupplicated(String code){
        code = code.trim().toUpperCase();
        return search(code) != null;
               
    }
    
    //Add new Student
    public void addStudent(){
        String newCode, newName;
        int newMark;
        boolean codeDupplicated = false;
        do{
            newCode = Inputter.inputPattern("St. code S000: ","[sS][\\d]{3}" ) ;
            newCode = newCode.trim().toUpperCase();
            codeDupplicated = isCodeDupplicated(newCode);
            if(codeDupplicated){
                System.out.println("Code is duplicated!");
            }                
        }while(codeDupplicated==true);
        
        newName = Inputter.inputNonBlankStr("Name of new student: ");
        newName = newName.toUpperCase();
        newMark = Inputter.inputInt("Mark: ", 0, 10);
        Student st = new Student(newCode, newName, newMark);
        this.add(st);
        System.out.println("Stdent "+newCode+" has been added.");
        
    }
    
    public void searchStudent(){
        if(this.isEmpty())
            System.out.println("Empty list. No search can be performed!");
        else{
            String sCode = Inputter.inputStr("Input student code for search: ");
            Student st = this.search(sCode);
                if(st == null)
                    System.out.println("Student "+ sCode+ " doesn't existed!");
                else System.out.println("Found: "+sCode+", " +st.name+", " + st.mark);    
        }  
    }
    
    public void updateStudent(){
        if(this.isEmpty())
            System.out.println("Empty list. No search can be performed!");
        else{
            String uCode = Inputter.inputStr("Input student code for search: ");
            Student st = this.search(uCode);
                if(st == null)
                    System.out.println("Student "+ uCode+ " doesn't existed!");
                else{
                    String oldName = st.getName();
                    String msg = "Old name: "+oldName+", new name: ";
                    String newName = Inputter.inputNonBlankStr(msg);
                    st.setName(newName.toUpperCase());
                    //update mark
                    int oldMark = st.getMark();
                    msg = "Old mark: " +oldMark+ ", new mark 0..10: ";
                    int newMark = Inputter.inputInt(msg, 0, 10);
                    st.setMark(newMark);
                    System.out.println("Student " + uCode+" has been updated.");
                }    
        }
    }
    
    public void removeStudent(){
        if(this.isEmpty())
            System.out.println("Empty list. No search can be performed!");
        else{
            String rCode = Inputter.inputStr("Input code of remove student: ");
            Student st = this.search(rCode);
            if(st == null)
                System.out.println("Student "+ rCode+ "doesn't existed!");
            else{
                this.remove(st);
                System.out.println("Student "+rCode+" has been removed.");
            } 
        }
    }
    
    public void displayAll(){
        if(this.isEmpty())
            System.out.println("Empty list!");
        else{
            System.out.println("Student list:");
            for(Student st: this) System.out.println(st);
            System.out.println("Total: "+this.size()+" student(s).");        
        }     
    }    
}
