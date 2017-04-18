package ru.otus;

import java.util.*;

/**
 * Итераторы без проверки модифакации коллекции
 * Реализованно без  AbstractList<E>, RandomAccess, Cloneable, java.io.Serializable
 * Некоторые операции также не реализованны и они выбрасывают исключения  UnsupportedOperationException,
 * потом надо дореализовать
 */
public class  MyArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 8;

    private E[] elements;
    private int size = 0;

    MyArrayList(int capacity)
    {
        if (capacity >= 0) {
            elements = (E[])new Object[capacity];
        } else {
            throw new IllegalArgumentException("Wrong Capacity: "+ capacity);
        }
    }

    MyArrayList()
    {
        elements = (E[])new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(Collection<? extends E> c) {
        elements = (E[])new Object[c.size()];
        int i = 0;
        for(E obj : c)
        {
            elements[i++] = obj;
        }
        size = c.size();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("MyArrayList::toArray()");
    }

    private void reallocateArray()
    {
        System.out.println("reallocateArray");
        if (elements.length < DEFAULT_CAPACITY)
        {
            elements = (E[])new Object[DEFAULT_CAPACITY];
            return;
        }
        E[] temp = (E[])new Object[elements.length * 2];
        java.lang.System.arraycopy(elements, 0, temp, 0, elements.length);
        elements = temp;
    }

    @Override
    public boolean add(E e) {
        if (size >= elements.length)
        {
            reallocateArray();
        }
        elements[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Wrong index " + index );
        if (size >= elements.length)
        {
            reallocateArray();
        }
        System.arraycopy(elements, index, elements, index + 1,
                size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("MyArrayList::remove()");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("MyArrayList::containsAll()");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("MyArrayList::addAll(Collection<? extends E> c)");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("MyArrayList::addAll(int index, Collection<? extends E> c)");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("MyArrayList::removeAll()");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("MyArrayList::retainAll()");
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; ++i)
        {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Wrong index: " + index);

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Wrong index: " + index);

        E old = elements[index];
        elements[index] = element;
        return old;
    }



    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("MyArrayList::remove()");
        //return null;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i)
        {
            if (elements[i] == null || elements[i].equals(o))
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i)
        {
            if (elements[i] == null || elements[i].equals(o))
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
            return new MyListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }

        return new MyListIterator(index);
    }

    class MyListIterator implements ListIterator<E>, Iterator<E> {

        int nextIndex = 0;
        int lastReturn = -1;

        MyListIterator() {
            nextIndex = 0;
        }

        MyListIterator(int index) {
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            if (nextIndex < size) return true;
            return false;
        }

        @Override
        public E next() {
            if (nextIndex < size)
            {
                lastReturn = nextIndex;
                ++nextIndex;
                return elements[lastReturn];
            } else
            {
                throw new NoSuchElementException();
            }
        }

        @Override
        public boolean hasPrevious() {
            if (nextIndex > 0)
            {
                return true;
            }
            return false;
        }

        @Override
        public E previous() {
            int current = nextIndex - 1;
            if (current < size && current >= 0)
            {
                lastReturn = current;
                nextIndex = current;
                return elements[lastReturn];
            } else
            {
                throw new NoSuchElementException();
            }
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {
            if (lastReturn < 0)
                throw new IllegalStateException();

            MyArrayList.this.set(lastReturn, e);

        }

        @Override
        public void add(E e) {
            MyArrayList.this.add(nextIndex, e);
            ++nextIndex;
            lastReturn = -1;
        }
    }


    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("MyArrayList::subList()");
        //return null;
    }

    @Override
    public String toString() {
        Iterator<E> it = iterator();

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (true) {
            if (it.hasNext()) {
                sb.append(it.next());
            }

            if (!it.hasNext()) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(", ");
        }
    }

    public int capacity()
    {
        return elements.length;
    }
}
