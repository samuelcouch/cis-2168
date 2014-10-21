//***********************************************************************
// FILE NAME    : MergesortTest.java
// DESCRIPTION  : This file contains the class MergeSort.
// NAME: Sam Couch - couch@temple.edu
//************************************************************************

import java.util.*;

public class MergesortTest {
   static int numcalls = 0;
   static int count = 0, m = 0;

   public static void main(String[] args)
   {
      Random gen = new Random(); 
      int[] a = new int[16384];
      int i; 
      for (i=0; i<a.length; i++) 
          a[i]=gen.nextInt(5000)+1;
      System.out.println("Initial array:");
      for (i=0; i<a.length; i++) 
          System.out.println(a[i] + " ");
      System.out.println();
      mergesort(a, 0, a.length-1);
      System.out.println("Sorted array:");
      for (i=0; i<a.length; i++) 
          System.out.println(a[i]);
      System.out.println("Done!");
      System.out.println("numcalls: " + numcalls);
      System.out.println("count: "+ count + " m: " + m);
   }
   
   public static void mergesort(int[] a, int top, int bottom)
   {
      numcalls++;
      if (top!=bottom)
      {
         int middle=(top+bottom)/2;
         mergesort(a, top, middle);
         mergesort(a, middle+1, bottom);
         merge(a, top, bottom);
      }
   }

   public static void merge(int[] a, int top, int bottom)
   {
      int t = top; 
      int middle = (top+bottom)/2; 
      int b = middle+1; 
      int i = 0;
      int[] s = new int[bottom-top+1];
      while ((t <= middle)&&(b <= bottom))
      {
         if (a[t] < a[b])
         {
            s[i] = a[t];
            t++;
         }
         else
         {
            s[i] = a[b];
            b++;
         }
         i++; 
         count++;
         m++;
      }
      int last = middle;
      if (b <= bottom) {
          t = b;
          last = bottom;
      }
      while (t <= last)
      {
         s[i] = a[t];
         t++;
         i++;
         m++;
      }
      for (i=0; i<s.length; i++){
          a[i+top] = s[i]; 
          m++;
      }
   }
}
