package linkedLists;

public class DLDHDTList<E> implements LinkedList<E> {
	private DNode<E> header, trailer; 
	private int length; 

	public DLDHDTList() { 
		header = new DNode<E>();
		trailer = new DNode<E>(null,header,null);
		header.setNext(trailer);
		length = 0; 
	}

	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
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
		DNode<E> dTarget = (DNode<E>) target;
		DNode<E> dNuevo = (DNode<E>) nuevo; 
		dTarget.getNext().setPrev(dNuevo);
		dNuevo.setNext(dTarget.getNext());
		dNuevo.setPrev(dTarget);
		dTarget.setNext(dNuevo);
		length++; 
	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NodeOutOfBoundsException {
		if (length == 0) 
			throw new NodeOutOfBoundsException("List is empty."); 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NodeOutOfBoundsException {
		if (length == 0) 
			throw new NodeOutOfBoundsException("List is empty."); 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target) throws NodeOutOfBoundsException {
		if(target == trailer)
			throw new NodeOutOfBoundsException("getNodeAfter(...) : target is the last node.");
		DNode<E> dTarget = (DNode<E>) target; 
		return dTarget.getNext(); 
	}

	public Node<E> getNodeBefore(Node<E> target) throws NodeOutOfBoundsException {
		if(target == header)
			throw new NodeOutOfBoundsException("getNodeBefore(...) : target is the first node.");
		DNode<E> dTarget = (DNode<E>) target; 
		return dTarget.getPrev(); 
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		DNode<E> dTarget = (DNode<E>) target;
		dTarget.getPrev().setNext(dTarget.getNext());
		dTarget.getNext().setPrev(dTarget.getPrev());
		dTarget.cleanLinks();
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
			header.setElement(null); 
			header.cleanLinks(); 
			header = nnode; 
		}
	}

	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
		this.destroy();
		header.setNext(trailer);
		trailer.setPrev(header);
		this.length = 0;
	}

	protected void finalize() throws Throwable {
		try {
			this.destroy(); 
		} finally {
			super.finalize();
		}
	}

	/**
	 * Class to represent a node of the type used in doubly linked lists. 
	 * @author pedroirivera-vega
	 *
	 * @param <E>
	 */
	private static class DNode<E> implements Node<E> {
		private E element; 
		private DNode<E> prev, next; 

		// Constructors
		public DNode() {}

		public DNode(E e) { 
			element = e; 
		}

		public DNode(E e, DNode<E> p, DNode<E> n) { 
			prev = p; 
			next = n; 
		}

		// Methods
		public DNode<E> getPrev() {
			return prev;
		}
		public void setPrev(DNode<E> prev) {
			this.prev = prev;
		}
		public DNode<E> getNext() {
			return next;
		}
		public void setNext(DNode<E> next) {
			this.next = next;
		}
		public E getElement() {
			return element; 
		}

		public void setElement(E data) {
			element = data; 
		} 

		/**
		 * Just set references prev and next to null. Disconnect the node
		 * from the linked list.... 
		 */
		public void cleanLinks() { 
			prev = next = null; 
		}

	}

}
