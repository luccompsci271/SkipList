
import java.util.*;

// for the superTest method - just to see java reflection in action
import java.lang.reflect.*;

/**
 * A sorted singly-linked list implementation
 * @author: COMP 271: Data structures class, Loyola University Chicago, Spring 2017
 * @version: 5  (3/21/2017, 11:50am)
 */

public class SortedList<E> implements List<E> {

    private int count;    // list size
    private Node<E> head; // ref. to first element

    /* the list constructor - starts with an empty list */
    public SortedList() {
        head = null;
        count = 0;
    }

    // --------------------------------------------------------------------------------------
    // ADD YOUR METHODS UNDER THE PROPER GROUP HEADING
    // --------------------------------------------------------------------------------------

    // --------------------------------------------------------------------------------------
    // Group 1's methods go here

    public boolean add(E e) {
        //Cast e to Comparable to use the CompareTo method
        @SuppressWarnings("unchecked")
        Comparable<E> ce = (Comparable<E>)e;
        // Modified by Patrick Miramontes, written by Dr. Mark V. Albert
        /*
         * Check to see if there is a value at the head node.
         * If there is no value at the head node then set e as the value
         * at the head node and create a reference to the next node.
         */
        /*
        if (head == null) {
            head = new Node<E>(e, null);
            count++;
            return true;
        }
        */
        /*
         * If there is a value at the head node check to see
         * if value e is less than that value.
         * if e is less than that value set e as the new value
         * of the head node and create a reference to the next node.
         */
        if (head == null || ce.compareTo(head.value())< 0) {
            Node<E> oldHead = head;
            Node<E> eNode = new Node<E> (e, oldHead);
            head = eNode;
            count++;
            return true;
        }

        /*
         * Iterate through the list to find the correct location for e
         * Create a new node to hold the value e
         * if e is greater than the value held at the previous node
         * and e is less than the value held at the next node.
         * Create a reference from the new node to the next node.
         */
        for (Node<E> i = head; i != null; i = i.next()) {
            // is the added element < the next element
            if ( (i.next() == null) || (ce.compareTo(i.next().value()) < 0) ) {
                Node<E> x = new Node<E>(e, i.next());
                i.setNext(x);
                count++;
                return true;
            }
        }

        return false;  // should never happen
    }

    public boolean testAdd(boolean verbose)  {
        //creates a new SortedList called testList
        List<Integer> testList = new SortedList<Integer>();

        //creates a second SortedList to compare against testList
        List<Integer> compareList = new SortedList<Integer>();

        //adds 4 integers to compareList in order
        compareList.add(4);
        compareList.add(7);
        compareList.add(7);
        compareList.add(10);

        //adds the same 4 integers to testList out of order if the initial size was zero
        testList.add(7);
        testList.add(4);
        testList.add(10);
        testList.add(7);

        int counter = 0; //to be used to check if list is sorted

        return compareList.equals(testList);
    }

    public boolean addAll(Collection<? extends E> c)  {
        // Written by Prachi Patel
        boolean isChanged = false;
        for(E e:c)
            if(add(e)) {
                isChanged = true;
            }
        return isChanged;
    }

        public static boolean testaddAll(boolean verbose) {
            //By Taryn Chovan

            List<Integer> aList = new SortedList<Integer>();

            //creates a second SortedList to compare against testList
            List<Integer> cList = new ArrayList<Integer>();

            //adds 4 integers to compareList in order
            cList.add(4);
            cList.add(7);
            cList.add(7);
            cList.add(10);

            //adds the same 4 integers to testList out of order if the initial size was zero
            aList.add(7);
            aList.add(4);
            aList.add(10);
            aList.add(7);

            return aList.addAll(cList); //add the collection in the LinkedList & return whether or not it works
        }
    public int indexOf(Object value)  {
        // Written by Jorge Ramirez
        if (isEmpty()){
            return -1;
        }
        Node<E> temp= head;
        for(int i=0; i<count; i++){
            if(temp.value()==value){
                return i;
            }
            temp = temp.next();}
        return -1;
    }

