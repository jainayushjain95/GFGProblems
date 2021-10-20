package lc.google;

class PairLongestZigZagPath {
	int leftMax = -1;
	int rightMax = -1;
	int max = -1;
	
}

public class LongestZigZagPath {

	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		
		root.right = new TreeNode(2);
	
		root.right.left = new TreeNode(3);
		root.right.right = new TreeNode(4);
		
		root.right.right.left = new TreeNode(5);
		root.right.right.right = new TreeNode(6);
		
		root.right.right.left.right = new TreeNode(7);
		root.right.right.left.right.right = new TreeNode(8);
		System.out.println((new LongestZigZagPath().longestZigZag(root)));
	}
	
	public int longestZigZag(TreeNode root) {
		return longestZigZagSolve(root).max;
	}
	
	public PairLongestZigZagPath longestZigZagSolve(TreeNode root) {
		if(root == null) {
			return new PairLongestZigZagPath();
		}
		
		PairLongestZigZagPath left = longestZigZagSolve(root.left);
		PairLongestZigZagPath right = longestZigZagSolve(root.right);
		

		int myLeftMax = 1 + left.rightMax;
		int myRightMax = 1 + right.leftMax;
		
		int myMax = Math.max(Math.max(left.max, right.max), Math.max(myLeftMax, myRightMax));
		
		
		PairLongestZigZagPath sol = new PairLongestZigZagPath();
		sol.leftMax = myLeftMax;
		sol.rightMax = myRightMax;
		sol.max = myMax;
		return sol;
    }

}
