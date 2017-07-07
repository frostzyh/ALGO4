import java.lang.IllegalArgumentException; // add null item
import java.util.NoSuchElementException; // remove item from an empty queue. OR call next() in iterator and no more items.
import java.lang.UnsupportedOperationException; //client calls the remove() in the iterator

import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
  private Node<Item> first;
  private Node<Item> last;
  private int n; // number of elements in queue

  private class Node<Item>{
    // Total = 16+8+8+8 = 48 bytes
    // Object Overhead 16 byte. Inner class overhead 8 bytes.
    private Item item;  // 8 bytes
    private Node<Item> prev;  // 8 bytes
    private Node<Item> next;  // 8 bytes
  }
  //construct an empty deque
  public Deque(){
    first = null;
    last = null;
    n = 0;
  }
  // is the dequeue empty?
  public boolean isEmpty(){
    return n == 0;
  }
  // return the number of items on the deque
  public int size(){
    return n;
  }
  // add the item to the front
  public void addFirst(Item item){
    if (item == null) throw new IllegalArgumentException("Null is not acceptable");

    Node<Item> oldFirst = first; // Store old First
    // New First
    first = new Node<Item>();

    first.item = item;
    first.prev = null;
    first.next = oldFirst;
    // If there was no elements in Deque
    if (isEmpty()) last = first;
    else oldFirst.prev = first;
    n++;
  }
  // add the item to the end
  public void addLast(Item item){
    if (item == null) throw new IllegalArgumentException("Null is not acceptable");
    Node<Item> oldLast = last; // Store old Last
    // New Last
    last = new Node<Item>();
    last.item = item;
    last.prev = oldLast;
    last.next = null;
    //If there was no elements in Deque
    if (isEmpty()) first = last;
    else oldLast.next = last;
    n++;
  }
   // remove and return the item from the front
  public Item removeFirst(){
    if (isEmpty()) throw new NoSuchElementException("Dequeue underflow");
    Item item = first.item;
    first = first.next;
    n--;
    // If deque is empty, then the new first is null.
    if (isEmpty())last = null;
    else first.prev = null;
    return item;
  }
  // remove and return the item from the end
  public Item removeLast(){
    if (isEmpty()) throw new NoSuchElementException("Dequeue underflow");
    Item item = last.item;
    last = last.prev;
    n--;
    // If deque is empty, then the new last is null.
    if (isEmpty()) first = null;
    else last.next = null;
    return item;
  }
  // return an iterator over items in order from front to end
  public Iterator<Item> iterator(){
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item> {
    private Node<Item> current = first;
    public boolean hasNext(){
      return current != null;
    }
    public void remove() {
      throw new UnsupportedOperationException();
    }
    public Item next() {
      if (!hasNext()) throw new NoSuchElementException("End of the deque");
      Item item = current.item;
      current = current.next;
      return item;
    }
  }

  public static void main(String[] args){
    Deque<String> deque = new Deque<>();
    Boolean addLast = true;
    //*
    while(!StdIn.isEmpty()){
      String s = StdIn.readString();
      if (s.equals("$RF"))
        System.out.println(deque.removeFirst());
      else if (s.equals("$RL"))
        System.out.println(deque.removeLast());
      else if (s.equals("$F")) addLast = false;
      else if (s.equals("$L")) addLast = true;
      else {
        if (addLast) deque.addLast(s);
        else deque.addFirst(s);
      }
    }

    //*/
    /*
    StdOut.println("Testing addLast");
    while(!StdIn.isEmpty()){
      String s = StdIn.readString();
      deque.addLast(s);
    }
    */
    /*
    StdOut.println("Testing addFirst");
    while(!StdIn.isEmpty()){
      String s = StdIn.readString();
      deque.addFirst(s);
    }
    */
    StdOut.println("\n\nHere is the Iterator:\n");
    Iterator<String> it = deque.iterator();
    while(it.hasNext()){
      System.out.println(it.next());
    }


  }
}
