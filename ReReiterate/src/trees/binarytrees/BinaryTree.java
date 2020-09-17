package trees.binarytrees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

	Node root;
	int index = 0;
	Node previous = null;
	int count = 0;
	
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
	
	public void preorderTraversal(Node root) {
		if(root == null) {
			return;
		}
		System.out.println(root.data);
		preorderTraversal(root.left);
		preorderTraversal(root.right);
	}
	
	public void postorderTraversal(Node root) {
		if(root == null) {
			return;
		}
		postorderTraversal(root.left);
		postorderTraversal(root.right);
		System.out.println(root.data);
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

	/*
	 * Serialization and deserialization using preorder traversal approach
	 */
	public void serializationOfBinaryTree(Node root, List<Integer> list) {
		if(root != null) {
			list.add(root.data);
			serializationOfBinaryTree(root.left, list);
			serializationOfBinaryTree(root.right, list);
		} else {
			list.add(-1);
		}
	}
	
	public Node desrializationOfBinaryTree(List<Integer> list) {
		if(index == list.size()) {
			return null;
		}
		int currentValue = list.get(index++);
		if(currentValue == -1) {
			return null;
		}
		Node root = new Node(currentValue);
		root.left = desrializationOfBinaryTree(list);
		root.right = desrializationOfBinaryTree(list);
		return root;
	}

	public void printPostorderFromInorderAndPreorder(int[] preOrder, int[] inOrder, int beginIndex, int endIndex) {
		if(beginIndex > endIndex) {
			return;
		}
		int currentRoot = preOrder[index++];
		int currentIndex = search(inOrder, currentRoot);
		
		printPostorderFromInorderAndPreorder(preOrder, inOrder, beginIndex, currentIndex - 1);
		printPostorderFromInorderAndPreorder(preOrder, inOrder, currentIndex + 1, endIndex);
		System.out.println(currentRoot);
	}
	
	public void printNthNodeInInorderTraversal(Node root, int n) {
		if(root == null) {
			return;
		}
		if(count <= n) {
			printNthNodeInInorderTraversal(root.left, n);
			count++;
			if(count == n) {
				System.out.println(root.data);
			}
			printNthNodeInInorderTraversal(root.right, n);	
		}
	}
	
	public static void printLevelOrderTraversalsLineByLine(Node root) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while(!queue.isEmpty()) {
			int queueSize = queue.size();
			for(int i = 0;i < queueSize; i++) {
				Node currentNode = queue.poll();
				System.out.print(currentNode.data + " ");
				if(currentNode.left != null) {
					queue.add(currentNode.left);
				}
				if(currentNode.right != null) {
					queue.add(currentNode.right);
				}
			}
			System.out.println();
		}
	}
	
	/*
	 * Helpers
	 */
	public int search(int[] A, int element) {
		int index = -1;
		for(int x : A) {
			index++;
			if(x == element) {
				break;
			}
		}
		return index;
	} 
}
