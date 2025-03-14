package binary.trees;

public class TreeNode {

	int val;
	TreeNode left;
	TreeNode right;
	int size;

	TreeNode() {}

	TreeNode(int data) {
		this.val = data;
	}

	@Override
	public String toString() {
		return "Node [data=" + val + "]";
	}
	
	
	
}
