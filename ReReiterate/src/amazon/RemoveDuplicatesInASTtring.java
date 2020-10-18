package amazon;

public class RemoveDuplicatesInASTtring {

	public static void main(String[] args) {
		String S = "HappyNewYear";
		System.out.println(solve(S));
	}
	
	public static String solve(String S) {
		String output = "";
		int N = S.length();
		if(N <= 1) {
			output = S;
		} else {
			StringBuilder sb = new StringBuilder();
			int[] alpha = new int[255];
			for(int i = 0;i < N; i++) {
				if(alpha[S.charAt(i)] == 0) {
					sb.append(S.charAt(i));
					alpha[S.charAt(i)]++;
				}
			}
			output = sb.toString();
		}
		return output;
	}

}
