package bitmagic.leetcode;

public class Problem24 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getHD(13123123, 4312312));
	}
	
	public static int getHD(int a, int b) {
		int output = 0;
		while(a != 0 || b != 0) {
			output += (a & 1) ^ (b & 1);
			a = a >> 1;
			b = b >> 1;
		}
		return output;
	}

}
