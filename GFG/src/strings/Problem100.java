package strings;

public class Problem100 {

	public static void main(String[] args) {
		String input = "qeeksforgeeks";
		String pattern = "egaek";
		System.out.println(isAnagramOfPatternPresent(input, pattern));
	}

	public static boolean isAnagramOfPatternPresent(String input, String pattern) {
		boolean isPresent = false;
		int[] inputCount = new int[256];
		int[] patternCount = new int[256];
		
		for(int i = 0;i < pattern.length(); i++) {
			inputCount[input.charAt(i)]++;
			patternCount[pattern.charAt(i)]++;
		}
		
		for(int i = pattern.length(); i < input.length(); i++) {
			if(areSameArrays(inputCount, patternCount)) {
				isPresent = true;
				break;
			}
			inputCount[input.charAt(i - pattern.length())]--;
			inputCount[input.charAt(i)]++;
		}
		
		return isPresent;
	}
	
	public static boolean areSameArrays(int[] a, int[] b) {
		boolean isSame = true;
		int i = 0;
		while(i < 256) {
			isSame = a[i] == b[i];
			if(!isSame) {
				break;
			}
			i++;
		}
		return isSame;
	}
	
}
 