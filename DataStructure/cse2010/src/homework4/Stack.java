package homework4;

/*
 * CSE2010 Homework #4: Stack.java
 * 
 * DO NOT MODIFY THIS FILE!
 * 
 */
public interface Stack<T> {
	
	public void push(T item);
	public T pop() throws EmptyStackException;
	public boolean isEmpty();
	public T top() throws EmptyStackException;
	
}