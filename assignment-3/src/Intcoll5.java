//*********************************************************************
// FILE NAME    : Intcoll5.java
// DESCRIPTION  : This file contains the class Intcoll5.
// NAME  : Sam Couch - couch@temple.edu
//*********************************************************************

import java.util.*;

public class Intcoll5
{
    private LinkedList<Integer> c;

    // Initializes Intcoll5
    public Intcoll5()
    {
        c = new LinkedList<Integer>();
    }

    // Only neccesary for backwards compatibility
    public Intcoll5(int i)
    {
        c = new LinkedList<Integer>();
    }

    // Copys contents of Intcoll5 instance named obj to this object
    public void copy(Intcoll5 obj)
    {
        if (this != obj)
        {
            c = new LinkedList<Integer>(obj.c);
        }
    }

    // Returns true if collection contains int i, false if otherwise
    public boolean belongs(int i)
    {
        return (c.indexOf(i) != -1); //or return c.contains(i);
    }

    // Inserts i into collection. If collection already contains i, function returns
    // if collection is filled to capacity, capacity is doubled
    public void insert(int i)
    {
        if (i > 0)
        {
            c.push(i);
        }
    }

    // If collection contains i, i will be omitted
    public void omit(int i)
    {
        c.remove(new Integer(i));
    }

    // Returns amount of ints stored in collection
    public int get_howmany()
    {
        return c.size();
    }

    // Prints contents of collection to output
    public void print()
    {
        ListIterator<Integer> i = c.listIterator();
        if(i.hasNext()){
            System.out.print("[" + i.next());
            while(i.hasNext())
                System.out.print(", " + i.next());
            System.out.println("]");
        }
        else
            System.out.println("[Empty Collection]");
    }

    // returns true if both Intcoll5 instances contain
    //   identical int collections
    public boolean equals(Intcoll5 obj)
    {
        return c.equals(obj.c);
    }
}