    public static boolean testIndexOf() {
        // Written by Patrick Miramontes
        List<Integer> testList = new SortedList<Integer>();

        // add 5 integers to testList
        testList.add(1);
        testList.add(2);
        testList.add(2);
        testList.add(2);
        testList.add(3);

        // checks if the the returned value of lastIndex returns the value we expect.

        int expectedIndex = 1;
        if(expectedIndex == testList.indexOf(2)){
            return true;
        }
        else {
            return false;
        }

    }

        public int lastIndexOf(Object o) {
            // by Jon Matthew
            // ind starts out at the first index of o
            int ind = indexOf(o);
            // if no indexOf() then no lastIndexOf()
            if (ind == -1) {
                return -1;
            }
            else {
                        // iterates temp node up to node from indexOf()
                Node<E> temp= head;
                for (int i=0; i<ind; i++) {
                                temp = temp.next();
                        }
                        // compares subsequent nodes to object until unequal
                        while (temp.next().value()==o) {
                                temp = temp.next();
                                ind++;
                        }
                        return ind;
                }
        }

    public static boolean testLastIndexOf() {
        // by Jon Matthew and Patrick Miramontes
        List<Integer> testList = new SortedList<Integer>();

        // add 5 integers to testList
        testList.add(1);
        testList.add(2);
        testList.add(2);
        testList.add(2);
        testList.add(3);

        // checks if lastIndexOf(2) is 3 as expected
        int expectedIndex = 3;
        //int attempedIndex = testList.lastIndexOf(2);
        if(expectedIndex == testList.lastIndexOf(2)){
            //System.out.println(testList.lastIndexOf(2));
            return true;
        }
        else {
            //System.out.println(testList.lastIndexOf(2));
            return false;
        }

    }

    // --------------------------------------------------------------------------------------
    // Group 2's methods go here

