package mathematics;

public class Problem1 {

	public static void main(String[] args) {
		System.out.println(3<<3);
	}
	
	public static int getNoOfDigits(long n) {
		if(n < 10) {
			return 1;
		}
		return 1 + getNoOfDigits(n/10);
	}
	
	public static int getNoOfDigitsByLog(long n) {
		return (int) Math.floor(1 + Math.log10(n));
	}
}
