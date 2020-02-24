package bitmagic;

public class Problem10 {

	public static void main(String[] args) {
		System.out.println(getLongestOnesSequenceLength((int)Math.pow(2, 20) - 1));

	}

	public static int getLongestOnesSequenceLength(int n) {
		int ans = 0, currentMax = 0;
		while(n > 0) {
			if(isLastBitSet(n)) {
				currentMax++;
				if(currentMax > ans) {
					ans = currentMax;	
				}
			} else {
				currentMax = 0;
			}
			n = n >> 1;
		}
		return ans;
	}
	
	public static boolean isLastBitSet(int n) {
		return (n & 1) != 0;
	}
	
}
