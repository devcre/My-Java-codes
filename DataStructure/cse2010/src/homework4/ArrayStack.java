package homework4;


/*
 * CSE2010 Homework #4: ArrayStack.java
 * 
 * Implement your stack here
 * 
 */
public class ArrayStack<T> implements Stack<T> {
	
	public static final int MaxSize = 100;
	private T[] elements;
	
	public ArrayStack() {
		elements = (T[]) new Object[MaxSize];
	}
	
	public ArrayStack(int capacity) {
		elements = (T[]) new Object[capacity];
	}
	
	public void push(T obj) throws FullStackException {
		int i = 0;
		while(true){
			if(elements[i] != null){
				i = i + 1;
			}
			else if(elements[i] == null){
				elements[i] = obj;
				break;
			}
			else if(i > MaxSize){
				throw new FullStackException();
			}
		}
	}
	
	public T pop() throws EmptyStackException {
		int j=0;
		T poped;
		while(elements[j] != null){
			j += 1;
		}
		
		if(j == 0){
			throw new EmptyStackException();
		}
		else{
			poped = elements[j-1];
			elements[j-1] = null;
			return poped;
		}
	}
	
	public T top() throws EmptyStackException {
		int k = elements.length;
		T toped;
		
		if(k == 0){
			throw new EmptyStackException();
		}
		else{
			toped = elements[k];
			return toped;
		}
	}
	
	public boolean isEmpty() {
		int l=0;
		while(elements[l] != null){
			l += 1;
		}
		
		if(l == 0){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isFull() {
		int le = elements.length;
		
		if(le == MaxSize){
			return true;
		}
		else{
			return false;
		}
	}
}