package main.java.implementation;

public class LinkedList<T> implements List<T>{
    private Node<T> first;
    private Node<T> last;
    private int size = 0;


    private class Node<E>{
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(E item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }


    @Override
    public void add(T t) {
        addLast(t);
    }


    @Override
    public void remove(int idx) throws NullPointerException{
        Node<T> exact = findNodeByIndex(idx);
        Node<T> beforeExact = exact.prev;
        Node<T> afterExact = exact.next;

        // сейчас будет жесткая логика, извини Кэп

        if (exact.prev != null){
            //     exact.prev = null;
            beforeExact.next = null;
            if (exact.next != null){
                beforeExact.next = afterExact;
            }
        }else{
            first = exact.next;
            first.prev = null;
        }

        if (exact.next != null){
            //    exact.next = null;
            afterExact.prev = null;
            if (exact.prev != null){
                afterExact.prev = beforeExact;
            }
        }else{
            last = exact.prev;
            last.next = null;

        }
        size--;
    }

    @Override
    public T get(int idx) {
        return findNodeByIndex(idx).item;
    }

    @Override
    public void clear() {
        for (int i = size; i>=0; i--) {
            remove(i);
        }
    }

    @Override
    public void reverse() {
        Node<T> start = first;
        Node<T> end = last;
        for (int i = 0; i<=(size/2); i++){
            T swp = end.item;
            end.item = start.item;
            start.item = swp;
            start = start.next;
            end = end.prev;
        }
    }

    @Override
    public int indexOf(T t) {
        int index = 1;
        for (Node<T> x = first; x != null; x = x.next) {
            if (t.equals(x.item)) {
                return index;
            }
            index++;
        }
        return -1; //Я не мог придумать, что вернуть и глянул в библеотеку, прошу простить -_0 : )))}
    }


    private void addLast(T t){
        Node<T> newNode = new Node<>(t);
        if (last == null){
            last = newNode;
            if (first == null) first = newNode;
        }else {
            Node<T> referenceKeeper = last;
            last = newNode;
            // Ex-last -> to presentLast -> keeping reference to Ex-last || [Ex] -> [last] and [last] -> [Ex]
            referenceKeeper.next = last;
            last.prev = referenceKeeper;
        }
        size++;
    }

    private Node<T> findNodeByIndex(int idx) {
        Node<T> element = first;
        if (first != null) {
            if (idx>size) throw new NullPointerException("Вы ввели неверный индекс");
            for (int i = 0; i != idx; i++) {
                if (element.next!=null){
                    element = element.next;
                }
            }
        }
        return element;
    }

    public T getFirst() {
        return first.item;
    }

    public T getLast() {
        return last.item;
    }

    public int getSize() {
        return size;
    }

}
