

import java.util.Scanner;
import java.util.*;
import java.lang.*;

public class Menu<E> {
    public int int_getChoice(ArrayList<E>options){
        int response;
        int N = options.size();

        for (int i = 0; i < N; i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.print("Please choose an option 1.." + N + ": ");

        Scanner scanner = new Scanner(System.in);
        response = scanner.nextInt();

        return response;
        
    }
    
    public E ref_getChoice(ArrayList<E> options) {
        int response;
        for (int i = 0; i < options.size(); i++)
            System.out.println((i + 1) + " - " + options.get(i));
        do 
            response = int_getChoice(options);
        while (response < 0 || response > options.size());
        return options.get(response - 1);
    }
    

    

}
