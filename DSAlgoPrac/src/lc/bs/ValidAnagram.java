package lc.bs;

public class ValidAnagram {

	public static void main(String[] args) {
		firstBadVersion(5);	
	}
	
	public static int firstBadVersion(int n) {
		ValidAnagram solve = new ValidAnagram();
        return solve.firstBadVersionSolve(1, n);
    }
    
	
    public int firstBadVersionSolve(int beginIndex, int endIndex) {
        if(beginIndex > endIndex) {
            return -1;
        }
        int midIndex = getMidIndex(beginIndex, endIndex);
        if(!isBadVersion(midIndex)) {
            return firstBadVersionSolve(midIndex + 1, endIndex);
        } else if(midIndex == 0 || !isBadVersion(midIndex - 1)) {
            return midIndex;
        } else {
            return firstBadVersionSolve(beginIndex, midIndex - 1);
        }
    }
    
    public static boolean isBadVersion(int n) {
		return n >= 4;
	}
    
    public static int getMidIndex(int beginIndex, int endIndex) {
        return (endIndex - beginIndex)/2 + beginIndex;
    }

}
