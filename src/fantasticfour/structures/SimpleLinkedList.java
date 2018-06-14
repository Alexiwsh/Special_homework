package fantasticfour.structures;

import java.util.Iterator;

public class SimpleLinkedList<E> {
	private int size = 0;
	private Node<E> last_node = null;
	private Node<E> first_node = null;
	
	SimpleLinkedList(){}
	
	public void add(E element) {
		if(first_node == null) {
			last_node = new Node<E>(element);
			first_node = last_node;
		}
		else
			last_node = new Node<E>(element, last_node);
		size++;
	}
	
	public void add(E element, int index) {
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		if(index <= size / 2) {
			Node<E> Cur = first_node;
			for(int i = 0; Cur != last_node; i++) {
				if(i == index) {
					new Node<E>(element, Cur, Cur.Next);
					break;
				}
				Cur = Cur.Next;
			}
		}
		else {
			Node<E> Cur = last_node;
			for(int i = size; Cur != first_node; i--) {
				if(i == index) {
					new Node<E>(element, Cur.Previous, Cur.Next);
					break;
				}
				Cur = Cur.Previous;
			}
		}
		size++;
	}
	public E get(int index) {
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException();
		Node<E> Cur = first_node;
		for(int i = 0; Cur != last_node; i++){
			if(i == index)
				return Cur.element;
			Cur = Cur.Next;
		}
		return Cur.element;
	}
	public void remove(int index) {
		Node<E> Cur = first_node;
		for(int i = 0; Cur != last_node; i++) {
			if(i == index) {
				Cur.Next.Previous = Cur.Previous;
				Cur.Previous.Next = Cur.Next;
				break;
			}
			Cur = Cur.Next;
		}
		size--;
	}
	public boolean contains(E O) {
		LinkedIterator<E> I = iterator();
		while(I.hasNext())
			if(I.next().equals(O))
				return true;
		return false;
	}
	public int indexOf(E O) {
		LinkedIterator<E> I = iterator();
		for(int i = 0; I.hasNext(); i++)
			if(I.next().equals(O))
				return i;
		return -1;
	}
	public void clear() {
		Node<E> Cur = first_node;
		for(int i = 0; i < size; i++) {
			Node<E> T = Cur;
			Cur.Previous = null;
			Cur = Cur.Next;
			T.Next = null;
		}
		first_node = null;
		last_node = null;
		size = 0;
	}
	public LinkedIterator<E> iterator(){
		return new LinkedIterator<E>(this);
	}
	public int size() {
		return size;
	}
	
	class Node<E> {
		Node<E> Previous = null;
		Node<E> Next = null;
		E element;
		Node(){}
		Node(E element){
			this.element = element;
		}
		Node(E element, Node<E> Previous){
			this(element);
			this.Previous = Previous;
			Previous.Next = this;
		}
		Node(E element, Node<E> Previous, Node<E> Next){
			this(element, Previous);
			this.Next = Next;
			Next.Previous = this;
		}
	}
	class LinkedIterator<E> implements Iterator<E>{
		SimpleLinkedList<E>.Node<E> Current = null;
		LinkedIterator(SimpleLinkedList<E> Master){
			Current = Master.first_node;
		}
		@Override
		public boolean hasNext() {
			return Current.Next != null;
		}
		@Override
		public E next() {
			Current = Current.Next;
			return Current.element;
		}
	}
	
}
