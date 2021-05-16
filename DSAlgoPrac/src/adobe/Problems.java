package adobe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


class Triplet {
	int i;
	int j;
	int k;
	public Triplet(int i, int j, int k) {
		super();
		this.i = i;
		this.j = j;
		this.k = k;
	}

}

public class Problems {

	public static void main(String[] args) {
		int[][] matrix = {
				{1, 2, 3}, {4, 5,6,},{7, 8, 9}
		};
		(new Problems()).spiralOrder(matrix);
	}
	/*
	 * Arrays and Strings
	 */
	

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> spiral = new ArrayList<Integer>();
		
		int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
		int totalElements = matrix.length * matrix[0].length;
		
		while(totalElements != 0) {
			for(int i = left; i <= right; i++) {
				spiral.add(matrix[top][i]);
				totalElements--;
			}
			top++;
			
			for(int i = top; totalElements > 0 && i <= bottom; i++) {
				spiral.add(matrix[i][right]);
				totalElements--;
			}
			right--;
			
			for(int i = right; totalElements > 0 && i >= left; i--) {
				spiral.add(matrix[bottom][i]);
				totalElements--;
			}
			bottom--;
			
			for(int i = bottom; totalElements > 0 && i >= top; i--) {
				spiral.add(matrix[i][left]);
				totalElements--;
			}
			left++;
		}
		
