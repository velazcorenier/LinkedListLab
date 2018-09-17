package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import linkedLists.LinkedList;

public class SLFLList<E> implements LinkedList<E>
{

	private SNode<E> first, last; 
	int length = 0; 

	public SLFLList() { 
		first = last = null; 
	}


	public void addFirstNode(Node<E> nuevo) {
		SNode<E> sNuevo = (SNode<E>) nuevo;
		sNuevo.setNext(first);
		first = sNuevo;
		if(length() == 0)
			last = (SNode<E>) nuevo;
		this.length++;		
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		SNode<E> sTarget = (SNode<E>) target;
		SNode<E> sNuevo = (SNode<E>) nuevo;
		sNuevo.setNext(sTarget.getNext());
		sTarget.setNext(sNuevo);
		if(last == sTarget)
			last = sNuevo;
		this.length++;
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		SNode<E> sTarget = (SNode<E>) target;
		SNode<E> sNuevo = (SNode<E>) nuevo;
		SNode<E> current = first;
		if(first == sTarget) {
			this.addFirstNode(nuevo);
		} else {
			while(current != sTarget)
				current.getNext();
			current.setNext(sNuevo);
			sNuevo.setNext(sTarget);
		}	
		this.length++;
	}

	public Node<E> getFirstNode() throws NodeOutOfBoundsException {
		if (first == null)
			throw new NodeOutOfBoundsException("getFirstNode() : linked list is empty..."); 
		return first;
	}

	public Node<E> getLastNode() throws NodeOutOfBoundsException {
		if (last == null)
			throw new NodeOutOfBoundsException("getLastNode() : linked list is empty...");
		return last;
	}

	public Node<E> getNodeAfter(Node<E> target) throws NodeOutOfBoundsException {
		SNode<E> sTarget = (SNode<E>) target; 
		if(sTarget == last)
			throw new NodeOutOfBoundsException("getNodeAfter(...) : target is the last node.");
		return sTarget.getNext();
	}

	public Node<E> getNodeBefore(Node<E> target) throws NodeOutOfBoundsException {
		SNode<E> sTarget = (SNode<E>) target;
		SNode<E> prev = first;
		if (sTarget == first)  
			throw new NodeOutOfBoundsException("getNodeBefore(...) : target is the first node.");  
		while(prev.getNext() != sTarget)
			prev = prev.getNext();
		return prev;
	}

	public int length() {
		return this.length;
	}

	public void removeNode(Node<E> target) {
		SNode<E> sTarget = (SNode<E>) target; 
		SNode<E> prev;
		if(target == first) {
			first = first.getNext();
		} else if(target == last) {
			prev = (SNode<E>) this.getNodeBefore(sTarget);
			prev.setNext(null);
			last = prev;
		} else {
			prev = (SNode<E>) this.getNodeBefore(sTarget);
			prev.setNext(sTarget.getNext());
		}
		sTarget.setElement(null);
		sTarget.setNext(null);
		length--; 
	}

	public Node<E> createNewNode() {
		return new SNode<E>();
	}


	///////////////////////////////////////////////////
	// private and static inner class that defines the 
	// type of node that this list implementation uses
	private static class SNode<T> implements Node<T> {
		private T element; 
		private SNode<T> next; 
		public SNode() { 
			element = null; 
			next = null; 
		}
		public SNode(T data, SNode<T> next) { 
			this.element = data; 
			this.next = next; 
		}
		public SNode(T data)  { 
			this.element = data; 
			next = null; 
		}
		public T getElement() {
			return element;
		}
		public void setElement(T data) {
			this.element = data;
		}
		public SNode<T> getNext() {
			return next;
		}
		public void setNext(SNode<T> next) {
			this.next = next;
		}
	}

}
