package bs.lc;

public class SearchInRotatedSortedArray {

	public static void main(String[] args) {
		int[] nums = {3, 1};
		System.out.println(search(nums, 0, nums.length - 1, 1));
	}

	public static int search(int[] nums, int beginIndex, int endIndex, int target) {
		if(beginIndex <= endIndex) {
			int midIndex = getMidIndex(beginIndex, endIndex);
			if(nums[midIndex] == target) {
				return midIndex;
			} else if(nums[beginIndex] <= nums[midIndex]) {
				if(target >= nums[beginIndex] && target < nums[midIndex]) {
					return search(nums, target, beginIndex, midIndex - 1);
				} else {
					return search(nums, target, midIndex + 1, endIndex);
				}
			} else {
				if(target > nums[midIndex] && target <= nums[endIndex]) {
					return search(nums, target, midIndex + 1, endIndex);
				} else {
					return search(nums, target, beginIndex, midIndex - 1);
				}
			}
		}
		return -1;
	}

	public static int searchSolve(int arr[], int l, int h, int key)
	{
		if (l > h) return -1;

		int mid = (l + h) / 2;
		if (arr[mid] == key)
			return mid;

		if (arr[l] <= arr[mid]) {
			if (key >= arr[l] && key <= arr[mid])
				return search(arr, l, mid - 1, key);
			return search(arr, mid + 1, h, key);
		}

		if (key >= arr[mid] && key <= arr[h])
			return search(arr, mid + 1, h, key);

		return search(arr, l, mid - 1, key);
	}

	public static int getMidIndex(int beginIndex, int endIndex) {
		return (endIndex - beginIndex)/2 + beginIndex;
	}
}
