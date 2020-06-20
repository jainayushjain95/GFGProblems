package strings.geeksforgeeks;

public class JuneStr1 {

	public static void main(String[] args) {
		String input = "abba";
		printMaxLength(input);
	}

	public static void printMaxLength(String input) {
		int[] array = getMaxSubEndArray(input);
		//get max value in array
	}
	
	/*
	 * array[k] -> length of longest substring that has distinct characters and ends at k
	 */
	public static int[] getMaxSubEndArray(String input) {
		int n = input.length();
		int[] array = new int[n];
		int[] alpha = new int[256];
		for(int i = 0;i < 256; i++) {
			alpha[i] = -1;
		}
		
		array[0] = 1;
		alpha[input.charAt(0)] = 0;
		
		for(int i = 1;i < n; i++) {
			char c = input.charAt(i);
			if(alpha[c] == -1) {
				array[i] = array[i - 1] + 1;
			} else {
				array[i] = Math.min(i - alpha[c], array[i - 1] + 1); 
			}
			alpha[c] = i;
		}
		
		return array;
	}
}
