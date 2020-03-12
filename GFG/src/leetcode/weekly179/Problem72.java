package leetcode.weekly179;

public class Problem72 {

	public static void main(String[] args) {
		System.out.println(generateTheStringSolution(24));

	}

	public static String generateTheStringSolution(int n) {
        String output = "";
        if(n % 2 != 0) {
        	for(int i = 0;i < n; i++) {
        		output += "a";
        	} ../i 
        } else {
        	for(int i = 0;i < n-1; i++) {
        		output += "a";
        	}
        	output += "b";
        }
        return output;
    }
	
}
