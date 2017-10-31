// https://docs.oracle.com/javase/8/docs/api/java/util/List.html

import java.util.*;
import java.lang.reflect.*;
import java.util.ArrayList;

public class SkipList<E> implements List<E>
{
    private static final int MAX_LEVELS = 30;

    private int count;    // list size
    private ArrayList<Node<E>> heads;

    /* the list constructor - starts with an empty list */
    public SkipList() {
        count = 0;
        heads = new ArrayList<Node<E>>(MAX_LEVELS);
        // initialize with null since ArrayLists start empty
        for (int i = 0; i < MAX_LEVELS; i++) {
            heads.add(i,null);
        }
    }

    // Group 1

    // Written as a group with Dr. Albert
    public boolean add(E e) {
        //Cast e to Comparable to use the CompareTo method
        @SuppressWarnings("unchecked")
        Comparable<E> ce = (Comparable<E>)e;

        Node<E> newNode = new Node<E>(e);
        int newNodeLevels = newNode.levels();

        // filling addPath with all the nodes to potentially be updated
        ArrayList<Node<E>> addPath = new ArrayList<Node<E>>(MAX_LEVELS);
        for (int i = 0; i < MAX_LEVELS; i++) {
            addPath.add(i,null);
        }

        Node<E> finger = null; // which means starting at the head
        for (int lvl = MAX_LEVELS - 1; lvl >= 0; lvl--) {
            if (finger == null) { // when finger is at the head
                if (heads.get(lvl) == null || ce.compareTo(heads.get(lvl).value()) < 0) {
                    addPath.set(lvl, null); // we'll have to update the head
                } else { // traveling on the level
                    finger = heads.get(lvl);
                    while ( (finger.next(lvl) != null)
                        && ce.compareTo(finger.next(lvl).value()) > 0 ) {
                        finger = finger.next(lvl);
                    }
                    addPath.set(lvl, finger);
                }
            } else { // finger is at a node
                while ( (finger.next(lvl) != null)
                    && ce.compareTo(finger.next(lvl).value()) > 0 ) {
                    finger = finger.next(lvl);
                }
                addPath.set(lvl,finger);
            }
        }

        // insert the new node in the skiplist
        for (int lvl = 0; lvl < newNodeLevels; lvl++) {
            if (addPath.get(lvl) == null) { // flag for the heads pointers
                newNode.setNext(lvl,heads.get(lvl));
                heads.set(lvl,newNode);
            } else { // affects a node
                newNode.setNext(lvl,addPath.get(lvl).next(lvl));
                addPath.get(lvl).setNext(lvl, newNode);
            }
        }

        count++;
        return true;
    }

    // initial version written by Dr. Albert
    public boolean testAdd(boolean verbose)  {
        //Written by Clair
        //creates a new SkipList called testList
        List<Integer> testList = new SkipList<Integer>();

        testList.add(7);
        testList.add(4);
        testList.add(10);
        testList.add(7);


        return (
                (testList.get(0) == 4) &&
                        (testList.get(1) == 7) &&
                        (testList.get(2) == 7) &&
                        (testList.get(3) == 10) &&
                        (testList.size() == 4)
        );

        //return compareList.equals(testList);
    }

    // Written by Clair, Sophia, Sofia, Kelsey
    public boolean addAll(Collection<? extends E> c) {

        boolean isDifferent = false;
        for (E e:c)
            if (add(e)) {
                isDifferent = true;
            }
        return isDifferent;
    }
    //
    public boolean testaddAll(boolean verbose) {
        // Written by Karson, Sofia and Maribel
        List<Integer> testList = new SkipList<Integer>();

        int testValue1 = 5;
        testList.add(7);
        testList.add(4);
        testList.add(10);
        testList.add(7);
        testList.add(testValue1);

        if ((testList.get(0) == 4) && (testList.get(5)==1)&&
                (testList.get(2) == 7) &&
                (testList.get(3) == 7) &&
                (testList.get(4) == 10) &&
                (testList.size() == 5)) {
            System.out.println( testValue1  + "has been placed in the correct index of the SkipList");
            return true;

        }
        else
            return false;


    }

