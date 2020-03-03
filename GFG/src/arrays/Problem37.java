package arrays;

public class Problem37 {

	public static void main(String[] args) {
		int[] input = {1, 2, 3, 4, 5};
		int d = 3;
		leftRotateByDElements1(input, d);
		for(int x : input) {
			System.out.println(x);
		}
	}
	
	//naive approach
	public static void leftRotateByDElements1(int[] input, int d) {
		for(int i = 0;i < d; i++) {
			leftRotatebyOne(input);
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
