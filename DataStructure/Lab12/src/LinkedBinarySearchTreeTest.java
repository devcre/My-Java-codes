import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class LinkedBinarySearchTreeTest {
	private LinkedBinarySearchTree<Integer, String> tree;

	@Before
	public void setUp() throws Exception {
		tree = new LinkedBinarySearchTree<>();
		tree.addRoot(new Entry<>(8, "A"));
		tree.put(4,  "B");
		tree.put(2,  "C");
		tree.put(6,  "D");
		tree.put(1,  "E");
		tree.put(3,  "F");
		tree.put(5,  "G");
		tree.put(7,  "H");
		tree.put(9,  "J");
		tree.put(12,  "K");
		tree.put(10,  "L");
		tree.put(19,  "M");
		tree.put(16,  "N");
		tree.put(17,  "O");	
	}

	@Test
	public void testPut() {
		//tree.put(11, "Z");
		System.out.println("testput: "+toList(tree.inOrder()));
		//assertEquals("(22, O)", tree.peek().toString());
		assertEquals("[(1, E), (2, C), (3, F), (4, B), (5, G), (6, D), (7, H), (8, A), (9, J), (10, L), (12, K), (16, N), (17, O), (19, M)]", toList(tree.inOrder()).toString());
	}

	@Test
	public void testGet() {
		assertEquals("(4, B)", tree.get(4).toString());
	}

	@Test
	public void testCeilingEntry(){
		assertEquals("(16, N)", tree.ceilingEntry(13).toString());
	}
	
	@Test
	public void testFirstEntry(){
		assertEquals("(1, E)", tree.firstEntry().toString());
	}
	
	@Test
	public void testFloorEntry(){
		assertEquals("(5, G)", tree.floorEntry(5).toString());
	}

	@Test
	public void testLastEntry() {
		assertEquals("(19, M)", tree.lastEntry().toString());
	}
	
	@Test
	public void testhigherEntry(){
		assertEquals("(8, A)",tree.higherEntry(7).toString());
	}
	
	@Test
	public void testlowerEntry(){
		assertEquals("(4, B)",tree.lowerEntry(5).toString());
	}

	@Test
	public void testRemove() {
		//tree.remove(6);
		tree.remove(17);
		System.out.println("test Remove put: "+toList(tree.inOrder()));
		//assertEquals("[(1, E), (2, C), (3, F), (4, B), (5, G), (7, H), (8, A), (9, J), (10, L), (12, K), (16, N), (17, O), (19, M)]", toList(tree.inOrder()).toString());
		assertEquals("[(1, E), (2, C), (3, F), (4, B), (5, G), (6, D), (7, H), (8, A), (9, J), (10, L), (12, K), (16, N), (19, M)]", toList(tree.inOrder()).toString());
	}
	
	private List<String> toList(List<Position<Entry<Integer, String>>> list) {
        return list.stream()
                 .map(p -> p.element().toString())
                 .collect(Collectors.toList());
    }
}
