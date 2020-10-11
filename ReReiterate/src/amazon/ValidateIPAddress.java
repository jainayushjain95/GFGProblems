package amazon;

public class ValidateIPAddress {

	public static void main(String[] args) {
		String S = "0.0100.10.0";
		System.out.println(isValidIPSolve(S));
	}

	public static boolean isValidIPSolve(String S) {
		int n = S.length(), dotsCount = 0;
		int beginIndex = n - 1;
		
		boolean isValid = S.length() > 6 && S.charAt(0) != '.' && S.charAt(n - 1) != '.' && (S.charAt(0) != '0' || S.charAt(1) == '.');
		
		int prevDotsIndex = n;
		
		while(isValid && beginIndex >= 0) {
			int endIndex = beginIndex, dotIndex = -1, curr = 0, index = 0;
			while(endIndex >= 0) {
				char c = S.charAt(endIndex);
				if(c == '.') {
					dotIndex = endIndex;
					dotsCount++;
					break;
				}
				if(!isValidInteger(c)) {
					isValid = false;
					break;
				}
				curr += (c - '0') * Math.pow(10, index++);
				if(curr > 255) {
					isValid = false;
					break;
				}
				endIndex--;
			}
			if(dotsCount > 3) {
				isValid = false;
				break;
			}
			if(isValid && dotIndex != -1 && dotIndex < n - 1) {
				isValid = (S.charAt(dotIndex + 1) != '0') || (prevDotsIndex - dotIndex - 1 == 1);
			} 
			
			beginIndex = endIndex - 1;
			prevDotsIndex = dotIndex;
		}
		
		return isValid && dotsCount == 3;
	}
	
	public static boolean isValidInteger(char c) {
		int cc = (int) c;
		return cc >= 48 && cc <= 57;
	}

}
