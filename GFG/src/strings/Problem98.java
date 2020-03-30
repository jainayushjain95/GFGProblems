package strings;

public class Problem98 {

	public static void main(String[] args) {
		System.out.println(getLeftmostIndex("abcdd"));
	}

	public static int getLeftmostIndex(String s) {
		int index = Integer.MAX_VALUE;
		int[] array = new int[256];
		for(int i = 0; i < 256; i++) {
			array[i] = -1;
		}
		for(int i = 0; i < s.length(); i++) {
			char x = s.charAt(i);
			if(array[x] == -1) {
				array[x] = i;
			} else {
				index = Math.min(index, array[x]);
			}
		}
		return index;
	}
	
}
