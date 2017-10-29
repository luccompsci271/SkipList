

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
        //creates a new SkipList called testList
        List<Integer> testList = new SkipList<Integer>();

        testList.add(7);
        testList.add(4);
        testList.add(10);
        testList.add(7);

        //System.out.println(testList.get(0));
        //System.out.println(testList.get(1));
        //System.out.println(testList.get(2));
        //System.out.println(testList.get(3));

        return (
            (testList.get(0) == 4) &&
            (testList.get(1) == 7) &&
            (testList.get(2) == 7) &&
            (testList.get(3) == 10) &&
            (testList.size() == 4)
            );

   

   
      package com.company;

import javax.xml.soap.Node;
import java.util.*;
import java.lang.reflect.*;
import java.util.List;

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
        //Written by
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
    // Written by  Karson, Sofia, and Maribel
    public int indexOf(Object number) {
        // Written by
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
            Node<E> temp = heads;
            for (int i = 0; i < index; i++) {
                temp = temp.next();
            }
            while (temp.next().value() == m) {
                temp = temp.next();
                index++;
            } return index;
        }
    }

    public static boolean testlastIndexOf() {
        // Written by Karson, Sofia and Maribel
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


