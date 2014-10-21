//***********************************************************************
// FILE NAME    : MergesortTest.java
// DESCRIPTION  : This file contains the class MergeSort.
// NAME: Sam Couch - couch@temple.edu
//************************************************************************

import java.util.*;

public class InsertionsortTest {
    static int icount = 0;
    
    public static void main(String[] args){
      Random gen = new Random(); 
      int[] a = new int[16384];
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
      System.out.println("Done!");
      System.out.println("icount: " + icount);
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
