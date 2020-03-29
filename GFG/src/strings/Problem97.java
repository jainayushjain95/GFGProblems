package strings;

public class Problem97 {

	public static void main(String[] args) {
		String a = "asaaasada", b = "aaadsaasa";
		System.out.println(isAnagram(a, b));
	}

	public static boolean isAnagram(String a, String b) {
		boolean isAnagram = true;
		
		if(a.length() != b.length()) {
			isAnagram = false;
			return isAnagram;
		}
		
		int[] alpabetSpace = new int[256];
		for(int i = 0;i < a.length(); i++) {
			alpabetSpace[a.charAt(i)]++;
		}
		
		for(int i = 0;i < b.length(); i++) {
			alpabetSpace[b.charAt(i)]--;
		}
		
		
		for(int x : alpabetSpace) {
			if(x != 0) {
				isAnagram = false;
				break;
			}
		}
		
		return isAnagram;
	}
	
}
