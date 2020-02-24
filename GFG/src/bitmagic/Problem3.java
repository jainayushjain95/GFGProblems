package bitmagic;

public class Problem3 {
	public static void main(String[] args) {
		int n = 1331231231;
		System.out.println(countOfSetBits(n));
		System.out.println(countSetBits2(n));
	}

	public static int countOfSetBits(int n) {
		int count = 0;
		while(n > 0) {
			count += n & 1;
			n = n >> 1;
		}
		return count;
	}
	
	//brian kernighan algorithm
	public static int countSetBits2(int n) {
		int count = 0;
		while(n > 0) {
			count++;
			n = turnOffLastSetBit(n);
		}
		return count;
	}

	public static int turnOffLastSetBit(int n) {
		return n & (n-1);
	}
}
