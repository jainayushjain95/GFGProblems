package recursion;

public class Problem17 {

	public static void main(String[] args) {
		System.out.println(sumOfDigits(9987));
	}
	
	public static int sumOfDigits(int n) {
		if(n < 10) {
			return n;  
		}
		return (n % 10) + sumOfDigits(n/10);
	}

}
