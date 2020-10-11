package amazon;

public class ReverseWordsInGivenString {

	public static void main(String[] args) {
		String S = "i.like.this.program.very.much";
		solve(S);
	}

	public static void solve(String S) {
		char[] chars = S.toCharArray();
		int n = S.length();
		for(int i = 0;i < n;) {
			int j = i;
			while(j < n && chars[j] != '.') {
				j++;
			}
			if(i != j) {
				reverse(chars, i, j - 1);	
			}
			i = j + 1;
		}
		
		reverse(chars, 0, n - 1);
		
		for(int i = 0;i < n;i++) {
			System.out.print(chars[i]);
		}
	}
	
	
	
	public static void reverse(char[] chars, int i, int j) {
		while(i < j) {
			char c = chars[i];
			chars[i] = chars[j];
			chars[j] = c;
			i++;
			j--;
		}
	}
	
	
}
