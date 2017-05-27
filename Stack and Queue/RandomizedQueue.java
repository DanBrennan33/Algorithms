import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
	private ArrayList<Item> arrList = new ArrayList<Item>();
		
	// construct an empty randomized queue
	public RandomizedQueue() { 	}
	
	// is the queue empty?
	public boolean isEmpty() {
		return (arrList.size() == 0);
	}
	
	// return the number of items on the queue
	public int size() {
		return arrList.size();
	}
	
	// add the item
	public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		arrList.add(item);
	}
	
	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		return arrList.remove(StdRandom.uniform(0, arrList.size()));
	}
	
	// return (but do not remove) a random item
	public Item sample() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		return arrList.get(StdRandom.uniform(0, arrList.size()));
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new ListIterator(arrList);
	}
	
	private class ListIterator implements Iterator<Item> {
		private ArrayList<Item> listQueue;
		private int index = 0;
		
		public ListIterator(ArrayList<Item> al) {
			listQueue = new ArrayList<Item>(al);
			Collections.shuffle(listQueue);
		}

		@Override
		public boolean hasNext() {
			return (index < listQueue.size());
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item i = listQueue.get(index);
			index++;
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
