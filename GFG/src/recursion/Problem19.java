package recursion;

public class Problem19 {

	public static void main(String[] args) {
		printAllSubsetsOfAString("ABC", "", 0);

	}

	public static void printAllSubsetsOfAString(String input, String temp, int index) {
		if(index == input.length()) {
			System.out.println(temp);
			return;
		}
		printAllSubsetsOfAString(input, temp, index + 1);
		printAllSubsetsOfAString(input, temp + input.charAt(index), index + 1);
	}
	
}
