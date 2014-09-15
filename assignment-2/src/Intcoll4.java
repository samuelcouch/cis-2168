//*********************************************************************
// FILE NAME    : Intcoll4.java
// DESCRIPTION  : This file contains the class Intcoll4.
// NAME  : Sam Couch - couch@temple.edu
//*********************************************************************

import java.util.*;

public class Intcoll4
{
    private class ListNode
    {
        private int info;
        private ListNode link;
        
        public ListNode()
        {
            //initialize info block to 0, link to null (none)
            info=0; link=null;
        }
        
        public ListNode(int i, ListNode l)
        {
            //initialize info block to i, link to l (another node)
            info=i; link=l;
        }
    }
    private ListNode c;
    private int how_many;

    // Initializes Intcoll4 instance which can hold 500 ints
    public Intcoll4()
    {
        c = null;
        how_many = 0;
    }

    // Initializes Intcoll4 instance which can hold i ints
    public Intcoll4(int i)
    {
        c = null;
        how_many = 0;
    }

    // Copys contents of Intcoll4 instance named obj to this
    public void copy(Intcoll4 obj)
    {
        if (this != obj)
        {
            c = null;
            ListNode j = obj.c, k = null, t = new ListNode();
            while (j != null)
            {
                t.info = j.info;
                if(k != null) k.link = t;
                else c = t;
                k = t;
                t = new ListNode();
                j = j.link;
            }
            how_many = obj.how_many;
        }
    }

    // Returns true if collection contains int i, false if otherwise
    public boolean belongs(int i)
    {
        ListNode j = c;
        while ((j != null)&&(j.info != i)){ 
            j = j.link;
        }
        return (j != null);
    }

    // Inserts i into collection. If collection already contains i, function returns
    // if collection is filled to capacity, capacity is doubled
    public void insert(int i)
    {
        if (i > 0)
        {
            ListNode j = c;
            while ((j != null) && (j.info != i)){ 
                j=j.link;
            }
            if (j == null)
            {
                j = new ListNode(i, c);
                c = j;
                how_many++;
            }
        }
    }

    // If collection contains i, i will be omitted
    public void omit(int i)
    {
        ListNode j = c,p = null;
        while ((j != null)&&(j.info != i))
        {
            p=j; j=j.link;
        }
        if (j != null)
        {
            how_many--;
            if (p == null) {
                c = j.link;
            } else {
                p.link = j.link;
            }
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
        if(this.how_many > 0){
            ListNode j = c;
            int n = 0;
            System.out.print("[");
            while (j != null)
            {
                if (n != 0){
                    System.out.print(", ");
                }
                System.out.print(j.info); 
                n++;
                j = j.link;
            }
            System.out.println("]");
        }
        else
            System.out.println("[Empty Collection]");
    }

    // returns true if both Intcoll4 instances contain
    // identical int collections
    public boolean equals(Intcoll4 obj)
    {
        ListNode j = c;
        boolean result = how_many == obj.how_many;
        while ((j != null)&&result)
        {
            result = obj.belongs(j.info); j = j.link;
        }
        return result;
    }
}

