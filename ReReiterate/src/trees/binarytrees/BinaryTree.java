package trees.binarytrees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
	
	public void printLevelOrderTraversalsLineByLine(Node root) {
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
	
	public void printLevelOrderTraversalSpiral(Node root) {
		Stack<Node> stackOne = new Stack<Node>();
		Stack<Node> stackTwo = new Stack<Node>();
		stackOne.push(root);
		while(!stackOne.isEmpty() || !stackTwo.isEmpty()) {
			while(!stackOne.isEmpty()) {
				Node top = stackOne.pop();
				System.out.print(top.data + " ");
				if(top.right != null) {
					stackTwo.add(top.right);
				}
				if(top.left != null) {
					stackTwo.add(top.left);
				}
			}
			System.out.println();
			while(!stackTwo.isEmpty()) {
				Node top = stackTwo.pop();
				System.out.print(top.data + " ");
				if(top.left != null) {
					stackOne.add(top.left);
				}
				if(top.right != null) {
					stackOne.add(top.right);
				}
			}
			System.out.println();
		}
	}
	
	public void printLevelOrderTraversalWithDirectionChangeAfterEveryTwoLevels(Node root) {
		Stack<Node> stack = new Stack<Node>();
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		
		boolean isLeftToRightLevel = true;
		int directionFlag = 0;
		
		while(!queue.isEmpty()) {
			directionFlag++;
			int queueSize = queue.size();
			for(int i = 0;i < queueSize; i++) {
				Node curr = queue.poll();
				if(isLeftToRightLevel) {
					System.out.print(curr.data + " ");
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
			if(isLeftToRightLevel) {
				System.out.println();
			}
			if(!isLeftToRightLevel) {
				while(!stack.isEmpty()) {
					System.out.print(stack.pop().data + " ");
				}
				System.out.println();
			}
			if(directionFlag == 2) {
				directionFlag = 0;
				isLeftToRightLevel = !isLeftToRightLevel;
			}
		}
	}
	
	public void printNthLevelNodes(int n, Node root) {
		if(root == null || n < 0) {
			return;
		}
		if(n == 0) {
			System.out.println(root.data);
		}
		
		printNthLevelNodes(n - 1, root.left);
		printNthLevelNodes(n - 1, root.right);
	}
	
	public void printReverseLevelOrderTraversalSpiral(Node root) {
		if(root == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		Queue<Node> queue = new LinkedList<Node>();
		
		queue.add(root);
		
		while(!queue.isEmpty()) {
			Node currentNode = queue.poll();
			stack.push(currentNode);
			if(currentNode.left != null) {
				queue.add(currentNode.left);
			}
			if(currentNode.right != null) {
				queue.add(currentNode.right);
			}
		}
		
		while(!stack.isEmpty()) {
			System.out.println(stack.pop().data);
		}
	}
	
	public void printLevelOrderTraversalAlternateLeftRightNodesPerfectTreeSpecific(Node root) {
		if(root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node a = queue.poll();
			Node b = (queue.size() > 0) ? queue.poll() : null;
			System.out.print(a.data + " ");
			if(a.left != null) {
				queue.add(a.left);
			}
			if(b != null) {
				System.out.print(b.data + " ");
			}
			if(b != null && b.right != null) {
				queue.add(b.right);
			}
			if(a.right != null) {
				queue.add(a.right);
			}
			if(b != null && b.left != null) {
				queue.add(b.left);
			}
		}
	}
	
	public void printLevelOrderTraversalAlternateLeftRightNodesLineWisePerfectTreeSpecific(Node root) {
		if(root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while(!queue.isEmpty()) {
			int queueSize = queue.size();
			for(int i = 0; i < queueSize; i = i + 2) {
				Node a = queue.poll();
				System.out.print(a.data + " ");
				Node b = (queue.size() > 0) ? queue.poll() : null;
				
				if(b != null) {
					System.out.print(b.data + " ");
				}
				
				if(a.left != null) {
					queue.add(a.left);	
				}
				if(b != null && b.right != null) {
					queue.add(b.right);
				}
				if(a.right != null) {
					queue.add(a.right);	
				}
				if(b != null && b.left != null) {
					queue.add(b.left);
				}
			}
			System.out.println();
		}
	}
	
	public void printLevelOrderTraversalAlternateLeftRightNodesPerfectTreeSpecificReverseOrder(Node root) {
		if(root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		Stack<Node> stack = new Stack<Node>();
		queue.add(root);
		while(!queue.isEmpty()) {
			int queueSize = queue.size();
			Queue<Node> levelQueue = new LinkedList<Node>();
			for(int i = 0; i < queueSize; i = i + 2) {
				Node a = queue.poll();
				Node b = (queue.size() > 0) ? queue.poll() : null;
				
				levelQueue.add(a);
				
				if(b != null) {
					levelQueue.add(b);
				}
				
				if(a.left != null) {
					queue.add(a.left);	
				}
				if(b != null && b.right != null) {
					queue.add(b.right);
				}
				if(a.right != null) {
					queue.add(a.right);	
				}
				if(b != null && b.left != null) {
					queue.add(b.left);
				}
			}
			while(!levelQueue.isEmpty()) {
				stack.push(levelQueue.poll());
			}
			stack.push(null);
		}
		while(!stack.isEmpty()) {
			if(stack.peek() != null) {
				System.out.print(stack.pop().data + " ");	
			} else {
				stack.pop();
				System.out.println();
			}
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