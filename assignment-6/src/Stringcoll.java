//***********************************************************************
// FILE NAME    : Intcoll6.java
// DESCRIPTION  : This file contains the class Intcoll6.
// NAME: Sam Couch - couch@temple.edu
//************************************************************************

import java.util.*;
import java.io.*;

public class Stringcoll
{
    
   private static class btNode
   {
       String info; 
       btNode left; 
       btNode right;

       private btNode(String s, btNode lt, btNode rt)
       {
          info = s; 
          left = lt; 
          right = rt;  
       }

       private btNode()
       {
          info = ""; 
          left = null; 
          right = null;
       }
   }
   
   private int howmany;
   private btNode c;

   public Stringcoll()
   {
      c = null;
      howmany = 0;
   }

   public Stringcoll(int i)
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

   public void copy(Stringcoll obj)
   {
      if (this!=obj)
      {
          howmany=obj.howmany;
          c=copytree(obj.c);  
      }
   }

   public void insert(String i)
   { 
       //Check that i doesn't already exist in the collection...
       if(this.belongs(i))
           return;
       
       //If it's an empty collection, insert the new item and call it a day
       if(c == null){
           c = new btNode(i, null, null);
           howmany++;
           return;
       }
       //If it's not empty... We've got some work to do
       btNode pointer = c;
       btNode prev = pointer;
       
       //Use the Java String equals and compateTo methods
       while(pointer != null && !pointer.info.equals(i)){
            prev = pointer;
            if(pointer.info.compareToIgnoreCase(i) < 0)
                pointer = pointer.right;
            else
                pointer = pointer.left;
        }

        if(pointer == null) {
            if(prev.info.compareToIgnoreCase(i) < 0)
                prev.right = new btNode(i, null, null);
            else
                prev.left = new btNode(i, null, null);
            howmany++;
        }
   }

// If collection contains i, i will be omitted
    public void omit(String i)
    {
        btNode pointer = c;
        btNode prev = null;
        while(pointer!=null && !pointer.info.equals(i)) {
            prev = pointer;
            if(pointer.info.compareToIgnoreCase(i) < 0)
                pointer = pointer.right;
            else
                pointer = pointer.left;
        }

        if(pointer!=null) {
            btNode q = pointer;
            if(pointer.right == null)
                q = pointer.left;
            else if(pointer.left == null)
                q = pointer.right;
            else {
                btNode j = pointer.left;
                if(j.right == null) {
                    q = j;
                    q.right = pointer.right;
                } else {
                    while(j.right.right != null)
                        j = j.right;
                    q = j.right;
                    j.right = q.left;
                    q.right = pointer.right;
                    q.left = pointer.left;
                }
            }

            if(prev==null)
                c = q;
            else if(prev.right == pointer)
                prev.right = q;
            else
                prev.left = q;

            howmany--;
        }
    }

   public boolean belongs(String i)
   {
        btNode p = c;
        while(p!=null && !p.info.equals(i)) {
            if(p.info.compareTo(i) < 0)
                p = p.right;
            else
                p = p.left;
        }
        return p!=null;
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
       if(this.c == null){
           System.out.println("[EMPTY COLLECTION]");
       }
       if (this.c != null){
           System.out.print("[");
           printtree(c);
           System.out.println("]");
       }
   }

   public boolean equals(Stringcoll obj)
   {
      int j = 0; boolean result  = (howmany==obj.howmany);
      if (result)
      { 
         String[] a=new String[howmany]; 
         String[] b=new String[howmany];
         toarray(c, a, 0); 
         toarray(obj.c, b, 0);

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
            System.out.printf("%s %s", t.info, " ");
            //System.out.printf("%d; lt: %d, rt: %d\n", t.info, (t.left != null) ? t.left.info : -1, (t.right != null) ? t.right.info : -1);
            printtree(t.right);
        }
        return;
   }

   private static int toarray(btNode t, String[] a, int i)
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
