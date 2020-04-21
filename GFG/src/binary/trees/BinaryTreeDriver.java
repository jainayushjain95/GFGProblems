package binary.trees;

public class BinaryTreeDriver {

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree(10);
		Node root = binaryTree.root;
		root.left = new Node(20);
		root.right = new Node(30);
		root.right.left = new Node(50);
		root.right.left.left = new Node(70);
		root.right.right = new Node(60);
		root.right.right.right = new Node(90);
		root.right.right.right.right = new Node(40);
		root.right.right.left = new Node(80);

		binaryTree.printAllTheNodesAtKDistanceFromRoot(root, 3);
	}

}
