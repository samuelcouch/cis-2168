//***********************************************************************
// FILE NAME    : MergesortTest.java
// DESCRIPTION  : This file contains the class MergeSort.
// NAME: Sam Couch - couch@temple.edu
//************************************************************************

import java.util.*;

public class InsertionsortTest {
    static int icount = 0;
    
   public static void main(String[] args)
   {
       int[] test_cases = {2048, 4096, 8192, 16384};
       int[][] results = new int[4][10];
       for(int k = 1; k<=4; k++){
           for(int j = 1; j <= 10; j++){
               icount = 0;
               Random gen = new Random(); 
               int[] a = new int[test_cases[k-1]];
               int i; 
               for (i=0; i<a.length; i++) 
                   a[i]=gen.nextInt(5000)+1;
               System.out.println("Initial array:");
               for (i=0; i<a.length; i++) 
                   System.out.println(a[i] + " ");
               System.out.println();
               insertionsort(a);
               System.out.println("Sorted array:");
               for (i=0; i<a.length; i++) 
                   System.out.println(a[i]);
               System.out.println(test_cases[k-1] + " done!");
               results[k-1][j-1] = icount;
           }
       }
       for(int q = 0; q<4; q++){
           System.out.print(test_cases[q] + ": ");
           int sum = 0; 
           double avg = 0.0;
           for(int r = 0; r<10; r++){
               insertionsort(results[q]);
               sum += results[q][r];
               avg = (double)sum/(results[q].length-1);
               System.out.print(results[q][r] + " ");
           }
           System.out.println();
           System.out.println("Min: " + results[q][0] + 
                              " Max: " + results[q][results[q].length-1] +
                              " AVG: " + avg);
       }
   }
    
    public static void insertionsort(int[] arr) {
        int i, j, newValue;
        for (i = 1; i < arr.length; i++) {
            newValue = arr[i];
            j = i;
            while (j > 0 && arr[j - 1] > newValue) {
                arr[j] = arr[j - 1];
                j--;
                icount++;
            }
            arr[j] = newValue;
        }
    }
}
