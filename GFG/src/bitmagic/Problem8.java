package bitmagic;

public class Problem8 {

	public static void main(String[] args) {
		System.out.println(findFirstSetBit(12));
	}

	public static int findFirstSetBit(int n) {
		int ans = 0;
		boolean flag = false;
		while(n > 0) {
			if((n & 1) == 0) {
				ans++;
			} else {
				ans++;
				flag = true;
				break;
			}
			n = n >> 1;
		}
		if(!flag) {
			ans = 0;
		}
		return ans;
	}

}
