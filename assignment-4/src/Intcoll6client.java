//*********************************************************************
// FILE NAME    : Intcoll6.java
// DESCRIPTION  : This file contains the class Intcoll6.
// NAME: Sam Couch - couch@temple.edu
//*********************************************************************

import java.util.*;

public class Intcoll6client
{
    public static final int SENTINEL = 0;
    
    public static void main(String[] args){
        int value; Scanner keyboard=new Scanner(System.in);
        Intcoll6 L = new Intcoll6(), P=new Intcoll6(), N=new Intcoll6();
//        
//        System.out.println("Enter an integer to be inserted or 0 to quit:");
//        value=keyboard.nextInt();
//        while(value != SENTINEL)
//        {
//            if (value > 0) {P.insert(value); L.insert(value);}
//            else {N.insert(-value); L.omit(-value);}
//            System.out.println("Enter next integer to be inserted or 0 to quit:");
//            value=keyboard.nextInt();
//        }
//        System.out.println("\nThe values in collection P are:");
//        P.print();
//        System.out.println("\nThe values in collection N are:");
//        N.print();
//        System.out.println("\nThe values in collection L are:");
//        L.print();
//        if (P.equals(N)) System.out.println("\nP and N are equal.");
//        else System.out.println("\nP and N are NOT equal.");
//        Intcoll6 A=new Intcoll6(); A.copy(L);
//        System.out.println("\nThe values in the copy of L are:\n");
//        A.print();
        
        for (int i=1;i<=10;i++){
            P.insert(i);
        }
        for (int i=1;i<=5;i++){
            N.insert(i);
        }
        System.out.println("First collection:");
        P.print();
        System.out.println();
        System.out.println("Second Collection:");
        N.print();
        System.out.println();
        P.copy(N);
        System.out.println("First after copy:");
        P.print();
        System.out.println();
        System.out.println("Second after copy:");
        N.print();
        System.out.println();
        System.out.println(P.equals(N));
        System.out.println(P.belongs(2));
        P.omit(100);
        System.out.println("ommitted 1");
        P.print();
        System.out.println();
        System.out.println(P.belongs(2));
        System.out.println(P.get_howmany());
    }
}
