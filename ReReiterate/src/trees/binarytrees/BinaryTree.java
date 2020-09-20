package trees.binarytrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import trees.binarytrees.BinaryTree.HeightSizePair;


public class BinaryTree {

	Node root;
	int index = 0;
	Node previous = null;
	int count = 0;
	
	class HeightSizePair {
		int height;
		int size;
		
		public HeightSizePair(int height, int size) {
			super();
			this.height = height;
			this.size = size;
		}
	}
	
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
	
	
	/* 
	 * ############################################################
	 * Level Order Traversal Problems: Top to Bottom
	 * #############################################################
	 */
	
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
	
	
	public void printLevelOrderTraversalAlternateLeftRightNodesPerfectTreeSpecific(Node root) {
		if(root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node a = queue.poll();
			Node b = (queue.size() > 0) ? queue.poll() : null;
			System.out.println(a.data);
			if(a.left != null) {
				queue.add(a.left);
			}
			if(b != null) {
				System.out.println(b.data);
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
	
	
	//https://www.geeksforgeeks.org/reverse-alternate-levels-binary-tree/
	public void reverseAlternateLevelsPerfectTreeSpecific(Node rootLeft, Node rootRight, int currentLevel) {
		if(rootLeft == null || rootRight == null) {
			return;
		}
		
		if(currentLevel % 2 == 0) {
			int temp = rootLeft.data;
			rootLeft.data = rootRight.data;
			rootRight.data = temp;
		}
		reverseAlternateLevelsPerfectTreeSpecific(rootLeft.left, rootRight.right, currentLevel + 1);
		reverseAlternateLevelsPerfectTreeSpecific(rootLeft.right, rootRight.left, currentLevel + 1);
	}
	
	
	
	/* 
	 * ############################################################
	 * Level Order Traversal Problems: Bottom to Top
	 * #############################################################
	 */
	
	public void printLevelOrderTraversalReverse(Node root) {
		if(root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		Stack<Node> stack = new Stack<Node>();
		
		queue.add(root);
		
		while(!queue.isEmpty()) {
			Node currentNode = queue.poll();
			stack.push(currentNode);
			if(currentNode.right != null) {
				queue.add(currentNode.right);
			}
			if(currentNode.left != null) {
				queue.add(currentNode.left);
			}
		}
		while(!stack.isEmpty()) {
			System.out.println(stack.pop().data);
		}
	}
	
	public void printLevelOrderTraversalsLineByLineReverse(Node root) {
		if(root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		Stack<Node> stack = new Stack<Node>();
		
		queue.add(root);
		
		while(!queue.isEmpty()) {
			int queueSize = queue.size();
			for(int i = 0;i < queueSize; i++) {
				Node currentNode = queue.poll();
				stack.push(currentNode);
				if(currentNode.right != null) {
					queue.add(currentNode.right);
				}
				if(currentNode.left != null) {
					queue.add(currentNode.left);
				}
			}
			stack.push(null);
		}
		while(!stack.isEmpty()) {
			Node top = stack.pop();
			if(top != null) {
				System.out.print(top.data + " ");	
			} else {
				System.out.println();
			}
		}
	}
	
	public void printReverseLevelOrderTraversalSpiral(Node root) {
		if(root == null) {
			return;
		}
		Stack<Node> stackOne = new Stack<Node>();
		Stack<Node> stackTwo = new Stack<Node>();
		Stack<Node> stackThree = new Stack<Node>();
		
		stackOne.push(root);
		
		while(!stackOne.isEmpty() || !stackTwo.isEmpty()) {
			while(!stackOne.isEmpty()) {
				Node currenNode = stackOne.pop();
				stackThree.push(currenNode);
				if(currenNode.right != null) {
					stackTwo.push(currenNode.right);
				}
				if(currenNode.left != null) {
					stackTwo.push(currenNode.left);
				}
			}
			
			while(!stackTwo.isEmpty()) {
				Node currenNode = stackTwo.pop();
				stackThree.push(currenNode);
				if(currenNode.left != null) {
					stackOne.push(currenNode.left);
				}
				if(currenNode.right != null) {
					stackOne.push(currenNode.right);
				}
			}
		}
		
		while(!stackThree.isEmpty()) {
			System.out.println(stackThree.pop().data);
		}
	}
	
	
	
	public void printLevelOrderTraversalAlternateLeftRightNodesPerfectTreeSpecificReverseOrder(Node root) {
		if(root == null) {
			return;
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		
		if(root.left != null) {
			queue.add(root.right);
			queue.add(root.left);
		}

		
		while(!queue.isEmpty()) {
			Node one = queue.poll();
			stack.push(one);
			//System.out.println(one.data);
			Node two = (queue.size() > 0) ? queue.poll() : null;
			if(two != null) {
				stack.push(two);
				//System.out.println(two.data);	
			}
			if(one.left != null) {
				queue.add(one.left);	
			}
			if(two != null && two.right != null) {
				queue.add(two.right);	
			}
			if(one.right != null) {
				queue.add(one.right);	
			}
			if(two != null && two.left != null) {
				queue.add(two.left);	
			}
			
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop().data + " ");
		}
	}
	
	
	/* 
	 * ############################################################
	 * Miscellaneous Traversal Problem: Top to Bottom
	 * #############################################################
	 */
	
	public void printDiagonalTraversal(Node root) {
		Map<Integer, List<Node>> map = new HashMap<Integer, List<Node>>();
		int slopeDistance = 1;
	    updateMapDiagonalTraversal(root, map, slopeDistance);
	    while(map.containsKey(slopeDistance)) {
	    	List<Node> list = map.get(slopeDistance);
	    	for(int i = 0;i < list.size(); i++) {
	    		System.out.print(list.get(i).data + " ");
	    	}
	    	System.out.println();
	    	slopeDistance++;
	    }
	}
	
	public void printBoundaryTraversal(Node root) {
		printLeftTree(root);
		printLeavesOfTreeLeftToRight(root);
		printRightTreeReverse(root);
	}
	
	public Double getDensityOfTreeInSingleTraversal(Node root) {
		double density = 1.0;
		HeightSizePair heightSizePair = getHeightSizePair(root);
		System.out.println(heightSizePair.size + ", " + heightSizePair.height);
		density = (double)heightSizePair.size / heightSizePair.height;
		return density;
	}
	
	/*
	 * Helpers
	 */
	
	public HeightSizePair getHeightSizePair(Node root) {
		if(root == null) {
			return new HeightSizePair(0, 0);
		}
		if(root.left == null && root.right == null) {
			return new HeightSizePair(1, 1); 
		}
		HeightSizePair heightSizePairLeft = getHeightSizePair(root.left);
		HeightSizePair heightSizePairRight = getHeightSizePair(root.right);
		
		int height = 1 + Math.max(heightSizePairLeft.height, heightSizePairRight.height);
		int size = 1 + heightSizePairLeft.size + heightSizePairRight.size;
		
		return new HeightSizePair(height, size);
	}
	
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
	
	public void updateMapDiagonalTraversal(Node root, Map<Integer, List<Node>> map, int slopeDistance) {
		if(root == null) {
			return;
		}
		if(!map.containsKey(slopeDistance)) {
			map.put(slopeDistance, new ArrayList<Node>());
		}
		map.get(slopeDistance).add(root);
		updateMapDiagonalTraversal(root.left, map, slopeDistance + 1);
		updateMapDiagonalTraversal(root.right, map, slopeDistance);
	}
	
	public void printLeftTree(Node root) {
		if(root == null) {
			return;
		}
		if(root.left != null) {
			System.out.println(root.data);
			printLeftTree(root.left);
		} else if(root.right != null){
			System.out.println(root.data);
			printLeftTree(root.right);
		}
	}
	
	public void printLeavesOfTreeLeftToRight(Node root) {
		if(root == null) {
			return;
		}
		if(root.left == null && root.right == null) {
			System.out.println(root.data);
			return;
		}
		printLeavesOfTreeLeftToRight(root.left);
		printLeavesOfTreeLeftToRight(root.right);
	}
	
	public void printRightTreeReverse(Node root) {
		if(root == null) {
			return;
		}
		if(root.right != null) {
			printRightTreeReverse(root.right);
			System.out.println(root.data);
		} else if(root.left != null){
			printRightTreeReverse(root.left);
			System.out.println(root.data);
		}
	}
	
}