		for(int x : spiral) {
			System.out.println(x);
		}
		return spiral;
    }
	
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> list = new ArrayList<Integer>();
		
		int wordLength = words[0].length();
		int totalLength = wordLength * words.length;
		Map<String, Integer> freqMap = getFreqMap(words);
		
		for(int beginIndex = 0;beginIndex <= s.length() - totalLength; beginIndex++) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			int index = 0;
			for(index = 0;index < totalLength; index = index + wordLength) {
				String word = s.substring(beginIndex + index, beginIndex + index + wordLength);
				if(!freqMap.containsKey(word)) {
					break;
				} else {
					map.put(word, 1 + map.getOrDefault(word, 0));
					if(map.get(word) > freqMap.get(word)) {
						break;
					}
				}
			}
			if(index == totalLength) {
				list.add(beginIndex);
			}
		}
		
		for(int x : list) {
			System.out.println(x);
		}
		return list;
    }

	public static Map<String, Integer> getFreqMap(String[] words) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(String word : words) {
			map.put(word, 1 + map.getOrDefault(word, 0));
		}
		return map;
	}

	public int[] twoSum(int[] numbers, int target) {
		int beginIndex = 0, endIndex = numbers.length - 1;
		int[] output = new int[2];

		while(beginIndex < endIndex) {
			if(numbers[beginIndex] + numbers[endIndex] == target) {
				output[0] = beginIndex + 1;
				output[1] = endIndex + 1;
				break;
			} else if((numbers[beginIndex] + numbers[endIndex]) > target) {
				endIndex--;
			} else {
				beginIndex++;
			}
		}

		return output;
	}

	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.parallelSort(nums);
		List<List<Integer>> sol = new ArrayList<List<Integer>>();
		for(int i = 0;i < nums.length; i++) {
			if(i > 0 && nums[i] == nums[i - 1]) {
				continue;
			} 
			int sum = -nums[i];
			int beginIndex = i + 1;
			int endIndex = nums.length - 1;
			while(beginIndex < nums.length - 1 && beginIndex < endIndex) {

				if(nums[beginIndex] + nums[endIndex] == sum) {
					List<Integer> list = new ArrayList<Integer>();
					list.add(nums[i]);
					list.add(nums[beginIndex++]);
					list.add(nums[endIndex--]);
					sol.add(list);
					while(beginIndex < endIndex && nums[beginIndex] == nums[beginIndex - 1]) {
						beginIndex++;
					}
				} else if((nums[beginIndex] + nums[endIndex]) > sum) {
					endIndex--;
				} else {
					beginIndex++;
				}
			}
		}
		return sol;
	}

	public List<List<Integer>> threeSumWithoutSort(int[] nums) {
		List<List<Integer>> sol = new ArrayList<List<Integer>>();

		Set<Integer> duplicates = new HashSet<Integer>();
		Map<Integer, Integer> map = new HashMap<>();

		for(int i = 0;i < nums.length; i++) {
			if(duplicates.add(nums[i])) {
				int sum = -nums[i];
				for(int j = i + 1; j < nums.length; j++) {
					if(map.containsKey(sum - nums[j]) && map.get(sum - nums[j]) == i) {
						List<Integer> list = new ArrayList<Integer>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(sum - nums[j]);
						sol.add(list);
					}
					map.put(nums[j], i);
				}
			}

		}
		return sol;
	}


	public String longestCommonPrefix(String[] strs) {
		String lcp = "";
		if(strs.length == 1) {
			return strs[0];
		}
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 1; i < strs.length; i++) {
			if(i == 1) {
				int beginIndex = 0, endIndex = 0;
				while(beginIndex < strs[0].length() && endIndex < strs[1].length() && strs[0].charAt(beginIndex) == strs[1].charAt(endIndex)) {
					stringBuilder.append(strs[0].charAt(beginIndex));
					beginIndex++;
					endIndex++;
				}
				if(stringBuilder.length() == 0) {
					lcp = "";
					break;
				}
			} else {
				int beginIndex = 0, endIndex = 0;
				String prefix = stringBuilder.toString();
				stringBuilder = new StringBuilder();
				while(beginIndex < prefix.length() && endIndex < strs[i].length() && prefix.charAt(beginIndex) == strs[i].charAt(endIndex)) {
					stringBuilder.append(strs[0].charAt(beginIndex));
					beginIndex++;
					endIndex++;
				}
				if(stringBuilder.length() == 0) {
					lcp = "";
					break;
				}
			}
			lcp = stringBuilder.toString();
		}
		return lcp;
	}

	public int lengthOfLongestSubstring(String s) {
		int maxLength = 0;
		int beginIndex = 0, endIndex = 0;
		int[] alpha = new int[256];

		for(int i = 0; i < 256; i++) {
			alpha[i] = -1;
		}

		while(beginIndex < s.length() && endIndex < s.length()) {
			if(alpha[s.charAt(endIndex)] == -1) {
				alpha[s.charAt(endIndex)] = endIndex;
			} else {
				beginIndex = Math.max(1 + alpha[s.charAt(endIndex)], beginIndex); 
				alpha[s.charAt(endIndex)] = endIndex;
			}
			maxLength = Math.max(maxLength, endIndex - beginIndex + 1);
			endIndex++;
		}
		System.out.println(maxLength);
		return maxLength;
	}

	public int maxArea(int[] height) {
		int beginIndex = 0, endIndex = height.length - 1;
		int maxArea = 0;
		while(beginIndex < endIndex) {
			int currArea = (endIndex - beginIndex) * Math.min(height[beginIndex], height[endIndex]);
			if(maxArea < currArea) {
				maxArea = currArea;
			}
			if(height[beginIndex] < height[endIndex]) {
				beginIndex++;
			} else if(height[beginIndex] > height[endIndex]) {
				endIndex--;
			} else {
				beginIndex++;
				endIndex--;
			}
		}
		System.out.println(maxArea);
		return maxArea;
	}

	public String intToRoman(int num) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "I");
		map.put(5, "V");
		map.put(10, "X");
		map.put(50, "L");
		map.put(100, "C");
		map.put(500, "D");
		map.put(1000, "M");

		map.put(4, "IV");
		map.put(9, "IX");
		map.put(40, "XL");
		map.put(90, "XC");
		map.put(400, "CD");
		map.put(900, "CM");

		StringBuilder stringBuilder = new StringBuilder();

		if(num >= 1000) {
			int count = num / 1000;
			while(count > 0) {
				stringBuilder.append(map.get(1000));
				count--;
			}
			num = num % 1000;
		}

		if(num >= 900) {
			stringBuilder.append(map.get(900));
			num = num % 900;
		}

		if(num >= 500) {
			stringBuilder.append(map.get(500));
			num = num % 500;
		}

		if(num >= 400) {
			stringBuilder.append(map.get(400));
			num = num % 400;
		}

		if(num >= 100) {
			int count = num / 100;
			while(count > 0) {
				stringBuilder.append(map.get(100));
				count--;
			}
			num = num % 100;
		}

		if(num >= 90) {
			stringBuilder.append(map.get(90));	
			num = num % 90;
		}

		if(num >= 50) {
			stringBuilder.append(map.get(50));
			num = num % 50;
		}

		if(num >= 40) {
			stringBuilder.append(map.get(40));
			num = num % 40;
		}

		if(num >= 10) {
			int count = num / 10;
			while(count > 0) {
				stringBuilder.append(map.get(10));
				count--;
			}
			num = num % 10;
		}

		if(num == 9) {
			stringBuilder.append(map.get(9));
			num = num % 9;
		}

		if(num >= 5) {
			stringBuilder.append(map.get(5));
			num = num % 5;
		}

		while(num >= 1) {
			if(num == 4) {
				stringBuilder.append(map.get(num));
				num = 0;
			} else {
				stringBuilder.append(map.get(1));
			}
			num--;
		}
		System.out.println(stringBuilder.toString());
		return stringBuilder.toString();
	}

	public int romanToInt(String s) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		map.put("I", 1);
		map.put("IV", 4);
		map.put("IX", 9);

		map.put("X", 10);
		map.put("XL", 40);
		map.put("XC", 90);

		map.put("C", 100);
		map.put("CD", 400);
		map.put("CM", 900);

		map.put("V", 5);
		map.put("L", 50);
		map.put("D", 500);
		map.put("M", 1000);


		int ans = 0, beginIndex = 0;

		while(beginIndex < s.length()) {
			if(s.charAt(beginIndex) == 'M') {
				ans = ans + 1000;
				beginIndex++;
			} else if(s.charAt(beginIndex) == 'D') {
				ans = ans + 500;
				beginIndex++;
			} else if(s.charAt(beginIndex) == 'L') {
				ans = ans + 50;
				beginIndex++;
			} else if(s.charAt(beginIndex) == 'V') {
				ans = ans + 5;
				beginIndex++;
			} else if(s.charAt(beginIndex) == 'C') {
				beginIndex++;
				if(beginIndex < s.length() && s.charAt(beginIndex) == 'D') {
					ans = ans + 400;
					beginIndex++;
				} else if(beginIndex < s.length() && s.charAt(beginIndex) == 'M') {
					ans = ans + 900;
					beginIndex++;
				} else {
					ans = ans + 100;
				}
			} else if(s.charAt(beginIndex) == 'X') {
				beginIndex++;
				if(beginIndex < s.length() && s.charAt(beginIndex) == 'L') {
					ans = ans + 40;
					beginIndex++;
				} else if(beginIndex < s.length() && s.charAt(beginIndex) == 'C') {
					ans = ans + 90;
					beginIndex++;
				} else {
					ans = ans + 10;
				}
			} else if(s.charAt(beginIndex) == 'I') {
				beginIndex++;
				if(beginIndex < s.length() && s.charAt(beginIndex) == 'V') {
					ans = ans + 4;
					beginIndex++;
				} else if(beginIndex < s.length() && s.charAt(beginIndex) == 'X') {
					ans = ans + 9;
					beginIndex++;
				} else {
					ans = ans + 1;
				}
			}
		}

		System.out.println(ans);
		return ans;
	}


	public int threeSumClosestWithSort(int[] nums, int target) {
		int ans = 1000000;
		Arrays.parallelSort(nums);

		for(int i = 0;i < nums.length; i++) {
			if(i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int beginIndex = i + 1, endIndex = nums.length - 1;

			while(beginIndex < endIndex) {
				int currSum = nums[i] + nums[beginIndex] + nums[endIndex];
				int currDiff = Math.abs(currSum - target);

				if(currDiff < Math.abs(target - ans)) {
					ans = currSum;
				}
				if(currSum > target) {
					endIndex--;
				} else if(currSum < target) {
					beginIndex++;
				} else {
					ans = target;
					beginIndex = nums.length + 1;
				}
			}
		}
		return ans;
	}


	public List<List<Integer>> fourSumWithSort(int[] nums, int target) {
		Arrays.parallelSort(nums);
		List<List<Integer>> data = new ArrayList<List<Integer>>();
		for(int i = 0;i < nums.length; i++) {
			if(i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int newI = i + 1;

			while(newI < nums.length) {
				if(newI > (i + 1) && nums[newI] == nums[newI - 1]) {
					newI++;
					continue;
				}
				int beginIndex = newI + 1, endIndex = nums.length - 1;

				while(beginIndex < endIndex) {
					int currSum = nums[i] + nums[newI] + nums[beginIndex] + nums[endIndex];

					if(currSum > target) {
						endIndex--;
					} else if(currSum < target) {
						beginIndex++;
					} else {
						List<Integer> list = new ArrayList<Integer>();
						list.add(nums[i]);
						list.add(nums[newI]);
						list.add(nums[beginIndex]);
						list.add(nums[endIndex]);
						data.add(list);
						beginIndex++;
						endIndex--;
						while(beginIndex < endIndex && nums[beginIndex] == nums[beginIndex - 1]) {
							beginIndex++;
						}
					}
				}	
				newI++;
			}
		}
		return data;
	}
}
