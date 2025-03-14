package leetcode.weekly179;

import java.util.*;
class Pair {
	int freq;
	int newIndex;
	public Pair(int freq, int newIndex) {
		this.freq = freq;
		this.newIndex = newIndex;
	}
}

public class Problem72 {

	public static void main(String[] args) {
		int[] nums = {1,1,0,0,1};
		String[] words = {"a","e","i"};
		int[][] queries = {{0, 1}};
		//A - 17, Z = 42, a = 49, z = 74
		System.out.println(new Problem72().vowelStrings(words, queries));
	}

	public int[] vowelStrings(String[] words, int[][] queries) {
		int[] sums = new int[words.length];
		int[] output = new int[queries.length];
		for(int i = 0; i < words.length; i++) {
			String word = words[i];
			if(isVowel(word.charAt(0)) && isVowel(word.charAt(word.length() - 1))) {
				sums[i] = 1;
			}
		}
		for(int i = 1; i < sums.length; i++) {
			sums[i] += sums[i - 1];
		}

		for(int i = 0;i < queries.length; i++) {
			output[i] = sums[queries[i][1]] - (i > 0 ? sums[i - 1] : 0);
		}
		return output;
	}

	private boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

	public int minSwaps2(int[] nums) {
		int noOfOnes = noOfOnes(nums), left = 0;
		int minSwapsCount = Integer.MAX_VALUE, currNoOfOnes = 0;
		for(int rightI = 0; rightI < nums.length + noOfOnes - 1; rightI++) {
			int right = rightI % (nums.length);
			currNoOfOnes += nums[right];
			int noOfElements = right - left + 1;
			if(left > right) {
				noOfElements = nums.length - left + right + 1;
			}
			if(noOfElements == noOfOnes) {
				minSwapsCount = Math.min(minSwapsCount, noOfOnes - currNoOfOnes);
				currNoOfOnes -= nums[left++];
			}
		}
		return minSwapsCount;
	}

	public long maximumSubarraySum(int[] nums, int k) {
		int currSum = 0, maxSum = 0, left = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for(int right = 0; right < nums.length; right++) {
			currSum += nums[right];
			map.put(nums[right], 1 + map.getOrDefault(nums[right], 0));
			if(right - left + 1 == k) {
				if(map.size() == k) {
					maxSum = Math.max(maxSum, currSum);
				}
				int leftFreq = map.get(nums[left]);
				if(leftFreq == 1) {
					map.remove(nums[left]);
				} else {
					map.put(nums[left], leftFreq  - 1);
				}
				currSum -= nums[left++];
			}
		}
		return maxSum;
	}

	public long countSubarrays(int[] nums, int k) {
		List<Integer> output = new ArrayList<>();
		int max = maxValue(nums), left = 0, right = 0, count = 0;
		k--;
		long countOfSubarrays = 0;
		while(right < nums.length) {
			if(nums[right] == max) {
				count++;
			}
			while(count > k) {
				if(nums[left] == max) {
					count--;
				}
				left++;
			}
			countOfSubarrays += right - left + 1;
			right++;
		}
		long n = nums.length;
		long noOfTotalSubarrays = n * (n + 1) / 2;
		return noOfTotalSubarrays - countOfSubarrays;
	}

	private int maxValue(int[] nums) {
		int max = -1;
		for(int x : nums) {
			max = Math.max(max, x);
		}
		return max;
	}

	public int minSwaps(int[] data) {
		int noOfOnes = noOfOnes(data), output = Integer.MAX_VALUE, windowSum = 0;
		int left = 0, right = 0;

		while(right < data.length) {
			windowSum += data[right];
			if(right - left + 1 < noOfOnes) {
				right++;
			} else {
				output = Math.min(output, noOfOnes - windowSum);
				windowSum -= data[left++];
				right++;
			}

		}
		return output;
	}

	private int noOfOnes(int[] data) {
		int noOfOnes = 0;
		for(int x : data) {
			noOfOnes += x;
		}
		return noOfOnes;
	}

