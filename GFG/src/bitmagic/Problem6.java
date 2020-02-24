package bitmagic;

public class Problem6 {
	
	public static void main(String[] args) {
		int[] input = {3, 4, 3, 4, 8, 4, 4, 32, 7, 7};
		printTwoOddOccuring(input);
	}
	
	public static void printTwoOddOccuring(int[] input) {
		int xor = getXOROfAll(input);
		int noWithOneBitSet = getNoWithOneBitSet(xor);
		int res1 = 0, res2 = 0;
		for(int x : input) {
			if((x & noWithOneBitSet) != 0) {
				res1 = res1 ^ x;
			} else {
				res2 = res2 ^ x;
			}
		}
		System.out.println(res1 + ", " + res2);
	}
	
	public static int getXOROfAll(int[] input) {
		int res = 0;
		for(int x : input) {
			res = res ^ x;
		}
		return res;
	}
	
	//And that set bit is the last set bit of n
	public static int getNoWithOneBitSet(int n) {
		return n & ~(n - 1);
	}
}
