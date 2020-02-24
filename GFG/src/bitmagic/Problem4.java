package bitmagic;

public class Problem4 {

	public static void main(String[] args) {
		System.out.println(ifPowerOf2(162));
	}
	
	public static boolean ifPowerOf2(int n) {
		if(n == 0)
			return false;
		return ((n & (n-1)) == 0);
	}
}
