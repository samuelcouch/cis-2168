import java.util.*;



public class Intcoll5tclient


{


    public static final int SENTINEL = 0;



    public static void main(String[] args)


    {


        Intcoll5 P=new Intcoll5(), N=new Intcoll5();


        


        for (int i=1;i<=10;i++)


        {


            P.insert(i); 


        }


        for (int i=1;i<=5;i++)


        {


            N.insert(i); 


        }


        


        System.out.println("First collection:");


        P.print();


        System.out.println("Second Collection:");


        N.print();



        P.copy(N);


        System.out.println("First after copy:");


        P.print();


        System.out.println("Second after copy:");


        N.print();


        


        System.out.println(P.equals(N));


        System.out.println(P.belongs(2));


        P.omit(2);


        System.out.println(P.belongs(2));


        System.out.println(P.get_howmany());


        


    }  


}
