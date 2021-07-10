package lc26june;

import java.util.Stack;

public class Probs {
	public static void main(String[] args) {
		int[] nums  = {541,783,433,744};
		System.out.println(removeOccurrences("eemckxmckx", "emckx"));;
	}

	public static String removeOccurrences(String s, String part) {
		StringBuilder stringBuilder = new StringBuilder();
		int m = s.length(), n = part.length();
		
		if(s.equals(part)) {
			return "";
		} else if(m < n) {
			return s;
		} else {
			for(int i = 0;i < m; i++) {
				char c = s.charAt(i);
				stringBuilder.append(c);
				if(stringBuilder.length() >= n && i >= n - 1 && stringBuilder.substring(stringBuilder.length() - n).equals(part)) {
					stringBuilder.delete(stringBuilder.length() - n, stringBuilder.length());
				}
			}
		}
		return stringBuilder.toString();
    }
	
	public static boolean canBeIncreasing(int[] nums) {
		boolean flag = false;
		int count = 0, index = 0;

		if(nums.length == 1) {
			flag = true;
		} else {
			for(int i = 1;i < nums.length; i++) {
				if(nums[i] <= nums[i - 1]) {
					count++;
					index = i;
				}
				if(count > 1) {
					break;
				}
			}
			if(count <= 1) {
				if(count == 0) {
					flag = true;
				} else if(index == 1 || index == nums.length - 1) {
					flag = true;
				} else if(count == 1) {
					if(nums[index - 1] < nums[index + 1]) {
						flag = true;
					}
					if(nums[index - 2] < nums[index + 1] && nums[index - 2] < nums[index]) {
						flag = true;
					}
				}	
			}
			
		}


//		System.out.println(flag);
		return flag;
	}

}
