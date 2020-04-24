package binary.trees;

public class BinaryTreeDriver {

	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
//		Node root = bst.insertNode(null, 50);
//		root = bst.insertNode(root, 30);
//		root = bst.insertNode(root, 70);
//		root = bst.insertNode(root, 40);
//		root = bst.insertNode(root, 60);
//		root = bst.insertNode(root, 80);
//		
//		
//		
//		System.out.println(bst.floorOfAnElement(root, 34).data);
		
		int[] array = {7, 10, 11, 15, 30, 35, 40};
		Node root = bst.createPerfectlyBalancedBSTFromSortedElements(null, array, 0, array.length - 1);
		System.out.println(root.data);
		
	}

}
