package binary.trees;

public class BinarySearchTree {
	
	Node root;
	
	public BinarySearchTree() {
		root = new Node();
	}

	public BinarySearchTree(int rootData) {
		root = new Node(rootData);
	}
	
	/*
	 * Depth First Traversals
	 */
	public void inorderTraversal(Node root) {
		if(root == null) {
			return;
		}
		inorderTraversal(root.left);
		System.out.println(root.data);
		inorderTraversal(root.right);
	}

	public void postOrderTraversal(Node root) {
		if(root == null) {
			return;
		}
		postOrderTraversal(root.left);
		postOrderTraversal(root.right);
		System.out.println(root.data);
	}

	public void preOrderTraversal(Node root) {
		if(root == null) {
			return;
		}
		System.out.println(root.data);
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
	}
	
	public Node insertNode(Node root, int data) {
		if(root == null) {
			return new Node(data);
		}
		if(root.data > data) {
			root.left = insertNode(root.left, data);
		} else {
			root.right = insertNode(root.right, data);
		}
		return root;
	}
	
	public boolean isPresent(Node root, int data) {
		if(root == null) {
			return false;
		}
		if(root.data == data) {
			return true;
		}
		return isPresent(root.left, data) || isPresent(root.right, data);
	}
	
	public Node deleteNode(Node root, int data) {
		if(root == null) {
			return null;
		}
		if(root.data > data) {
			root.left = deleteNode(root.left, data);
		} else if(root.data < data) {
			root.right = deleteNode(root.right, data);
		} else {
			if(root.left == null) {
				return root.right;
			} else if(root.right == null) {
				return root.left;
			} else {
				Node inorderSuccessor = getInorderSuccessorForDeleteFunction(root);
				root.data = inorderSuccessor.data;
				root.right = deleteNode(root.right, inorderSuccessor.data);
			}
		}
		return root;
	} 
	
	/*
	 * Works only in delete function, otherwise has some anamolies
	 */
	public Node getInorderSuccessorForDeleteFunction(Node node) {
		Node temp = node.right;
		while(temp != null && temp.left != null) {
			temp = temp.left;
		}
		return temp;
	}
	
	public Node floorOfAnElement(Node root, int element) {
		Node floorNode = null;
		Node temp = root;
		while(temp != null) {
			if(temp.data == element) {
				floorNode = temp;
				break;
			} else if(temp.data < element) {
				floorNode = temp;
				temp = temp.right;
			} else {
				temp = temp.left;
			}
		}
		return floorNode;
	}
	
	public Node createPerfectlyBalancedBSTFromSortedElements(Node root, int[] array, int beginIndex, int endIndex) {
		if(beginIndex > endIndex) {
			return root;
		}
		int midIndex = (beginIndex + endIndex)/2;
		if(root == null) {
			root = new Node(array[midIndex]);
		}
		root.left = createPerfectlyBalancedBSTFromSortedElements(root.left, array, beginIndex, midIndex - 1);
		root.right = createPerfectlyBalancedBSTFromSortedElements(root.right, array, midIndex + 1, endIndex);
		return root;
	}
	
}
