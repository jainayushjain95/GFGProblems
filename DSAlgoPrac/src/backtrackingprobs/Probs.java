package backtrackingprobs;
import java.util.*;

class Model {
	String a;
	int b;
	public Model(String a, int b) {
		super();
		this.a = a;
		this.b = b;
	}
	
	
}

public class Probs {

	public static char OPEN = '{';
	public static char CLOSE = '}';
	public static char COMMA = ',';


	public static void main(String[] args) {
		Model m1 = new Model("A", 12);
		Model m2 = m1;
		Model m3 = m1;
		
		System.out.println(m1.hashCode());
		System.out.println(m2.hashCode());
		System.out.println(m3.hashCode());
		
//		expand("{a,b}c");
	}

	public static String[] expand(String s) {
		List<String> output = new ArrayList<String>();
		expandSolve("", s, 0, output);
		Collections.sort(output);
		int index = 0;
		String[] results = new String[output.size()];

		for(String string : output) {
			results[index++] = string;
		}

		return results;
	}

	public static void expandSolve(String currWord, String s, int index, List<String> output)  {
		if(index >= s.length()) {
			output.add(currWord);
			return;
		}

		if(s.charAt(index) == CLOSE || s.charAt(index) == COMMA) {
			expandSolve(currWord, s, index + 1, output);
		}

		if(s.charAt(index) != OPEN) {
			currWord += s.charAt(index);
			expandSolve(currWord, s, index + 1, output);
			return;
		}

		int closeIndex = index + 1;
		while(closeIndex < s.length() && s.charAt(closeIndex) != CLOSE) {
			closeIndex++;
		}

		for(int i = index + 1; i < closeIndex; i++) {

			if(s.charAt(i) != COMMA) {
				currWord += s.charAt(i);
				expandSolve(currWord, s, closeIndex + 1, output);
				currWord = currWord.substring(0, currWord.length() - 1);
			}
		}
	}

}