    // Written by  Karson
    public int indexOf(Object number) {
        // Written by
        int index = 0;
        if (isEmpty()) {
            return -1;
        }
        Node<E> temp = heads.get(heads.size() - 1);
        int i = 0;
        boolean flag = false;
        temp = heads.get(heads.size() - 1);

        while (temp.next(temp.levels() - 1) != null) {
            temp = temp.next(temp.levels() - 1);
            if (temp.value() == number) {
                flag = true;
                break;

            }
            index++;
        }
        if (flag){
            return index;

        }
        else{
            return -1;
        }
    }


    public static boolean testindexOf(){
        // Written by Karson, Sofia and Maribel
        List<Integer> testList = new SkipList<Integer>();
        int testValue2 = 10;
        int assumedIndex = 3;
        testList.add(7);
        testList.add(4);
        testList.add(testValue2);
        testList.add(7);

        if (assumedIndex == testList.indexOf(testValue2)){
            System.out.println(testValue2 + " is located at the index of " + assumedIndex);
            return true;

        }
        else {
            return false;
        }
    }
    // Written by Clair, Sophia, Sofia, Kelsey
    public int lastIndexOf(Object m) {

        int index = indexOf(m);
        if (index == -1) {
            return -1;
        }
        else {
            Node<E> temp = heads.get(0);
            for (int i = 0; i < index; i++) {
                temp = temp.next(0);
            }
            while (temp.next(0).value() == m) {
                temp = temp.next(0);
                index++;
            } return index;
        }
    }

    public static boolean testlastIndexOf() {
        // Written by Sofia and Maribel
        List<Integer> testList = new SkipList<Integer>();
        int testValue3 = 7;
        testList.add(testValue3);
        testList.add(4);
        testList.add(10);
        testList.add(testValue3);


        int assumedIndex = 2;
        if (assumedIndex == testList.lastIndexOf(testValue3)){
            System.out.println( testValue3 + " is located in the SkipList at two separate indexes, its last index is " + assumedIndex);
            return true;

        }
        else {
            return false;
        }
    }

    // Group 2

    /*
        Legend says that they struggled against the Evil, but they prevailed
        helped by a hero<?> who is THE Absolute Madman
    */
    public boolean contains(Object o) // by Lucas Perez and Nat Kanyarak
    {
        @SuppressWarnings("unchecked") // Supressing type checks
        Comparable<E> co = (Comparable<E>) o; // Casting Comparable to the Object, so compareTo magic can happen, 10 points to Gryffindor!
        if (heads == null) return false; // return false if no heads pointers
        int lvl = heads.size() - 1; // start level at max
        while (heads.get(lvl) == null) // get level to where the highest node is
        {
            lvl--;
        }
        Node<E> temp = heads.get(lvl); // set temp to that node
        while (lvl >= 0)
        {
            if (temp != null) // if it's not null, it's filled, so...
            {
                if (co.compareTo(temp.value()) == 0)  // o is equal to temp
                    return true;
                if (lvl == 0 && (temp.next(0) == null || co.compareTo(temp.value()) < 0))  // couldn't find o
                    return false;
            }
            if (temp.next(lvl) == null) // next node at this level is null, go down a level
            {
                lvl--;
            }
            else if (co.compareTo(temp.next(lvl).value()) < 0) // o is less than temp, go down a level
            {
                lvl--;
            }
            else if (co.compareTo(temp.next(lvl).value()) >= 0) // o is more than temp, jump to this node
            {
                temp = temp.next(lvl);
            }
        }
        return false; // if leve goes below 0, well son, it's not there.
    }

