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
       int[] test = {5, 4, 3, 2, 1};
       insertionsort(test);
   }
    
    public static void insertionsort(int[] arr) {
        int i, j, newValue;
        for (i = 1; i < arr.length; i++) {
            newValue = arr[i];
            j = i;
            System.out.println("\n" + i + ". Before: \n");
            for(int k = 0; k < arr.length; k++)
                System.out.println(arr[k]);
            while (j > 0 && arr[j - 1] > newValue) {
                arr[j] = arr[j - 1];
                
                j--;
                icount++;
                
            }
            arr[j] = newValue;
            System.out.println("\n" + i + ". After: \n");
            for(int q = 0; q < arr.length; q++)
                System.out.println(arr[q]);
        }
    }
}
