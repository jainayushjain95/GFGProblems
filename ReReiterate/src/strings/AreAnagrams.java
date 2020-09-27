package strings;

public class AreAnagrams {

	public static void main(String[] args) {
		System.out.println(areAnagrams("listen", "silent"));

	}

	public static boolean areAnagrams(String a, String b) {
		boolean anagrams = true;
		
		if(a.length() != b.length()) {
			anagrams = false;
		} else {
			int[] alphas = new int[255]; 	
			for(int i = 0;i < a.length(); i++) {
				alphas[a.charAt(i)]++;
				alphas[b.charAt(i)]--;
			}
			for(int i = 0;i < 255; i++) {
				if(alphas[i] != 0) {
					anagrams = false;
					break;
				}
			}
		}
		
		
		return anagrams;
	}
}
