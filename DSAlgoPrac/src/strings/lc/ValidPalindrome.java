package strings.lc;

public class ValidPalindrome {

	public static void main(String[] args) {
		String s = "ebcbbececabbacecbbcbe";
		System.out.println(validPalindromeSolveIterative(s, 0, s.length() - 1));
	}

	public static boolean validPalindromeSolveRecursive(String s, int beginIndex, int endIndex, boolean atMostOneCharIgnored) {
		if(beginIndex >= endIndex) {
			return true;
		}
		if(s.charAt(beginIndex) == s.charAt(endIndex)) {
    		return validPalindromeSolveRecursive(s, beginIndex + 1, endIndex - 1, atMostOneCharIgnored);
    	} else if(!atMostOneCharIgnored) {
    		return validPalindromeSolveRecursive(s, beginIndex + 1, endIndex, !atMostOneCharIgnored) || validPalindromeSolveRecursive(s, beginIndex, endIndex - 1, !atMostOneCharIgnored);
    	}
		return false;
	}
	
	
	public static boolean validPalindromeSolveIterative(String s, int beginIndex, int endIndex) {
		if(beginIndex >= endIndex) {
            return true;
        }
        boolean isValid = true;
        while(beginIndex < endIndex && isValid) {
            if(s.charAt(beginIndex) == s.charAt(endIndex)) {
                beginIndex++;
                endIndex--;
            } else {
                isValid = validPalindromeSolveIterativeTest(s, beginIndex + 1, endIndex) || validPalindromeSolveIterativeTest(s, beginIndex, endIndex - 1);
                break;
            }
        }
        
        return isValid;
	}
	
	public static boolean validPalindromeSolveIterativeTest(String s, int beginIndex, int endIndex) {
		boolean isValid = true;
		while(beginIndex < endIndex && isValid) {
			if(s.charAt(beginIndex) == s.charAt(endIndex)) {
				beginIndex++;
				endIndex--;
			} else {
				isValid = false;
			}
		}
		
		return isValid;
	}
}
