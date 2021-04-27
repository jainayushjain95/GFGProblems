package strings.lc;

public class StringCompression {

	public static void main(String[] args) {
		char[] chars = {'a','a','a','a','a','a','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','c','c','c','c','c','c','c','c','c','c','c','c','c','c'};
		int len = compressSolve(chars);
		System.out.println(len + "\n");
		for(char c : chars) {
			System.out.println(c);
		}
	}
	
	public static int compressSolve(char[] chars) {
		int solution = 0;
		int index = 0;
		for(int i = 0;i < chars.length;) {
			int j = i;
			while(j < chars.length && chars[j] == chars[i]) {
				j++;
			}
			int length = j - i;
			if(length == 1) {
				if(index >= chars.length) {
					break;
				}
				chars[index] = chars[i];
				index++;
				solution++;
			} else if(length < 10) {
				chars[index] = chars[i];
				index++;
				chars[index] = getCharFromInt(length);
				solution = solution + 2;
				index++;
			} else {
				chars[index] = chars[i];
				index++;
				solution++;
				int indexLen = index + getNoOfDecimals(length) - 1;
				while(length > 0) {
					solution++;
					chars[indexLen--] = getCharFromInt(length % 10);
					length = length/10;
					index++;
				}
			}
			if(i == j - 1) {
				i++;
			} else {
				i = j;
			}
		}
		return solution;
    }
	
	public static int getNoOfDecimals(int n) {
		return (int)(Math.log10(n) + 1);
	}
	
	public static char getCharFromInt(int a) {
		return (char)(a + '0');
	}
}
