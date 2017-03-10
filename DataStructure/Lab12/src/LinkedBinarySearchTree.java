import java.util.List;

public class LinkedBinarySearchTree<K extends Comparable<K>, V> extends LinkedBinaryTree<Entry<K, V>> implements BinarySearchTree<K, V> {

	@Override
	public void put(K key, V value) {
		Position<Entry<K, V>> curNode = root;
		//root.setLeft(new ENode<>());
		//root.setRight(new ENode<>());
		Position<Entry<K, V>> newEntry = new INode<>(new Entry<>(key, value));
		
		while(true){
			if((int)curNode.element().key > (int)newEntry.element().key){
				if(curNode.hasLeft() == false){
					curNode.setLeft(newEntry);
					break;
				}
				curNode = curNode.left();
			}
			else{
				if(curNode.hasRight() == false){
					curNode.setRight(newEntry);
					break;
				}
				curNode = curNode.right();
			}
		}
	}

	@Override
	public Entry<K, V> get(K key) {
		Position<Entry<K, V>> curNode = root;
		while(true){
			if((curNode.hasLeft() == false) && (curNode.hasRight() == false)){
				return null;
			}
			
			if(curNode.element().key == key){
				return curNode.element();
			}
			else{
				if((int)curNode.element().key > (int)key){
					curNode = curNode.left();
				}
				else{
					curNode = curNode.right();
				}
			}
		}
	}

	@Override
	public Entry<K, V> ceilingEntry(K key) { //entry with smallest key กร k
		Position<Entry<K, V>> curNode = root;
		Entry<K, V> ceilingKey = null;
		
		while(true){
			if(curNode.hasRight() == true && (int)curNode.element().key < (int)key){
				curNode = curNode.right();
			}
			else if(curNode.hasLeft() == true && (int)curNode.element().key > (int)key){
				ceilingKey = curNode.element();
				curNode = curNode.left();
			}
			else if((int)curNode.element().key == (int)key){//key.compaerTo(curNode.element().key) < 0
				ceilingKey = curNode.element();
				break;
			}
			else{
				ceilingKey = curNode.element();
				break;
			}
		}
		if(ceilingKey != null){
			return ceilingKey;
		}
		return null;
	}

	@Override
	public Entry<K, V> floorEntry(K key) {
		Position<Entry<K, V>> curNode = root;
		Entry<K, V> ceilingKey = null;
		
		while(true){
			if(curNode.hasRight() == true && (int)curNode.element().key < (int)key){
				ceilingKey = curNode.element();
				curNode = curNode.right();
			}
			else if(curNode.hasLeft() == true && (int)curNode.element().key > (int)key){
				curNode = curNode.left();
			}
			else if((int)curNode.element().key == (int)key){
				ceilingKey = curNode.element();
				break;
			}
			else{
				ceilingKey = curNode.element();
				break;
			}
		}
		if(ceilingKey != null){
			return ceilingKey;
		}
		return null;
	}

	@Override
	public Entry<K, V> higherEntry(K key) {
		int count = 0;
		List<Position<Entry<K, V>>> binarytree = inOrder();
		for(Position<Entry<K, V>> x : binarytree){
			if(key == x.element().key){
				break;
			}
			count += 1;
		}
		return binarytree.get(count+1).element();
	}

	@Override
	public Entry<K, V> lowerEntry(K key) {
		int count = 0;
		List<Position<Entry<K, V>>> binarytree = inOrder();
		for(Position<Entry<K, V>> x : binarytree){
			if(key == x.element().key){
				break;
			}
			count += 1;
		}
		return binarytree.get(count-1).element();
	}

	@Override
	public Entry<K, V> firstEntry() {
		Position<Entry<K, V>> curNode = root;
		if(curNode == null){
			return null;
		}
		
		while(curNode.hasLeft() != false){
			curNode = curNode.left();
		}
		return curNode.element();
	}

	@Override
	public Entry<K, V> lastEntry() {
		Position<Entry<K, V>> curNode = root;
		if(curNode == null){
			return null;
		}
		
		while(curNode.hasRight() != false){
			curNode = curNode.right();
		}
		return curNode.element();
	}

	@Override
	public Entry<K, V> remove(K key) {
		Position<Entry<K, V>> delNode = root;
		Position<Entry<K, V>> leastKeyNode;
		while(true){
			if(delNode.element().key == key){
				break;
			}
			else if(delNode.hasRight() == true && (int)delNode.element().key < (int)key){
				delNode = delNode.right();
			}
			else if(delNode.hasLeft() == true && (int)delNode.element().key > (int)key){
				delNode = delNode.left();
			}
			else{
				break;
			}
		}
			
		if(delNode.numChildren() == 1){
			if(delNode.isLeftChild() == true && delNode.hasLeft() == true){
				delNode.parent().setLeft(delNode.left());
			}
			else if(delNode.isLeftChild() == true  && delNode.hasRight() == true){
				delNode.parent().setLeft(delNode.right());
			}
			else if(delNode.isRightChild() == true && delNode.hasLeft() == true){
				delNode.parent().setRight(delNode.left());
			}
			else if(delNode.isRightChild() == true && delNode.hasRight() == true){
				delNode.parent().setRight(delNode.right());
			}
		}
		else if(delNode.numChildren() == 2){
			leastKeyNode = delNode.right();
			while(leastKeyNode.hasLeft() != false){
				leastKeyNode = leastKeyNode.left();
			}
			if(leastKeyNode.hasRight() == true){
				leastKeyNode.parent().setLeft(leastKeyNode.right());
			}
			delNode.replace(leastKeyNode.element());
			if(leastKeyNode.isLeftChild() == true){
				leastKeyNode.parent().setLeft(new ENode<>());
			}
			else if(leastKeyNode.isRightChild() == true){
				leastKeyNode.parent().setRight(new ENode<>());
			}
		}
		else{
			Entry<K, V> delElement = delNode.element();
			if(delNode.isLeftChild() == true){
				delNode.parent().setLeft(new ENode<>());
			}
			else{
				delNode.parent().setRight(new ENode<>());
			}
			return delElement;
		}
		return delNode.element();
	}

}
