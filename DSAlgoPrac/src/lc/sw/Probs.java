package lc.sw;

public class Probs {

	public static void main(String[] args) {
		System.out.println(new Probs().minWindow("ADOBECODEBANC", "ABC"));
	}

	public String minWindow(String s, String t) {
        String ans = "";
        int m = s.length(), n = t.length();
        
        if(m >= n) {
        	int desiredMatchCount = t.length(), matchCount = 0;
        	int[] alphaT = getAlpha(t);
        	int[] alphaS = new int[256];
        	int i = -1, j = -1;
        	
        	while(true) {
        		boolean flag1 = false, flag2 = false;
        		while(i < m - 1 && matchCount < desiredMatchCount) {
        			flag1 = true;
        			i++;
        			alphaS[s.charAt(i)]++;
        			if(alphaS[s.charAt(i)] <= alphaT[s.charAt(i)]) {
        				matchCount++;
        			}
        		}
        		
        		while(j < i && matchCount == desiredMatchCount) {
        			flag2 = true;
        			j++;
        			String potentialAns = s.substring(j, i + 1);
        			if(ans.length() == 0 || ans.length() > potentialAns.length()) {
        				ans = potentialAns;
        			}
        			alphaS[s.charAt(j)]--;
        			if(alphaS[s.charAt(j)] < alphaT[s.charAt(j)]) {
        				matchCount--;
        			}
        		}
        		if(!flag1 && !flag2) {
        			break;
        		}
        	}
        }
        
        return ans;
    }
	
	public static int[] getAlpha(String s) {
		int[] alpha = new int[256];
		for(int i = 0;i < s.length(); i++) {
			alpha[s.charAt(i)]++;
		}
		return alpha;
	}
}
