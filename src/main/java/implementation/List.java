package main.java.implementation;

public interface List<T> {

    void add (T t);

    void remove(int idx);

    T get(int idx);

    void clear();

    void reverse();

    int indexOf(T t);
}
