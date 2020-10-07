package strings;

import java.util.Arrays;

public class LongestSubstringWithDistinctCharacters {

	public static void main(String[] args) {
		String s = "abcadbd";
		System.out.println(solve(s));
	}
	
	public static int solve(String s) {
		int solution = 0, n = s.length(), i = 0;
		int[] visited = new int[255];
		
		Arrays.fill(visited, -1);
		
		for(int j = 0; j < n; j++) {
			i = Math.max(i, 1 + visited[s.charAt(j)]);
			int length = j - i + 1;
			
			solution = Math.max(solution, length);
			visited[s.charAt(j)] = j;
		}
		
		return solution;
	}

}
