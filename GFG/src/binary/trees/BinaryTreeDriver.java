package binary.trees;

public class BinaryTreeDriver {

	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		Node root = bst.insertNode(null, 50);
		root = bst.insertNode(root, 30);
		root = bst.insertNode(root, 70);
		root = bst.insertNode(root, 40);
		root = bst.insertNode(root, 60);
		root = bst.insertNode(root, 80);
		
		
		
		System.out.println(bst.floorOfAnElement(root, 34).data);
		
	}

}
