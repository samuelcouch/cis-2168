//***********************************************************************
// FILE NAME    : StringColl.java
// NAME: Sam Couch - couch@temple.edu
//************************************************************************

public class StringColl extends MultistringColl {
    
    //Invoked the constructor
    public StringColl(){
        super();
    }
    
    //Only for backwards compatibility
    public StringColl(int i){
        super(i);
    }
    
    //Override the MultistringColl insert by invoking the belongs method to
    //check if i is already in the collection, since StringColl only supports
    //unique strings in the collection. If it's not, invoke insert as usual.
    public void insert(String i){
        if(!super.belongs(i)){
            super.insert(i);
        }
    }
}
