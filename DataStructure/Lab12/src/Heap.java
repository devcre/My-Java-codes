
public interface Heap<K extends Comparable<K>, V>{
	enum Type {
		MAX_HEAP("MAX"), MIN_HEAP("MIN");
		private String value;
		
		private Type(String value) {
			this.value = value;
		}
	}
	/**
	 * Inserts a key-value pair and return the entry created.
	 * @param key
	 * @param value
	 * @return the entry storing the new key-value pair
	 * @throws IllegalArgumentException - if the key is unacceptable for this queue
	 */
	Entry<K, V> insert(K key, V value);
	
	/**
	 * Returns (but does not remove) an entry with the minimal (or maximal) key depending on the heap type.
	 * @return entry having a minimal or maximal key depending on heap type (or null if empty)
	 */
	Entry<K, V> peek();
	
	void printHeap();
	/**
	 * Removes and returns an entry with the minimal (or maximal) key depending on the heap type.
	 * @return the removed entry (or null if empty)
	 */
	Entry<K, V> remove();
	
	/**
	 * Returns the number of entries in the heap.
	 * @return the size of the heap
	 */
	int size();
}
