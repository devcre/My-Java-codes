public class DoublyLinkedList {
	private Node header;
	private Node trailer;
	private int size;
	private class Node {
    	String data;
    	Node prev;
	    Node next;

	    Node(String data) {
	        this.data = data;
	        prev = null;
	        next = null;
	    }
	}
	
	public DoublyLinkedList() {
		header = new Node(null);
		trailer = new  Node(null);
		size = 0;
	}

	public void addFirst(String data) {
		if(this.isEmpty() == true){
			Node node = new Node(data);
			header.next = node;
			trailer.prev  = node;
			
			node.next = trailer;
			node.prev = header;
			size += 1;
		}
		else{
			Node node = new Node(data);
			Node nnode = header.next;
			
			header.next = node;
			nnode.prev = node;
			
			node.next = nnode;
			node.prev = header;
			size += 1;
		}
	}

	public void addLast(String data) {
		if(this.isEmpty() == true){
			Node node = new Node(data);
			header.next = node;
			trailer.prev = node;
			
			node.next = trailer;
			node.prev = header;
			size += 1;
		}
		else{
			Node node = new Node(data);
			Node pnode = trailer.prev;
			
			pnode.next = node;
			trailer.prev = node;
			
			node.next = trailer;
			node.prev = pnode;
			size += 1;
		}
	}

	public void addAt(int position, String data) {
		if (position > size || position < 0) return;
		else if(position == 0){
			addFirst(data);
		}
		else{
			Node node = new Node(data);
			Node preNode = header.next;
			Node ppreNode = new Node(null);
			for(int i=0; i<position;i++){
				preNode = preNode.next;
				ppreNode = preNode.prev;
			}
			ppreNode.next = node;
			preNode.prev = node;
			
			node.next = preNode;
			node.prev = ppreNode;
			size += 1;
		}
	}

	public void removeFirst() {
		Node nnode = header.next.next;
		header.next = nnode;
		nnode.prev = header;
		size -= 1;
	}

	public void removeLast() {
		Node pnode = trailer.prev.prev;
		trailer.prev = pnode;
		pnode.next = trailer;
		size -= 1;
	}

	public void removeAt(int position) {
		Node pcur = header;
		Node cur = header.next;
		Node ncur = cur.next;
		
		if (position > size || position < 0) return;
		else{
			for(int i=0; i<position; i++){
				pcur = pcur.next;
				cur = cur.next;
				ncur = ncur.next;
			}
			pcur.next = ncur;
			ncur.prev = pcur;
			cur.next = null;
			cur.prev = null;
			size -= 1;
		}
	}

	public void printList() {
		Node hcurrent = header;
		System.out.print("header <-> ");
		while(hcurrent.next != trailer){
			hcurrent = hcurrent.next;
			if(hcurrent.next != trailer){
				System.out.print(hcurrent.data + " <-> ");
			}
			else{
				System.out.println(hcurrent.data + " <-> trailer");
			}
		}
	}

	public void printListBack() {
		Node tcurrent = trailer;
		System.out.print("trailer <->");
		while(tcurrent.prev != header){
			tcurrent = tcurrent.prev;
			if(tcurrent.prev != header){
				System.out.print(tcurrent.data + " <-> ");
			}
			else{
				System.out.println(tcurrent.data + " <-> header");
			}
		}
	}

	public boolean isEmpty() {
		return size == 0 ? true : false;
	}
}