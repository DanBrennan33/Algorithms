import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node head, tail;
	private int n;
	
	private class Node {
		private Item item = null;
		private Node next = null; 
		private Node prev = null;
	}
	
	// construct an empty deque
	public Deque() {
		head = tail = null;
		this.n = 0;
	}
	
	// is the deque empty?
	public boolean isEmpty() {
		return n == 0;
	}
	
	// return the number of items on the deque
	public int size() {
		return n;
	}
	
	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) 
			throw new java.lang.NullPointerException();
		
		Node temp = new Node();
		temp.item = item;
		temp.next = head;
		
		if (head != null)
			head.prev = temp;
		
		head = temp;
		n++;
		
		if (tail == null)
			tail = head;
	}
	
	// add the item to the end
	public void addLast(Item item) {
		if (item == null) 
			throw new java.lang.NullPointerException();
		
		Node temp = new Node();
		temp.item = item;
		temp.prev = tail;
		if (tail != null)
			tail.next = temp;
			
		tail = temp;
		n++;
		
		
	}
	
	// remove and return the item from the front
	public Item removeFirst() {
		if (n == 0) 
			throw new java.util.NoSuchElementException();
		
		Item i = head.item;
		if (n == 1) 
			head = tail = null;
		else {
			head = head.next;
			head.prev = null;
		}
		n--;
		return i;
	}
	
	// remove and return the item from the end
	public Item removeLast() {
		if (n == 0) 
			throw new java.util.NoSuchElementException();
		
		Item i = tail.item;

		if (n == 1) 
			head = tail = null;
		else {
			tail = tail.prev;
			tail.next = null;
		}
		n--;
		return i;
	}
	
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node temp = head;
		
		@Override
		public boolean hasNext() {
			return (temp != null);
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			
			Item i = temp.item;
			temp = temp.next;
			return i;
		}
		
		@Override 
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	// unit testing (optional)
	public static void main(String[] args) {
		
	}
}