    public boolean testcontains(boolean verbose)
    { // by Lucas Perez, THE Absolute Madman
        List<Integer> list = new SkipList<Integer>();

        list.add(1);
        list.add(3);
        list.add(8);
        list.add(12);
        list.add(9);
        list.add(6);
        list.add(2);
        list.add(24);
        list.add(18);
        list.add(13);

        // if false returns true && if true returns true
        return (!list.contains(14) && list.contains(13));
    }

    public boolean containsAll(Collection c)
    { // by Paul Risteca, and logical fixes by Lucas Perez
        for (Object o: c)
        {
            if (!contains(o))
                return false;
        }
        // if nothing ever returned false, return true
        return true;
    }

    public boolean testcontainsAll(boolean verbose)
    { // by Lucas Perez
        List<Integer> test = new LinkedList<Integer>();
        List<Integer> comp = new LinkedList<Integer>();
        test.add(1);
        test.add(2);
        test.add(3);

        comp.add(1);
        comp.add(2);
        comp.add(3);

        return (test.containsAll(comp));
    }

    public boolean equals(Object o)
    { // by Lucas Perez and by Janeen Soria
        if (o == this) // seems like an obvious check, but hey now.
            return true;

        Collection<?> c = (Collection<?>) o; // cast o to a generic Collection, not all objects are one, right?

        if (c.size() != size()) // why check everything if the size is different? Gotta go fast.
            return false;

        return containsAll(c);
    }

    public boolean testequals(boolean verbose)
    { // by Lucas Perez, THE Absolute Madman
        List<Integer> list = new LinkedList<Integer>();
        List<Integer> comp = new LinkedList<Integer>();
        list.add(null);
        list.add(32);
        list.add(99);
        comp.add(null);
        comp.add(32);
        comp.add(99);
        return list.equals(comp);
    }

    public List<E> subList(int fromIndex, int toIndex)
    { // by Ryan Schubert, with a tiny help by Lucas Perez
        // we need to return a List of a generic type
        if (fromIndex < 0 || toIndex > this.size())
        {
          throw new IndexOutOfBoundsException();
        }

        List<E> sub = new SkipList<E>();
        /*
            start at fromIndex, iterate until given index toIndex, add them to
            list created inside this method, return this list.
        */
        for(int i = fromIndex; i<toIndex; i++)
        {
            sub.add(this.get(i));
        }
        return sub;
    }

    public static boolean testSubList()
    {
        // by Ryan Schubert
        List<String> testList = new ArrayList<String>();

        testList.add("fuck");
        testList.add("the");
        testList.add("police");
        testList.add("from");
        testList.add("the");
        testList.add("underground");

        ArrayList<String> testSubList = new ArrayList<String>(testList.subList(1, 5));
        if (testList.containsAll(testSubList))
            return true;
        else
            return false;
    }

    // Group 3
    public boolean isEmpty() { // Blake Chambliss
    	if (count == 0){
    		return true;
    	} else {
    		return false;
    	}
    }

    public static boolean testIsEmpty() { // Blake Chambliss
    	SkipList<Integer> testList = new SkipList<Integer>();
    	//create new list
    	boolean newTestList = testList.isEmpty();

    	//should return true since the list is empty
    	if (newTestList){
    		return true;
    	}

    	//add two elements to list so the list is now populated
    	testList.add(3);
    	testList.add(4);

    	//checks to see if the testList is has any values. Asks for the opposite. Since the test list is populated, itEmpty() will return false, but since
    	//we are looking for the opposite, the if-else should return true
    	if (!testList.isEmpty()){
    		return true;
    	} else{
    		return false;
    	}
    }

    public int size() { // Prof. Albert
        return this.count;
    }

    public boolean testSize() { // Maya Gocal

        int testListSize;
        int randInt;

        Random rand = new Random();

        boolean test;

        // Creates a new list
        SkipList<Integer> testList = new SkipList<Integer>();

        // Creates a random number between 1 and 15
        randInt = rand.nextInt(15) + 1;

        // Adds random number of 1's to test list
        for (int i = 0; i < randInt; i++) {
            testList.add(1);
        }

        // Find list size according to .size() method
        testListSize = testList.size();

        // Compare result
        if(testListSize == randInt){
            test = true;
        } else {
            test = false;
        }
        return test;
    }

