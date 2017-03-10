import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class LinkedBinaryTreeTest {

    private BinaryTree<Integer> tree;

    private static BinaryTree<Integer> treeA = new LinkedBinaryTree<>(new INode<>(9,
        new INode<>(5, new INode<>(2, new INode<>(1), new INode<>(4, new INode<>(3), null)), new INode<>(8, new INode<>(6, null, new INode<>(7)), null)),
        new INode<>(12, new INode<>(10, null, new INode<>(11)), new INode<>(14, new INode<>(13), null))
    ));

    @Test
    public void test_for_empty_tree() throws Exception {

        tree = new LinkedBinaryTree<>();

        assertEquals("size", 0, tree.size());
        assertEquals("depth", 0, tree.depth());
        assertEquals("height", 0, tree.height());
        assertEquals(0, tree.inOrder().size());
        assertEquals(0, tree.numChildren(tree.root()));
    }

    @Test
    public void test_for_root_only_tree() throws Exception {
        Node<Integer> n = new INode<>(40);

        tree = new LinkedBinaryTree<>(n);

        assertEquals("size", 1, tree.size());
        assertEquals("depth", 1, tree.depth());  // because of external node
        assertEquals("height", 1, tree.height());
        assertEquals(1, tree.inOrder().size());
        assertFalse(tree.hasLeft(tree.root()));
        assertFalse(tree.hasRight(tree.root()));
        assertNull(tree.sibling(tree.root()));
        assertEquals(0, tree.numChildren(tree.root()));
    }

    @Test
    public void test_for_addRoot() throws Exception {

        tree = new LinkedBinaryTree<>();
        tree.addRoot(10);

        assertEquals("size", 1, tree.size());
        assertEquals("depth", 1, tree.depth());  // because of external node
        assertEquals("height", 1, tree.height());
        assertEquals(1, tree.inOrder().size());
        assertFalse(tree.hasLeft(tree.root()));
        assertFalse(tree.hasRight(tree.root()));
        assertNull(tree.sibling(tree.root()));
        assertEquals(0, tree.numChildren(tree.root()));
    }

    @Test
    public void test_for_left_skewed_tree() throws Exception {
        Node<Integer> n = new INode<>(40, new INode<>(20, new INode<>(10), null), null);

        tree = new LinkedBinaryTree<>(n);

        assertEquals("size", 3, tree.size());
        assertEquals("depth", 3, tree.depth());  // because of external node
        assertEquals("height", 3, tree.height());
        assertTrue(tree.hasLeft(tree.root()));
        assertTrue(tree.hasLeft(tree.root().left()));
        assertFalse(tree.hasRight(tree.root()));
        assertFalse(tree.hasRight(tree.root().right()));
        assertEquals(true, tree.root().right() == tree.sibling(tree.root().left()));
        assertEquals(1, tree.numChildren(tree.root()));
    }

    @Test
    public void test_for_right_skewed_tree() throws Exception {
        Node<Integer> n = new INode<>(40, null, new INode<>(20, null, new INode<>(10)));

        tree = new LinkedBinaryTree<>(n);

        assertEquals("size", 3, tree.size());
        assertEquals("depth", 3, tree.depth());  // because of external node
        assertEquals("height", 3, tree.height());
        assertFalse(tree.hasLeft(tree.root()));
        assertTrue(tree.hasRight(tree.root()));
        assertTrue(tree.hasRight(tree.root().right()));
    }

    @Test
    public void test_for_balanced_tree() throws Exception {
        Node<Integer> n = new INode<>(40,
                             new INode<>(25, new INode<>(10), new INode<>(20)),
                             new INode<>(50, new INode<>(35), new INode<>(60))
        );

        tree = new LinkedBinaryTree<>(n);

        assertEquals("size", 7, tree.size());
        assertEquals("depth", 3, tree.depth());  // because of external node
        assertEquals("height", 3, tree.height());
        assertTrue(tree.isExternal(tree.root().left().left().left()));
        assertTrue(tree.isExternal(tree.root().right().right().right()));
        assertEquals(2, tree.numChildren(tree.root()));
    }

    @Test
    public void inorder_test() throws Exception {
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14), toList(treeA.inOrder()));
    }

    @Test
    public void preOrder_test() throws Exception {
        assertEquals(Arrays.asList(9, 5, 2, 1, 4, 3, 8, 6, 7, 12, 10, 11, 14, 13), toList(treeA.preOrder()));
    }

    @Test
    public void postOrder_test() throws Exception {
        assertEquals(Arrays.asList(1, 3, 4, 2, 7, 6, 8, 5, 11, 10, 13, 14, 12, 9), toList(treeA.postOrder()));
    }

    @Test
    public void levelOrder_test() throws Exception {
        assertEquals(Arrays.asList(9, 5, 12, 2, 8, 10, 14, 1, 4, 6, 11, 13, 3, 7), toList(treeA.levelOrder()));
    }

    @Test
    public void should_be_able_to_attach_subtrees() throws Exception {
        BinaryTree<Integer> tree = new LinkedBinaryTree<>(new INode(10));

        assertEquals(1, tree.size());

        Node<Integer> left = new INode(30, new INode(20), new INode(40));
        Node<Integer> right = new INode(60, new INode(50), new INode(70));

        tree.attachLeftSubtree(tree.root(), new LinkedBinaryTree<>(left));

        assertEquals(4, tree.size());

        tree.attachRightSubtree(tree.root(), new LinkedBinaryTree<>(right));

        assertEquals(7, tree.size());
        assertEquals(Arrays.asList(20,30,40,10,50,60,70), toList(tree.inOrder()));
    }

    private List<Integer> toList(List<Position<Integer>> positions) {
        return positions.stream()
                 .map(p -> p.element().intValue())
                 .collect(Collectors.toList());
    }

}