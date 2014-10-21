//***********************************************************************
// FILE NAME    : QuicksortTest.java
// DESCRIPTION  : This file contains the class Quicksort.
// NAME: Sam Couch - couch@temple.edu
//************************************************************************

import java.util.*;

public class QuicksortTest {
    static int qcount = 0;
    
   public static void main(String[] args)
   {
       int[] test_cases = {2048, 4096, 8192, 16384};
       int[][] results = new int[4][10];
       for(int k = 1; k<=4; k++){
           for(int j = 1; j <= 10; j++){
               qcount = 0;
               Random gen = new Random(); 
               int[] a = new int[test_cases[k-1]];
               int i; 
               for (i=0; i<a.length; i++) 
                   a[i]=gen.nextInt(5000)+1;
               System.out.println("Initial array:");
               for (i=0; i<a.length; i++) 
                   System.out.println(a[i] + " ");
               System.out.println();
               quickSort(a, 0, a.length-1);
               System.out.println("Sorted array:");
               for (i=0; i<a.length; i++) 
                   System.out.println(a[i]);
               System.out.println(test_cases[k-1] + " done!");
               results[k-1][j-1] = qcount;
           }
       }
       for(int q = 0; q<4; q++){
           System.out.print(test_cases[q] + ": ");
           int sum = 0; 
           double avg = 0.0;
           for(int r = 0; r<10; r++){
               quickSort(results[q], 0, results[q].length-1);
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

    public static int partition(int arr[], int left, int right){
      int i = left, j = right;
      int temp;
      int pivot = arr[(left + right) / 2];
     
      while (i <= j) {
        while (arr[i] < pivot)
            i++;
        while (arr[j] > pivot)
              j--;
        if (i <= j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        qcount++;
      }
      return i;
    }
 
    public static void quickSort(int arr[], int left, int right) {
//        qcount++;
        int index = partition(arr, left, right);
        if (left < index - 1)
            quickSort(arr, left, index - 1);
        if (index < right)
            quickSort(arr, index, right);
    }
}
