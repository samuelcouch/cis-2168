//*********************************************************************
// FILE NAME    : Intcoll2.java
// DESCRIPTION  : This file contains the class Intcoll2.
// NAME  : Sam Couch - couch@temple.edu
//*********************************************************************

import java.util.*;

public class Intcoll2
{
    private int[] c;
    private int how_many;

    // Initializes Intcoll2 instance which can hold 500 ints
    public Intcoll2()
    {
        c = new int[500];
        how_many = 0;
    }

    // Initializes Intcoll2 instance which can hold i ints
    public Intcoll2(int i)
    {
        c = new int[i];
        how_many = 0;
    }

    // Copy's the contents of Intcoll2 instance named obj to caller instance (this)
    public void copy(Intcoll2 obj)
    {
        if (this != obj)
        {
            c = new int[obj.c.length];
            int j = 0;
            while (j != obj.how_many)
            {
                c[j] = obj.c[j]; j++;
            }
            c[j] = 0;
            how_many = obj.how_many;
        }
    }

    // Returns true if collection contains i, false otherwise
    public boolean belongs(int i)
    {
        int j = 0;
        while ((j != how_many)&&(c[j] != i)){
            j++;
        }
        return (j != how_many);
    }

    // Inserts i into collection. If collection already contains i, function exits.
    // If collection is filled to capacity, capacity is doubled
    public void insert(int i)
    {
        if (i > 0)
        {
            int j = 0;
            while ((j != how_many) && (c[j] != i)) j++;
            if (c[j] != i)
            {
                if (j == c.length - 1)
                {
                    int[] t = new int[2*c.length];
                    for(int k=0; k != how_many; k++) t[k]=c[k];
                    c = t;
                }
                c[j] = i; how_many++;
            }
        }
    }

    // If collection contains i, it will be omitted
    public void omit(int i)
    {
        int j = 0;
        while ((j != how_many)&&(c[j] != i)) j++;
        if (j != how_many && c[j] == i)
        {
            how_many--;
            c[j] = c[how_many];
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
        int j = 0;
        System.out.print("[");
        while (j != how_many)
        {
            if(j != 0)
                System.out.print(", ");
            System.out.print(c[j]); j++;
        }
        System.out.println("]");
    }

    // returns true if both Intcoll2 instances contain identical collections
    public boolean equals(Intcoll2 obj)
    {
        int j = 0; boolean result = how_many == obj.how_many;
        while ((j != how_many)&&result)
        {
            result = obj.belongs(c[j]); j++;
        }
        j = 0;
        while ((j != obj.how_many)&&result)
        {
            result = belongs(obj.c[j]); j++;
        }
        return result;
    }
}

