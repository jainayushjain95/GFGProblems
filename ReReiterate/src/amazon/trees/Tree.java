package amazon.trees;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {
	Node root;

	public Tree(Node root) {
		this.root = root;
	}

	public void inorderTraversal(Node root) {
		if(root == null) {
			return;
		}
		inorderTraversal(root.left);
		System.out.println(root.data);
		inorderTraversal(root.right);
	}
	
	public void postorderTraversal(Node root) {
		if(root == null) {
			return;
		}
		postorderTraversal(root.left);
		postorderTraversal(root.right);
		System.out.println(root.data);
	}
	
	public void preorderTraversal(Node root) {
		if(root == null) {
			return;
		}
		System.out.println(root.data);
		preorderTraversal(root.left);
		preorderTraversal(root.right);
	}
	
	public int getHeight(Node root) {
		if(root == null) {
			return 0;
		}
		if(root.left == null && root.right == null) {
			return 1;
		}
		return 1 + Math.max(getHeight(root.left), getHeight(root.right));
	}
	
	public void printNodesAtKDistance(Node root, int k) {
		if(root == null) {
			return;
		}
		if(k == 0) { 
			System.out.println(root.data);
		}
		printNodesAtKDistance(root.left, k - 1);
		printNodesAtKDistance(root.right, k - 1);
	}
	
	public void printLevelOrderTraversal(Node root) {
		if(root == null) {
			return;
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			System.out.println(curr.data);
			if(curr.left != null) {
				queue.add(curr.left);
			}
			if(curr.right != null) {
				queue.add(curr.right);
			}
		}
		
	}
}

