package lab09.problem;

public class Main {

	public static void main(String[] args) {
	
		UnsortedPriorityQueue<Integer, String> uq = new UnsortedPriorityQueue<Integer, String>();
		System.out.println("Unsorted Priority Queue");
		uq.insert(3, "A"); uq.insert(10, "B"); uq.insert(7, "B");
		uq.insert(2, "F"); uq.insert(6, "C");
		uq.printList();
		Node<Integer, String> removedNode = uq.removeMin();
		System.out.println("removeMin(): " + "(" + removedNode.getKey() + "," + removedNode.getValue() + ")");
		System.out.println("size(): " + uq.size());
		removedNode = uq.removeMin();
		System.out.println("removeMin(): " + "(" + removedNode.getKey() + "," + removedNode.getValue() + ")");
		System.out.println("min(): " + "(" + uq.min().getKey() + "," + uq.min().getValue() + ")");
		uq.insert(1, "Z"); uq.insert(5, "E");
		uq.printList();
		
		SortedPriorityQueue<Integer, String> sq = new SortedPriorityQueue<Integer, String>();
		System.out.println("\nSorted Priority Queue");
		sq.insert(3, "A"); sq.insert(10, "B"); sq.insert(7, "B");
		sq.insert(2, "F"); sq.insert(6, "C");
		sq.printList();
		removedNode = sq.removeMin();
		System.out.println("removeMin(): " + "(" + removedNode.getKey() + "," + removedNode.getValue() + ")");
		removedNode = sq.removeMin();
		System.out.println("removeMin(): " + "(" + removedNode.getKey() + "," + removedNode.getValue() + ")");
		System.out.println("size(): " + sq.size());
		System.out.println("min(): " + "(" + sq.min().getKey() + "," + sq.min().getValue() + ")");
		sq.insert(1, "Z"); sq.insert(5, "E");
		sq.printList();

	}
}
