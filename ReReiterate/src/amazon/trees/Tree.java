package amazon.trees;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {
	
	Node root;
	
	int maxLevel = 0;
	Node prev = null;
	class isBalancedHeightPair {
		int height;
		boolean isBalanced;
		
		public isBalancedHeightPair(int height, boolean isBalanced) {
			super();
			this.height = height;
			this.isBalanced = isBalanced;
		}

		public String toString() {
			return "isBalancedHeightPair [height=" + height + ", isBalanced=" + isBalanced + "]";
		}
	
	}
	
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
	
	public void printLevelOrderTraversalLineByLine(Node root) {
		if(root == null) {
			return;
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			int queueSize = queue.size();
			for(int i = 0;i < queueSize; i++) {
				Node curr = queue.poll();
				System.out.print(curr.data + " ");
				if(curr.left != null) {
					queue.add(curr.left);
				}
				if(curr.right != null) {
					queue.add(curr.right);
				}
			}
			System.out.println();
		}
	}
	
	public int sizeOfTree(Node root) {
		if(root == null) {
			return 0;
		}
		if(root.left == null && root.right == null) {
			return 1;
		}
		return 1 + sizeOfTree(root.left) + sizeOfTree(root.right);
	}
	
	public int getMaxValueInTree(Node root) {
		if(root == null) {
			return -1;
		}
		if(root.left == null && root.right == null) {
			return root.data;
		}
		return Math.max(root.data, Math.max(getMaxValueInTree(root.left), getMaxValueInTree(root.right)));
	}
	
	public void printLeftViewOfBinaryTree(Node root, int currentLevel) {
		if(root == null) {
			return;
		}
		if(currentLevel > maxLevel) {
			System.out.println(root.data);
			maxLevel = currentLevel;
		}
		printLeftViewOfBinaryTree(root.left, currentLevel + 1);
		printLeftViewOfBinaryTree(root.right, currentLevel + 1);
	}
	
	public boolean isChildrenSumPropertyFollowed(Node root) {
		if(root == null) {
			return true;
		}
		
		if(root.left == null && root.right == null) {
			return true;
		}
		if(root.left == null) {
			return root.data == root.right.data;
		}
		if(root.right == null) {
			return root.data == root.left.data;
		}
		return (root.data == root.left.data + root.right.data) && isChildrenSumPropertyFollowed(root.left) && isChildrenSumPropertyFollowed(root.right);
	}
	
	public isBalancedHeightPair isHeightBalanced(Node root) {
		if(root == null) {
			return new isBalancedHeightPair(0, true);
		}
		if(root.left == null && root.right == null) {
			return new isBalancedHeightPair(1, true);
		}
		isBalancedHeightPair isBalancedHeightPairLeft = isHeightBalanced(root.left);
		isBalancedHeightPair isBalancedHeightPairRight = isHeightBalanced(root.right);
		
		int newHeight = 1 + Math.max(isBalancedHeightPairLeft.height, isBalancedHeightPairRight.height);
		boolean newIsbalanced = isBalancedHeightPairLeft.isBalanced && isBalancedHeightPairRight.isBalanced && Math.abs(isBalancedHeightPairLeft.height - isBalancedHeightPairRight.height) <= 1;
		
		return new isBalancedHeightPair(newHeight, newIsbalanced);
	}
	
	public Node convertBinaryTreeToDoublyLinkedList(Node root) {
		if(root == null) {
			return root;
		}
		
		Node head = convertBinaryTreeToDoublyLinkedList(root.left);
		
		if(prev == null) {
			head = root;
		} else {
			prev.right = root;
			root.left = prev;
		}
		prev = root;
		convertBinaryTreeToDoublyLinkedList(root.right);
		return head;
	}
}

