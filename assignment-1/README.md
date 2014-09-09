Assignment 1
============

## Part 1: Intcoll1
Member objects:
* c[]
  * Contains array of integers

Constructors: 
* Intcoll1()
  * Creates an Intcoll1 instance with capacity for 500 integers + the sentinel (500+1)
* Intcoll1(int i)
  * Creates an Intcoll1 instance with capacity for *i* integers + the sentinel (i+1)

Methods:
* void copy(Intcoll1 obj)
* boolean belongs(int i)
* void insert(int i)
* void omit(int i)
* int get_howmany()
* void print()
* boolean equals(Intcoll1 obj)

### Test Run
![alt text](images/intcoll1.png "Intcoll1 Test Run")

Test flow:

1. Check if *input* is equal to the *SENTINEL* 
1. Check if number is positive or negative
  * Positive numbers get placed in collection P
  * Negative number get placed in collection N
1. If the number is positive
  * Call the *insert(input)* method on collection P
  * Call the *insert(input)* method on collection L
1. If the number is negative
  * Call the *insert(-input)* method on collection N
  * Call the *omit(-input)* method on collection L
1. Output all 3 collections using the *print()* method on each collection

## Part 2: Intcoll2
Member objects:
* c[]
  * Contains array of integers
* how_many
  * Contains a count of how many integers we have in the collection

Constructors: 
* Intcoll2()
  * Creates an Intcoll1 instance with capacity for 500 integers + the sentinel (500+1)
* Intcoll2(int i)
  * Creates an Intcoll1 instance with capacity for *i* integers + the sentinel (i+1)

### Test Run
![alt text](images/intcoll2.png "Intcoll2 Test Run")

Test flow:
1. Check if *input* is equal to the *SENTINEL* 
1. Check if number is positive or negative
  * Positive numbers get placed in collection P
  * Negative number get placed in collection N
1. If the number is positive
  * Call the *insert(input)* method on collection P
  * Call the *insert(input)* method on collection L
1. If the number is negative
  * Call the *insert(-input)* method on collection N
  * Call the *omit(-input)* method on collection L
1. Output all 3 collections using the *print()* method on each collection

## Part 3: Intcoll3
Member objects:
* c[]
  * Contains an array of booleans, true if the index value is in the collection, false otherwise
* how_many
  * Contains a count of how many integers we have in the collection
 
### Test Run
![alt text](images/intcoll3.png "Intcoll3 Test Run")

Test flow:
1. Check if *input* is equal to the *SENTINEL* 
1. Check if number is positive or negative
  * Positive numbers get placed in collection P
  * Negative number get placed in collection N
1. If the number is positive
  * Call the *insert(input)* method on collection P
  * Call the *insert(input)* method on collection L
1. If the number is negative
  * Call the *insert(-input)* method on collection N
  * Call the *omit(-input)* method on collection L
1. Output all 3 collections using the *print()* method on each collection

Unique for *intcoll3*, if the input number is larger than the length of *c[]*, the size of *c[]* is increased for more space.