package general.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

import general.exceptions.ObserverException;

/**
 * List that implements Observable
 * @author Nathaniel Brooke
 * @version 04-01-2017
 * @param <T>
 */
public class ObservableList<T> extends Observable implements List<T> {

	private ArrayList<T> list;
	
	/**
	 * Creates a new, empty ObservableList.
	 */
	public ObservableList() {
		list = new ArrayList<T>();
	}
	
	/**
	 * Creates an ObservableList out of a given list input.
	 * Note that modifying the List does not modify the ObservableList.
	 * @param input list.
	 */
	public ObservableList(List<T> input) {
		this();
		list.addAll(input);
	}

	@Override
	public boolean add(T e) {
		if(list.add(e)) {
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}

	@Override
	public void add(int index, T element) {
		list.add(index, element);
		setChanged();
		notifyObservers();
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		if(list.addAll(c)) {
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		if(list.addAll(index, c)) {
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		list.clear();
		setChanged();
		notifyObservers();		
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public T get(int index) {
		return list.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return list.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		if(list.remove(o)) {
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}

	@Override
	public T remove(int index) {
		T val = list.remove(index);
		setChanged();
		notifyObservers();	
		return val;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if(list.removeAll(c)) {
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if(list.retainAll(c)) {
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}

	@Override
	public T set(int index, T element) {
		T val = list.set(index, element);
		setChanged();
		notifyObservers();
		return val;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		ObservableList<T> newList = new ObservableList<>();
		newList.addAll(list.subList(fromIndex, toIndex));
		return newList;
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <F> F[] toArray(F[] a) {
		return list.toArray(a);
	}
	
	@Override
	public void addObserver(Observer o) {
		if(o != null) {
			super.addObserver(o);
		}
		else {
			throw new ObserverException("Null observer added");
		}
	}
	
	@Override
	public String toString() {
		return "[List " + list + " with " + 
				this.countObservers() + " Observers]";
	}
	
}
