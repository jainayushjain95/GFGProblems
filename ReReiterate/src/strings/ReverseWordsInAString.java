package strings;

public class ReverseWordsInAString {

	public static void main(String[] args) {
		String S = "Welcome a a s a s d f cas ca sa sd";
		char[] chars = new char[S.length()];
		
		for(int i = 0;i < chars.length; i++) {
			chars[i] = S.charAt(i);
		}
		
		reverseStringWords(chars);
		
		for(int i = 0;i < chars.length; i++) {
			if(chars[i] != ' ') {
				System.out.print(chars[i]);
			} else {
				System.out.print(" ");
			}
		}
	}

	public static void reverseStringWords(char[] chars) {
		for(int beginIndex = 0; beginIndex < chars.length;) {
			int endIndex = beginIndex;
			while(endIndex < chars.length - 1 && chars[endIndex + 1] != ' ') {
				endIndex++;
			}
			reverse(chars, beginIndex, endIndex);
			beginIndex = endIndex + 2;
		}
		reverse(chars, 0, chars.length - 1);
	}
	
	public static void reverse(char[] chars, int beginIndex, int endIndex) {
		while(beginIndex < endIndex) {
			swap(chars, beginIndex++, endIndex--);
		}
	}
	
	public static void swap(char[] chars, int beginIndex, int endIndex) {
		char c = chars[beginIndex];
		chars[beginIndex] = chars[endIndex];
		chars[endIndex] = c;
	}
	
}
