package binary.trees;

public class BinaryTreeDriver {

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree(10);
		Node root = binaryTree.root;
		root.left = new Node(20);
		root.right = new Node(30);
		root.left.left = new Node(40);
		root.left.right = new Node(50);
		root.right.left = new Node(60);
		root.left.left.right = new Node(70);
		binaryTree.printNodesAtKDistanceFromRoot(root, 2);
	}

}
