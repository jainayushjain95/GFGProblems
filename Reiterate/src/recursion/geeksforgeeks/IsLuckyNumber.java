package recursion.geeksforgeeks;

public class IsLuckyNumber {

	public static int counter = 2;
	
	public static void main(String[] args) {
		System.out.println(isLuckyNumber(19));
	}
	
	public static boolean isLuckyNumber(int n) {
		if(n < counter) {
			return true;
		}
		if(n % counter == 0) {
			return false;
		}
		n = n - n / counter;
		counter++;
		return isLuckyNumber(n);
	}

}
