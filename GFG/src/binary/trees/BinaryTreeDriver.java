package binary.trees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeDriver {

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree(10);
		Node root = binaryTree.root;
		root.left = new Node(20);
		root.right = new Node(30);
		root.right.left = new Node(40);
		root.right.left.left = new Node(60);
		root.right.right = new Node(50);
		root.right.right.left = new Node(70);
		root.right.right.right = new Node(80);
		//binaryTree.inorderTraversal(root);
		List<Node> path = new ArrayList<Node>();
		binaryTree.findPath(root, path, 70);
		System.out.println(path.toString());
	}

}
