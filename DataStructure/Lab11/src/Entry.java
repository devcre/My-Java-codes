import java.lang.Object;


public class Entry<K extends java.lang.Comparable<K> , V> {
	public K key;
	public V value;
	
	public Entry(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	@Override
	public String toString(){ //entry를 내맘대로 출력
		return "(" + key + ", " + value + ")";
	}
}
