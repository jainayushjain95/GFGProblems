package lc.google;

import java.util.*;

public class ExpressionAddOperators {

	List<String> possibiliies;
	char[] choices = {'+', '-', '*'};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<String> addOperators(String num, int target) {
		possibiliies = new ArrayList<String>();
		solve(num, target, 0, "");
		return possibiliies;
    }
	
	public void solve(String num, int target, int currentIndex, String curr) {
		if(currentIndex >= num.length() - 1) {
			int solution = solveExpression(curr);
			if(solution == target) {
				possibiliies.add(curr);
			}
			return;
		}
		
		for(int index = currentIndex; index < num.length() - 1; index++) {
			for(int i = 0;i < choices.length; i++) {
				String follow = curr + choices[i];
				solve(num, target, index, follow);
			}	
		}
	}
	
	public int solveExpression(String expression) {
		int solution = 0;
		return solution;
	}
	
}
