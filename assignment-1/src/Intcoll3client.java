
//**********************************************************
// FILE: NAME   : intcoll1client.java
// DESCRIPTION  : This is a client of class Intcoll3.
// NAME  : Sam Couch - couch@temple.edu
//**********************************************************

import java.util.*;

public class Intcoll3client
{
    public static final int SENTINEL = 0;

    public static void main(String[] args)
    {
        int value; Scanner keyboard=new Scanner(System.in);
        Intcoll3 P=new Intcoll3(), N=new Intcoll3(), L= new Intcoll3();
        System.out.println("--Intcoll3Client--");

        System.out.println("Enter an integer to be inserted or 0 to quit:");
        value=keyboard.nextInt();
        while(value != SENTINEL)
        {
            if (value > 0) {P.insert(value); L.insert(value);}
            else {N.insert(-value); L.omit(-value);}
            System.out.println("Enter next integer to be inserted or 0 to quit:");
            value=keyboard.nextInt();
        }
        System.out.println("\nThe values in collection P are:");
        P.print();
        System.out.println("\nThe values in collection N are:");
        N.print();
        System.out.println("\nThe values in collection L are:");
        L.print();
        if (P.equals(N)) System.out.println("\nP and N are equal.");
        else System.out.println("\nP and N are NOT equal.");
        Intcoll3 A=new Intcoll3(); A.copy(L);
        System.out.println("\nThe values in the copy of L are:");
        A.print();

        Intcoll3 B = new Intcoll3(2);
        B.insert(1);B.insert(2);B.insert(3);B.insert(4);
        System.out.println("The values in collection B are:");
        B.print();
    }
}




















