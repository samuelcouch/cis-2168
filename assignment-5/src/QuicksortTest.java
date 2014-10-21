//***********************************************************************
// FILE NAME    : QuicksortTest.java
// DESCRIPTION  : This file contains the class Quicksort.
// NAME: Sam Couch - couch@temple.edu
//************************************************************************

import java.util.*;

public class QuicksortTest {
    static int numcalls = 0, qcount = 0;
    
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
      quickSort(a, 0, a.length-1);
      System.out.println("Sorted array:");
      for (i=0; i<a.length; i++) 
          System.out.println(a[i]);
      System.out.println("Done!");
      System.out.println("qcount: "+ qcount);
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
      }
      return i;
    }
 
    public static void quickSort(int arr[], int left, int right) {
        qcount++;
        int index = partition(arr, left, right);
        if (left < index - 1)
            quickSort(arr, left, index - 1);
        if (index < right)
            quickSort(arr, index, right);
    }
}
