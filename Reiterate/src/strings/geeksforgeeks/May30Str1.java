package strings.geeksforgeeks;

public class May30Str1 {

	public static void main(String[] args) {
		String input = "abcd";
		String pattern = "dd";
		System.out.println(solve(input, pattern));
	}

	public static boolean solve(String input, String pattern) {
		int n = input.length(), m = pattern.length();
		int noOfWindows = n - m + 1;

		int[] ip = new int[256];
		int[] pp = new int[256];
		
		
		for(int i = 0; i < m; i++) {
			ip[input.charAt(i)]++;
			pp[pattern.charAt(i)]++;	
		}
		
		if(areSame(ip, pp)) {
			return true;
		}
		
		for(int i = 1;i < noOfWindows; i++) {
			ip[input.charAt(i - 1)]--;
			ip[input.charAt(i + m - 1)]++;
			if(areSame(ip, pp)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean areSame(int[] p1, int[] p2) {
		boolean flag = true;
		for(int i = 0;i < 256; i++) {
			if(p1[i] != p2[i]) {
				flag = false;
				break;
			}
		}
		return flag;
	}

}
