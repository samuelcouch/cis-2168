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
       Scanner keyboard = new Scanner(System.in);
       int kTests = 10;
       System.out.println("Enter an integer k times to run the tests: ");
       kTests = keyboard.nextInt();
       
       int[] test_cases = {2048, 4096, 8192, 16384};
       int[][] results = new int[4][kTests];
       for(int k = 1; k<=4; k++){
           for(int j = 1; j <= kTests; j++){
               numcalls = 0;
               count = 0;
               m = 0;
               Random gen = new Random(); 
               int[] a = new int[test_cases[k-1]];
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
               System.out.println(test_cases[k-1] + " done!");
               results[k-1][j-1] = m;
           }
       }
       for(int q = 0; q < 4; q++){
           System.out.print(test_cases[q] + ": ");
           int sum = 0; 
           double avg = 0.0;
           for(int r = 0; r < kTests; r++){
               mergesort(results[q], 0, results[q].length-1);
               sum += results[q][r];
               avg = (double)sum/kTests;
               System.out.print(results[q][r] + " ");
           }
           System.out.println();
           System.out.println("Min: " + results[q][0] + 
                              " Max: " + results[q][results[q].length-1] +
                              " AVG: " + avg);
       }
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