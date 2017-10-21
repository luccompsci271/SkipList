// https://docs.oracle.com/javase/8/docs/api/java/util/List.html

import java.util.*;
import java.lang.reflect.*;

public class SkipList<E> implements List<E>
{
    // needed for compiling purposes
    public E set(int index, E element)
    {
        throw new IndexOutOfBoundsException();
    }

    // needed for compiling purposes
    public boolean addAll(int index, Collection <? extends E> c)
    {
        return true;
    }

    // Group 1
    public boolean add(E e)
    {
        return true;
    }

    public void add(int index, E e)
    {

    }

    public boolean addAll(Collection c)
    {
        return true;
    }

    public int indexOf(Object o)
    {
        int index = 0;
        return index;
    }

    public int lastIndexOf(Object o)
    {
        int index = 0;
        return index;
    }

    // Group 2
    public boolean contains(Object o)
    {
        return true;
    }

    public boolean containsAll(Collection c)
    {
        return true;
    }

    public boolean equals(Object o)
    {
        return true;
    }


    public List<E> subList(int fromIndex, int toIndex)
    {
        List<E> sub = new SkipList<>();
        return sub;
    }

    // Group 3
    public boolean isEmpty()
    {
        return true;
    }

    public int size()
    {
        int size = 0;
        return size;
    }

    public void clear()
    {

    }

    public E get(int index)
    {
        throw new IndexOutOfBoundsException();
    }

    public E getQuantile(double quantile) // e.g. 0 = minimum, 0.5 = median, 1 = max
    {
        throw new IndexOutOfBoundsException();
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
        int hashCode = 0;
        return hashCode;
    }

    public Object[] toArray()
    {
        Object[] arr = new Object[0];
        return arr;
    }

    public <T> T[] toArray(T[] a)
    {
        return a;
    }
}
