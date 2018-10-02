package general.attributes;

public interface ReadOnlyAttribute<T> extends Comparable<T> {

	public T getValue(); 
}
