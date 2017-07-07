import java.lang.IllegalArgumentException; // add null item
import java.util.NoSuchElementException; // remove item from an empty queue. OR call next() in iterator and no more items.
import java.lang.UnsupportedOperationException; //client calls the remove() in the iterator

import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item>{
	private Node<Item> first;
	private Node<Item> last;
	private int n;

	private class Node<Item>{
	  // Total = 16+8+8 = 40 bytes
	  // Object Overhead 16 byte. Inner class overhead 8 bytes.
	  private Item item;  // 8 bytes
	  private Node<Item> next;  // 8 bytes
	}

	// construct an empty randomized queue
	public RandomizedQueue(){
		first = null;
		last = null;
		n = 0;
	}
	// is the queue empty?
	public boolean isEmpty(){
		return n == 0;
	}
	// return the number of items on the queue
	public int size(){
		return Integer.valueOf(n);
	}
	// add the item
	public void enqueue(Item item){
		if (item == null) throw new IllegalArgumentException("Null is not acceptable");
		Node<Item> oldLast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		if (isEmpty()) first = last;
		else oldLast.next = last;
		n++;
	}
	// remove and return a random item
	public Item dequeue(){
		if (isEmpty()) throw new NoSuchElementException("Dequeue underflow");
		int removeIndex = StdRandom.uniform(size());
		Item item;
		//StdOut.println("---------------------------");
		if (removeIndex == 0){
			item = first.item;
			first = first.next;
			if (first == null) last = null; //avoid loitering
		}
		else{
			Node<Item> prevTarget = first;
			//StdOut.println("Here is n " + n);
			//StdOut.println("Here is index " + removeIndex);
			for (int i=1; i< removeIndex; i++){
				prevTarget = prevTarget.next;
			}
			//StdOut.println("Now we want to dequeue: " + prevTarget.next.item);
			item = prevTarget.next.item;
			prevTarget.next = prevTarget.next.next;
			// This line costs me ~3 hours to debug
			if (prevTarget.next == null) last = prevTarget;
		}
		n--;
		//StdOut.println("---------------------------");
		//removeIndex = null;
		return item;
	}
	// return (but do not remove) a random item
	public Item sample(){
		if (isEmpty()) throw new NoSuchElementException("Dequeue underflow");
		int itemIndex = StdRandom.uniform(size());
		Node<Item> target = first;
		for (int i=0; i< itemIndex; i++){
			target = target.next;
		}
		Item item = target.item;
		return item;
	}
	// return an independent iterator over items in random order
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item> {
		private Item[] arr;
		private int current = 0;
		private ListIterator(){
			// Java allows array of size 0;
			//StdOut.println("Iterator Testing--------------");
			//StdOut.println("Iterator (n): " + n);
			//StdOut.println("Iterator (first): " + first.item);
			//StdOut.println("Iterator (last): " + last.item);
			arr = (Item[]) new Object[n];
			if (n != 0){
				Node<Item> node = first;
				arr[0] = node.item;
				for(int i = 1; i < n; i++){
					//StdOut.println("Iterator (i): " + i);
					//StdOut.println("Iterator Prev(node): " + node.item);
					node = node.next;
					arr[i] = node.item;
				}
				StdRandom.shuffle(arr);
			}
		}

		public boolean hasNext(){
			return current < n;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
		public Item next(){
			if (current >= n) throw new NoSuchElementException("End of the deque");
			Item item = arr[current];
			current++;
			return item;
		}
	}

	public static void main(String[] args){
		RandomizedQueue<String> rq = new RandomizedQueue<>();

		while(!StdIn.isEmpty()){
        String s = StdIn.readString();
        if (s.equals("$D"))
          StdOut.println("Dequeue: "+ rq.dequeue());
        else if (s.equals("$S"))
          StdOut.println("Show: " + rq.sample());
        else {
			  rq.enqueue(s);
			  StdOut.println("Added: " + s);
        }
		  /*
		  StdOut.println("---Here is the Iterator:---\n");
		  Iterator<String> it = rq.iterator();
        while(it.hasNext()){
          System.out.print(it.next() + "  ");
        }
		  StdOut.println();
		  */
      }

		StdOut.println("\n\nHere is the Iterator:\n");
      Iterator<String> it = rq.iterator();
      while(it.hasNext()){
			StdOut.print(it.next() + " ");
      }

		//Test ListIterator with empty list.
	}

}


/*
Test:
javac-algs4 RandomizedQueue.java
java-algs4 RandomizedQueue < rqTest.txt
cls   clear screen
del *.class   delete all class files



*/
