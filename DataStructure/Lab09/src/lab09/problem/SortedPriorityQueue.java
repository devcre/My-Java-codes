package lab09.problem;

import java.util.Comparator;

public class SortedPriorityQueue<K, V> implements Comparator<Node<K,V>> {
	private Node<K, V> header;
	private Node<K, V> trailer;
	
	public SortedPriorityQueue() { //insert시에 자기가 들어갈 자리를 찾아 들어감
		header = (Node<K, V>) new Node(0,"Header");
		trailer = (Node<K, V>) new Node(9999,"trailer");
		header.next = trailer;
		trailer.prev = header;
	}
	
	public void insert(K key, V value) {
		Node<K, V> newNode = new Node<K, V>(key, value);
		Node<K, V> curNode = header;
		Node<K, V> curnNode = null;
		
		while(curNode != trailer){
			if((compare(curNode, newNode) < 0) && (compare(newNode, curNode.next) < 0)){
				curnNode = curNode.next;
				
				curNode.next = newNode;
				newNode.next = curnNode;
				
				curnNode.prev = newNode;
				newNode.prev = curNode;
			}
			curNode = curNode.next;
		}
	}
	
	public Node<K, V> removeMin() {
		Node<K, V> rNode = header;
		Node<K, V> nNode;
		
		if(header.next != trailer){// queue안에 원소가 하나라도 있으면
			rNode = rNode.next;
			nNode = rNode.next;
			
			header.next = nNode;
			nNode.prev = header;
			
			rNode.next = null;
			rNode.prev = null;
			return rNode;
		}
		return header;
	}
	
	public Node<K, V> min() {
		Node<K, V> mNode = header;
		
		if(header.next != trailer){
			return mNode.next;
		}
		return header;
	}
	
	public int size() {
		Node<K, V> curNode = header;
		int count = 0;
		
		while(curNode.next != trailer){
			curNode = curNode.next;
			count += 1;
		}
		return count;
	}
	
	public boolean isEmpty() {
		return header.next == trailer? true : false;
	}
	
	public void printList() {
		Node<K, V> curNode = header;
		
		while(curNode.next != trailer){
			curNode = curNode.next;
			System.out.print("(" + curNode.getKey() + "," + curNode.getValue() + ")" + " ");
		}
		System.out.println("");
	}

	@Override
	public int compare(Node<K, V> o1, Node<K, V> o2) {
		if((int)o1.getKey() < (int)o2.getKey()){
			return -1; // o1의 키값이 더 작다.
		}
		return 1;
	}
}
