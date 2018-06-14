package fantasticfour.structures;
import java.util.*;

public class SimpleArray<E> implements List<E>, Collection<E> {
	private int size = 0;
	public int capacity = 10;
	private E[] arr;
	@SuppressWarnings("unchecked")
	SimpleArray(){
		arr = (E[]) new Object[capacity];
	}
	
	@SuppressWarnings("unchecked")
	SimpleArray(int capacity){
		this.capacity = capacity;
		arr = (E[]) new Object[capacity];
		}
	@Override
	public Iterator<E> iterator() {
		return new SimpleIterator<E>(this);
	}
	@Override
	public int size() {
		return size;
	}
	@SuppressWarnings("unchecked")
	public void trim() {
		E temp[] = (E[]) new Object[size];
		for(int i = 0; i < temp.length; i++)
			temp[i] = arr[i];
		arr = temp;
		capacity = size;
	}
	public void increase_capacity() {
		increase_capacity(10);
	}
	@SuppressWarnings("unchecked")
	public void increase_capacity(int value) {
		E temp[] = arr.clone();
		capacity += value;
		arr = (E[]) new Object[capacity];
		for(int i = 0; i < temp.length; i++)
			arr[i] = temp[i];
	}
	@Override
	public boolean add(E element) {
		if(size >= capacity) {
			increase_capacity();
		}
		arr[size++] = element;
		return true;
	}
	@Override
	public void add(int index, E element) {
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		if(size + 1 >= capacity)
			increase_capacity();
		
		E temp = arr[index];
		arr[index] = element;
		size++;
		
		for(int i = index + 1; i <= size; i++) {
			E sec_temp = arr[i];
			arr[i] = temp;
			temp = sec_temp;
		}
	}
	@Override
	public boolean addAll(Collection<? extends E> another) {
		if(another.size() + size > capacity)
			increase_capacity(another.size());
		Iterator<? extends E> it = another.iterator();
		for(int i = size; it.hasNext(); i++)
			arr[i] = it.next();
		size += another.size();
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(int index, Collection<? extends E> another) {
		if(another.size() + size > capacity)
			increase_capacity(another.size());
		E temp[] = (E[]) new Object[size - index];
		for(int i = 0; i < temp.length; i++)
			temp[i] = arr[index + i];
		Iterator<? extends E> it = another.iterator();
		int last_index = 0;
		for(int i = 0; it.hasNext(); i++) {
			last_index = index + i;
			arr[last_index] = it.next();
		}
		for(int i = 0; i < temp.length; i++)
			arr[last_index + 1 + i] = temp[i];
		size += another.size();
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		capacity = 10;
		size = 0;
		arr = (E[]) new Object[capacity];
	}
	@Override
	public boolean contains(Object o) {
		for(int i = 0; i < size; i++)
			if(arr[i].equals(o))
				return true;
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> another) {
		Iterator<?> it = another.iterator();
		while(it.hasNext())
			if(!contains(it.next()))
				return false;
		return true;
	}
	@Override
	public E get(int index) {
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		return arr[index];
	}
	@Override
	public int indexOf(Object o) {
		for(int i = 0; i < size; i++) {
			if(arr[i].equals(o))
				return i;
		}
		return -1;
	}
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	@Override
	public int lastIndexOf(Object o) {
		int ret = -1;
		for(int i = 0; i < size; i++) {
			if(arr[i].equals(o))
				ret = i;
		}
		return ret;
	}
	@Override
	public boolean remove(Object o) {
		int value = indexOf(o);
		if(value == -1)
			return false;
		remove(value);
		return true;
	}
	@Override
	public E remove(int index) {
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		E ret = arr[index];
		size--;
		if(index == size) {
			arr[index] = null;
			return ret;
		}
		for(int i = index; i < size; i++) {
			arr[i] = arr[i + 1];
		}
		return ret;
	}
	@Override
	public E set(int index, E element) {
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		E ret = arr[index];
		arr[index] = element;
		return ret;
	}
	@Override
	public boolean removeAll(Collection<?> another) {
		Iterator<?> it = another.iterator();
		while(it.hasNext())
			remove(it.next());
		trim();		
		return true;
	}
	@Override
	public boolean retainAll(Collection<?> another) {
		for(int i = 0; i < size; i++) {
			boolean need_to_del = true;
			Iterator<?> it = another.iterator();
			while(it.hasNext())
				if( arr[i].equals(it.next()) ) {
					need_to_del = false;
					break;
				}
			if(need_to_del)
				remove(i--);
		}
		return true;
	}
	@Override
	public ListIterator<E> listIterator() {
		return null;
	}
	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Object[] toArray() {
		 E temp[] = (E[]) new Object[size];
		for(int i = 0; i < temp.length; i++)
			temp[i] = arr[i];
		return temp;
	}
	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}
	
	class SimpleIterator<E> implements Iterator<E>{
		int current_iteration = 0;
		SimpleArray<E> Master;
		SimpleIterator(SimpleArray<E> Master){
			this.Master = Master;
		}
		public boolean hasNext() {
			return current_iteration < Master.size();
		}
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			return Master.arr[current_iteration++];
		}
	}

}
