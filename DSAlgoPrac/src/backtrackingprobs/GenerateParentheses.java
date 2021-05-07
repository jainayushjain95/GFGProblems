package backtrackingprobs;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
	
	public static void main(String[] args) {
		(new GenerateParentheses()).generateParenthesis(2);
	}
	
	public List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<String>();
		generateParenthesisSolve("", list, n, n);
		for(String s : list) {
			System.out.println(s);
		}
		return list;
    }
	
	public void generateParenthesisSolve(String currString, List<String> list, int openBracketsAvailable, int closeBracketsAvailable) {
		if(openBracketsAvailable == 0 && closeBracketsAvailable == 0) {
			list.add(currString);
			return;
		}
		if(openBracketsAvailable > 0) {
			generateParenthesisSolve(currString + "(", list, openBracketsAvailable - 1, closeBracketsAvailable);
		}
		
		if(openBracketsAvailable < closeBracketsAvailable) {
			generateParenthesisSolve(currString + ")", list, openBracketsAvailable, closeBracketsAvailable - 1);
		}
    }
}
