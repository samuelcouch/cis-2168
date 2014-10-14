//***********************************************************************
// FILE NAME    : Intcoll6.java
// DESCRIPTION  : This file contains the class Intcoll6.
//************************************************************************

import java.util.*;
import java.io.*;

public class Intcoll6
{
    
   private static class btNode
   {
       int info; 
       btNode left; 
       btNode right;

       private btNode(int s, btNode lt, btNode rt)
       {
          info=s; 
          left=lt; 
          right=rt;  
       }

       private btNode()
       {
          info=0; 
          left=null; 
          right=null;
       }
   }
   
   private int howmany;
   private btNode c;

   public Intcoll6()
   {
      c = null;
      howmany = 0;
   }

   public Intcoll6(int i)
   {
      c = null;
      howmany = 0;
   }

   private static btNode copytree(btNode t)
   {
      btNode root=null;
      if (t!=null)
      {
         root=new btNode();
         root.info=t.info; 
         root.left=copytree(t.left);
         root.right=copytree(t.right);
      }
      return root;
   }

   public void copy(Intcoll6 obj)
   {
      if (this!=obj)
      {
          howmany=obj.howmany;
          c=copytree(obj.c);  
      }
   }

   public void insert(int i)
   {
      btNode pred=null, p=c;

      while ((p!=null)&&(p.info!=i))
      {
		  pred=p;
		  if (p.info>i) p=p.left;
		  else p=p.right;
      }
      if (p==null)
      {
         howmany++; p=new btNode(i, null, null);
         if (pred!=null)
         {
			 if (pred.info>i) pred.left=p;
			 else pred.right=p;
		 }
		 else c=p;
      }
   }

// If collection contains i, i will be omitted
    public void omit(int i)
    {
        btNode p = c; //point to the root node
        btNode oneUp = null; //blank node
        while(p!=null && p.info!=i) {
            oneUp = p;
            if(p.info < i)
                p = p.right;
            else
                p = p.left;
        }
        /* p should now point to the node representing i
           prev should be pointing to the root node of i
        */

        if(p != null) { //assuming that i is in the collection
            btNode q = p; //q points to p (node containing i)
            if(p.right == null) //if there is no right-branch
                q = p.left; //q points to to the left branch of the i-node
            else if(p.left == null) //if there is no left-branch
                q = p.right; //q points to the i-right-branch
            else { //if both branches have children
                btNode j = p.left; //j points to the left of i
                if(j.right == null) { //check if the i-left has a right branch
                    q = j; //if so, q (i-node) points to j (i-left-node)
                    q.right = p.right; //i-right-node points to the i-right-node
                               //ie. the left-node shifts upwards
                } else { //if j.r is not null
                    while(j.right.right != null) //finds the last right-branch
                        j = j.right; 
                    //j will land at the second to last right-branch
                    q = j.right; //q is the last right-node
                    j.right = q.left;
                    q.right = p.right; 
                    q.left = p.left;
                    /* This shifts the remaining nodes around */
                }
            }

            if(oneUp == null) //Only true if the root node is i
                c = q; //c points to q based on above logic
            else if(oneUp.right == p) //if the i-node was a right-branch
                oneUp.right = q; //point prev.r to q from above logic
            else //prev.l was the i-node
                oneUp.left = q; //point the left-node to q from above 

            howmany--; //decrement how_many
        }
    }

   public boolean belongs(int i)
   {
      btNode p=c;
      while ((p!=null)&&(p.info!=i)) 
      {
         if (p.info>i) p=p.left;
         else p=p.right;
      }
      return (p!=null); 
   }

   public int get_howmany() {return howmany;}

   public void print(String outname)
   {
      try
      {
	 PrintWriter outs=new PrintWriter(new FileOutputStream(outname));
         outs.println("The number of integers is "+howmany);
         outs.println();
         printtree(c, outs);
         outs.close();
      }
      catch (IOException ex)
      {
      }
   }

   public void print()
   {
      printtree(c);
   }

   public boolean equals(Intcoll6 obj)
   {
      int j = 0; boolean result  = (howmany==obj.howmany);
      if (result)
      { 
         int[] a=new int[howmany]; 
         int[] b=new int[howmany];
         toarray(c, a, 0); toarray(obj.c, b, 0);

         j=0;
         while ((result) && (j<howmany))
         {
            result=(a[j]==b[j]); j++;
         }
      }
      return result;
   }

   private static void printtree(btNode t, PrintWriter outs)
   {   
        if (t!=null)
        {
                printtree(t.left, outs);
                outs.println(t.info);
                printtree(t.right, outs);
        }
        return;
   }

   private static void printtree(btNode t)
   {
      if (t!=null)
      {
          printtree(t.left);
          System.out.print(t.info + " ");
          printtree(t.right);
      }
      return;
   }

   private static int toarray(btNode t, int[] a, int i)
   {
      int num_nodes=0;
      if (t!=null)
      {
         num_nodes=toarray(t.left, a, i);
         a[num_nodes+i]=t.info;   
         num_nodes=num_nodes+1+toarray(t.right, a, num_nodes+i+1);
      }
      return num_nodes;
   } 
}
