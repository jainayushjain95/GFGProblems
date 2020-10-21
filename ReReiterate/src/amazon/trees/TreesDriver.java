package amazon.trees;

import java.util.LinkedList;
import java.util.Queue;

public class TreesDriver {

	public static void main(String[] args) {
		Tree tree = new Tree(new Node(1));
		Node root = tree.root;
		getTree(tree);
		System.out.println(tree.sizeOfTree(root));
	}

	public static void getNthLevelPerfectBinaryTree(int levels, Tree tree) {
		Node root = tree.root;
		int data = 2;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while(levels > 1) {
			int queueSize = queue.size();
			for(int i = 0;i < queueSize; i++) {
				Node node = queue.poll();
				node.left = new Node(data++);
				node.right = new Node(data++);
				queue.add(node.left);
				queue.add(node.right);
			}
			levels--;
		}
	}
	
	public static void getTree(Tree tree) {
		Node root = tree.root;
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
	}
}
