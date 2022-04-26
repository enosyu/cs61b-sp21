package deque;

import jh61b.junit.In;

public class LinkedListDeque<T> implements Deque<T> {
    private class IntNode {
        public IntNode prev;
        public T item;
        public IntNode next;

        public IntNode(T i, IntNode n, IntNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private IntNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item){
        sentinel = new IntNode(null, null, null);
        sentinel.next = new IntNode(item, null,null);
        sentinel.prev = new IntNode(item, null, null);
        size = 1;
    }

    public T getRecursive(int index){
        IntNode n = sentinel;
        if (index==0){
            if(isEmpty()){
                return null;
            }
            return n.prev.item;
        }else{
            n.next = n.next.next;
            return getRecursive(index - 1);
        }
    }

    @Override
    public void addFirst(T item){
        IntNode n = sentinel.next;
        sentinel.next = new IntNode(item, n, sentinel);
        n.prev = sentinel.next;
        size = size + 1;
    }

    @Override
    public void addLast(T item){
        IntNode p = sentinel.prev;
        sentinel.prev = new IntNode(item, sentinel, p);
        p.next = sentinel.prev;
        size = size + 1;
    }

    @Override
    public boolean isEmpty(){
        return sentinel.next.item == null;
    }

    @Override
    public int size(){ return size; }

    @Override
    public void printDeque(){
        for(int n = 0; n < size; n++){
            System.out.println(get(n).toString());
        }
    }

    @Override
    public T removeFirst(){
        IntNode f = sentinel.next;
        if (f == null) {
            return null;
        }
        f.next.prev = sentinel;
        sentinel.next = f.next;
        size = size - 1;
        if(size < 0){
            size = 0;
        }
        return f.item;
    }

    @Override
    public T removeLast(){
        IntNode p = sentinel.prev;
        if (p == null) {
            return null;
        }
        p.prev.next = sentinel;
        sentinel.prev = p.prev;
        size = size - 1;
        if(size < 0){
            size = 0;
        }
        return p.item;
    }

    @Override
    public T get(int index){
        IntNode p = sentinel.next;
        for(int i = 0; i < index; i++){
            if (isEmpty()){
                return null;
            }
            p = p.next;
        }
        return p.item;
    }

    /*
    public interface Iterator<T> {
        boolean hasNext();
        T next();
    }
    private class LinkedListDequeIterator implements Iterator<T> {
        private int wizPos;

        public LinkedListDequeIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }


    public Iterator<T> iterator(){
        return new LinkedListDequeIterator();
    }
    */

    public boolean equals(Object o){
        return false;
    }

}
