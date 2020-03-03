package arrays;

public class Problem36 {

	public static void main(String[] args) {
		int[] input = {1, 2, 3, 4, 5};
		leftRotatebyOne(input);
		for(int x : input) {
			System.out.println(x);
		}
	}
	
	public static void leftRotatebyOne(int input[]) {
		int firstElement = input[0];
		for(int beginIndex = 1; beginIndex < input.length; beginIndex++) {
			input[beginIndex - 1] = input[beginIndex]; 
		}
		input[input.length - 1] = firstElement;
	}

}
