package strings;

public class Problem99 {

	public static void main(String[] args) {
		System.out.println(getLeftmostIndexNonRepeatingIndex("abcadadddd"));
	}
	
	public static int getLeftmostIndexNonRepeatingIndex(String s) {
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
				array[x] = -2;
			}
		}
		for(int i = 0; i < 256; i++) {
			if(array[i] >= 0) {
				index = Math.min(index, array[i]);
			}
		}
		return index;
	}

}
