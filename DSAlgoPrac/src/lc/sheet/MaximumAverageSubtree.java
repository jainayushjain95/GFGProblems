package lc.sheet;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode() {}
	TreeNode(int val) { this.val = val; }
	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

class AverageCountPair {
	double average;
	int count;
	
	public AverageCountPair(double average, int count) {
		super();
		this.average = average;
		this.count = count;
	}
}

public class MaximumAverageSubtree {
	
	double maxAverage = 0;

	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(6);
		root.right = new TreeNode(1);
		System.out.println(new MaximumAverageSubtree().maximumAverageSubtree(root));
	}
	
	public double maximumAverageSubtree(TreeNode root) {
        maximumAverageSubtreeSolve(root);
        return maxAverage;
    }
	
	public AverageCountPair maximumAverageSubtreeSolve(TreeNode root) {
		if(root == null) {
			return new AverageCountPair(0, 0);
		}
		if(root.left == null && root.right == null) {
			return new AverageCountPair(root.val, 1);
		}
		
		AverageCountPair averageCountPairLeft = maximumAverageSubtreeSolve(root.left);
		AverageCountPair averageCountPairRight = maximumAverageSubtreeSolve(root.right);
		
		int count = averageCountPairLeft.count + averageCountPairRight.count + 1;
		double average = (root.val + (averageCountPairLeft.count * averageCountPairLeft.average)
				+ (averageCountPairRight.count * averageCountPairRight.average))/count;
		
		maxAverage = Math.max(average, maxAverage);
		maxAverage = Math.max(maxAverage, averageCountPairLeft.average);
		maxAverage = Math.max(maxAverage, averageCountPairRight.average);
		
        return new AverageCountPair(average, count);
    }
	

}
