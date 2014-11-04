//***********************************************************************
// FILE NAME    : MultistringColl.java
// NAME: Sam Couch - couch@temple.edu
//************************************************************************

public class MultistringColl {
    private static class btNode {
        public btNode l;
        public btNode r;
        public String info;
        public int count;
        
        public btNode() {
            l = null; 
            r = null; 
            info = "";
            count = 1;
        }
        
        public btNode(String i, btNode lt, btNode rt){
            l = lt; 
            r = rt; 
            info = i;
            count = 1;
        }
        
        public btNode(String i, btNode lt, btNode rt, int n) {
            l = lt; 
            r = rt; 
            info = i;
            count = n;
        }
    }
    
    private btNode c;
    private int how_many;

    // Initializes Stringcoll instance which can hold 500 ints
    public MultistringColl()
    {
        c = null;
        how_many = 0;
    }

    // Initializes Stringcoll instance which can hold i ints
    public MultistringColl(int i)
    {
        c = null;
        how_many = 0;
    }

    // Copys contents of Stringcoll instance named obj to this
    public void copy(MultistringColl obj)
    {
        if (this != obj)
        {
            c = btclone(obj.c);
            how_many = obj.how_many;
        }
    }

    // Returns true if collection contains int i, false if otherwise
    public boolean belongs(String i)
    {
        btNode p = c;
        while(p!=null && !p.info.equals(i)) {
            if(p.info.compareToIgnoreCase(i) < 0)
                p = p.r;
            else
                p = p.l;
        }
        return p!=null;
    }

    // Inserts i into collection. If collection already contains i, function returns
    //   if collection is filled to capacity, capacity is doubled
    public void insert(String i)
    {
        if(c==null) {
            c = new btNode(i, null, null);
            how_many++;
            return;
        }

        btNode p = c;
        btNode prev = p;
        
        while(p!=null && !p.info.equals(i)) {
            prev = p;
            if(p.info.compareToIgnoreCase(i) < 0)
                p = p.r;
            else
                p = p.l;
        }

        if(p==null) {
            if(prev.info.compareToIgnoreCase(i) < 0)
                prev.r = new btNode(i, null, null);
            else
                prev.l = new btNode(i, null, null);
        }
        
        else
            p.count++;
        
        how_many++;
    }

    // If collection contains i, i will be omitted
    public void omit(String i)
    {
        btNode n_w_i = c;
        btNode prev = null;
        while(n_w_i != null && !n_w_i.info.equals(i)) {
            prev = n_w_i;
            if(n_w_i.info.compareToIgnoreCase(i) < 0)
                n_w_i = n_w_i.r;
            else
                n_w_i = n_w_i.l;
        }
       
        //If all we're doing is removing 1 count of 1.
        if(n_w_i != null){
            n_w_i.count--;
            this.how_many--;
        }
        
        //Otherwise we have to remove the whole node
        if(n_w_i != null && n_w_i.count == 0) {
            btNode q = n_w_i;
            if(n_w_i.r == null)
                q = n_w_i.l;
            else if(n_w_i.l==null)
                q = n_w_i.r;
            else {
                btNode j = n_w_i.l;
                if(j.r==null) {
                    q = j;
                    q.r = n_w_i.r;
                } else {
                    while(j.r.r!=null)
                        j = j.r;
                    q = j.r;
                    j.r = q.l;
                    q.r = n_w_i.r;
                    q.l = n_w_i.l;
                }
            }

            if(prev == null)
                c = q;
            else if(prev.r == n_w_i)
                prev.r = q;
            else
                prev.l = q;

            how_many--;
        }
    }

    // Returns amount of ints stored in collection
    public int get_howmany()
    {
        return how_many;
    }

    // Prints contents of collection to output
    public void print()
    {
        btprint(c);
    }

    // returns true if both Stringcoll instances contain
    //   identical int collections
    public boolean equals(MultistringColl obj)
    {
        if(how_many != obj.how_many)
            return false;
        String a1[] = new String[how_many];
        String a2[] = new String[how_many];
        int i=0;
        btToArray(c, a1, i);
        i=0;
        btToArray(obj.c, a2, i);

        boolean ret = true;
        for(i=0; ret && i<how_many; i++)
            ret = (a1[i].equals(a2[i]));

        return ret;
    }

    private static btNode btclone(btNode b) {
        if(b==null)
            return null;

        return new btNode(b.info, btclone(b.l), btclone(b.r));
    }

    private static int btToArray(btNode b, String[] a, int i) {
        if(b!=null) {
            i = btToArray(b.l, a, i);
            a[i++] = b.info; // i++ returns i before it adds 1, so a[i] == a[i++] != a[++i]
            i = btToArray(b.r, a, i);
        }
        return i;
    }

    private static void btprint(btNode b) {
        if(b==null)
            return;
        btprint(b.l);
        System.out.printf("%s (%d):: L: %s, R: %s\n", b.info, b.count, (b.l != null) ? b.l.info : "NULL", (b.r != null) ? b.r.info : "NULL");
        btprint(b.r);
    }
}
