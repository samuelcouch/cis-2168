//***********************************************************************
// FILE NAME    : MultistringCollClient.java
// NAME: Sam Couch - couch@temple.edu
//************************************************************************

import java.util.*;

public class MultistringCollClient {
    
    public static final String SENTINEL = "END";
    
    public static void main(String[] args){
        String input; 
        Scanner keyboard = new Scanner(System.in);
        MultistringColl P = new MultistringColl(), N = new MultistringColl();
        System.out.println("Enter a string to be inserted or END to quit:");
        input = keyboard.next();
        while(!input.equals(SENTINEL))
        {
            if(P.belongs(input)){
                P.insert(input);
                N.omit(input);
            }
            else{
                P.insert(input);
                N.insert(input);
            }
            System.out.println("Enter next string to be inserted or END to quit:");
            input = keyboard.next();
        }
        System.out.println("\nAll inputs (P):");
        P.print();
        System.out.println("\n\nOmmited Inputs (N):");
        N.print();
    }
}