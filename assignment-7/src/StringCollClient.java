
import java.util.Scanner;

//***********************************************************************
// FILE NAME    : StringCollClient.java
// NAME: Sam Couch - couch@temple.edu
//************************************************************************

import java.util.*;

public class StringCollClient {
    
    public static final String SENTINEL = "END";
    
    public static void main(String[] args){
        String input; 
        Scanner keyboard = new Scanner(System.in);
        StringColl L = new StringColl(), P=new StringColl(), N=new StringColl();
        System.out.println("Enter a string to be inserted or END to quit:");
        input = keyboard.next();
        while(!input.equals(SENTINEL))
        {
            //If it's already in the main collection
            //Insert to N ("negative") and omit from L
            if(P.belongs(input)){
                N.insert(input);
                L.omit(input);
            }
            else{
                P.insert(input);
                L.insert(input);
            }
            System.out.println("Enter next string to be inserted or END to quit:");
            input = keyboard.next();
        }
        System.out.println("\nAll inputs (P):");
        P.print();
        System.out.println("\n\nOmmited Inputs (N):");
        N.print();
        System.out.println("\n\nRemaining Inputs (L):");
        L.print();
    }
}
