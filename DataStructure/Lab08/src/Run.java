
public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue queue = new Queue();
		queue.printQueue();
		queue.enqueue(1);
		queue.printQueue();
		queue.enqueue(2);
		queue.printQueue();
		queue.enqueue(3);
		queue.printQueue();
		queue.enqueue(4);
		queue.printQueue();
		queue.enqueue(5);
		queue.printQueue();
		System.out.println();
		
		queue.dequeue();
		queue.printQueue();
		queue.dequeue();
		queue.printQueue();
		queue.dequeue();
		queue.printQueue();
		queue.enqueue(6);
		queue.printQueue();
	}

}
