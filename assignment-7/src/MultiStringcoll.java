//***********************************************************************
// FILE NAME    : Stringcoll.java
// DESCRIPTION  : This file contains the class Intcoll6.
// NAME: Sam Couch - couch@temple.edu
//************************************************************************

import java.util.*;

public class MultiStringcoll {
    private static class btNode {
       int dupe_counts;
       String info; 
       btNode left; 
       btNode right;

       private btNode(String s, btNode lt, btNode rt)
       {
          dupe_counts = 1;
          info = s; 
          left = lt; 
          right = rt;  
       }

       private btNode()
       {
          dupe_counts = 0;
          info = ""; 
          left = null; 
          right = null;
       }
   }
}
