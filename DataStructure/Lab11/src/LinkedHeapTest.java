import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class LinkedHeapTest {

    private LinkedHeap<Integer, String> heap;

    private void initialize(Heap.Type type) {
    	heap = new LinkedHeap<>(type);
		heap.addRoot(new Entry<>(2, "H"));
		heap.insert(3, "D");
		heap.insert(5, "L");
		heap.insert(4, "B");
		heap.insert(7, "F");
		heap.insert(11, "J");
		heap.insert(19, "M");
		heap.insert(22, "O");
	}

	@Test
	public void testInsert() {
		initialize(Heap.Type.MAX_HEAP);
		heap.insert(1, "Z");
		System.out.println("testInsert: (Max-heap)\n"+toList(heap.levelOrder()));
		assertEquals("(22, O)", heap.peek().toString());
		assertEquals("[(22, O), (19, M), (11, J), (5, L), (4, B), (3, D), (7, F), (2, H), (1, Z)]", toList(heap.levelOrder()).toString());
	}

	@Test
	public void testRemove() {
		initialize(Heap.Type.MIN_HEAP);
		heap.insert(1, "Z");
		heap.insert(10, "K");
		heap.insert(20, "V");
		heap.insert(9, "P");
		heap.insert(8, "R");
		heap.remove();
		System.out.println("testRemove: (Min-heap)\n" + toList(heap.levelOrder()));
		assertEquals("(2, H)", heap.peek().toString());
		assertEquals("[(2, H), (3, D), (5, L), (4, B), (7, F), (8, R), (19, M), (22, O), (9, P), (10, K), (20, V), (11, J)]", toList(heap.levelOrder()).toString());
	}
	
	private List<String> toList(List<Position<Entry<Integer, String>>> list) {
        return list.stream()
                 .map(p -> p.element().toString())
                 .collect(Collectors.toList());
    }
	
}
