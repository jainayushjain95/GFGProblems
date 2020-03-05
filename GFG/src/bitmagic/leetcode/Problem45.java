package bitmagic.leetcode;

public class Problem45 {

	public static void main(String[] args) {
		//		System.out.println(1 << 2);
		System.out.println(getNosComplement(5));
	}

	public static int getNosComplement(int n) {
		if(n == 0) {
			return 1;
		}
		return (getNoWithAllSetBits(n)) ^ n;
	}

	//and in that no, no of bits is same as in n
	//ex: n = 01101 --> output = 11111
	public static int getNoWithAllSetBits(int n) {
		int bitsCount = noOfBitsReqd(n);
		return (1 << bitsCount) - 1;
	}
	
	public static int noOfBitsReqd(int n) {
		return 1 + (int)Math.floor(Math.log(n)/Math.log(2));
	}

}
