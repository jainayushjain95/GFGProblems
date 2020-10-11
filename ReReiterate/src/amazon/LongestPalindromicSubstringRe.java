package amazon;

public class LongestPalindromicSubstringRe {

	public static void main(String[] args) {
		String S = "aaaabbaa";
		System.out.println(longestPalidromicString(S));
	}
	
	public static String longestPalidromicString(String S) {
		String solution = S.charAt(0) + "";
		int N = S.length();
		boolean[][] dpArray = new boolean[N][N];
		int maxLength = 1;
		
		for(int i = 0;i < N; i++) {
			dpArray[i][i] = true;
			if(i < N - 1 && S.charAt(i) == S.charAt(i + 1)) {
				dpArray[i][i + 1] = true;	
				if(maxLength < 2) {
					maxLength = 2;
					solution = S.substring(i, i + 2);
				}
			}
		}
		
		int rowIndex = N - 3, columnCount = 0;
		
		while(rowIndex >= 0) {
			columnCount++;
			int columnIndex = N - 1;
			for(int i = 0;i < columnCount; i++) {
				dpArray[rowIndex][columnIndex] = dpArray[rowIndex + 1][columnIndex - 1] && (S.charAt(rowIndex) == S.charAt(columnIndex));
				int length = columnIndex - rowIndex + 1;
				if(dpArray[rowIndex][columnIndex] && maxLength < length) {
					maxLength = length;
					solution = S.substring(rowIndex, 1 + columnIndex);
				}
				columnIndex--;
			}
			rowIndex--;
		}
		
		return solution;
	}

}
