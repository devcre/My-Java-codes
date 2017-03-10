
public interface BinarySearchTree<K extends Comparable<K>, V> extends BinaryTree<Entry<K, V>>{
	
	/**
	 * Associates the given value with the given key, returning any overridden value.
	 * @param key - search key
	 * @param value - value of entry
	 */
	void put(K key, V value);

	/**
	 * Returns the value associated with the specified key (or else null).
	 * @param key - search key
	 * @return the value associated with the specified key (or else null)
	 */
	Entry<K,V> get(K key);

	/**
	 * Returns the entry with the least key value greater than or equal to "key" (or null if no such entry exists).
	 * @param key - search key
	 * @return the entry with the least key value greater than or equal to "key" (or null, if no such entry exists)
	 */
	Entry<K,V> ceilingEntry(K key);
	
	/**
	 * Returns the entry with the greatest key value less than or equal to "key" (or null if no such entry exists).
	 * @param key - search key
	 * @return the entry with the greatest key value less than or equal to k (or null, if no such entry exists)
	 */
	Entry<K,V> floorEntry(K key);

	/**
	 * Returns the entry with the least key value strictly greater than "key" (or null if no such entry exists).
	 * @param key - search key
	 * @return the entry with the least key value strictly greater than "key" (or null if no such entry exists)
	 */
	Entry<K,V> higherEntry(K key);

	/**
	 * Returns the entry with the greatest key value strictly less than "key" (or null if no such entry exists).
	 * @return the entry with the greatest key value strictly less than k (or null, if no such entry exists).
	 */
	Entry<K,V> lowerEntry(K key);
	
	/**
	 * Returns the entry with smallest key value (or null, if the tree is empty).
	 * @return the entry with smallest key value (or null, if the tree is empty)
	 */
	Entry<K,V> firstEntry();
	
	/**
	 * Returns the entry with largest key value(or null if the tree is empty)
	 * @return the entry with largest key value (or null, if the map is empty)
	 */
	Entry<K,V> lastEntry();
	
	/**
	 * Removes the entry having key "key" (if any) and returns its associated value.
	 * @param key - search key
	 */
	Entry<K, V> remove(K key);
}
