package binary.trees;

import java.util.LinkedList;
import java.util.Queue;


public class BinaryTree {

	Node root;
	
	public BinaryTree() {
		root = new Node();
	}
	
	public BinaryTree(int rootData) {
		root = new Node(rootData);
	}
	
	/*
	 * Depth First Traversals
	 */
	public void inorderTraversal(Node root) {
		if(root == null) {
			return;
		}
		inorderTraversal(root.left);
		System.out.println(root.data);
		inorderTraversal(root.right);
	}
	
	public void postOrderTraversal(Node root) {
		if(root == null) {
			return;
		}
		postOrderTraversal(root.left);
		postOrderTraversal(root.right);
		System.out.println(root.data);
	}
	
	public void preOrderTraversal(Node root) {
		if(root == null) {
			return;
		}
		System.out.println(root.data);
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
	}
	
	/*
	 * BFS
	 */
	public void levelOrderTraversal(Node root) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node first = queue.poll(); 
			System.out.println(first.data);
			if(first.left != null) {
				queue.add(first.left);
			}
			if(first.right != null) {
				queue.add(first.right);
			}
		}
	}
	
	public int getSizeOfBinaryTree(Node root) {
		if(root == null) {
			return 0;
		}
		return 1 + getSizeOfBinaryTree(root.left) + getSizeOfBinaryTree(root.right);
	}
	
	public int getMaxElement(Node root) {
		if(root == null) {
			return Integer.MIN_VALUE;
		}
		return Math.max(root.data, Math.max(getMaxElement(root.left), getMaxElement(root.right)));
	}
	
	public int getHeight(Node root) {
		if(root == null) {
			return 0;
		}
		return 1 + Math.max(getHeight(root.left), getHeight(root.right));
	}
	
	public void printNodesAtKDistanceFromRoot(Node root, int k) {
		if(root == null) {
			return;
		}
		if(k == 0) {
			System.out.println(root.data);
			return;
		}
		printNodesAtKDistanceFromRoot(root.left, k - 1);
		printNodesAtKDistanceFromRoot(root.right, k - 1);
	}
	
}
