package lc.google;

import java.util.*;

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

public class AllNodesDistanceK {

	TreeNode[] parentsMap;
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		
		root.left = new TreeNode(5);
		root.right = new TreeNode(1);
	
		root.left.left = new TreeNode(6);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(8);
		
		root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(4);
		
		System.out.println((new AllNodesDistanceK().distanceK(root, root.left, 2)));
	}
	
	public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
		if(k == 0) {
			List<Integer> nodes = new ArrayList<Integer>();
			nodes.add(target.val);
			return nodes;
		}
		parentsMap = new TreeNode[501];
		updateParentsMap(root, null);
		return distanceKSolve(root, target, k);
    }
	
	public List<Integer> distanceKSolve(TreeNode root, TreeNode target, int k) {
		List<Integer> nodes = new ArrayList<Integer>();
		boolean[] visited = new boolean[501];
		visited[target.val] = true;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(target);
		
		while(k != 0 && !queue.isEmpty()) {
			k--;
			int queueSize = queue.size();
			for(int i = 0;i < queueSize; i++) {
				TreeNode currentNode = queue.poll();
				if(currentNode.left != null && !visited[currentNode.left.val]) {
					if(k == 0) {
						nodes.add(currentNode.left.val);
					} else {
						visited[currentNode.left.val] = true;
						queue.add(currentNode.left);
					}
				}
				
				if(currentNode.right != null && !visited[currentNode.right.val]) {
					if(k == 0) {
						nodes.add(currentNode.right.val);
					} else {
						visited[currentNode.right.val] = true;
						queue.add(currentNode.right);
					}
				}
				
				if(parentsMap[currentNode.val] != null && !visited[parentsMap[currentNode.val].val]) {
					if(k == 0) {
						nodes.add(parentsMap[currentNode.val].val);
					} else {
						visited[parentsMap[currentNode.val].val] = true;
						queue.add(parentsMap[currentNode.val]);
					}
				}
			}
			
		}
		
		return nodes;
	}

	
	public void updateParentsMap(TreeNode root, TreeNode parent) {
		if(root == null) {
			return;
		}
		parentsMap[root.val] = parent;
		updateParentsMap(root.left, root);
		updateParentsMap(root.right, root);
	}
}