    //Harmeet Singh
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if(o instanceof SortedList && ((SortedList)o).count == this.count){ //checks to see if the object is also a list and checks if the lists are the same length
            Node<E> thisFinger = head;
            Node<E> oFinger = ((SortedList)o).head; //initializes head pointers
            while((thisFinger != null) && (oFinger != null) ) {
                if( thisFinger.value().equals(oFinger.value()) ) {
                    thisFinger = thisFinger.next();
                    oFinger = oFinger.next();
                } else {
                    return false;
                }
            }
            return true;
        } else return false;

    }

    public static boolean testEquals(boolean verbose) {

        // creates a new SortedList called testList
        List<Integer> listA = new SortedList<Integer>();
        List<Integer> listB = new SortedList<Integer>();
        List<Integer> listC = new SortedList<Integer>();
        List<String> listD = new SortedList<String>();
        List<String> listE = new SortedList<String>();
        List<String> listF = new SortedList<String>();

        listA.add(1);
        listA.add(4);
        listA.add(3);

        listB.add(1);
        listB.add(3);
        listB.add(4);

        listC.add(2);
        //tests for equality in integer lists
        if (listA.equals(listB) == false)
            return false;
        if (listA.equals(listC) == true)
            return false;

        listD.add("one");
        listD.add("two");
        listD.add("zero");

        listE.add("zero");
        listE.add("one");
        listE.add("two");

        listF.add("one");
        //tests for equality in string lists
        if (listD.equals(listE) == false)
            return false;
        if (listD.equals(listF) == true)
            return false;
        //if all checks pass
        return true;
    }

    /*Vasilios Christides
    I would like to dedicate this piece of code to Max Dabek
    Praise be to Harambe*/
    public boolean contains(Object o) {
        Node<E> finger=head;
        if (finger==null)
        {
            return false;
        }
        if (finger.value().equals(o))
        {
            return true;
        }
        while (finger!=null && !finger.value().equals(o))
        {
            finger=finger.next();
            if (finger==null)
            {
                return false;
            }
            if (finger.value().equals(o))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean testContains(boolean verbose) {
        List<Integer> listX = new SortedList<Integer>();
        List<String> listY = new SortedList<String>();
        listX.add(5);
        listX.add(6);
        listX.add(7);
        listY.add("cat");
        listY.add("dog");
        listY.add("mouse");
        if (listX.contains(5))
            return true;
        if (listY.contains("cat"))
            return true;
        if (listX.contains(20))
            return false;
        if (listY.contains("monkey"))
            return false;
        return true;
    }

    public boolean containsAll(Collection<?> c) {
        if (c.isEmpty()) {
            return true;
        }

        if (this.count < c.size()) {
            return false;
        }

        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    public static boolean testContainsAll(boolean verbose) {
        List<Integer> listA = new ArrayList<Integer>();
        List<Integer> listB = new ArrayList<Integer>();
        List<Integer> listC = new ArrayList<Integer>();

        List<String> listD = new ArrayList<String>();
        List<String> listF = new ArrayList<String>();

        listA.add(5);
        listA.add(6);
        listA.add(7);
        listB.add(5);
        listB.add(6);
        listC.add(5);

        listD.add("cat");
        listD.add("dog");
        listF.add("cat");
        listF.add("monkey");

        if (listA.containsAll(listB) && listB.containsAll(listC) && !(listD.containsAll(listF))) {
            return true;
        }
        return false;
    }


    /**
     * Created by BillyTsakalios on 3/17/17.
     */
    public List<E> subList(int fromIndex, int toIndex) {

        if (fromIndex < 0 || toIndex > count+1) {
            throw new IndexOutOfBoundsException("You have entered an invalid index.");
        }

        List <E> sub = new SortedList<>();
        Node<E> temp = head;

        for (int i=0; i < toIndex; i++) {
            if (i>= fromIndex) {

                sub.add(temp.value());

            }
            temp = temp.next();
        }
        return sub;
    }

    public static boolean testSubList() {
        ArrayList<String> nameList = new ArrayList<String>();

        nameList.add("Billy");
        nameList.add("Max");
        nameList.add("Vasilios");
        nameList.add("Harmeet");
        nameList.add("Austin");

        ArrayList<String> newList = new ArrayList<String>(nameList.subList(2,4));

        if(nameList.containsAll(newList)){
            return true;
        }
        return false;
    }

    // --------------------------------------------------------------------------------------
    // Group 3's methods go here

    public int size() { //David Cheng
    // The size of the list is stored in count
        return this.count;
    }

    public static boolean testSize() { // Don Stolz

    // Construct a new list
        SortedList<Integer> testList = new SortedList<Integer>();
    // Test function for 0
        if (testList.size() != 0) {
            return false;
        }
    // Construct a random number for testing
        Random numGenerator = new Random();
        int  numR = numGenerator.nextInt(25) + 1;
    // Add random number of elements to the list
        for (int q = 0; q < numR; q++) {
            testList.add(1);
        }
    // Declare variable for result
        int result = testList.size();
    // Compare result to numR
        if (result == numR) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() { // David Cheng

    // If the list is empty the head will not be referencing an element
        if (this.head == null) {
            return true;
        }
        return false;
    }

    public static boolean testIsEmpty() { // Don Stolz

    // Declare variable for function results
        boolean resEmpty;
        boolean resPart;
    // Construct a new list
        SortedList<Integer> testList = new SortedList<Integer>();
    // Test with empty list (Should return true)
        resEmpty = testList.isEmpty();
        if (!resEmpty) {
            return false;
        }
    // Add element to list
        testList.add(1);
    // Test (Should return false)
        if (testList.isEmpty()) {
            return false;
        }
        return true;
    }

    public void clear() { // Grant Smith & Don Stolz

    // We set the head to null
        this.head = null;
    // The garbage collector will remove the objects for us
        return;
    }

    public static boolean testClear() { // David Jirles

    // Constuct & fill new list
        SortedList<Integer> testList = new SortedList<Integer>();
        testList.add(1);
    // Use clear function
        testList.clear();
    // Check true with isEmpty()
        if (testList.testIsEmpty())
        {
            return true;
        }
        return false;
    }

    public E get(int index) { // David Jirles
        //sets pointer equal to head (start of the collection)
        Node<E> pointer = head;
        /* if the requested index is less than zero, or if the requested index is
         * greater than the size of collection an exception is thrown and the ouput prints
         * "chosen index is out of bounds"*/
        if ( index < 0 || index >= this.count ) {
            throw new IndexOutOfBoundsException ("chosen index is out of bounds");
        } else {
            //uses a for loop to move the pointer to the desired index in the location
            for (int i = 0; i < index; i++){
                pointer = pointer.next();
            }
            /*return the value of the pointer after it has been moved to the derired index in
             *the collection*/
            return pointer.value();
        }
    }

    public static boolean testGet (boolean verbose)  {
        // FIX: test with a longer list and more scenarios
        //creates a SortedList called myList of type string to be filled and testes
        SortedList<String> myList = new SortedList<String>();
        //adds the letter a to the SortedList
        myList.add("a");
        //runs the E get (index) method
        String m = myList.get(0);
        //if the E get (index) method returns a, boolean testGet() returns true
        if (m.equals("a")){
            return true;
        } else {
            //if the E get (index) method does not return a, boolean testGet() returns false
            return false;
        }
    }

    public E getQuantile(double quantile) { // Nick Coteus

    // Check that quantile is valid
        if (quantile < 0 || quantile > 1) {
            throw new IndexOutOfBoundsException ("chosen quantile is out of bounds");
        }
    // Multiply quantile by the size, cast double into int
        int index = (int) (quantile * this.count);
    // Return the value of the pointer
        return this.get(index);
    }

    public static boolean testGetQuantile() { // Don Stolz
    // Create new list
        SortedList<Integer> testList = new SortedList<Integer>();
    // Fill list with 0 - 100 ints in order
        for (int q = 0; q <= 100; q++) {
            testList.add(q);
        }
    // Create random value 0-100 (101)
        Random numGenerator = new Random();
        int  numR = numGenerator.nextInt(101);
    // Create testQ (/100)
        double testQ = numR / 100.0;
    // Create variable to store result
        int result = testList.getQuantile(testQ);
    // Check value of result
        if (result == numR) {
            return true;
        }
        return false;
    }


    // --------------------------------------------------------------------------------------
    // Group 4's methods here

    public Iterator<E> iterator()  {
        return new SortedListIterator<E>(this);
    }

    public ListIterator<E> listIterator() {
        return new SortedListIterator<E>(this);
    }

    public ListIterator<E> listIterator(int index) {
        return new SortedListIterator<E>(this, index);
    }

    public class SortedListIterator<E> implements ListIterator<E>
    {

        private SortedList<E> theSortedList;  // the list being iterated through
        private Node<E> current;    // the "finger" on the current node
        private int current_index;  // holds the index that the list iterator is at

        public SortedListIterator(SortedList<E> s)  {
            theSortedList=s;
            current = s.head;
            current_index = 0;
        }

        public SortedListIterator(SortedList<E> s, int index) {
            theSortedList = s;
            current_index = index;
            current = s.head;
            for (int i=0; i<index; i++) {
                current = current.next();
            }
            /*
             * for the test, we want to start at whatever spot we want
             * when we use a the for loop, we are making sure we end up at the index we are told to be at
             */
            //going to have to create code here that gets to the location in the index

        }

        public boolean hasNext() {
            return current_index < theSortedList.count;
        }

        public boolean hasPrevious() {
            return current_index > 0;
        }

        public E next()  {

            if(this.hasNext()){
                current_index++;
                E temp = current.value();
                current = current.next();
                return temp;
            }
            else{
                throw new NoSuchElementException();
            }
        }

        public int nextIndex()  {
            return current_index+1;
        }

        public E previous() {
            if (this.hasPrevious()) {
                // the index of the previous node
                current_index--;

                current = theSortedList.head;
                // considering the case that we are at the beginning of the list
                /// an exceptional circumstance
                if (current_index == -1)
                    return current.value();

                // otherwise, point to the node one step ahead, and return the next node's value
                int cnt = 0;
                while (cnt < current_index) {
                    current = current.next();
                    cnt++;
                }
                return (current.next().value());
            } else {
                throw new NoSuchElementException();
            }
        }

        public int previousIndex()  {
            return current_index-1;
        }

        public void reset() {
             current = theSortedList.head;
             current_index = 0;
        }

        // unsupported methods for the iterator
        // these don't make sense for a SortedList implementation
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }  // end the SortedListIterator



    //The following tests the iterator implementations accordingly
    //ADJUST THE RETURN FALSE: IF WE TEST THESE, NO MATTER WHAT THEY RETURN FALSE... We're supposed to edit them

    public static boolean testIterator() {
        SortedList<Integer> mySL = new SortedList<Integer>();

        mySL.add(5);
        mySL.add(10);
        mySL.add(20);
        mySL.add(30);
        // skip index 0 by starting at 1
        Iterator<Integer> it = mySL.listIterator();
        if((it.next().equals(5)) && (it.next().equals(10)))
        {
            return true;
        } else {
            return false;
        }
    }

    public static boolean testListIterator () {

        SortedList<Integer> mySL = new SortedList<Integer>();

        mySL.add(5);
        mySL.add(10);
        mySL.add(20);
        mySL.add(30);
        // skip index 0 by starting at 1
        Iterator<Integer> it = mySL.listIterator();
        if((it.next().equals(5)) && (it.next().equals(10)))
        {
            return true;
        } else {
            return false;
        }
    }



    public static boolean testListIteratorWithIndex() {
        SortedList<Integer> mySL = new SortedList<Integer>();

        mySL.add(5);
        mySL.add(10);
        mySL.add(20);
        mySL.add(30);

        // skip index 0 by starting at 1
        Iterator<Integer> it = mySL.listIterator(1);
        if((it.next().equals(10)) && (it.next().equals(20)))
        {
            return true;
        } else {
            return false;
        }
    }

    // --------------------------------------------------------------------------------------
    // Group 5's methods go here

    public boolean remove(Object o) {
        Node<E> finger = head;
        Node<E> previous = null;
        while (finger != null && !finger.value().equals(o))
        {
            previous = finger;
            finger = finger.next();
        }
        // finger points to target value or null
        if (finger != null) {
            // we found element to remove
            if (previous == null) // it is first item
            {
                head = finger.next();
            } else { // it's not first
                previous.setNext(finger.next());
            }
            count--;
            return true;
        }
        // didn't find it, return false
        return false;
    }

    public static boolean testRemoveObj(boolean verbose) {
        // FIX restructure the tests to be more readable. Consider more testing
        SortedList<Integer> intList = new SortedList<Integer>();
        intList.add(4); // adding ints to the List
        intList.add(5);
        intList.add(8);

        boolean a = intList.remove(new Integer(6)); //removing an int that is NOT present
        if (a == true)
            return false;

        boolean b = intList.remove(new Integer(5)); // removing an int that is present
        if (b == false)
            return false;

        SortedList<Integer> sameAsList = new SortedList<Integer>();
        sameAsList.add(4); // adding ints to the List
        sameAsList.add(8);

        return intList.equals(sameAsList);
    }
    //Received helped from tutor, Tom B
    public E remove(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Your index was out of bounds:" + index);
            }
            Node<E> eliminate;
                if (index == 0){
                    eliminate = head;
                    head=head.next();
            }
            else{
                Node<E> tracker = head;
                for (int i = 0; i < index-1; i++) {
                    tracker = tracker.next();
            }
                eliminate = tracker.next();
                tracker.setNext(eliminate.next());
            }
                count--;
            return eliminate.value();
    }

    public static boolean testRemoveInd() {
        SortedList<Integer> intList = new SortedList<Integer>();
        intList.add(1); // adding ints to the List
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.add(5);
        int test1=intList.remove(2);
        //testing remove at any index that is not at the ends
        int test2=intList.remove(0);
        //testing remove at beginning
        int test3=intList.remove(2); //testing remove at end
        if(test1==(3) && test2==(1)&& test3==(5)){
            return true;
        }else{
            return false;
        }
    }

    public boolean retainAll(Collection<?> c) {
        if(head==null || count == 0){
            throw new UnsupportedOperationException();
        }
        else{

            Node<E> pointer = head;
            while (pointer!=null){
                if (!c.contains(pointer.value())){
                    remove(pointer.value());
                }
                pointer = pointer.next();
            }

        }
        return false;
    }

    public boolean testRetainAll() {
        SortedList<Integer> intList = new SortedList<Integer>();
        intList.add(1); // adding ints to the List
        intList.add(2);
        intList.add(2);//duplicate
        intList.add(3);
        intList.add(4);
        intList.add(5);



        ArrayList<Integer> NewList = new ArrayList<Integer>();
        NewList.add(1); // adding ints to the List
        NewList.add(3);
        NewList.add(5);

        SortedList<Integer> sameAsList = new SortedList<Integer>();
        sameAsList.add(1); // adding ints to the List
        sameAsList.add(3);
        sameAsList.add(5);

        intList.retainAll(NewList);

        boolean a = intList.equals(sameAsList);

        if(a != false){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean removeAll(Collection<?> c) {
        if(head==null || count == 0){
            throw new UnsupportedOperationException();
        }
        else{

            Node<E> pointer = head;
            while (pointer!=null){
                if (c.contains(pointer.value())){
                    remove(pointer.value());
                }
                pointer = pointer.next();
            }

        }
        return false;
    }



    public boolean testRemoveAll() {
        SortedList<Integer> intList = new SortedList<Integer>();
        intList.add(1);// adding ints to the List
        intList.add(3);
        intList.add(3);//duplicate
        intList.add(4);
        intList.add(5);
        intList.add(5);//duplicate
        intList.add(7);

        ArrayList<Integer> NewList = new ArrayList<Integer>();
        NewList.add(1); // adding ints to the List
        NewList.add(3);
        NewList.add(5);

        intList.removeAll(NewList);//Should remove everything but 4 & 7

        SortedList<Integer> sameAsList = new SortedList<Integer>();
        sameAsList.add(4);
        sameAsList.add(7); // adding ints to the List to test if they are equal

        boolean a = intList.equals(sameAsList);

        if(a != true){
            return false;
        }
        else{
            return true;
        }

    }
    // --------------------------------------------------------------------------------------
    // Group 6's methods go here


    public int hashCode() {
        //Written by Gabe Borcean, Myles Lucas, and Emily He
        // rewritten by Prof Albert

        int hashCode = 1;
        Node finger = head;
        while (finger != null) {
            hashCode = 31*hashCode +
                (finger == null ? 0 : finger.value().hashCode());
            finger = finger.next();

        }
        return hashCode;
    }
    /*  older version for comparison
    public int hashCode() {
        //Written by Gabe Borcean, Myles Lucas, and Emily He

        int hashCode = 1;
        while (indexOf(head) <= count) { //compares the index of head to count (list size) and iterates through the list
            hashCode = 31*hashCode() + (head== null ? 0 : head.hashCode()); //conditional operator: if the head is null, multiply by 0.
                                                                            // If not, multiply it by the head's value
            head = head.next();             //moves on to the next head in the list

        } return hashCode;
    }
    */

    public static boolean testHashCode() {
        //Written by Gabe Borcean, Myles Lucas, and Emily He

        //Creates two Array List of type Integer object to be tested with the HashCode function

        ArrayList<Integer> list1 = new ArrayList<Integer>();  //Creates a new, resizeable ArrayList with object Integer as the parameter
        list1.add(20);
        list1.add(12);
        list1.add(6);

        ArrayList<Integer> list2 = new ArrayList<Integer>();    //Creates a new, resizeable ArrayList with object Integer as the parameter
        list2.add(20);
        list2.add(12);
        list2.add(6);

        return list2.hashCode() == list1.hashCode(); //compares the results of both lists to determine if the each HashCode is the same
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a){
        // FIX not necessary to add null element if creating new array
        // only necessary if using passed array T which is too large
        // FIX unchecked type casting issue
        Node<E> finger = head;
        int i = 0;

        //create new array if list will not fit in passed array
        if(this.size() >= a.length){
            //only way java allows generic array creation
            T[] arr = (T[]) new Object[this.size()+1];//one extra space for null element

            while(finger.next() != null){
                arr[i] = (T)finger.value();//fill new array
                finger = finger.next();
                i++;
            }
            arr[i] = (T)finger.value();//add last element
            arr[i+1] = null; //set last element to null
            return arr;
        }
        //use passed array since it is big enough
        else{
            while(finger.next() != null){
                a[i] = (T)finger.value();//fill passed array
                finger = finger.next();
                i++;
            }
            a[i] = (T)finger.value();//add last element
            a[i+1] = null; //set last element to null
            return a;
        }

    }


    public static boolean testToArrayT() {
        // FIX test with an empty array, passed array that is too small,
        //  and a passed array that is too large

        SortedList<String> testList = new SortedList<String>();
        testList.add("zero");
        testList.add("one");
        testList.add("two");

        String[] s = {"test", "array", "with", "strings"};
        testList.toArray(s);
        if( s[0].equals("one") && s[1].equals("two") && s[2].equals("zero") && (s[3] == null) )
            return true;
        else
            return false;
    }

    public Object[] toArray(){
        //Written by Gabe Borcean, Myles Lucas, and Emily He

        Object[] arr = new Object[count+1];     //create an empty Array with the size of list +1
        while (indexOf(head) <= count) {        //Loops through the Array by comparing it to count (list size)
            arr[head.hashCode()] = head;        //References the head of the hashCode and creates an array
            head = head.next();
        }

        return arr;
    }

    public static boolean testToArrayObj() {
        //Written by Gabe Borcean, Myles Lucas, and Emily He

        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(20);
        list1.add(12);
        list1.add(6);
        //System.out.println(Arrays.asList(list1.toArray()));     //returns a List reference to ArrayList while putting list1 into an array
        //System.out.println(list1.hashCode());

        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(72);
        list2.add(3);
        list2.add(6);
        //System.out.println(Arrays.asList(list2.toArray()));     //returns a List reference to ArrayList while putting list2 into an array
        //System.out.println(list2.hashCode());

        return list1.equals(list2);                     //Test fails because the lists hold different values/Passes if values are the same

    }

    //-----------------------------------------------------------------------------------------------
    // functions to get the compiler to agree to implement the list interface
    // these functions don't make sense in a SortedList implementation
    // and they are techincally (optional)

    public void add(int index, E e) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    public E set(int index, E e) {
        throw new UnsupportedOperationException();
    }

    //--------------------------------------------------------------------------------------------

    /** superTest runs the test methods of the program and displays if they worked
     * @author: Mark Albert
     *
     * Note: this method uses java reflection to run only the methods
     * with 'test' as the first four letters of the method.
     */
    public static void superTest() {
        Class<?> c;
        Object retobj;

        // setup for running the test methods using reflection
        try {
            c = Class.forName("SortedList");
        } catch (ClassNotFoundException e) {
            return;
        }
        Method m[] = c.getDeclaredMethods();
        SortedList<Integer> myList = new SortedList<Integer>();

        boolean didWork = false;
        Object [] falseObjArray = new Object[1];
        falseObjArray[0] = new Boolean(false);

        System.out.println("\nTESTING THE SORTEDLIST...\n");

        // iterate through the methods of SortedList
        for (int i = 0; i < m.length; i++) {
            String methodName = m[i].toString().substring(m[i].toString().indexOf("SortedList.") + 11);
            // are the first 4 letters 'test'?
            if (methodName.indexOf("test") > -1) {
                // then execute the test method
                Class<?> pvec[] = m[i].getParameterTypes();
                try {
                    // if the test method has no parameters
                    if (pvec.length == 0) {
                        retobj = m[i].invoke(myList);
                        // if the test method has one, it's the verbose flag - give it false
                    } else {
                        retobj = m[i].invoke(myList, falseObjArray);
                    }
                } catch (IllegalAccessException e) {
                    System.out.println("IllegalAccessException");
                    return;
                    // this exception happens if the method ran throws an exception during execution
                } catch (InvocationTargetException e) {
                    System.out.println("EXCEPTION THROWN from: " + methodName);
                    retobj = (Object) new Boolean(false);
                }

                didWork = (Boolean) retobj;

                // show if the test method worked or failed
                if (didWork) {
                    System.out.println(" Passed: " + methodName);
                } else {
                    System.out.println("FAILED: " + methodName);
                }
            }
        }
    }

    public static void main(String[] args) {
        superTest();
    }

}  // end SortedList definition
