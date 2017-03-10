package lab09.problem;

public class UnsortedPriorityQueue<K, V> {
	private Node<K, V> header;
	private Node<K, V> trailer;
	
	public UnsortedPriorityQueue() {
		// 순서에 상관없이 삽입하지만(막넣는다) 뺄때는 가장 우선순위가 높은 키값을 찾아 제거한다.
		header = (Node<K, V>) new Node(0,"Header");
		trailer = (Node<K, V>) new Node(9999,"trailer");
		header.next = trailer;
		trailer.prev = header;
	}
	
	public void insert(K key, V value) {
		Node<K, V> newNode = new Node<K, V>(key, value);
		Node<K, V> cNode = header;
		
		while(cNode.next != trailer){
			cNode = cNode.next;
		}
		cNode.next = newNode;
		newNode.next = trailer;
		
		trailer.prev = newNode;
		newNode.prev = cNode;
	}
	
	public Node<K, V> removeMin() {
		Node<K, V> curNode = header.next;
		Node<K, V> rNode = trailer;
		Node<K, V> preNode;
		Node<K, V> ntNode;
		
		while(curNode.next != trailer){
			if(curNode.compareTo(rNode) < 0){
				rNode = curNode;
			}
			curNode = curNode.next;
		}
		
		preNode = rNode.prev;
		ntNode = rNode.next;
		preNode.next = ntNode;
		ntNode.prev = preNode;
		
		rNode.next = null;
		rNode.prev = null;
		
		return rNode;
	}
	
	public Node<K, V> min() {
		Node<K, V> curNode = header.next;
		Node<K, V> mNode = trailer;
		
		while(curNode != trailer){
			if(curNode.compareTo(mNode) < 0){
				mNode = curNode;
			}
			curNode = curNode.next;
		}
		
		return mNode;
	}
	
	public int size() {
		int count = 0;
		Node<K, V> curNode = header;
		
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
		Node<K, V> curNode = header.next;
		
		while(curNode != trailer){
			System.out.print("(" + curNode.getKey() + "," + curNode.getValue() + ")" + " ");
			curNode = curNode.next;
		}
		System.out.println("");
	}
}
