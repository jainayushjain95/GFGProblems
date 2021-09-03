package lc.trees;

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

public class BinaryTree2 {

	long max = 0;
	int mod = 1000000007;
	long sum = 0;
	
	public static void main(String[] args) {
		for(int i = 1; i < 9; i++) {
			System.out.println((char)(i + '0'));
		}
		BinaryTree binaryTree = new BinaryTree();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);

		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		
		System.out.println(new BinaryTree2().maxProduct(root));

	}

	
	public int maxProduct(TreeNode root) {
		sum = getTotalSum(root);
		maxProductSolve(root);
		return (int)(max % mod);
    }
	
	public long getTotalSum(TreeNode root) {
		if(root == null) {
			return 0;
		}
		if(root.left == null && root.right == null) {
			return root.val;
		}
		return root.val + getTotalSum(root.left) + getTotalSum(root.right);
	}
	
	public long maxProductSolve(TreeNode root) {
		if(root == null) {
			return 0;
		}
		if(root.left == null && root.right == null) {
			return root.val;
		}
		
		long leftSum = maxProductSolve(root.left);
		long rightSum = maxProductSolve(root.right);
		
		max = Math.max(max, leftSum * (sum - leftSum));
		max = Math.max(max, rightSum * (sum - rightSum));
		
		return leftSum + rightSum + root.val;
    }
	
	public int goodNodes(TreeNode root) {
		return goodNodesSolve(root, Integer.MIN_VALUE);
	}

	public int goodNodesSolve(TreeNode root, int max) {
		if(root == null) {
			return 0;
		}

		int base = (root.val >= max) ? 1 : 0;
		int newMax = (base == 1) ? root.val : max;
		return base + goodNodesSolve(root.left, newMax) + goodNodesSolve(root.right, newMax);
	}


}
