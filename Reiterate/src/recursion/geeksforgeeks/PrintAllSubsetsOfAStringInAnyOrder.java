package recursion.geeksforgeeks;

public class PrintAllSubsetsOfAStringInAnyOrder {

	public static void main(String[] args) {
		printSubsets("ABCDEF", "", 0);
	}
	
	public static void printSubsets(String input, String current, int levelIndex) {
		if(input.length() == levelIndex) {
			System.out.println(current);
			return;
		}
		printSubsets(input, current, 1 + levelIndex);
		printSubsets(input, current + input.charAt(levelIndex), 1 + levelIndex);
	}
	
}
