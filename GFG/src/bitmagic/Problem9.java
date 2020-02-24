package bitmagic;

public class Problem9 {

	public static void main(String[] args) {
		int m = 52, n = 4;
		System.out.println(getRightMostDifferentBitPosition(m, n));
	}
	
	public static int getRightMostDifferentBitPosition(int m, int n) {
		int rightMostDifferentBitPosition = -1;
		if(m != n) {
			rightMostDifferentBitPosition = 0;
			int xor = m ^ n;
			rightMostDifferentBitPosition = getFirstSetBitFromRight(xor);
		}
		return rightMostDifferentBitPosition;
	}
	
	public static int getFirstSetBitFromRight(int n) {
		int ans = 1;
		while(n > 0) {
			if((n & 1) == 0) {
				ans++;
			} else {
				break;
			}
			n = n >> 1;
		}
		return ans;
	}

}
