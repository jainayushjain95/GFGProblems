package bitmagic;

public class Problem11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static int swapBits(int n) {
		int x = getAllEvenBitsOfN(n);
		x = x >> 1;
		int y = getAllOddBitsOfN(n);
		y = y << 1;
		return x | y;
	}

	public static int getAllEvenBitsOfN(int n) {
		return n & 0xAAAAAAA;
	}
	
	public static int getAllOddBitsOfN(int n) {
		return n & 0x5555555;
	}
}
