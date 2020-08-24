package trees.binarytrees;

public class BinaryTreesDriver {

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree(1);
		Node root = binaryTree.root;
		root.left = new Node(2);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right = new Node(3);
		
		binaryTree.levelOrderTraversal(root);
	
	}

}
