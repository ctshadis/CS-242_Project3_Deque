import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * The Dequeue is an implementation of Queue that allows for insertions and deletions from both ends
 * @author Christian Shadis
 * @Version 1.2
 * 
 */
public class Dequeue<E> extends java.lang.Object{
	private int front, rear;
	E[] elements;
	private static final int INITIAL_CAPACITY = 5;

	/*
	 * Creates an empty Dequeue
	 * Running time O(1)
	 */
	@SuppressWarnings("unchecked")
	public Dequeue(){
		elements = (E[]) new Object[INITIAL_CAPACITY];
		front = 0;
		rear = -1;
	}
	
	/*
	 * Creates a larger array containing the same elements in the same order
	 * @post: elements is a larger array and front and rear are reset
	 * 
	 * Running time O(n)
	 */
	@SuppressWarnings("unchecked")
	private void reallocate() {
		int newSize = elements.length + 2;
		E[] temp = (E[]) new Object[newSize];
		int i = front;
		for (int j = 0; j < elements.length; j++) {
			temp [j] = elements[i % elements.length];
			i++;
		}
		front = 0;
		rear = elements.length - 2;
		elements = temp;
					
	}
	/*
	 * Adds an item to the front of the queue
	 * @param anEntry is the item to add to the beginning of the dequeue
	 * @return true
	 * Running time O(1) NOT counting time for the reallocate method
	 */
	public boolean addFront(E anEntry) {
		if ((rear + 2) % elements.length == front) reallocate();
		if (size() == 0) {
			elements[front] = anEntry;
			rear++;
		}
		else {
			if(front <= 0) 
				front = elements.length - 1 - front; // moves front to the last element in the array
			else 
				front--;
			elements[front] = anEntry;
		}
		return true;
	}
	
	/*
	 * Adds an item to the rear of the queue
	 * @param anEntry is the item to add to the beginning of the dequeue
	 * @return true
	 * Running time O(1) NOT counting time for the reallocate method
	 */
	public boolean addRear(E anEntry) {
		if ((rear + 2) % elements.length == front) reallocate();
		rear++;
		elements[rear] = anEntry;
		return true;
	}
	
	/*
	 * @pre: The dequeue is not empty
	 * @post: the front item on the dequeue has been removed and the queue has one fewer element
	 * Removes and returns the element in the front of the dequeue
	 * @throws NoSuchElementException if the dequeue is empty
	 * @return the element that was in the front position of the dequeue
	 */
	public E removeFront() {
		if(size() == 0) throw new NoSuchElementException();
		else {
			E result = elements[front];
			elements[front] = null;
			front++;
			return result;
		}
	}
	
	/*
	 * @pre: the dequeue is not empty
	 * @post: the rear item on the queue has been removed and the dequeue is one element shorter
	 * Removes and returns the rear element in the dequeue
	 * @throws NoSuchElementException if the dequeue is empty
	 * @return the element that was in the rear position of the dequeue
	 * Running time O(n) since it calls size()
	 */
	public E removeRear() {
		if(size() == 0) throw new NoSuchElementException();
		else {
			E result = elements[rear];
			elements[rear] = null;
			if (rear <= 0) 
				front = elements.length - 1 - rear; // moves rear to the last element in the array
			else 
				rear--;
			return result;
		}
	}
	/*
	 * Returns the object at the front of the dequeue without removal
	 * post: dequeue unchanged
	 * @return the object at the front of the dequeue
	 * @throws NoSuchElementException if the dequeue is empty
	 * Running time O(n) since it calls size()
     */
	public E peekFront() {
		if(size() == 0) throw new NoSuchElementException();
		else return elements[front];
	}
	
	/*
	 * Returns the object at the rear of the dequeue without removal
	 * post: dequeue unchanged
	 * @return the object at the rear of the dequeue
	 * @throws NoSuchElementException if the dequeue is empty
	 * Running time O(n) since it calls size()
     */
	public E peekRear() {
		if(size() == 0) throw new NoSuchElementException();
		
		else return elements[rear];
	}
	
	/*
	 * Checks if there are any elements in the dequeue
	 * post: dequeue unchanged
	 * @return whether it is empty
	 * Running time O(n) since it calls size().
	 */
	public boolean empty() {
		return (size() == 0);
	}
	
	/*
	 * Checks the size of the dequeue
	 * @pre: none
	 * @post: dequeue unchanged
	 * @return number of elements in the dequeue
	 * Running time O(n)
	 */
	public int size() {
		int counter = 0;
		for (int i = 0; i < elements.length; i++) {
			if(elements[i] != null) {
				counter ++;
			}
		}
		return counter;
	}
	
	/*
	 * Returns an Iterator object
	 */
	public java.util.Iterator<E> iterator(){
		return new myIterator();
	}
	
	private class myIterator implements Iterator<E> {
		// Data Fields
		// Index of next element
		private int index;
		// Count of elements accessed so far
		private int count = 0;

		// Methods
		// Constructor
		/** Initializes the Iterator object to reference the first dequeue element. */
		public myIterator() {
			index = front;
		}

		/** Returns true if there are more elements in the dequeue to access. */
		@Override
		public boolean hasNext() {
			return count < size();
		}

		/**
		 * Returns the next element in the dequeue.
		 * 
		 * @pre index references the next element to access.
		 * @post index and count are incremented.
		 * @return The element with subscript index
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E returnValue = elements[index];
			index = (index + 1) % elements.length;
			count++;
			return returnValue;
		}

		/** Remove the item accessed by the Iterator object – not implemented. */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}
//line to verify this is the latest version of the code: 10/23/2019 3:42 PM


	



