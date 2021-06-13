package lc13june;

public class Probs {

	public static void main(String[] args) {
		String[] words = {"a", "aa", "bbb"};
		String s = "abcacb", p = "ab";
		int[] removable = {3, 1, 0};
		System.out.println(new Probs().maximumRemovals(s, p, removable));
	}
	
	public boolean makeEqual(String[] words) {
		boolean equal = true;
		int[] alpha = new int[26];
		
		for(String word : words) {
			for(int i = 0;i < word.length(); i++) {
				alpha[word.charAt(i) - 97]++;
			}
		}
		
		for(int i = 0;i < 26; i++) {
			int freq = alpha[i];
			if(freq % words.length != 0) {
				equal = false;
				break;
			}
		}
		
		return equal;
    }
	
	public int maximumRemovals(String s, String p, int[] removable) {
		int maxRemovals = 0;
		boolean[][] dpArray = getDPArray(s, p);
		StringBuilder stringBuilder = new StringBuilder(s);
		
		for(int i = 0;i < removable.length; i++) {
			stringBuilder.setCharAt(removable[i], '-');
			if(s.charAt(removable[i]) != p.charAt(p.length() - 1)) {
				maxRemovals++;
				s = stringBuilder.toString();
				continue;
			}
			
			
			s = stringBuilder.toString();
			for(int row = 1; row < p.length() + 1; row++) {
				for(int col = removable[i] + 1; col < s.length() + 1; col++) {
					if(p.charAt(row - 1) == s.charAt(col - 1)) {
						dpArray[row][col] = dpArray[row - 1][col - 1];
					} else {
						dpArray[row][col] = dpArray[row][col - 1];
					}
				}	
			}
			if(dpArray[p.length()][s.length()]) {
				maxRemovals++;
			} else {
				break;
			}
		}
		return maxRemovals;
    }

	public boolean[][] getDPArray(String s, String p) {
		boolean[][] dpArray = new boolean[p.length() + 1][s.length() + 1];
		
		for(int i = 0;i < s.length() + 1; i++) {
			dpArray[0][i] = true;
		}
		
		for(int i = 1; i < p.length() + 1; i++) {
			for(int j = 1; j < s.length() + 1; j++) {
				if(p.charAt(i - 1) == s.charAt(j - 1)) {
					dpArray[i][j] = dpArray[i - 1][j - 1];
				} else {
					dpArray[i][j] = dpArray[i][j - 1];
				}
			}
		}
		
		return dpArray;
	}
	
}
