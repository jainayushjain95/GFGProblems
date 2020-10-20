package amazon.trees;

import java.util.LinkedList;
import java.util.Queue;

public class TreesDriver {

	public static void main(String[] args) {
		Tree tree = new Tree(new Node(1));
		Node root = tree.root;
		getNthLevelPerfectBinaryTree(3, tree);
		tree.inorderTraversal(root);
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
}
