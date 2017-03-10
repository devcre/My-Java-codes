public class Main {
	public static void main(String[] args) {
		DoublyLinkedList list = new DoublyLinkedList();
		list.addFirst("t");
		list.printList();
		list.addFirst("a");
		list.printList();
		list.addFirst("d");
		list.printList();
		list.addLast("a");
		list.addAt(2, "c");
		list.printList();
		list.printListBack();
		list.removeAt(2);
		list.printList();
		list.removeAt(1);
		list.printList();
		list.removeAt(0);
		list.printList();
	}
}
