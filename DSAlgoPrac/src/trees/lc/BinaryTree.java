package trees.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode() {}
	TreeNode(int val) { this.val = val; }
	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

class MinMaxNode {
	Integer min;
	Integer max;
	boolean isBst;
	public MinMaxNode(Integer min, Integer max, boolean isBst) {
		super();
		this.min = min;
		this.max = max;
		this.isBst = isBst;
	}
	
}

class DiameterData {
	int diameter;
	int height;

	public DiameterData(int diameter, int height) {
		this.diameter = diameter;
		this.height = height;
	}
}

class HeightBalancedPair {
	int height;
	boolean isBalanced;
	int min;
	int max;
	public HeightBalancedPair(int height, boolean isBalanced) {
		super();
		this.height = height;
		this.isBalanced = isBalanced;
	}
}

class SizeIsBSTPair {
	int size;
	boolean isBst;
	int min;
	int max;
	public SizeIsBSTPair(int size, boolean isBst, int min, int max) {
		super();
		this.size = size;
		this.isBst = isBst;
		this.min = min;
		this.max = max;
	}
	
}

public class BinaryTree {

	TreeNode root;
	int diamater = 0;
	int maxLevel = 0;
	boolean isBalanced = true;
	TreeNode prev;
	TreeNode head;
	int preOrderIndex;

	public void inorderTraversalRecursive(TreeNode root) {
		if(root == null) {
			return;
		}
		inorderTraversalRecursive(root.left);
		System.out.println(root.val);
		inorderTraversalRecursive(root.right);
	}

	public List<Integer> inorderTraversalIterative(TreeNode root) {
		List<Integer> data = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();

		while(true) {
			if(root != null) {
				stack.push(root);
				root = root.left; 
			} else {
				if(stack.isEmpty()) {
					break;
				}
				TreeNode pop = stack.pop();
				data.add(pop.val);
				root = pop.right;
			}
		}

		return data;
	}

	public List<Integer> postorderTraversalIterative(TreeNode root) {
		List<Integer> data = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();

		while(!stack.isEmpty() || root != null) {
			if(root != null) { 
				stack.push(root);
				root = root.left;
			} else {
				TreeNode temp = stack.peek().right;
				if(temp == null) {
					temp = stack.pop();
					data.add(temp.val);
					while(!stack.isEmpty() && temp == stack.peek().right) {
						temp = stack.pop();
						data.add(temp.val);
					}
				} else {
					root = temp;
				}
			}
		}

		return data;
	}

	public void postorderTraversalRecursive(TreeNode root) {
		if(root == null) {
			return;
		}
		postorderTraversalRecursive(root.left);
		postorderTraversalRecursive(root.right);
		System.out.println(root.val);
	}

	public List<Integer> preorderTraversalIterative(TreeNode root) {
		List<Integer> data = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if(root != null) {
			stack.push(root);

			while(!stack.isEmpty()) {
				root = stack.pop();
				data.add(root.val);
				if(root.right != null) {
					stack.push(root.right);
				}

				if(root.left != null) {
					stack.push(root.left);
				}
			}	
		}

		return data;
	}

