package trees.binarytrees;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

	Node root;
	
	public BinaryTree() {
		this.root = new Node();
	}
	
	public BinaryTree(int data) {
		this.root = new Node(data);
	}
	
	public void inorderTraversal(Node root) {
		if(root == null) {
			return;
		}
		inorderTraversal(root.left);
		System.out.println(root.data);
		inorderTraversal(root.right);
	}

	public void levelOrderTraversal(Node root) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			System.out.println(curr.data);
			if(curr.left != null) {
				queue.add(curr.left);
			}
			if(curr.right != null) {
				queue.add(curr.right);
			}
		}
	}
	
}
