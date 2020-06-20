package strings.geeksforgeeks;

public class AreTwoStringsAnagrams {

	public static void main(String[] args) {
		String a = "aaddssff";
		String b = "fdasfaas";
		System.out.println(areAnagrams(a, b));
	}

	public static boolean areAnagrams(String a, String b) {
		boolean output = true;
		int[] p = new int[256];
		if(a.length() == b.length()) {
			for(int i = 0;i < a.length(); i++) {
				p[a.charAt(i)]++;
				p[b.charAt(i)]--;
			}
			for(int i = 0;i < 256; i++) {
				if(p[i] != 0) {
					output = false;
					break;
				}
			}
		} else {
			output = false;
		}
		return output;
	}
}
