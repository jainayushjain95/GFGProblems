package strings;

public class Problem101 {

	public static void main(String[] args) {
		String input1 = "abcd", input2 = "cadb";
		input1 += input1;
		System.out.println(input1.contains(input2));
	}
	

}
