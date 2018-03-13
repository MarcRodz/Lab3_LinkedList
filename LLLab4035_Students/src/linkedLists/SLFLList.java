package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.util.NoSuchElementException;

import linkedLists.LinkedList;
import linkedLists.AbstractSLList.SNode;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		// TODO Auto-generated method stub
		((SNode<E>) nuevo).setNext(first);
		first = (SNode<E>) nuevo;
		length++;
		
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		// TODO Auto-generated method stub
		if(target == last)
			this.addLastNode(nuevo);
		else{
			((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext()); 
			((SNode<E>) target).setNext((SNode<E>) nuevo);
		}
		 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// TODO Auto-generated method stub
		if(target == first){
			this.addFirstNode(nuevo);
		}
		else{
			Node<E> prevToTarg = getNodeBefore(target);
			((SNode<E>) nuevo).setNext((SNode<E>) target); 
			((SNode<E>) prevToTarg).setNext((SNode<E>) nuevo);
		}
		length++; 
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(first == null)
			throw new NoSuchElementException("getFirstNode(): linked list is empty...");
		
		
		return first;
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(first == null)
			throw new NoSuchElementException("getLastNode(): linked list is empty...");
		else{
			last = first; 
			while (((SNode<E>) last).getNext() != null)
				last = last.getNext(); 
			return last;
		}
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		// TODO Auto-generated method stub
		
		
		if(target == last)
			throw new NoSuchElementException("getNodeAfter(...): target is last node...");
		
		SNode<E> nextN = ((SNode<E>) target).getNext();
		return nextN;
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(target == first)
			throw new NoSuchElementException("getNodeBefore(...): target is first node...");
		else{
			SNode<E> prev = first;
			
			while(prev.getNext() != ((SNode<E>) target)){
				prev = prev.getNext();
			}
			return prev;
		}
	}

	public int length() {
		// TODO Auto-generated method stub
		return this.length;
	}

	public void removeNode(Node<E> target) {
		// TODO Auto-generated method stub
		if(target == first){
			first = first.getNext();
		}
		else{
			SNode<E> prev = ((SNode<E>) getNodeBefore(target));
			prev.setNext(((SNode<E>)target).getNext());
		}
		((SNode<E>) target).clean();
		length--;
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}

}