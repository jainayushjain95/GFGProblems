package recursion;

public class Problem28 {

	public static void main(String[] args) {
		int n = 134591;
		System.out.println(findDigitalRoot(n));
	}

	public static int findDigitalRoot(int n) {
		int sum = sumOfDigits(n);
		while(sum > 9) {
			sum = sumOfDigits(sum);
		}
		return sum;
	}
	
	public static int sumOfDigits(int n) {
		if(n < 10) {
			return n;
		}
		return (n % 10) + sumOfDigits(n/10);
	}
}
