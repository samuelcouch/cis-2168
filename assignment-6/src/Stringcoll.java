//***********************************************************************
// FILE NAME    : Stringcoll.java
// DESCRIPTION  : This file contains the class Intcoll6.
// NAME: Sam Couch - couch@temple.edu
//************************************************************************

import java.util.*;

public class Stringcoll
{
    private static class btNode {
        public btNode l;
        public btNode r;
        public String info;
        public btNode() {
            l = null; r = null; info = "";
        }
        public btNode(String i, btNode lt, btNode rt) {
            l = lt; r = rt; info = i;
        }
    }
    private btNode c;
    private int how_many;

    // Initializes Stringcoll instance which can hold 500 ints
    public Stringcoll()
    {
        c = null;
        how_many = 0;
    }

    // Initializes Stringcoll instance which can hold i ints
    public Stringcoll(int i)
    {
        c = null;
        how_many = 0;
    }

    // Copys contents of Stringcoll instance named obj to this
    public void copy(Stringcoll obj)
    {
        if (this != obj)
        {
            c = btclone(obj.c);
            how_many = obj.how_many;
        }
    }

    // Returns true if collection contains int i, false if otherwise
    public boolean belongs(String i)
    {
        btNode p = c;
        while(p!=null && !p.info.equals(i)) {
            if(p.info.compareToIgnoreCase(i) < 0)
                p = p.r;
            else
                p = p.l;
        }
        return p!=null;
    }

    // Inserts i into collection. If collection already contains i, function returns
    //   if collection is filled to capacity, capacity is doubled
    public void insert(String i)
    {
        if(c==null) {
            c = new btNode(i, null, null);
            how_many++;
            return;
        }

        btNode p = c;
        btNode prev = p;
        while(p!=null && !p.info.equals(i)) {
            prev = p;
            if(p.info.compareToIgnoreCase(i) < 0)
                p = p.r;
            else
                p = p.l;
        }

        if(p==null) {
            if(prev.info.compareToIgnoreCase(i) < 0)
                prev.r = new btNode(i, null, null);
            else
                prev.l = new btNode(i, null, null);
            how_many++;
        }
    }

    // If collection contains i, i will be omitted
    public void omit(String i)
    {
        btNode p = c;
        btNode prev = null;
        while(p!=null && !p.info.equals(i)) {
            prev = p;
            if(p.info.compareToIgnoreCase(i) < 0)
                p = p.r;
            else
                p = p.l;
        }

        if(p!=null) {
            btNode q = p;
            if(p.r==null)
                q = p.l;
            else if(p.l==null)
                q = p.r;
            else {
                btNode j = p.l;
                if(j.r==null) {
                    q = j;
                    q.r = p.r;
                } else {
                    while(j.r.r!=null)
                        j = j.r;
                    q = j.r;
                    j.r = q.l;
                    q.r = p.r;
                    q.l = p.l;
                }
            }

            if(prev==null)
                c = q;
            else if(prev.r==p)
                prev.r = q;
            else
                prev.l = q;

            how_many--;
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
        btprint(c);
    }

    // returns true if both Stringcoll instances contain
    //   identical int collections
    public boolean equals(Stringcoll obj)
    {
        if(how_many != obj.how_many)
            return false;
        String a1[] = new String[how_many];
        String a2[] = new String[how_many];
        int i=0;
        btToArray(c, a1, i);
        i=0;
        btToArray(obj.c, a2, i);

        boolean ret = true;
        for(i=0; ret && i<how_many; i++)
            ret = (a1[i].equals(a2[i]));

        return ret;
    }

    private static btNode btclone(btNode b) {
        if(b==null)
            return null;

        return new btNode(b.info, btclone(b.l), btclone(b.r));
    }

    private static int btToArray(btNode b, String[] a, int i) {
        if(b!=null) {
            i = btToArray(b.l, a, i);
            a[i++] = b.info; // i++ returns i before it adds 1, so a[i] == a[i++] != a[++i]
            i = btToArray(b.r, a, i);
        }
        return i;
    }

    private static void btprint(btNode b) {
        if(b==null)
            return;
        btprint(b.l);
        System.out.printf("%s :: L: %s, R: %s\n", b.info, (b.l != null) ? b.l.info : "NULL", (b.r != null) ? b.r.info : "NULL");
        btprint(b.r);
    }
}