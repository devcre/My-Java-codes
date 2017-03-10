
public class LinkedList {
	private Node head; // 리스트의 헤드를 가리키는 포인터	point to head of the list
	private int count; // 리스트의 노드갯수	the number of nodes in the list

	private class Node {	// 내부클래스 Node	inner class Node
		int data;
		Node next;

		Node(int input) {
			data = input;
			next = null;
		}
	}
	public LinkedList() {
		head = null;
		count = 0;
	}
	public void insert(int input) {// 맨앞에 input 데이터를 가지는 노드 추가	insert a node at the first of the list
		// TODO
		Node nnode = new Node(input);
		Node tail = head;
		
		for(int k=0; k<count-1; k++){
			tail = head.next;
		}
		if(head != null){
			tail.next =  nnode;
		}
		else{
			Node mnode = new Node(input);
			head = mnode;
		}
		count += 1;
	}
	public void insert(int position, int input){// position 위치에 input 데이터를 가지는 노드 추가	insert a node at the given position
		if(position > count || position <0) {
			System.out.println("position value - out of range.");
			return;
		}
		// TODO
		Node inode = new Node(input);//inset node
		Node curpoi = head;//current point
		
		for(int i=1; i<position; i++){
			curpoi = curpoi.next;//i+1 번째 노드가 저장됨
		}
		
		inode.next = curpoi.next;
		curpoi.next = inode;
		count += 1;
	}

	public void delete() {	// 맨앞의 노드를 삭제
		// TODO
		Node temp1 = head;
		head = temp1.next;
		temp1 = null;
		count -= 1;
	}
	public void delete(int position) {	// position 위치의 노드를 삭제		delete a Node at the given position
		if(position > count || position <0){
			System.out.println("position value - out of range.");
			return;
		}
		// TODO
		Node temp2;
		Node dnode = head.next;
		Node cp = head;
		
		for(int j=1; j<position; j++){
			cp = cp.next;
			dnode = dnode.next;
		}
		if(position == 0){
			delete();
		}
		else{
			//dnode = dnode.next;
			temp2 = cp.next;
			cp.next = dnode.next;
			count -= 1;
			temp2 = null;
		}
	}
	public boolean isEmpty() { // 리스트가 비어있는지 검사	check whether the list is empty or not
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}
	public void print_list() {
		Node temp = head;
		for (int i = 0; i < count; i++) { // 노드 출력
			System.out.print(temp.data + "  ");
			temp = temp.next;
		}
		System.out.println("");
	}

}