	public void levelOrderPrint(TreeNode root) {
		if(root != null) {
			Queue<TreeNode> queue = new LinkedList<TreeNode>();
			queue.add(root);
			while(!queue.isEmpty()) {
				int queueSize = queue.size();
				List<Integer> data = new ArrayList<Integer>();
				for(int i = 0;i < queueSize; i++) {
					TreeNode peek = queue.poll();
					System.out.print(peek.val + ", ");
					data.add(peek.val);
					if(peek.left != null) {
						queue.add(peek.left);
					}
					if(peek.right != null) {
						queue.add(peek.right);
					}
				}
				System.out.println();
			}	
		}
	}
	
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> levelOrder = new ArrayList<List<Integer>>();
		if(root != null) {
			Queue<TreeNode> queue = new LinkedList<TreeNode>();
			queue.add(root);
			while(!queue.isEmpty()) {
				int queueSize = queue.size();
				List<Integer> data = new ArrayList<Integer>();
				for(int i = 0;i < queueSize; i++) {
					TreeNode peek = queue.poll();
					data.add(peek.val);
					if(peek.left != null) {
						queue.add(peek.left);
					}
					if(peek.right != null) {
						queue.add(peek.right);
					}
				}
				levelOrder.add(data);
			}	
		}
		return levelOrder;
	}

	public List<List<Integer>> levelOrder2(TreeNode root) {
		LinkedList<List<Integer>> levelOrder = new LinkedList<List<Integer>>();
		if(root != null) {
			Queue<TreeNode> queue = new LinkedList<TreeNode>();
			queue.add(root);
			while(!queue.isEmpty()) {
				int queueSize = queue.size();
				List<Integer> data = new ArrayList<Integer>();
				for(int i = 0;i < queueSize; i++) {
					TreeNode peek = queue.poll();
					data.add(peek.val);
					if(peek.left != null) {
						queue.add(peek.left);
					}
					if(peek.right != null) {
						queue.add(peek.right);
					}
				}
				levelOrder.addFirst(data);
			}	
		}
		return levelOrder;
	}

	public List<List<Integer>> verticalTraversal(TreeNode root) {
		List<List<Integer>> data = new ArrayList<List<Integer>>();
		if(root != null) {
			Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
			Queue<TreeNode> queue = new LinkedList<TreeNode>();
			Map<TreeNode, Integer> hm = new HashMap<TreeNode, Integer>();
			queue.add(root);
			hm.put(root, 0);

			int minDistance = 0;
			addToMap(map, 0, root.val);

			while(!queue.isEmpty()) {
				int queueSize = queue.size();

				for(int i = 0;i < queueSize; i++) {
					TreeNode poll = queue.poll();
					if(poll.left != null) {
						queue.add(poll.left);
						hm.put(poll.left, hm.get(poll) - 1);
						addToMap(map, hm.get(poll.left), poll.left.val);
						minDistance = Math.min(minDistance, hm.get(poll.left));
					}
					if(poll.right != null) {
						queue.add(poll.right);
						hm.put(poll.right, hm.get(poll) + 1);
						addToMap(map, hm.get(poll.right), poll.right.val);
					}
				}
			}

			while(map.containsKey(minDistance)) {
				data.add(map.get(minDistance++));
			}

		}

		return data;
	}

	public static void addToMap(Map<Integer, List<Integer>> map, int distance, int value) {
		if(map.containsKey(distance)) {
			map.get(distance).add(value);
		} else {
			map.put(distance, new ArrayList<Integer>());
			map.get(distance).add(value);
		}
	}


	public DiameterData diameterOfBinaryTreeSolve(TreeNode root) {
		if(root == null) {
			return new DiameterData(0, 0);
		}
		if(root.left == null && root.right == null) {
			return new DiameterData(0, 1);
		}

		DiameterData leftDiameterData = diameterOfBinaryTreeSolve(root.left);
		DiameterData rightDiameterData = diameterOfBinaryTreeSolve(root.right);

		int sumHeight = leftDiameterData.height + rightDiameterData.height;
		int diameter = Math.max(sumHeight, Math.max(leftDiameterData.diameter, rightDiameterData.diameter));

		return new DiameterData(diameter, 1 + Math.max(leftDiameterData.height, rightDiameterData.height));
	}

	public int diameterOfBinaryTree(TreeNode root) {
		longestPath(root);
		return diamater;
	}

	public int longestPath(TreeNode root) {
		if(root == null) {
			return 0;
		}
		int leftPath = longestPath(root.left);
		int rightPath = longestPath(root.right);

		diamater = Math.max(diamater, leftPath + rightPath);

		return Math.max(leftPath, rightPath) + 1;
	}

	public TreeNode invertTree(TreeNode root) {
		if(root == null) {
			return null;
		}
		if(root.left == null && root.right == null) {
			return root;
		}
		invertTree(root.left);
		invertTree(root.right);

		TreeNode left = root.left;
		root.left = root.right;
		root.right = left;

		return root;
	}

	public void rightSideViewRecurtive(List<Integer> list, TreeNode root, int currentLevel) {
		if(root == null) {
			return;
		}
		if(maxLevel == currentLevel) {
			list.add(root.val);
			maxLevel++;
		}
		rightSideViewRecurtive(list, root.right, currentLevel + 1);
		rightSideViewRecurtive(list, root.left, currentLevel + 1);
	}
	
	public List<Integer> rightSideViewIterative(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		if(root != null) {
			Queue<TreeNode> queue = new LinkedList<TreeNode>();
			queue.add(root);
			while(!queue.isEmpty()) {
				int queueSize = queue.size();
				for(int i = 0;i < queueSize; i++) {
					TreeNode first = queue.poll();
					if(i + 1 == queueSize) {
						list.add(first.val);
					}
					if(first.left != null) {
						queue.add(first.left);
					}
					if(first.right != null) {
						queue.add(first.right);
					}
				}
			}
		}
		return list;
	}
	
	public List<Integer> topView(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		if(root != null) {
			Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
			Queue<TreeNode> queue = new LinkedList<TreeNode>();
			Map<Integer, TreeNode> visited = new HashMap<Integer, TreeNode>();
			int minDistance = 0;
			queue.add(root);
			map.put(root, 0);
			
			while(!queue.isEmpty()) {
				int queueSize = queue.size();
				for(int i = 0;i < queueSize; i++) {
					TreeNode peek = queue.poll();
					int distance = map.get(peek);
					if(!visited.containsKey(distance)) {
						visited.put(distance, peek);
					}
					if(peek.left != null) {
						queue.add(peek.left);
						map.put(peek.left, distance - 1);
						minDistance = Math.min(minDistance, distance - 1);
						
					}
					if(peek.right != null) {
						queue.add(peek.right);
						map.put(peek.right, distance + 1);	
					}
				}
			}
			
			while(visited.containsKey(minDistance)) {
				list.add(visited.get(minDistance++).val);
			}
		}
		return list;
	}
	
	public List<List<Integer>> zigZagLevelOrder(TreeNode root) {
		 List<List<Integer>> levelOrder = new ArrayList<List<Integer>>();
			if(root != null) {
				Queue<TreeNode> queue = new LinkedList<TreeNode>();
				queue.add(root);
				boolean leftToRight = true;
				while(!queue.isEmpty()) {
					int queueSize = queue.size();
					List<Integer> data = new ArrayList<Integer>();
					for(int i = 0;i < queueSize; i++) {
						TreeNode peek = queue.poll();
						data.add(peek.val);
						if(peek.left != null) {
							queue.add(peek.left);
						}
						if(peek.right != null) {
							queue.add(peek.right);
						}
					}	
					
					leftToRight = !leftToRight;
					if(!leftToRight) {
						int i = 0, j = data.size() - 1;
						while(i < j) {
							int temp = data.get(i);
							data.set(i, data.get(j));
							data.set(j, temp);
							i++;j--;
						}
					}
					levelOrder.add(data);
				}	
			}
			return levelOrder;
	}
	
	public HeightBalancedPair isBalancedSolve(TreeNode root) {
		if(root == null) {
			return new HeightBalancedPair(0, true);
		}
		if(root.left == null && root.right == null) {
			return new HeightBalancedPair(1, true);
		}
		
		HeightBalancedPair heightBalancedPairLeft = isBalancedSolve(root.left);
		HeightBalancedPair heightBalancedPairRight = isBalancedSolve(root.right);
		
		int height = 1 + Math.max(heightBalancedPairLeft.height, heightBalancedPairRight.height);
		boolean isBalanced = heightBalancedPairLeft.isBalanced 
				&& heightBalancedPairRight.isBalanced 
				&& (Math.abs(heightBalancedPairLeft.height - heightBalancedPairRight.height) <= 1); 
		
		return new HeightBalancedPair(height, isBalanced);
	}
	
	public int isBalancedSolve2(TreeNode root) {
		if(root == null) {
			return 0;
		}
		if(root.left == null && root.right == null) {
			return 1;
		}
		int leftHeight = isBalancedSolve2(root.left);
		int rightHeight = isBalancedSolve2(root.right);
		
		isBalanced = isBalanced && Math.abs(rightHeight - leftHeight) <= 1;
		
		return 1 + Math.max(leftHeight, rightHeight);
	}
	
	public ArrayList<Integer> diagonalTraversalDriver(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		diagonalTraversalSolve(map, root, 0);
		int distance = 0;
		while(map.containsKey(distance)) {
			list.addAll(map.get(distance++));
		}
		return list;
	}
	
	public void diagonalTraversalSolve(Map<Integer, List<Integer>> map, TreeNode root, int d) {
		if(root == null) {
			return;
		}
		List<Integer> list = map.get(d);
		if(list == null) {
			list = new ArrayList<Integer>();
			map.put(d, list);
		}
		list.add(root.val);
		diagonalTraversalSolve(map, root.left, d + 1);
		diagonalTraversalSolve(map, root.right, d);
	}
	
	public List<Integer> boundaryOfBinaryTreeSolve(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		if(root != null) {
			list.add(root.val);
			addLeftBoundary(list, root.left);
			addBottomBoundary(list, root.left);
			addBottomBoundary(list, root.right);
			addRightBoundary(list, root.right);	
		}
		return list;
    }
	
	
	
	public void addBottomBoundary(List<Integer> list, TreeNode root) {
		if(root == null) {
			return;
		}
		if(root.left == null && root.right == null) {
			list.add(root.val);
			return;
		}
		addBottomBoundary(list, root.left);
		addBottomBoundary(list, root.right);
	}
	
	public void addLeftBoundary(List<Integer> list, TreeNode root) {
		if(root == null) {
			return;
		}
		
		if(root.left != null) {
			list.add(root.val);
			addLeftBoundary(list, root.left);
		} else if(root.right != null) {
			list.add(root.val);
			addLeftBoundary(list, root.right);
		}
	}
	
	public void addRightBoundary(List<Integer> list, TreeNode root) {
		if(root == null) {
			return;
		}
		
		if(root.right != null) {
			addRightBoundary(list, root.right);
			list.add(root.val);
		} else if(root.left != null) {
			addRightBoundary(list, root.left);
			list.add(root.val);
		}
	}
	
	public void convertBinaryTreeToDLLDriver(TreeNode root) {
		prev = null;
		convertBinaryTreeToDLLDriverSolve(root);
	}
	
	public void convertBinaryTreeToDLLDriverSolve(TreeNode root) {
		if(root == null) {
			return;
		}
		convertBinaryTreeToDLLDriverSolve(root.left);
		if(prev == null) {
			head = root;
		} else {
			prev.right = root;
			root.left = prev;
		}
		prev = root;
		convertBinaryTreeToDLLDriverSolve(root.right);
	}
	
	public TreeNode constructBinaryTreeFromPreorderAndInorderTraversalDriver(int[] preorder, int[] inorder) {
		preOrderIndex = 0;
		Map<Integer, Integer> map = getInorderMap(inorder);
		return constructBinaryTreeFromPreorderAndInorderTraversal(map, inorder, preorder, 0, inorder.length - 1);
	}
	
	public Map<Integer, Integer> getInorderMap(int[] inorder) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0;i < inorder.length; i++) {
			map.put(inorder[i], i);
		}	
		return map;
	}
	
	public TreeNode constructBinaryTreeFromPreorderAndInorderTraversal(Map<Integer, Integer> map, int[] inorder, int[] preorder, int inorderStartingIndex, int inorderEndingIndex) {
		if(inorderEndingIndex < inorderStartingIndex) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[preOrderIndex++]);
		int rootIndex = map.get(root.val);
		root.left = constructBinaryTreeFromPreorderAndInorderTraversal(map, inorder, preorder, inorderStartingIndex, rootIndex - 1);
		root.right = constructBinaryTreeFromPreorderAndInorderTraversal(map, inorder, preorder, rootIndex + 1, inorderEndingIndex);
		return root;
	}
	
	public static int getIndex(int[] inorder, int element, int beginIndex, int endIndex) {
		while(beginIndex <= endIndex) {
			if(inorder[beginIndex] == element) {
				break;
			}
			beginIndex++;
		}
		return beginIndex;
	}
	
	public int isDuplicateSubtreeExistsDriver(TreeNode root) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<TreeNode> list = new ArrayList<TreeNode>();
		isDuplicateSubtreeExistsSolve(list, map, root);
		int solution = 0;
		for(String key : map.keySet()) {
			if(map.get(key) > 1) {
				solution = 1;
				break;
			}
		}
		return solution;
	}
	
	public String isDuplicateSubtreeExistsSolve(List<TreeNode> list, Map<String, Integer> map, TreeNode root) {
		if(root == null) {
			return "$";
		}
		if(root.left == null && root.right == null) {
			addToMap2(list, root.val + "$", map, root);
			return root.val + "$";
		}
		String s = root.val + "$" + isDuplicateSubtreeExistsSolve(list, map, root.left) + isDuplicateSubtreeExistsSolve(list, map, root.right);
		addToMap2(list, s, map, root);
		return s;
	}
	
	public static void addToMap2(List<TreeNode> list, String s, Map<String, Integer> map, TreeNode root) {
		if(map.containsKey(s)) {
			map.put(s, 1 + map.get(s));
		} else {
			map.put(s, 1);
		}
		if(map.get(s) == 2) {
			list.add(root);
		}
	}
	
	public List<TreeNode> getPathFromRoot(TreeNode root, TreeNode p) {
		List<TreeNode> path = new ArrayList<TreeNode>();
		getPathFromRootSolve(path, root, p);
		return path;
	}
	
	public boolean getPathFromRootSolve(List<TreeNode> path, TreeNode root, TreeNode p) {
		if(root == null) {
			return false;
		}
		path.add(root);
		if(root.val == p.val) {
			return true;
		}
		if(getPathFromRootSolve(path, root.left, p) || getPathFromRootSolve(path, root.right, p)) {
			return true;
		}
		path.remove(path.size() - 1);
		return false;
	}
	
	public TreeNode getLCA(TreeNode root, TreeNode p, TreeNode q) {
		List<TreeNode> pathP = getPathFromRoot(root, p);
		List<TreeNode> pathQ = getPathFromRoot(root, q);
		
		int i = 0, j = 0;
		
		TreeNode lca = new TreeNode();
		
		while(i < pathP.size() && j < pathQ.size() && pathP.get(i) == pathQ.get(j)) {
			lca = pathP.get(i);
			i++;
			j++;
		}
		
		return lca;
	}
	
	public TreeNode getLCAOptimized(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null) {
			return null;
		}
		if(root.val == p.val || root.val == q.val) {
			return root;
		}
		TreeNode lca1 = getLCAOptimized(root.left, p, q);
		TreeNode lca2 = getLCAOptimized(root.right, p, q);
		if(lca1 != null && lca2 != null) {
			return root;
		}
		if(lca1 != null) {
			return lca1;
		}
		return lca2;
	}
		
	public int findDistance(TreeNode root, int p, int q) {
		TreeNode lca = getLCA2(root, p, q);
        return getDistanceFromNode(lca, p) + getDistanceFromNode(lca, q);
    }
	
	public int getDistanceFromNode(TreeNode node, int n) {
		if(node == null) {
			return -1;
		}
		if(node.val == n) {
			return 0;
		}
		int left = getDistanceFromNode(node.left, n);
		int right = getDistanceFromNode(node.right, n);
		
		if(left != -1) {
			return ++left;
		}
		
		if(right != -1) {
			return ++right;
		}
		
		return -1;
	}
	
	public TreeNode getLCA2(TreeNode root, int p, int q) {
		if(root == null) {
			return null;
		}
		if(root.val == p || root.val == q) {
			return root;
		}
		TreeNode lca1 =  getLCA2(root.left, p, q);
		TreeNode lca2 =  getLCA2(root.right, p, q);
		
		if(lca1 != null && lca2 != null) {
			return root;
		}
		if(lca1 != null) {
			return lca1;
		}
		return lca2;
	}
	
	public TreeNode searchBST(TreeNode root, int val) {
		if(root == null) {
			return null;
		}
		int rootData = root.val;
		if(rootData == val) {
			return root;
		} else if(rootData < val) {
			return searchBST(root.right, val);
		} else {
			return searchBST(root.left, val);	
		}
    }
	
	public TreeNode deleteNode(TreeNode root, int val) {
		if(root == null) {
			return null;
		}
		if(root.val < val) {
			root.right = deleteNode(root.right, val);
		} else if(root.val > val) {
			root.left = deleteNode(root.left, val);	
		} else {
			if(root.left == null) {
				return root.right;
			} else if(root.right == null) {
				return root.left;
			} else {
				root.val = getMinimumValueNodeOnLeft(root.right).val;
				root.right = deleteNode(root.right, root.val);
			}
		}
		return root;
    }

	public TreeNode getMinimumValueNodeOnLeft(TreeNode root) {
		if(root == null) {
			return null;
		}
		if(root.left == null) {
			return root;
		}
		return getMinimumValueNodeOnLeft(root.left);
	}
	
    public TreeNode inorderSuccessorSolve(TreeNode root, TreeNode p) {
    	if(p == null) {
    		return null;
    	}
    	if(p.right != null) {
    		return getMinimumValueNodeOnLeft(p.right);
    	} else {
    		TreeNode ancestor = root, successor = null;
    		while(ancestor != null && ancestor.val != p.val) {
    			if(ancestor.val > p.val) {
    				successor = ancestor;
    				ancestor = ancestor.left;
    			} else {
    				ancestor = ancestor.right;
    			}
    		}
    		return successor;
    	}
    }

    public boolean isValidBST(TreeNode root) {
    	return isValidBSTSolve(root, null, null);
    }
    
    public boolean isValidBSTSolve(TreeNode root, Integer min, Integer max) {
    	if(root == null) {
    		return true;
    	}
    	
    	if((min != null && root.val <= min) || (max != null && root.val >= max)) {
    		return false;
    	}
    	return isValidBSTSolve(root.left, min, root.val) && isValidBSTSolve(root.right, root.val, max);
    }
    
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
    	if(root == null) {
    		return null;
    	}
    	
    	if(root.val < p.val && root.val < q.val) {
    		return lowestCommonAncestorBST(root.right, p, q);
    	} else if(root.val > p.val && root.val > q.val) {
    		return lowestCommonAncestorBST(root.left, p, q);
    	} else {
    		return root;
    	}
    }
    
    public TreeNode bstFromPreorder(int[] preorder) {
    	preOrderIndex = 0;
    	return bstFromPreorderSolve(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public TreeNode bstFromPreorderSolve(int[] preorder, int lowerLimit, int higherLimit) {
    	if(preOrderIndex == preorder.length) {
    		return null;
    	}
    	if(preorder[preOrderIndex] > higherLimit || preorder[preOrderIndex] < lowerLimit) {
    		return null;
    	}
    	TreeNode root = new TreeNode(preorder[preOrderIndex++]);
    	root.left = bstFromPreorderSolve(preorder, lowerLimit, root.val);
    	root.right = bstFromPreorderSolve(preorder, root.val, higherLimit);
    	return root;
    }
    
    public int kthSmallest(TreeNode root, int k) {
    	List<Integer> list = new ArrayList<Integer>();
    	kthSmallestSolve(root, list, k);
    	
    	return list.get(k - 1);
    }
    
    public int kthSmallest2(TreeNode root, int k) {
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	int e = -1;
    	while(k != 0) {
    		while(root != null) {
    			stack.push(root);
    			root = root.left;
    		}
    		if(stack.isEmpty()) {
    			break;
    		}
    		root = stack.pop();
    		if(--k == 0) {
    			e = root.val;
    			break;
    		}
    		root = root.right;
    	}
    	return e;
    }
    
    public void kthSmallestSolve(TreeNode root, List<Integer> list, int k) {
    	if(root == null) {
    		return;
    	}
    	if(list.size() == k) {
    		return;
    	}
    	kthSmallestSolve(root.left, list, k);
    	list.add(root.val);
    	kthSmallestSolve(root.right, list, k);
    }
    
    public int largestBst(TreeNode root) {
    	return -1;
    }
    
    public SizeIsBSTPair getSizeIsBSTPair(TreeNode root) {
    	if(root == null) {
    		return new SizeIsBSTPair(0, true, Integer.MAX_VALUE, Integer.MIN_VALUE);
    	}
    	
    	if(root.left == null && root.right == null) {
    		return new SizeIsBSTPair(1, true, root.val, root.val);
    	}
    	
    	SizeIsBSTPair left = getSizeIsBSTPair(root.left);
    	SizeIsBSTPair right = getSizeIsBSTPair(root.right);
    	
    	boolean isBST = left.isBst && right.isBst && root.val > left.max && root.val < right.min;
    	int size = (isBST) ? 1 + left.size + right.size : Math.max(left.size, right.size);
    	
    	int min = Math.min(left.min, root.val);
    	int max = Math.max(right.max, root.val);
    	
    	return new SizeIsBSTPair(size, isBST, min, max);
    }
    
	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree();
		TreeNode root = new TreeNode(10);
//
		root.left = new TreeNode(20);
		root.right = new TreeNode(16);
//
		root.left.left = new TreeNode(10);
		root.left.right = new TreeNode(50);
		root.left.left.left = new TreeNode(4);
		
		System.out.println(binaryTree.getSizeIsBSTPair(root).size);
		
//		root.right.left = new TreeNode(6);
//		root.left.right.left = new TreeNode(7);
//		root.left.right.right = new TreeNode(8);
//		root.right.left.left = new TreeNode(9);
//		root.right.left.right = new TreeNode(10);
//		
//		binaryTree.boundaryOfBinaryTreeSolve(root);
		
//		int[] preorder = {8,5,1,7,10,12};
//		TreeNode root = binaryTree.bstFromPreorder(preorder);
//		binaryTree.levelOrderPrint(root);
	}

}
