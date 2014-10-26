//*********************************************************************
// FILE NAME    : Stringcoll.java
// DESCRIPTION  : This file contains the class Intcoll6.
// NAME: Sam Couch - couch@temple.edu
//*********************************************************************

import java.util.*;

public class StringcollClient
{
    public static final String SENTINEL = "END";
    
    public static void main(String[] args){
        String input; 
        Scanner keyboard = new Scanner(System.in);
        Stringcoll L = new Stringcoll(), P=new Stringcoll(), N=new Stringcoll();
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
        System.out.println("All inputs (P):");
        P.print();
        System.out.println("Ommited Inputs (N):");
        N.print();
        System.out.println("Remaining Inputs (L):");
        L.print();
    }
}
