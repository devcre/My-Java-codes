import java.util.List;

public class LinkedHeap<K extends Comparable<K>, V> extends LinkedBinaryTree<Entry<K, V>> implements Heap<K, V> {
private static final Heap.Type MAX_HEAP = null;
	//	private Position<Entry<K, V>> root = null;
	private Heap.Type type;

	LinkedHeap() {
	}
	LinkedHeap(Heap.Type type) {
		this.type = type;
	}

	/**
	 * Inserts a key-value pair and return the entry created
	 * 
	 */
	
	@Override
	public Entry<K, V> insert(K key, V value) { //root노드부터 저장이 안됨
		Position<Entry<K, V>> newEntry = new INode<>(new Entry<>(key, value));
		List<Position<Entry<K, V>>> heap = levelOrder();
		Position<Entry<K, V>> tempParent;
		//Position<Entry<K, V>> prevLastNode = heap.get(heap.size()-1);
		
		if(heap.size() % 2 == 0){
			tempParent = heap.get(heap.size() / 2 - 1);
			tempParent.setRight(newEntry);
		}
		else{
			tempParent = heap.get(heap.size() / 2);
			tempParent.setLeft(newEntry);
		}
		upHeap(newEntry);
		size += 1;
		
		return newEntry.element();
	}
	
	public void upHeap(Position<Entry<K, V>> newEntry){
		Position<Entry<K, V>> curNode = newEntry;
		Entry<K, V> temp;
		if(type.toString() == "MAX_HEAP"){
			while(curNode != root){
				if((int)curNode.element().key > (int)curNode.parent().element().key){
					//System.out.println(curNode.parent() + " --- " + curNode);
					temp = curNode.parent().element();
					curNode.parent().replace(curNode.element());
					 curNode.replace(temp);
					//System.out.println(curNode.parent() + " --- " + curNode);
				}
				curNode = curNode.parent();
			}
		}
		else{
			while(curNode != root){
				if((int)curNode.element().key < (int)curNode.parent().element().key){
					temp = curNode.parent().element();
					curNode.parent().replace(curNode.element());
					curNode.replace(temp);
				}
				curNode = curNode.parent();
			}
		}
	}
	
	public void downHeap(){
		Position<Entry<K, V>> curNode = root;
		Entry<K, V> temp;
		
		if(type.toString() == "MAX_HEAP"){
			while(curNode.hasLeft() != false && curNode.hasRight() != false){
				if((int)curNode.left().element().key > (int)curNode.right().element().key){
					temp = curNode.element();
					curNode.replace(curNode.left().element());
					curNode.left().replace(temp);
					curNode = curNode.left();
				}
				else{
					temp = curNode.element();
					curNode.replace(curNode.right().element());
					curNode.right().replace(temp);
					curNode = curNode.right();
				}
			}
		}
		else{
			while(curNode.hasLeft() != false && curNode.hasRight() != false){
				//System.out.println( "          "+ curNode);
				//System.out.println(curNode.left() + " --- " + curNode.right());
				if((int)curNode.left().element().key < (int)curNode.right().element().key){
					temp = curNode.element();
					curNode.replace(curNode.left().element());
					curNode.left().replace(temp);
					curNode = curNode.left();
				}
				else{
					temp = curNode.element();
					curNode.replace(curNode.right().element());
					curNode.right().replace(temp);
					curNode = curNode.right();
				}
				//System.out.println(curNode.isExternal()); //Debug
			}
		}
	}
	
	@Override
	public Entry<K, V> peek() {
		List<Position<Entry<K, V>>> heap = levelOrder();
		if(size == 0){
			return null;
		}
		System.out.println(heap.get(0));
		return   heap.get(0).element();
	}

	@Override
	public void printHeap() {
		Position<Entry<K, V>> curNode = root;
		List<Position<Entry<K, V>>> heap = levelOrder();
		int count = 1;
		for(Position<Entry<K, V>> x : heap){
			System.out.print(curNode.toString() + "        ");
		}
	}

	@Override
	public Entry<K, V> remove() {
		Entry<K, V> rootElemet = root.element();
		List<Position<Entry<K, V>>> heap = levelOrder();
		Position<Entry<K, V>> lastNode = heap.get(size()-1);
		root.replace(lastNode.element());
		if(size() % 2 == 0){
			lastNode.parent().setLeft(new ENode<>());
		}
		else{
			lastNode.parent().setRight(new ENode<>());
		}
		downHeap();
		return rootElemet;
	}

	@Override
	public int size() {
		return size;
	}
	
}
