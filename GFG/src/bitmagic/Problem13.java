package bitmagic;

public class Problem13 {

	public static void main(String[] args) {
		System.out.println(isPalindrome(32237));
	}
	
	public static boolean isPalindrome(int n) {
		boolean isPalindrome = true;
		int noOfDigits = getNoOfDigits(n);
		int mask = getMask(noOfDigits);
		for(int i = 0;i < (noOfDigits/2); i++) {
			int mostSignificantDigit = n / mask;
			int leastSignificantDigit = n % 10;
			if(mostSignificantDigit != leastSignificantDigit) {
				isPalindrome = false;
				break;
			}
			n = chopOffMSD(n, mask);
			n = chopOffLSD(n);
			mask = mask / 100;
		}
		return isPalindrome;
	}
	
	public static int getNoOfDigits(int n) {
		return (int)Math.floor(Math.log10(n)) + 1;
	}

	public static int getMask(int k) {
		return (int)Math.pow(10, k - 1);
	}
	
	public static int chopOffMSD(int n, int mask) {
		return n % mask;
	}
	
	public static int chopOffLSD(int n) {
		return n / 10;
	}
	
}
