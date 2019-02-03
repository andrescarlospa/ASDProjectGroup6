package edu.mum.asd.framework.iterator;

public class LinkedList implements ILinkedList{
	private Link firstLink;

	public LinkedList() {
		firstLink = null;
	}

	// true if empty, false otherwise
	public boolean empty() {
		return firstLink == null;
	}

	// add an Object to our list
	public void add(Object newValue) {
		firstLink = new Link(newValue, firstLink);
	}

	// inspect front of list
	public Object front() {
		if (firstLink == null)
			return null;

		return firstLink.value();
	}

	// pop front of list
	public Object pop() {
		if (firstLink == null)
			return null;

		Object result = firstLink.value();
		firstLink = firstLink.next();
		return result;
	}

	// return an iterator for the list
	public ListIterator iterator() {
		return new ListIterator(firstLink);
	}
}