	private int[] getLeftRightCount(int[] data) {
		int noOfOnes = 0, left = 0, right = 0, maxNoOfOnes = 0, currNoOfOnes = 0, tempI = 0, tempJ = 0;
		for(int i = 0;i < data.length; i++) {
			if(data[i] == 1) {
				noOfOnes++;
				if(currNoOfOnes == 0) {
					tempI = i;
				}
				currNoOfOnes++;
				if(currNoOfOnes > maxNoOfOnes) {
					left = tempI;
					right = i;
					maxNoOfOnes = currNoOfOnes;
				}
			} else {
				currNoOfOnes = 0;
			}
		}
		int[] output = new int[3];
		output[0] = left;
		output[1] = right;
		output[2] = noOfOnes;
		return output;
	}

	public int maxFrequency(int[] nums, int k) {
		Arrays.sort(nums);
		int index = nums.length - 1, maxFreq = 0;
		while(index >= 0) {
			Pair pair = getPair(nums, k, index);
			index = pair.newIndex;
			maxFreq = Math.max(maxFreq, pair.freq);
		}
		return maxFreq;
	}

	private Pair getPair(int[] nums, int k, int index) {
		int freq = 0, target = nums[index], newIndex = index;
		while(index >= 0 && k >= 0) {
			if(nums[index] == target) {
				freq++;
				newIndex--;
			} else {
				int kNeeded = target - nums[index];
				k = k - kNeeded;
				if(k >= 0) {
					freq++;
				}
			}
			index--;
		}
		return new Pair(freq, newIndex);
	}

	public String minWindow(String s, String t) {
		int[] tFrequencyArray = prepareFrequencyArray(t);
		int[] sFrequencyArray = new int[256];
		int minLeftIndex = 0, minRightIndex = 0, left = 0, right = 0, minLength = Integer.MAX_VALUE;
		while(right < s.length()) {
			sFrequencyArray[s.charAt(right) - '0']++;
			while(left <= right && isSubstringValid(sFrequencyArray, tFrequencyArray)) {
				int currentLength = right - left + 1;
				if(currentLength < minLength) {
					minLength = currentLength;
					minRightIndex = right;
					minLeftIndex = left;
				}
				sFrequencyArray[s.charAt(left) - '0']--;
				left++;
			}
			right++;
		}
		return (minLength == Integer.MAX_VALUE) ? "" : s.substring(minLeftIndex, minRightIndex + 1);
	}

	private boolean isSubstringValid(int[] sFrequencyArray, int[] tFrequencyArray) {
		for(int i = 0;i < 256; i++) {
			if(sFrequencyArray[i] < tFrequencyArray[i]) {
				return false;
			}
		}
		return true;
	}
	private int[] prepareFrequencyArray(String str) {
		int[] frequencyArray = new int[256];
		for(int i = 0;i < str.length(); i++) {
			frequencyArray[str.charAt(i) - '0']++;
		}
		return frequencyArray;
	}

	public int subarraysWithKDistinct(int[] nums, int k) {
		return getCount(nums, k) - ((k > 1) ? getCount(nums, k - 1) : 0);
	}

	// No of subarrays with <= k different integers
	private int getCount(int[] nums, int k) {
		int count = 0, left = 0, right = 0;
		Map<Integer, Integer> map = new HashMap<>();
		while(right < nums.length) {
			map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
			while(map.size() > k) {
				int freq = map.get(nums[left]);
				if(freq > 1) {
					map.put(nums[left], freq - 1);
				} else {
					map.remove(nums[left]);
				}
				left++;
			}
			count += right - left + 1;
			right++;
		}
		return count;
	}

	public int numberOfSubarrays(int[] nums, int k) {
		int noOfOdds = 0, endIndex = nums.length - 1, output = 0, index = nums.length - 1;
		while(endIndex >= 0) {
			if(nums[endIndex] % 2 == 0) {
				endIndex--;
				continue;
			}
			if(nums[endIndex] % 2 != 0) {
				noOfOdds++;
				endIndex--;
			}
			while(endIndex > 0 && noOfOdds == k && nums[endIndex - 1] % 2 == 0) {
				endIndex--;
			}
			while(noOfOdds == k && index >= endIndex) {
				if(nums[index] % 2 != 0) {
					noOfOdds--;
				}
				output++;
				index--;
			}
//			endIndex--;
		}
		return output;
	}

	public static String generateTheStringSolution(int n) {
        String output = "";
        if(n % 2 != 0) {
        	for(int i = 0;i < n; i++) {
        		output += "a";
        	}
        } else {
        	for(int i = 0;i < n-1; i++) {
        		output += "a";
        	}
        	output += "b";
        }
        return output;
    }
	
}
