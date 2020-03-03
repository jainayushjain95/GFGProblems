package arrays;

public class Problem35 {
	public static void main(String[] args) {
		int[] input = {10, 10};
		int size = removeDuplicates(input);
		for(int i = 0; i < size; i++) {
			System.out.println(input[i]);
		}
	}
	
	public static int removeDuplicates(int[] input) {
		int beginIndex, endIndex = 1;
		for(beginIndex = 1; beginIndex < input.length; beginIndex++) {
			if(input[beginIndex] != input[endIndex - 1]) {
				input[endIndex] = input[beginIndex];
				endIndex++;
			}
		}
		return endIndex;
	}
	
}
