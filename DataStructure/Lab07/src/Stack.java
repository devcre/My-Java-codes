public class Stack {       
    private Node top; 
    private int size;
    private class Node {
        int data;
        Node next;
        Node(int data){
        	this.data = data;
        }
    }
    
    public Stack() {
        top = null;
    }

    public void push(int data){
    	Node nstack =new Node(data);
    	if(top == null){
    		top = nstack;
    		nstack.next = null;
    	}
    	else{
    		nstack.next = top;
    		top = nstack;
    	}
    	size += 1;
    }

    public Node pop(){
    	Node pStack;
    	Node ntop;
    	ntop = top.next;
    	pStack = top;
    	top = ntop;
    	size -= 1;
    	return pStack;
    }
   
    public Node top() {
    	return top;
    }
   
    public boolean isEmpty(){
    	if(top == null){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    public void printStack(){
    	int i;
    	Node nStack;
    	Node cStack;
    	
    	cStack = top;
    	for(i=0; i<size; i++){
    		System.out.print(cStack.data + "  ");
    		cStack = cStack.next;
    	}
    	System.out.println("");
    }


}