   public void clear() { // Erika Oller & Spencer Johnston //set count to zero
       count = 0;
       heads = new ArrayList<Node<E>>(MAX_LEVELS);

       // initialize with null since ArrayLists start empty
       for (int i = 0; i < MAX_LEVELS; i++) {
    	   heads.add(i, null);
       }
    }

    public static boolean testClear() { // Erika Oller & Spencer Johnston
    	// creates a test list
        SkipList<Integer> testList = new SkipList<Integer>();

        // adds some values to the testList
        testList.add(1);
        testList.add(2);
        testList.add(3);

        // removes those values from the testList
        testList.clear();

        // since clear has been called, the testList should be empty, which is what isEmpty() checks
        if(testList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public E get(int index) { // Prof. Albert & Edited by Maya Gocal, Mikey Cabrera & Erika Oller
        //sets pointer equal to head (start of the collection)
        Node<E> pointer = heads.get(0);

        /* if the requested index is less than zero, or if the requested index is
         * greater than the size of collection an exception is thrown and the output prints
         * "chosen index is out of bounds"*/
        if ( index < 0 || index >= this.count ) {
            throw new IndexOutOfBoundsException ("chosen index is out of bounds");
        } else {
            //uses a for loop to move the pointer to the desired index in the location
            for (int i = 0; i < index; i++){
                pointer = pointer.next(0);
            }
            /*return the value of the pointer after it has been moved to the desired index in
             *the collection*/
            return pointer.value();
        }
    }

    public static boolean testGet() { // Mikey Cabrera & Spencer Johnston
    	// creates a testList
        SkipList<Integer> testList = new SkipList<Integer>();

        // adds the number one to the testList in index 0
        testList.add(1);

        // returns true if the value at index 0 equals 1
        if(testList.get(0) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public E getQuantile(double quantile) { //Spencer Johnston
        // checks to see if the value is within the acceptable range 0 <= x <= 1
        if(quantile >= 1 | quantile < 0) {
            throw new IndexOutOfBoundsException("Quantile is out of range");
        }

        // typecasts the double into an int and returns the index of the node given the percentage
        int index = (int)(this.size() * quantile);

        // returns the value at the requested index
        return this.get(index);
    }

    public static boolean testGetQuantile() { //Spencer Johnston
        // Creates a new list
        SkipList<Integer> testList = new SkipList<Integer>();

        // Prepopulates the list from 0 - 100
        for(int i = 0; i <= 100; i++) {
            testList.add(i);
        }

        // creates a random index from 0-100
        int randomQuantile = (int)Math.random() * 101;

        // gets the value using the random index
        int value = testList.getQuantile(randomQuantile);

        // checks to see if the value is equal to the quantile amount since they should be the same
        if(value == randomQuantile) {
            return true;
        } else {
            return false;
        }
    }

    // Group 4
    public Iterator<E> iterator()
    {
        throw new IndexOutOfBoundsException();
    }

    public ListIterator<E> listIterator()
    {
        throw new IndexOutOfBoundsException();
    }

    public ListIterator<E> listIterator(int index)
    {
        throw new IndexOutOfBoundsException();
    }

    // Group 5
    public E remove(int index)
    {
        throw new IndexOutOfBoundsException();
    }

    public boolean remove(Object o)
    {
        return true;
    }

    public boolean removeAll(Collection c)
    {
        return true;
    }

    public boolean retainAll(Collection c)
    {
        return true;
    }

    // Group 6
	public int hashCode()
    {
		//Written by Group 6 members Enlil Adam and Hans Johnson.
        int hashCode = 1;
		//Starts the finger at the heads
		Node<E> finger = heads.get(0);
		//Moves finger over to a node where a value might be stored.
		finger.next(0);
		//If the list isn't empty or if finger hasn't reached the end it'll hash the current value and move to the next.
		while (finger != null)
        {
			hashCode = 31*hashCode + (finger == null ? 0 : finger.value().hashCode());
			finger = finger.next(0);
		}
        return hashCode;
    }

	public boolean testHashCode()
	{
		List<Integer> testList1 = new SkipList<Integer>();
		List<Integer> testList2 = new SkipList<Integer>();

		testList1.add(3);
		testList1.add(4);
		testList1.add(7);

		testList2.add(3);
		testList2.add(4);
		testList2.add(7);

	    return (testList1.hashCode() == testList2.hashCode());
	}

	public Object[] toArray()
        {
            //Thaer Mohomad and Hans Johnson's code
            Object[] arr = new Object[size()];
            //Starting position is set equal to the head (null)
            Node<E> current = null;
            //loop created that iterates through the values of the list
            for (int i = 0; i < size(); i++)
            {
                //current set to position of head at index, which changes after for loop completes
                current = heads.get(i);
                arr[i] = current;
            }
            return arr;
        }

        public static boolean testToArrayObj()
        {
            //Hans Johnson & Thaer Mohomad
            ArrayList<Integer> list1 = new ArrayList<Integer>();
            list1.add(18);
            list1.add(12);
            list1.add(6);

            ArrayList<Integer> list2 = new ArrayList<Integer>();
            list2.add(18);
            list2.add(12);
            list2.add(6);

            return list1.equals(list2);
        }

    public <T> T[] toArray(T[] a)
    {
        // written by Collin Yan and Nguyen Do, minor additions by Lucas Perez
        @SuppressWarnings("unchecked") // So it won't complain about Unchecked/Unsafe
        Node <E> pointer = heads.get(0); // start at the head
        int index = 0; // the current index of the array is 0
        if (this.size() > a.length) // makes a new ArrayList if the one passed is too big
       	{
        	T[] arr = (T[]) new Object[this.size()];

        	for (int i = 0; i < index; i++) // iterates through the SkipList and stores the nodes into the array
        	{
        		arr[i] = (T)pointer.value(); // stores the value of the pointer into the ArrayList
       			pointer = pointer.next(i); // moves pointer to the next node
    			// after storing the value it moves the pointer to the node after it and then it would store that value and continue iterating through the loop
       		}
        }
        else if (this.size() <= a.length)
       	{
        	T[] arr = (T[]) a[this.size()];
        	return arr;
       	}
        return a;
    }

    public boolean testToArrayT()
    {
        //written by Collin Yan and Nguyen Do, with minor changes and fixes by Lucas Perez
        @SuppressWarnings("unchecked") // So it won't complain about Unchecked/Unsafe
        List<Integer> testList = new LinkedList<Integer>();
        int arr[] = new int[30];
        for (int i = 0; i <= arr.length; i++) // iterates testList.add to add to the SkipList for the length of the array
    	{
    		testList.add(i);
    	}

    	testList.toArray(); // passes testList through the Array arr
    	return true;
    }

    //-----------------------------------------------------------------------------------------------
    // functions to get the compiler to agree to implement the list interface
    // these functions don't make sense in a SkipList implementation
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
            c = Class.forName("SkipList");
        } catch (ClassNotFoundException e) {
            return;
        }
        Method m[] = c.getDeclaredMethods();
        SkipList<Integer> myList = new SkipList<Integer>();

        boolean didWork = false;
        Object [] falseObjArray = new Object[1];
        falseObjArray[0] = new Boolean(false);

        System.out.println("\nTESTING THE SKIPLIST...\n");

        // iterate through the methods of SkipList
        for (int i = 0; i < m.length; i++) {
            String methodName = m[i].toString().substring(m[i].toString().indexOf("SkipList.") + 9);
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

}  // end SkipList definition
