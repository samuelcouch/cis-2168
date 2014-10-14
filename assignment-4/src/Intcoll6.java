//*********************************************************************
// FILE NAME    : Intcoll6.java
// DESCRIPTION  : This file contains the class Intcoll6.
// NAME: Sam Couch - couch@temple.edu
//*********************************************************************

import java.util.*;

public class Intcoll6
{
    private static class btNode {
        public btNode l;
        public btNode r;
        public int info;
        public btNode() {
            l = null; r = null; info = 0;
        }
        public btNode(int i, btNode lt, btNode rt) {
            l = lt; r = rt; info = i;
        }
    }
    private btNode c;
    private int how_many;

    // Initializes Intcoll6 instance which can hold 500 ints
    public Intcoll6()
    {
        c = null;
        how_many = 0;
    }

    // Initializes Intcoll6 instance which can hold i ints
    public Intcoll6(int i)
    {
        c = null;
        how_many = 0;
    }

    // Copys contents of Intcoll6 instance named obj to this
    public void copy(Intcoll6 obj)
    {
        if (this != obj)
        {
            c = btClone(obj.c);
            how_many = obj.how_many;
        }
    }
    
    // Necceary for copy() --> recursively copies each node
    private static btNode btClone(btNode b) {
        if(b==null)
            return null;

        return new btNode(b.info, btClone(b.l), btClone(b.r));
    }

    // Returns true if collection contains int i, false if otherwise
    public boolean belongs(int i)
    {
        btNode p = c;
        while(p!=null && p.info!=i) {
            if(p.info < i)
                p = p.r;
            else
                p = p.l;
        }
        return p!=null;
    }

    // Inserts i into collection. If collection already contains i, function returns
    // if collection is filled to capacity, capacity is doubled
    public void insert(int i)
    {
        if(c==null) {
            c = new btNode(i, null, null);
            how_many++;
            return;
        }

        btNode p = c;
        btNode prev = p;
        while(p!=null && p.info!=i) {
            prev = p;
            if(p.info < i)
                p = p.r;
            else
                p = p.l;
        }

        if(p==null) {
            if(prev.info < i)
                prev.r = new btNode(i, null, null);
            else
                prev.l = new btNode(i, null, null);
            how_many++;
        }
    }

    // If collection contains i, i will be omitted
    public void omit(int i)
    {
        btNode p = c; //point to the root node
        btNode prev = null; //blank node
        while(p!=null && p.info!=i) {
            prev = p;
            if(p.info < i)
                p = p.r;
            else
                p = p.l;
        }
        /* p should now point to the node representing i
           prev should be pointing to the root node of i
        */

        if(p!=null) { //assuming that i is in the collection
            btNode q = p; //q points to p (node containing i)
            if(p.r==null) //if there is no right-branch
                q = p.l; //q points to to the left branch of the i-node
            else if(p.l==null) //if there is no left-branch
                q = p.r; //q points to the i-right-branch
            else { //if both branches have children
                btNode j = p.l; //j points to the left of i
                if(j.r==null) { //check if the i-left has a right branch
                    q = j; //if so, q (i-node) points to j (i-left-node)
                    q.r = p.r; //i-right-node points to the i-right-node
                               //ie. the left-node shifts upwards
                } else { //if j.r is not null
                    while(j.r.r!=null) //finds the last right-branch
                        j = j.r; 
                    //j will land at the second to last right-branch
                    q = j.r; //q is the last right-node
                    j.r = q.l;
                    q.r = p.r; 
                    q.l = p.l;
                    /* This shifts the remaining nodes around */
                }
            }

            if(prev==null) //Only true if the root node is i
                c = q; //c points to q based on above logic
            else if(prev.r==p) //if the i-node was a right-branch
                prev.r = q; //point prev.r to q from above logic
            else //prev.l was the i-node
                prev.l = q; //point the left-node to q from above 

            how_many--; //decrement how_many
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
        printtree(c);
    }
    
    //Recursively prints each node in 
    private static void printtree(btNode t) {
        if(t != null){
            printtree(t.l);
            System.out.print(t.info + " ");
            printtree(t.r);
        }
        return;
    }

    // returns true if both Intcoll6 instances contain
    //   identical int collections
    public boolean equals(Intcoll6 obj)
    {
        if(how_many != obj.how_many)
            return false;
        int a1[] = new int[how_many];
        int a2[] = new int[how_many];
        int i=0;
        btToArray(c, a1, i);
        i=0;
        btToArray(obj.c, a2, i);

        boolean ret = true;
        for(i=0; ret && i<how_many; i++)
            ret = (a1[i] == a2[i]);

        return ret;
    }
   
    
   public static int btToArray(btNode t, int[] a, int i){
      int num_nodes=0;
      if (t!=null)
      {
         num_nodes=btToArray(t.l, a, i);
         a[num_nodes+i]=t.info;   
         num_nodes=num_nodes+1+btToArray(t.r, a, num_nodes+i+1);
      }
      return num_nodes;
   } 
}