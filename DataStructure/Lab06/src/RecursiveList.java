public class RecursiveList {
	private Node header;
	private Node trailer;
	private int size;

	private class Node {
		String data;
		Node next;
		Node prev;
		Node(String data) {
			this.data = data;
			next = null;
			prev = null;
		}
	}

	public RecursiveList() {
		header = new Node("Header");
		trailer = new Node("Trailer");
		header.next = trailer;
		trailer.prev = header;
		size = 0;
	}

	public void recursiveInsert(int size) {
		int count;
		String inputS;
		
		if(size != 0){
			Node newN;
			if(header.next == trailer){
				newN = new Node("0");
				header.next = newN;
				newN.next = trailer;
				
				trailer.prev = newN;
				newN.prev = header;
			}
			else{
				count = 1;
				Node beforeNode = header.next;
				while(beforeNode.next != trailer){
					beforeNode = beforeNode.next;
					count += 1;
				}
				inputS = " " + count * 5;
				newN = new Node(inputS);
				beforeNode.next = newN;
				newN.next = trailer;
				
				newN.prev = beforeNode;
				trailer.prev = newN;
			}
			recursiveInsert(size-1);
		}
	}

	public void recursiveDelete(int size) {
		Node cur;
		Node beforecur;
		
		if(size != 0){
			cur = trailer;
			cur = cur.prev;
			beforecur = cur.prev;
			
			beforecur.next = trailer;
			trailer.prev = beforecur;
			recursiveDelete(size-1);
		}
	}

	public void printList() {
		Node temp = header.next;
		while(temp.next != trailer){
			System.out.print(temp.data );
			temp = temp.next;
//		for(int i=0; i<size; i++){
//			System.out.print(temp.data + " ");
//			if(temp.next != trailer){
//				temp = temp.next;
//			}
		}
		System.out.println("");
	}
}