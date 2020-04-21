package binary.trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class BinaryTree {

	Node root;
	int maxLevel = 0; // for left view problem
	Node prev = null; // for convertBinaryTreeToDoublyLL
	int preIndex = 0; // for getTreeFromInorderAndPreorderTraversals

	class DiameterHeightPair {
		int diameter;
		int height;
	}

	public BinaryTree() {
		root = new Node();
	}

	public BinaryTree(int rootData) {
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

	/*
	 * BFS
	 */
	public void levelOrderTraversal(Node root) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node first = queue.poll(); 
			System.out.println(first.data);
			if(first.left != null) {
				queue.add(first.left);
			}
			if(first.right != null) {
				queue.add(first.right);
			}
		}
	}

	public int getSizeOfBinaryTree(Node root) {
		if(root == null) {
			return 0;
		}
		return 1 + getSizeOfBinaryTree(root.left) + getSizeOfBinaryTree(root.right);
	}

	public int getMaxElement(Node root) {
		if(root == null) {
			return Integer.MIN_VALUE;
		}
		return Math.max(root.data, Math.max(getMaxElement(root.left), getMaxElement(root.right)));
	}

	public int getHeight(Node root) {
		if(root == null) {
			return 0;
		}
		return 1 + Math.max(getHeight(root.left), getHeight(root.right));
	}

	public void printNodesAtKDistanceFromRoot(Node root, int k) {
		if(root == null) {
			return;
		}
		if(k == 0) {
			System.out.println(root.data);
			return;
		}
		printNodesAtKDistanceFromRoot(root.left, k - 1);
		printNodesAtKDistanceFromRoot(root.right, k - 1);
	}

	public void printLeftViewOfTree(Node root, int currentLevel) {
		if(root == null) {
			return;
		}
		if(maxLevel < currentLevel) {
			System.out.println(root.data);
			maxLevel = currentLevel;
		}
		printLeftViewOfTree(root.left, currentLevel + 1);
		printLeftViewOfTree(root.right, currentLevel + 1);
	}

	public boolean satisfiesChildrenSumProperty(Node root) {
		if(root == null || (root.left == null && root.right == null)) {
			return true;
		}
		int leftData = (root.left == null) ? 0 : root.left.data;
		int rightData = (root.right == null) ? 0 : root.right.data;
		return (leftData + rightData == root.data) && satisfiesChildrenSumProperty(root.left) && satisfiesChildrenSumProperty(root.right);
	}

	//returns -1 if not balanced otherwise returns height of tree
	public int isTreeBalanced(Node root) {
		if(root == null) {
			return 0;
		}
		int leftHeight = isTreeBalanced(root.left);
		if(leftHeight == -1) {
			return -1;
		}
		int rightHeight = isTreeBalanced(root.right);
		if(rightHeight == -1) {
			return -1;
		}
		if(Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		} else {
			return 1 + Math.max(leftHeight, rightHeight);
		}
	}

	public Node convertBinaryTreeToDoublyLL(Node root) {
		if(root == null) {
			return null;
		} 
		Node head = convertBinaryTreeToDoublyLL(root.left);
		if(prev == null) {
			head = root;
		} else {
			root.left = prev;
			prev.right = root;
		}
		prev = root;
		convertBinaryTreeToDoublyLL(root.right);
		return head;
	}

	/*
	 * getTreeFromInorderAndPreorderTraversals
	 */
	public Node getTreeFromInorderAndPreorderTraversals(int[] inorder, int[] preorder, int inorderStartingIndex, int inorderEndingIndex) {
		if(inorderStartingIndex > inorderEndingIndex) {
			return null;
		}
		Node root = new Node(preorder[preIndex++]);
		int currentRootIndex = getIndex(inorder, root.data);
		root.left = getTreeFromInorderAndPreorderTraversals(inorder, preorder, inorderStartingIndex, currentRootIndex - 1);
		root.right = getTreeFromInorderAndPreorderTraversals(inorder, preorder, currentRootIndex + 1, inorderEndingIndex);
		return root;
	}

	public int getIndex(int[] input, int element) {
		int index = -1;
		for(int i = 0;i < input.length; i++) {
			if(input[i] == element) {
				index = i;
				break;
			}
		}
		return index;
	}

	/*
	 * tree traversal in spiral form
	 */
	public void printTreeInSpiralForm(Node root) {
		if(root == null) {
			return;
		}
		boolean reverse = false;
		Queue<Node> queue = new LinkedList<Node>();
		Stack<Node> stack = new Stack<Node>();
		queue.add(root);

		while(!queue.isEmpty()) {
			int count = queue.size();
			for(int i = 0; i < count; i++) {
				Node curr = queue.poll();
				if(!reverse) {
					System.out.print(curr.data + ", ");
				} else {
					stack.push(curr);
				}
				if(curr.left != null) {
					queue.add(curr.left);
				}
				if(curr.right != null) {
					queue.add(curr.right);
				}
			}
			if(reverse) {
				while(!stack.isEmpty()) {
					System.out.print(stack.pop().data + ", ");
				}
			}
			reverse = !reverse;
			System.out.println();
		}
	}

	/*
	 * get Diameter
	 */
	public DiameterHeightPair getDiameterOfTree(Node root) {
		if(root == null) {
			DiameterHeightPair diameterHeightPair = new DiameterHeightPair();
			diameterHeightPair.height = 0;
			diameterHeightPair.diameter = 0;
			return diameterHeightPair;
		} else if(root.left == null && root.right == null) {
			DiameterHeightPair diameterHeightPair = new DiameterHeightPair();
			diameterHeightPair.height = 1;
			diameterHeightPair.diameter = 1;
			return diameterHeightPair;
		}

		DiameterHeightPair leftDiameterHeightPair = getDiameterOfTree(root.left);
		DiameterHeightPair rightDiameterHeightPair = getDiameterOfTree(root.right);

		DiameterHeightPair winner = new DiameterHeightPair();

		winner.height = 1 + Math.max(leftDiameterHeightPair.height, rightDiameterHeightPair.height);
		winner.diameter = Math.max(1 + leftDiameterHeightPair.height + rightDiameterHeightPair.height, Math.max(leftDiameterHeightPair.diameter, rightDiameterHeightPair.diameter));

		return winner;
	}

	/*
	 * Find path
	 */
	public boolean findPath(Node root, List<Node> path, int n) {
		if(root == null) {
			return false;
		}
		path.add(root);
		if(root.data == n) {
			return true;
		}
		if(findPath(root.left, path, n) || findPath(root.right, path, n)) {
			return true;
		}
		path.remove(path.size() - 1);
		return false;
	}

	/*
	 * Get LCA
	 */
	public Node getLowestCommonAncestor(Node root, int n1, int n2) {
		return null;
	}

}
