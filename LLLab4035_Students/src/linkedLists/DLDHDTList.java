package linkedLists;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class DLDHDTList<E> extends AbstractDLList<E> {
	private DNode<E> header, trailer; 
	private int length; 
	
	public DLDHDTList() { 
		// ADD CODE HERE to generate empty linked list of this type 
		header = null;
		trailer = null;
		length = 0;
	}
	
	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
	}
	
	public void addLastNode(Node<E> nuevo) { 
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = trailer.getPrev();  
		nBefore.setNext(dnuevo); 
		trailer.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(trailer); 
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = (DNode<E>) target; 
		DNode<E> nAfter = nBefore.getNext(); 
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// ADD CODE HERE
		DNode<E> dnuevo = (DNode<E>) nuevo;
		DNode<E> dAfter = (DNode<E>) target;
		DNode<E> dBefore = dAfter.getPrev();
		dBefore.setNext(dnuevo);
		dAfter.setPrev(dnuevo);
		dnuevo.setNext(dAfter);
		dnuevo.setPrev(dBefore);
		length++;
	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target)
			throws NoSuchElementException {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		if(target == trailer)
			throw new NoSuchElementException("getNodeAfter(...): target is last node...");
		
		DNode<E> dNext = ((DNode<E>) target).getNext();
		return dNext; 
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		if(target == header)
			throw new NoSuchElementException("getNodeBefore(...): target is first node...");
		
		DNode<E> dPrev = ((DNode<E>) target).getPrev();
		return dPrev; 
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		// ADD CODE HERE to disconnect target from the linked list, reduce lent, clean target...
		if(target == header)
			header = header.getNext();
		
		else{
			DNode<E> prev = ((DNode<E>)getNodeBefore(target));
			prev.setNext(((DNode<E>) target).getNext());
		}
		((DNode<E>) target).clean();
		length--;
	}
	
	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (header != null) { 
			DNode<E> nnode = header.getNext(); 
			header.clean(); 
			header = nnode; 
		}
	}
	
	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
		// TODO
	}
		
	protected void finalize() throws Throwable {
	    try {
			this.destroy(); 
	    } finally {
	        super.finalize();
	    }
	}
public Object[] toArray() {
		
		Object[] arr = new Object[this.length()];
		int i=0;
		DNode<E> prev = (DNode<E>)this.getFirstNode();
		
		while(prev != null){ // prev=null when the last node has been added
			arr[i] = prev;
			i++;
			prev = prev.getNext();
		}
		return arr;
	}
	@Override
	public <T> T[] toArray(T[] array) {
		
		if(array.length < this.length()) {
			T[] newInstance = (T[]) Array.newInstance(array.getClass().getComponentType(), this.length());
			array = newInstance;
		}
		else if(array.length > this.length()) {
			for(int j=this.length(); j<array.length; j++) {
				array[j] = null;
			}
		}
		
		int c=0;
		DNode<E> prev = (DNode<E>)this.getFirstNode();
		while(prev != null) {
			array[c] = (T) prev;
			c++;
			prev = prev.getNext();
		}

		return array;
	}

}