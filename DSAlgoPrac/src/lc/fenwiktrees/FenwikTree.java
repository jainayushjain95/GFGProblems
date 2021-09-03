package lc.fenwiktrees;

public class FenwikTree {

	int[] fenwikTree;
	
	public static void main(String[] args) {
		int rating[] = {2,5,3,4,1};
		FenwikTree ft = new FenwikTree();
		ft.numTeams(rating);
	}

	public int numTeams(int[] rating) {
		int count = 0;
		fenwikTree = new int[6];
		for(int r : rating) {
			updateFenwikTree(1, r);
		}
		return count;
	}
	
	public void updateFenwikTree(int val, int index) {
		while(index < fenwikTree.length) {
			fenwikTree[index] += val;
			index = getNext(index);
		}
	}
	
	public void createFenwikTree(int[] nums) {
		fenwikTree = new int[nums.length + 1];
		for(int i = 0;i < nums.length; i++) {
			updateFenwikTree(nums[i], i + 1);
		}
	}
	
	public int getSum(int index) {
		int sum = 0;
		index++;
		while(index > 0) {
			sum += fenwikTree[index];
			index = getParent(index);
		}
		return sum;
	}
	
	public int getParent(int index) {
		return index - (index & -index);
	}
	
	public int getNext(int index) {
		return index + (index & -index);
	}
}
