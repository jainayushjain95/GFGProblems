package bitmagic.leetcode;

public class Problem45 {

	public static void main(String[] args) {
		System.out.println(getNosComplement(1));
	}

	public static int getNosComplement(int n) {
		if(n == 0) {
			return 1;
		}
		int temp = n;
		int bitsCount = noOfBitsReqd(n);
		for(int i = 0;i < bitsCount; i++) {
			int lastBit = temp & 1;
			if(lastBit == 0) {
				n = n + (int)Math.pow(2, i);
			} else {
				n = n - (int)Math.pow(2, i);
			}
			temp = temp >> 1;
		}
		return n;
	}
	
	public static int noOfBitsReqd(int n) {
		return 1 + (int)Math.floor(Math.log(n)/Math.log(2));
	}
	
}
