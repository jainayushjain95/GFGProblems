package trees.binarytrees;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreesDriver {

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree(10);
		Node root = binaryTree.root;
		root.left = new Node(20);
		root.left.left = new Node(30);
		root.left.left.right = new Node(40);
		
		List<Integer> list = new ArrayList<Integer>();
		
		binaryTree.serializationOfBinaryTree(root, list);
		for(int x : list) {
			System.out.println(x);
		}
	
	}

}
