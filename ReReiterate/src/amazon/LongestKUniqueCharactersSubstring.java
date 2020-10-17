package amazon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class LongestKUniqueCharactersSubstring {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(sc.nextLine());
		while(t != 0) {
			t--;
			sb.append(solve(sc.nextLine(), Integer.parseInt(sc.nextLine())) + "\n");
		}
		System.out.println(sb.toString());
	}

	public static int solve(String S, int K) {
		int solution = -1, N = S.length(), left = 0, right = 0;
		HashMap<Character, Integer> window = new HashMap<Character, Integer>();
		
		if(K > N) {
			solution = -1;
		} else {
			while(left < N && right < N) {
				if(window.size() < K) {
					add(window, S.charAt(right));
					right++;
				} else if(window.size() == K) {
					solution = Math.max(solution, right - left);
					int newWindowSize = newWindowSize(window, S.charAt(right));
					if(newWindowSize == K) {
						add(window, S.charAt(right));
						right++;
						solution = Math.max(solution, right - left);
					} else {
						remove(window, S.charAt(left));
						left++;
					}
				} else {
					remove(window, S.charAt(left));
					left++;
				}
			}	
		}

		return solution;
	}
	
	public static int newWindowSize(HashMap<Character, Integer> window, char c) {
		int newSize = 0;
		if(window.containsKey(c)) {
			newSize = window.size();
		} else {
			newSize = window.size() + 1;
		}
		return newSize;
	}
	
	public static void add(HashMap<Character, Integer> window, char c) {
		if(window.containsKey(c)) {
			window.put(c, window.get(c) + 1);
		} else {
			window.put(c, 1);
		}
	}
	
	public static void remove(HashMap<Character, Integer> window, char c) {
		if(window.containsKey(c) && window.get(c) > 1) {
			window.put(c, window.get(c) - 1);
		} else {
			window.remove(c);
		}
	}
}
