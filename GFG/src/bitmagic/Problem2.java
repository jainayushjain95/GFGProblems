package bitmagic;

public class Problem2 {
	public static void main(String[] args) {
		int n = 5, k = 3;
		System.out.println(isKthBitSet(n, k));
	}
	public static boolean isKthBitSet(int n, int k) {
		if(n == 0) {
			return false;
		}
		
		return (n & 1<<(k-1)) != 0;
	}
}
