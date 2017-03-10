package lab09.problem;

public class Node<K, V> implements Comparable<Node<K, V>> {
	public Node<K, V> prev;
	public Node<K, V> next;
	private K key;
	private V value;
	
	public Node(K key, V value) {
		this.key = key;
		this.value = value;
		prev = next = null;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
	
	@Override
	public int compareTo(Node<K, V> o) {
		if((int)this.getKey() < (int)o.getKey()){
			return -1;
		}
		return 1;
	}
}
