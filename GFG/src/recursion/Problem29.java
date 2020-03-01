package recursion;

public class Problem29 {

	public static int counter = 2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 19;
		System.out.println(isLuckyNumber(n));
	
	}

	public static int isLuckyNumber(int n) {
		if(n < counter) 
			return 1;
		if(n % counter == 0) 
			return 0;
		n = n - n / counter;
		counter++;
		return isLuckyNumber(n);
	}
	
}
