package trees.binarytrees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreesDriver {

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree(1);
		Node root = binaryTree.root;
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.right = new Node(6);
		
		binaryTree.printLevelOrderTraversalsLineByLine(root);	
		
//		List<Integer> list = new ArrayList<Integer>();
//		
//		binaryTree.serializationOfBinaryTree(root, list);
//		for(int x : list) {
//			System.out.println(x);
//		}
		
	}

}
