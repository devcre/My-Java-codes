package homework4;

/*
 * CSE2010 Homework #4: Stack.java
 * 
 * Define FullStackException exception here
 * 
 */
public class FullStackException extends RuntimeException {
	
	public FullStackException() {
		super();
	}
	
	public FullStackException(String e) {
		super(e);
	}
}