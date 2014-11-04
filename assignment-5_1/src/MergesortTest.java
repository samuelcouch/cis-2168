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
       int[] test = {5, 4, 3, 2, 1};
       mergesort(test, 0, test.length-1);
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