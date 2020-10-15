package amazon;

public class ReverseWords {

	public static void main(String[] args) {
		System.out.println(reverse("i.like.this.program.very.much"));;
	}
	
	public static String reverse(String S) {
		int N = S.length();
		char[] chars = new char[N];
		for(int i = 0;i < N; i++) {
			chars[i] = S.charAt(i);
		}
		
		int beginIndex = 0, endIndex = 0;
		while(beginIndex < N && endIndex < N) {
			if(S.charAt(endIndex) == '.' || endIndex == N - 1) {
				int index = (endIndex == N - 1) ? N - 1 : endIndex - 1;
				reverse(chars, beginIndex, index);
				beginIndex = endIndex + 1;
				endIndex = beginIndex;
			} else {
				endIndex++;
			}
		}
		
		reverse(chars, 0, N - 1);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < chars.length; i++) {
			sb.append(chars[i]);
		}
		
		return sb.toString();
	}
	
	public static void reverse(char[] chars, int beginIndex, int endIndex) {
		while(beginIndex < endIndex) {
			char c = chars[beginIndex];
			chars[beginIndex] = chars[endIndex];
			chars[endIndex] = c;
			beginIndex++;
			endIndex--;
		}
	}

}
