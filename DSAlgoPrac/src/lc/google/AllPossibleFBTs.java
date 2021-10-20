package lc.google;

import java.util.*;

public class AllPossibleFBTs {

	List<TreeNode>[] memo = new ArrayList[21];
	
	public static void main(String[] args) {
		
	}
	
	public List<TreeNode> allPossibleFBT(int n) {
		if(n % 2 == 0) {
			List<TreeNode> nodes = new ArrayList<TreeNode>();
			return nodes;
		}
		
		if(n == 1) {
			List<TreeNode> nodes = new ArrayList<TreeNode>();
			nodes.add(new TreeNode(0));
			return nodes;
		}
		
		if(memo[n] != null) {
			return memo[n];
		}
		
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		
		for(int i = 1;i <= n - 2; i = i + 2) {
			List<TreeNode> leftNodes = allPossibleFBT(i);
			List<TreeNode> rightNodes = allPossibleFBT(n - i - 1);
			
			for(TreeNode treeNodeLeft : leftNodes) {
				for(TreeNode treeNodeRight : rightNodes) {
					TreeNode root = new TreeNode(0);
					root.left = treeNodeLeft;
					root.right = treeNodeRight;
					nodes.add(root);
				}
			}
			
		}
		
		memo[n] = nodes;
		
		return nodes;
    }

}
