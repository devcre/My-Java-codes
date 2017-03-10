
public class LinkedList {
	private Node head; // ����Ʈ�� ��带 ����Ű�� ������	point to head of the list
	private int count; // ����Ʈ�� ��尹��	the number of nodes in the list

	private class Node {	// ����Ŭ���� Node	inner class Node
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
	public void insert(int input) {// �Ǿտ� input �����͸� ������ ��� �߰�	insert a node at the first of the list
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
	public void insert(int position, int input){// position ��ġ�� input �����͸� ������ ��� �߰�	insert a node at the given position
		if(position > count || position <0) {
			System.out.println("position value - out of range.");
			return;
		}
		// TODO
		Node inode = new Node(input);//inset node
		Node curpoi = head;//current point
		
		for(int i=1; i<position; i++){
			curpoi = curpoi.next;//i+1 ��° ��尡 �����
		}
		
		inode.next = curpoi.next;
		curpoi.next = inode;
		count += 1;
	}

	public void delete() {	// �Ǿ��� ��带 ����
		// TODO
		Node temp1 = head;
		head = temp1.next;
		temp1 = null;
		count -= 1;
	}
	public void delete(int position) {	// position ��ġ�� ��带 ����		delete a Node at the given position
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
	public boolean isEmpty() { // ����Ʈ�� ����ִ��� �˻�	check whether the list is empty or not
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}
	public void print_list() {
		Node temp = head;
		for (int i = 0; i < count; i++) { // ��� ���
			System.out.print(temp.data + "  ");
			temp = temp.next;
		}
		System.out.println("");
	}

}
