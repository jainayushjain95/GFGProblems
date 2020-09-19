package trees.binarytrees;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreesDriver {

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree(1);
		Node root = (new BinaryTreesDriver()).getTree2(binaryTree);
		binaryTree.printLevelOrderTraversalsLineByLineReverse(root);
		System.out.println("\n\n");
		
		
		/*
		 * printReverseLevelOrderTraversalSpiral
		 * printLevelOrderTraversalAlternateLeftRightNodesPerfectTreeSpecificReverseOrder
		 */
	}

	public Node getTree1(BinaryTree binaryTree) {
		Node root = binaryTree.root;
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		
		root.right.left = new Node(8);
		root.right.right = new Node(6);
		
		root.right.left.left = new Node(9);
		root.right.left.right = new Node(10);
		return root;
	}
	
	public Node getTree2(BinaryTree binaryTree) {
		Node root = binaryTree.root;
		root.left = new Node(2);
		root.right = new Node(3);

		root.left.left = new Node(4);
		root.left.right = new Node(5);

		root.right.left = new Node(6);
		root.right.right = new Node(7);

		root.left.left.left = new Node(8);
		root.left.left.right = new Node(9);

		root.left.right.left = new Node(10);
		root.left.right.right = new Node(11);

		root.right.left.left = new Node(12);
		root.right.left.right = new Node(13);
		root.right.right.left = new Node(14);
		root.right.right.right = new Node(15);

		root.left.left.right.left = new Node(16);
		root.left.left.right.right = new Node(17);

		root.right.left.left.left = new Node(18);
		root.right.left.left.right = new Node(19);

		root.right.right.left.right = new Node(20);
		root.right.right.right.left = new Node(21);
		return root;
	}
	
	public Node getPerfectBinaryTreeNLevels(int level, BinaryTree binaryTree) {
		Node root = binaryTree.root;
		int data = 2;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		for(int i = 2; i <= level; i++) {
			int queueSize = queue.size();
			for(int j = 0;j < queueSize; j++) {
				Node curr = queue.poll();
				curr.left = new Node(data++);
				curr.right = new Node(data++);
				queue.add(curr.left);
				queue.add(curr.right);
			}
		}
		return root;
	}
}
