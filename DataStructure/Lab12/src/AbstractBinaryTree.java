import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The common abstract base class for binary tree implementation.
 *
 * @param <T> element type for the node
 */
abstract public class AbstractBinaryTree<T> implements BinaryTree<T> {

    /**
     * Number of positions in a tree
     */
    protected int size = 0;

    /**
     * Returns the number of nodes in the tree.
     * @return the size of the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Get the sibling of the position.
     * @param p the position of the tree
     * @return the sibling of the position if exists, null otherwise
     */
    @Override
    public Position<T> sibling(Position<T> p) {
        if (isRoot(p))
            return null;

        return isLeftChild(p) ? rightSibling(p) : leftSibling(p);
    }

    /**
     * Return the list of positions in the tree in pre-order.
     * @return the list of positions
     */
    @Override
    public List<Position<T>> preOrder() {
        List<Position<T>> nodes = new ArrayList<>();

        if (!isEmpty())
            preOrder(root(), nodes);

        return nodes;
    }

    /**
     * Return via parameter the list of positions in the subtree rooted at <tt>p</tt> in pre-order.
     * @param p the position
     * @param positions the list of positions in pre-order (inout parameter)
     */
    protected void preOrder(Position<T> p, List<Position<T>> positions) {
    	
    	positions.add(p);
        if(p.hasLeft() == true){
        	preOrder(p.left(), positions);
        }
        if(p.hasRight() == true){
        	preOrder(p.right(), positions);
        }
    }

    /**
     * Return the list of positions in the tree in in-order.
     * @return the list of positions
     */
    @Override
    public List<Position<T>> inOrder() {
        List<Position<T>> positions = new ArrayList<>();

        if (!isEmpty())
            inOrder(root(), positions);

        return positions;
    }

    /**
     * Return via parameter the list of positions in the subtree rooted at <tt>p</tt> in in-order.
     * @param p the position
     * @param positions the list of positions in in-order (inout parameter)
     */
    protected void inOrder(Position<T> p, List<Position<T>> positions) {
    	if(p.hasLeft() == true){
    		inOrder(p.left(), positions);
    	}
    	positions.add(p);
    	if(p.hasRight() == true){
    		inOrder(p.right(), positions);
    	}
		
    }

    /**
     * Return the list of positions in the tree in post-order.
     * @return the list of positions
     */
    @Override
    public List<Position<T>> postOrder() {
        List<Position<T>> positions = new ArrayList<>();

        if (!isEmpty())
            postOrder(root(), positions);

        return positions;
    }

    /**
     * Return via parameter the list of positions in the subtree rooted at <tt>p</tt> in post-order.
     * @param p the position
     * @param positions the list of positions in post-order (inout parameter)
     */
    protected void postOrder(Position<T> p, List<Position<T>> positions) {
    	if(p.hasLeft() == true){
    		postOrder(p.left(), positions);
    	}
    	if(p.hasRight() == true){
    		postOrder(p.right(), positions);
    	}
    	positions.add(p);
		
    }

    /**
     * Return the list of positions in the tree in level-order.
     * @return the list of positions
     */
    @Override
    public List<Position<T>> levelOrder() {
        List<Position<T>> positions = new ArrayList<>();

        if (!isEmpty())
            levelOrder(root(), positions);

        return positions;
    }

    /**
     * Return via parameter the list of positions in the subtree rooted at <tt>p</tt> in level-order.
     * @param p the position
     * @param positions the list of positions in level-order (inout parameter)
     */
    protected void levelOrder(Position<T> p, List<Position<T>> positions) {
        Queue<Position<T>> queue = new LinkedList<>();
        ArrayList<Position<T>> plist = new ArrayList<Position<T>>();
        int depthCount = 0;
        
        preOrder(p, plist);
        while(depthCount <= p.height()){
        	for(Position<T> x : plist){
        		if(x.depth() == depthCount){
        			queue.add(x);
        		}
        	}
        	depthCount += 1;
        }
        
        for(Position<T> t : queue){
        	//System.out.print(t + " ");
        	positions.add(t);
        }
        System.out.println("");
    }

    /**
     * Print the contents of the tree.
     */
    public void printTree() {
        System.out.println("You may implement your own print routine");
    }

    /**
     * Attach a tree as the left child of the position <tt>p</tt> if the left child of the
     * position <tt>p</tt> is an external node. Throw exception otherwise.
     * @param p the position to insert left subtree
     * @param tree a subtree to be attached
     */
    public void attachLeftSubtree(Position<T> p, BinaryTree<T> tree) {
        if (isInternal(left(p))) throw new IllegalStateException("Left subtree already exists");

        p.setLeft(tree.root());

        size += tree.preOrder().size();
    }

    /**
     * Attach a tree as the right child of the position <tt>p</tt> if the right child of the
     * position <tt>p</tt> is an external node. Throw exception otherwise.
     * @param p the position to insert left subtree
     * @param tree a subtree to be attached
     */
    public void attachRightSubtree(Position<T> p, BinaryTree<T> tree) {
        if (isInternal(right(p))) throw new IllegalStateException("Right subtree already exists");

        p.setRight(tree.root());

        size += tree.preOrder().size();
    }
}
