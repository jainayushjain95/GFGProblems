package bitmagic;

public class Problem5 {

	public static void main(String[] args) {
		int[] input = {1, 2, 3, 1, 2, 3, 4};
		System.out.println(getOddOccuring(input));
	}
	
	public static int getOddOccuring(int[] input) {
		int res = 0;
		for(int x : input) {
			res = res ^ x;
		}
		return res;
	}

}
