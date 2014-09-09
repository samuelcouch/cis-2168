//*********************************************************************
// FILE NAME    : Intcoll3.java
// DESCRIPTION  : This file contains the class Intcoll3.
// NAME  : Sam Couch - couch@temple.edu
//*********************************************************************

import java.util.*;

public class Intcoll3
{
    private boolean[] c;
    private int how_many;

    // Initializes Intcoll3 instance which can hold 500 ints
    public Intcoll3()
    {
        c = new boolean[500+1];
        for(int j=0; j<c.length; j++) c[j]=false;
        how_many = 0;
    }

    // Initializes Intcoll3 instance which can hold i ints
    public Intcoll3(int i)
    {
        c = new boolean[i+1];
        for(int j=0; j<c.length; j++) c[j]=false;
        how_many = 0;
    }

    // Copy's the contents of Intcoll3 instance named obj to this
    public void copy(Intcoll3 obj)
    {
        if (this != obj)
        {
            c = new boolean[obj.c.length];
            for(int j=0; j<c.length; j++) 
                c[j]=obj.c[j];
            how_many = obj.how_many;
        }
    }

    // Returns true if collection contains int i, false otherwise
    public boolean belongs(int i)
    {
        return (i < c.length && i > 0 &&  c[i]);
    }

    // Inserts i into collection. If collection already contains i, function exits.
    // If collection is filled to capacity, capacity is doubled
    public void insert(int i)
    {
        if (i > 0)
        {
            if(i >= c.length)
            {
                boolean[] t = new boolean[2*i+1];
                for(int j=0; j<c.length; j++)  t[j]=c[j];
                for(int j=c.length; j<t.length; j++) t[j]=false;
                c = t;
                how_many++;
                c[i] = true;
            } else if(!c[i]) {
                how_many++;
                c[i] = true;
            }
        }
    }

    // If collection contains i, i will be omitted
    public void omit(int i)
    {
        if(i < c.length && i > 0 && c[i]) {
            how_many--;
            c[i] = false;
        }
    }

    // Returns amount of ints stored in collection
    public int get_howmany()
    {
        return how_many;
    }

    // Prints contents of collection to output
    public void print()
    {
        boolean first = true;
        System.out.print("[");
        for(int j=0,k=0; k < how_many && j < c.length; j++)
        {
            if(c[j]) {
                if (first){
                    System.out.print(j);
                    first = false;
                }
                else{
                    System.out.print(", " + j);
                }
                k++;
            }
        }
        System.out.println("]");
    }

    // returns true if both Intcoll3 instances contain
    // identical collections
    public boolean equals(Intcoll3 obj)
    {
        boolean result = how_many == obj.how_many;
        int len = c.length;
        if(obj.c.length > c.length) len = obj.c.length;
        for(int j=0; result && j<len; j++)
        {
            result = (this.belongs(j) == obj.belongs(j));
        }

        return result;
    }
}

