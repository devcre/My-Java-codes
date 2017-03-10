
public class Queue {
	private Node front;
	private Node back;
	private int size;
	
	private class Node{
		private int data;
		private Node next;
		Node(int data){
			this.data = data;
		}
	}
	public Queue() {
		front = null;
		back = null;
	}

	public void enqueue(int data){
		Node fNode = front;
		Node newNode = new Node(data);
		if(front == null){
			front = newNode;
			back = newNode;
			front.next = null;
		}
		else{
			while(fNode.next != null){
				fNode = fNode.next;
			}
			fNode.next = newNode;
			back = newNode;
		}
		size += 1;
	}

	public Node dequeue(){
		Node nNode;
		Node dNode;
		if(size != 0){
			dNode = front;
			nNode = front.next;
			front = null;
			front = nNode;
			size -= 1;
			
			return dNode;
		}
		else{
			System.out.println("There is no data in queue.");
			return null;
		}
	}

	public void printQueue(){
		Node cNode = front;
		for(int i=0; i < size; i++){
			System.out.print(cNode.data + " ");
			if(cNode != back){
				cNode = cNode.next;
			}
		}
		System.out.println("");
	}

	public boolean isEmpty(){
		return front == null? true: false;
	}

}
