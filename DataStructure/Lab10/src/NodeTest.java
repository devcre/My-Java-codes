import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void create_single_internal_node() throws Exception {
        Node<Integer> n = new INode<>(10);

        assertEquals(10, n.element().intValue());
        assertEquals(true, n.isInternal());
        assertEquals(false, n.isExternal());
        assertEquals(false, n.left().isInternal());
        assertEquals(true, n.left().isExternal());
        assertEquals(null, n.parent());

        n.setElement(20);
        assertEquals(20, n.element().intValue());
        assertEquals(true, n.isInternal());
        assertEquals(false, n.isExternal());
        assertEquals(false, n.left().isInternal());
        assertEquals(true, n.left().isExternal());
        assertEquals(null, n.parent());
        assertEquals(0, n.depth());
        assertEquals(1, n.height());
    }

    @Test
    public void create_single_external_node() throws Exception {
        Node<Integer> n = new ENode<>();

        assertEquals(false, n.isInternal());
        assertEquals(true, n.isExternal());
        assertEquals(null, n.left());
        assertEquals(null, n.right());
        assertEquals(null, n.parent());
        assertEquals(0, n.depth());
        assertEquals(0, n.height());
    }

    @Test
    public void create_node_with_one_left_child() throws Exception {
        Node<Integer> n = new INode<>(10, new INode<>(20), null);

        assertEquals(2, n.height());
        assertEquals(true, n.left().isInternal());
        assertEquals(false, n.right().isInternal());
        assertEquals(true, n.right().isExternal());
        assertEquals(false, n.left().left().isInternal());
        assertEquals(null, n.right().right());
        assertEquals(null, n.parent());
        assertEquals(n, n.left().parent());
        assertEquals(n, n.right().parent());
    }

    @Test
    public void create_node_with_one_right_child() throws Exception {
        Node<Integer> n = new INode<>(10, null, new INode<>(20));

        assertEquals(2, n.height());
        assertEquals(false, n.left().isInternal());
        assertEquals(true, n.right().isInternal());
        assertEquals(false, n.right().isExternal());
        assertEquals(null, n.left().left());
        assertEquals(null, n.parent());
        assertEquals(n, n.left().parent());
        assertEquals(n, n.right().parent());
    }

    @Test
    public void test_balanced_tree() throws Exception {
        Node<Integer> n = new INode<>(10, new INode<>(20), new INode<>(30));

        assertEquals(2, n.height());
        assertEquals(true, n.left().left().isExternal());
        assertEquals(true, n.right().right().isExternal());
    }

}