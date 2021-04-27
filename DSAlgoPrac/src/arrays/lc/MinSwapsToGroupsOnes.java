package arrays.lc;

public class MinSwapsToGroupsOnes {

	public static void main(String[] args) {
		int[] data = {1, 0, 0, 1, 1, 1};
		System.out.println(minSwapsSolve(data));
	}

	public static int minSwapsSolve(int[] data) {
		int count = 0;
		int countOfOnes = getTotalNoOfOnes(data, 0, data.length - 1);
		int maxOnes = getMaxOnesInWindowOfSize(data, countOfOnes);
		count = countOfOnes - maxOnes;
		return count;
	}
	
	public static int getMaxOnesInWindowOfSize(int[] data, int countOfOnes) {
		int count = getTotalNoOfOnes(data, 0, countOfOnes - 1), n = data.length;
		int maxCount = count;
		for(int beginIndex = 1; beginIndex <= n - countOfOnes && count < countOfOnes; beginIndex++) {
			if(data[beginIndex - 1] == 1) {
				count--;
			}
			
			if(data[beginIndex + countOfOnes - 1] == 1) {
				count++;
			}
			maxCount = Math.max(count, maxCount);
		}
		return maxCount;
	}
	
	public static int getTotalNoOfOnes(int[] data, int beginIndex, int endIndex) {
		int count = 0;
		for(int i = beginIndex; i <= endIndex; i++) {
			if(data[i] == 1) {
				count++;
			}
		}
		return count;
	}
}
