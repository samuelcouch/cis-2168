//*********************************************************************
// FILE NAME    : Intcoll1.java
// DESCRIPTION  : This file contains the class Intcoll1.
// NAME  : Sam Couch - couch@temple.edu
//*********************************************************************

import java.util.*;

public class Intcoll1
{
    private int[] c;

    // Initializes Intcoll1 instance that can hold 500 ints
    public Intcoll1()
    {
        c = new int[500+1];
        c[0] = 0;
    }

    // Initializes Intcoll1 instance that can hold i ints
    public Intcoll1(int i)
    {
        c = new int[i+1];
        c[0] = 0;
    }

    // Copys contents of Intcoll1 instance named obj to the caller instance (this)
    public void copy(Intcoll1 obj)
    {
        if (this != obj)
        {
            c = new int[obj.c.length];
            int j = 0;
            while (obj.c[j] != 0)
            {
                c[j] = obj.c[j]; j++;
            }
            c[j] = 0;
        }
    }

    // Returns true if collection contains int i, false otherwise
    public boolean belongs(int i)
    {
        int j = 0;
        while ((c[j] != 0)&&(c[j] != i)) j++;
        return (c[j] != 0);
    }

    // Inserts i into collection. If collection already contains i, function exits.
    // If collection is filled to capacity, capacity is doubled
    public void insert(int i)
    {
        if (i > 0)
        {
            int j = 0;
            while ((c[j] != 0) && (c[j] != i)) j++;
            if (c[j] == 0)
            {
                if (j == c.length - 1)
                {
                    int[] t = new int[2*c.length - 1];
                    t[0]=c[0];
                    for(int k=1; c[k-1] != 0; t[k]=c[k]);
                    c = t;
                }
                c[j] = i; c[j + 1] = 0;
            }
        }
    }

    // If collection contains i, i will be omitted
    public void omit(int i)
    {
        int j = 0;
        while ((c[j] != 0)&&(c[j] != i)) j++;
        if (c[j] == i)
        {
            int k = j+1;
            while (c[k] != 0) k++;
            c[j] = c[k-1]; c[k-1]=0;
        }
    }

    // Returns amount of ints stored in collection
    public int get_howmany()
    {
        int i=0, howmany=0;
        
        while (c[i]!=0) {
            howmany++; i++;
        }
        return howmany;
    }

    // Prints contents of collection to output
    public void print()
    {
        int i = 0;
        System.out.print("[");
        while (c[i] != 0)
        {
            if(i != 0)
                System.out.print(", ");
            System.out.print(c[i]); 
            i++;
        }
        System.out.println("]");
    }

    // returns true if both Intcoll1 instances contain identical collections
    public boolean equals(Intcoll1 obj)
    {
        int i = 0; boolean result = true;
        while ((c[i] != 0)&&result)
        {
            result = obj.belongs(c[i]); 
            i++;
        }
        i = 0;
        while ((obj.c[i] != 0)&&result)
        {
            result = belongs(obj.c[i]); i++;
        }
        return result;
    }
}

