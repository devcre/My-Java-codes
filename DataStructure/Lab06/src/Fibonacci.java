public class Fibonacci {
	public int fibonacci(int number) {
		if(number == 1 || number ==2){
			return 1;
		}
		else{
			return fibonacci(number-2) + fibonacci(number-1);
		}
	}
	
	public int fibo(int number){
		int s =0;
		int count = number;
		int a, b;
		if(number == 1 || number == 2){
			return 1;
		}
		else {
			count = count -2;
			a = 1;
			b = 1;
			while(count <= 0){
				s = a + b;
				a = b;
				b = s;
				count -= 1;
			}
			return s;
		}
	}
	
	public int fiboTail(int number, int a1, int a2){
		if(number == 2){
			return a2;
		}
		else{
			return fiboTail(number-1,a2, a1 + a2);
		}
	}
	
	public int fibonacciTail(int number) {
		return fiboTail(number,1,1);
	}
}
