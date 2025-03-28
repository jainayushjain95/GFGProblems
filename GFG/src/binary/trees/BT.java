package binary.trees;

import com.sun.source.tree.Tree;

import java.util.*;
class PairSRT {
    int level;
    int sum;

    public PairSRT(int level, int sum) {
        this.level = level;
        this.sum = sum;
    }
}


class Pair {
    int diameter;
    int depth;

    public Pair(int diameter, int depth) {
        this.diameter = diameter;
        this.depth = depth;
    }
}

class Result {
    TreeNode LCA;
    int depth;
    int distance;

    public Result(TreeNode LCA, int depth, int distance) {
        this.LCA = LCA;
        this.depth = depth;
        this.distance = distance;
    }
}
class Triplet {
    int col;
    int row;
    int val;
    public Triplet(int col, int row, int val) {
        this.col = col;
        this.row = row;
        this.val = val;
    }
}

public class BT {
    Map<TreeNode, Integer> dpMap;
    List<Integer> output;
    List<Triplet> triplets = new ArrayList<>();
    int minCol = 0;
    boolean pfound;
    boolean qfound;
    static int count = 0;
    ListNode head1;

    int[] nodeLevels;
    int[] nodeHeight;
    int[][] top2HeightsAtLevel;

    Map<Integer, List<Integer>> data;
    Map<String, Boolean> mapFDT;
    List<TreeNode> solutionFDT;


    public static void main(String[] args) {
        Integer[] nodes = {6,2,13,1,4,9,15,null,null,null,null,null,null,14};
        BT bt = new BT();
        TreeNode root = bt.construct(nodes);
        List<Integer> queries = new ArrayList<>();
        queries.add(2);
        queries.add(5);
        bt.closestNodes(root, queries);
    }

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        return closestNodes1(root, queries);
    }

    public List<List<Integer>> closestNodes1(TreeNode root, List<Integer> queries) {
        List<List<Integer>> output = new ArrayList<>();
        for(int query : queries) {
            int[] smallerOutput = new int[2];
            smallerOutput[0] = Integer.MIN_VALUE;
            smallerOutput[1] = Integer.MAX_VALUE;
            solve(root, smallerOutput, query);
            if(smallerOutput[0] == Integer.MIN_VALUE) {
                smallerOutput[0] = -1;
            }
            if(smallerOutput[1] == Integer.MAX_VALUE) {
                smallerOutput[1] = -1;
            }
            List<Integer> temp = new ArrayList<>();
            temp.add(smallerOutput[0]);
            temp.add(smallerOutput[1]);
            output.add(temp);
        }
        return output;
    }

    private void solve(TreeNode root, int[] output, int val) {
        if(root == null) {
            return;
        }
        if(root.val == val) {
            output[0] = val;
            output[1] = val;
            return;
        }
        if(root.val < val) {
            if(output[0] < root.val) {
                output[0] = root.val;
            }
        }
        if(root.val > val) {
            if(output[1] < root.val) {
                output[1] = root.val;
            }
        }
        solve(root.left, output, val);
        solve(root.right, output, val);
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) {
            return null;
        }
        if(root.val == key) {
            if(root.left == null && root.right == null) {
                return null;
            }
            if(root.left == null) {
                return root.right;
            }
            if(root.right == null) {
                return root.left;
            }
            TreeNode smallest = getSmallest(root.right);
            if(smallest == null) {
                smallest = root.right;
            }
            swap(root, smallest);
            root.right = deleteNode(root.right, key);
        } else if(root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }

        return root;
    }

    private void swap(TreeNode root, TreeNode smallest) {
        int temp = root.val;
        root.val = smallest.val;
        smallest.val = temp;
    }

    private TreeNode getSmallest(TreeNode root) {
        if(root == null) {
            return null;
        }
        if(root.left == null) {
            return root;
        }
        return getSmallest(root.left);
    }

    public TreeNode reverseOddLevels(TreeNode root) {
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int queueSize = queue.size();
            if(level % 2 == 0) {
                List<TreeNode> list = new ArrayList<>();
                while(queueSize > 0) {
                    TreeNode node = queue.poll();
                    if(node.left == null && node.right == null) {
                        break;
                    }
                    queue.add(node.left);
                    queue.add(node.right);
                    list.add(node.left);
                    list.add(node.right);
                    queueSize--;
                }
                for(int i = 0;i < list.size()/2; i++) {
                    TreeNode left = list.get(i);
                    TreeNode right = list.get(list.size() - i - 1);
                    int temp = left.val;
                    left.val = right.val;
                    right.val = temp;
                }
            } else {
                while(queueSize > 0) {
                    TreeNode node = queue.poll();
                    if(node.left == null && node.right == null) {
                        break;
                    }
                    queue.add(node.left);
                    queue.add(node.right);
                    queueSize--;
                }
            }
            level++;
        }

        return root;
    }

    public TreeNode str2tree(String s) {
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i < s.length();) {
            if(s.charAt(i) == '(') {
                i++;
                continue;
            }

            if(s.charAt(i) == ')') {
                TreeNode node = stack.pop();
                if(stack.peek().left == null) {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
                i++;
            } else {
                int sign = 1;
                if(s.charAt(i) == '-') {
                    sign = -1;
                    i++;
                }
                StringBuilder value = new StringBuilder();
                while(i < s.length() && s.charAt(i) != ')' && s.charAt(i) != '(') {
                    value.append(s.charAt(i++));
                }

                stack.push(new TreeNode(sign * Integer.parseInt(value.toString())));
            }
        }
        return stack.pop();
    }


    public String tree2str(TreeNode root) {
        if(root == null) {
            return "";
        }

        if(root.left == null && root.right == null) {
            return root.val + "";
        }

        if(root.right == null) {
            return root.val + "(" + tree2str(root.left) + ")";
        }

        return root.val + "(" + tree2str(root.left) + ")" + "(" + tree2str(root.right) + ")";
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        mapFDT = new HashMap<>();
        solutionFDT = new ArrayList<>();
        findDuplicateSubtreesSolve(root);
        return solutionFDT;
    }

    private String findDuplicateSubtreesSolve(TreeNode root) {
        if(root == null) {
            return "#";
        }
        String struct = "";
        if(root.left == null && root.right == null) {
            struct = root.val + "##";
        } else {
            String left = findDuplicateSubtreesSolve(root.left);
            String right = findDuplicateSubtreesSolve(root.right);
            struct = root.val + "-" + left + right;
        }

        if(!mapFDT.containsKey(struct)) {
            mapFDT.put(struct, false);
        } else if(!mapFDT.get(struct)) {
            mapFDT.put(struct, true);
            solutionFDT.add(root);
        }

        return struct;
    }

    public int minimumOperations(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int swaps = 0;
        while(!queue.isEmpty()) {
            int queueSize = queue.size();
            List<Integer> list = new ArrayList<>();
            while(queueSize > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
                queueSize--;
            }
            if(list.size() > 1) {
                swaps += countNoOfSwapsNeeded(list);
            }
        }
        return swaps;
    }

    private int countNoOfSwapsNeeded(List<Integer> list) {
        return countNoOfSwapsNeededUsingSorting(list);
    }

    private int countNoOfSwapsNeededUsingSorting(List<Integer> list) {
        int swaps = 0;
        Map<Integer, Integer> map = getMap(list);
        List<Integer> sortedList = cloneAndSort(list);

        for(int i = 0;i < list.size(); i++) {
            int a = list.get(i), b = sortedList.get(i);
            if(a != b) {
                int index = map.get(b);
                int temp = list.get(index);
                list.set(index, a);
                list.set(i, temp);
                map.put(b, i);
                map.put(a, index);
                swaps++;
            }
        }

        return swaps;
    }

    private List<Integer> cloneAndSort(List<Integer> list) {
        List<Integer> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        return sortedList;
    }

    private Map<Integer, Integer> getMap(List<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0;i < list.size(); i++) {
            map.put(list.get(i), i);
        }
        return map;
    }

    public int sumNumbers(TreeNode root) {
        List<PairSRT> pairs = sumNumbersSolve(root);
        int sum = 0;
        for(PairSRT pair : pairs) {
            sum += pair.sum;
        }
        return sum;
    }

    public List<PairSRT> sumNumbersSolve(TreeNode root) {
        if(root == null) {
            List<PairSRT> pairs = new ArrayList<>();
            return pairs;
        }

        if(root.left == null && root.right == null) {
            List<PairSRT> pairs = new ArrayList<>();
            pairs.add(new PairSRT(root.val, 0));
            return pairs;
        }

        List<PairSRT> pairsLeft = sumNumbersSolve(root.left);
        List<PairSRT> pairsRight = sumNumbersSolve(root.right);

        List<PairSRT> pairs = new ArrayList<>();

        for(PairSRT pair : pairsLeft) {
            pairs.add(new PairSRT(1 + pair.level, pair.sum + root.val * (int)Math.pow(10, 1 + pair.level)));
        }

        for(PairSRT pair : pairsRight) {
            pairs.add(new PairSRT(1 + pair.level, pair.sum + root.val * (int)Math.pow(10, 1 + pair.level)));
        }

        return pairs;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        if(root != null) {
            data = new HashMap<>();
            int rootHeight = prepareData(root);
            for(int i = 0;i <= rootHeight; i++) {
                if(data.containsKey(i)) {
                    output.add(data.get(i));
                }
            }
        }
        return output;
    }

    private int prepareData(TreeNode root) {
        if(root == null) {
            return -1;
        }

        int curr = 0;

        if(root.left != null && root.right != null) {
            int left = prepareData(root.left);
            int right = prepareData(root.right);
            curr = 1 + Math.max(left, right);
        }

        if(!data.containsKey(curr)) {
            data.put(curr, new ArrayList<>());
        }

        data.get(curr).add(root.val);

        return curr;
    }

    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if(node == null) {
                    stringBuilder.append("#");
                    stringBuilder.append(",");
                } else {
                    stringBuilder.append(node.val);
                    stringBuilder.append(",");

                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }
        return stringBuilder.toString();
    }

    public TreeNode deserialize(String data) {
        TreeNode root = null;
        if(!data.isEmpty()) {
            int index = 0;
            String[] nodes = data.split(",");
            Queue<TreeNode> queue = new LinkedList<>();
            root = new TreeNode(Integer.parseInt(nodes[index++]));
            queue.add(root);
            while(!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if(!nodes[index].equals("#")) {
                    node.left = new TreeNode(Integer.parseInt(nodes[index]));
                    queue.add(node.left);
                }
                index++;
                if(!nodes[index].equals("#")) {
                    node.right = new TreeNode(Integer.parseInt(nodes[index]));
                    queue.add(node.right);
                }
                index++;
            }
        }

        return root;
    }

    public int rob(TreeNode root) {
        return robDP(root);
    }

    public int robDP(TreeNode root) {
        dpMap = new HashMap<>();
        return robRecursive(root);
    }


    public int robRecursive(TreeNode root) {
        if(root == null) {
            return 0;
        }

        if(dpMap.containsKey(root)) {
            System.out.println("Caught");
            return dpMap.get(root);
        }

        if(root.left == null && root.right == null) {
            return root.val;
        }


        int notPickedRoot = rob(root.left) + rob(root.right);
        int leftLeft = (root.left == null) ? 0 : rob(root.left.left);
        int leftRight = (root.left == null) ? 0 : rob(root.left.right);
        int rightLeft = (root.right == null) ? 0 : rob(root.right.left);
        int rightRight = (root.right == null) ? 0 : rob(root.right.right);

        int pickedRoot = root.val + leftLeft + leftRight + rightLeft + rightRight;
        dpMap.put(root, Math.max(notPickedRoot, pickedRoot));
        return dpMap.get(root);
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode lca = getLCA_getDirections(root, startValue, destValue);
        StringBuilder pathOne = new StringBuilder();
        StringBuilder pathTwo = new StringBuilder();

        boolean pathFromStartToLCA = getPathFromLCAToStartNode(lca, startValue, pathOne);
        boolean pathFromDestToLCA = getPathFromLCAToStartNode(lca, destValue, pathTwo);

        StringBuilder output = new StringBuilder();

        output.append("U".repeat(pathOne.length()));
        output.append(pathTwo);
        return output.toString();
    }

    private boolean getPathFromLCAToStartNode(TreeNode root, int targetValue, StringBuilder path) {
        if(root == null) {
            return false;
        }
        if(root.val == targetValue) {
            return true;
        }
        path.append("L");
        if(getPathFromLCAToStartNode(root.left, targetValue, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1);

        path.append("R");
        if(getPathFromLCAToStartNode(root.right, targetValue, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1);
        return false;
    }

    private TreeNode getLCA_getDirections(TreeNode root, int startValue, int destValue) {
        if(root == null) {
            return null;
        }
        if(root.val == startValue || root.val == destValue) {
            return root;
        }

        TreeNode leftLCA = getLCA_getDirections(root.left, startValue, destValue);
        TreeNode rightLCA = getLCA_getDirections(root.right, startValue, destValue);

        if(leftLCA != null && rightLCA != null) {
            return root;
        }

        if(leftLCA != null) {
            return leftLCA;
        }

        return rightLCA;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        return diameterOfBinaryTreeSolve(root).diameter;
    }

    public Pair diameterOfBinaryTreeSolve(TreeNode root) {
        if(root == null) {
            return new Pair(0, 0);
        }

        if(root.left == null && root.right == null) {
            return new Pair(0, 1);
        }

        Pair left = diameterOfBinaryTreeSolve(root.left);
        Pair right = diameterOfBinaryTreeSolve(root.left);

        int depth = 1 + Math.max(left.depth, right.depth);
        int diameter = Math.max(left.depth + right.depth, Math.max(left.diameter, right.diameter));
        return new Pair(diameter, depth);

    }

    public int[] treeQueries(TreeNode root, int[] queries) {
        int n = getNoOfNodes(root);
        nodeLevels = new int[n + 1];
        nodeHeight = new int[n + 1];
        top2HeightsAtLevel = new int[n][2];

        solve(root, 0);
        for(int i = 0; i < queries.length; i++) {
            int level = nodeLevels[queries[i]];
            int height = (top2HeightsAtLevel[level][0] == nodeHeight[queries[i]]) ? top2HeightsAtLevel[level][1] : top2HeightsAtLevel[level][0];
            queries[i] = height + level - 1;
        }
        return queries;
    }

    private int solve(TreeNode root, int level) {
        if(root == null) {
            return 0;
        }

        nodeLevels[root.val] = level;

        if(root.left == null && root.right == null) {
            nodeHeight[root.val] = 1;
        } else {
            int left = solve(root.left, level + 1);
            int right = solve(root.right, level + 1);
            nodeHeight[root.val] = 1 + Math.max(left, right);
        }

        if(nodeHeight[root.val] > top2HeightsAtLevel[level][0]) {
            top2HeightsAtLevel[level][1] = top2HeightsAtLevel[level][0];
            top2HeightsAtLevel[level][0] = nodeHeight[root.val];
        } else if(nodeHeight[root.val] > top2HeightsAtLevel[level][1]) {
            top2HeightsAtLevel[level][1] = nodeHeight[root.val];
        }

        return nodeHeight[root.val];
    }

    private int getNoOfNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if(root.left == null && root.right == null) {
            return 1;
        }
        return 1 + getNoOfNodes(root.left) + getNoOfNodes(root.right);
    }

    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int nextLevelSum = root.val;
        queue.add(root);
        while(!queue.isEmpty()) {
            int currentLevelSum = 0;
            int queueSize = queue.size();
            while(queueSize > 0) {
                int siblingsSum = 0;
                TreeNode node = queue.poll();
                node.val = nextLevelSum - node.val;
                if(node.left != null) {
                    currentLevelSum += node.left.val;
                    queue.add(node.left);
                    siblingsSum += node.left.val;
                }
                if(node.right != null) {
                    currentLevelSum += node.right.val;
                    queue.add(node.right);
                    siblingsSum += node.right.val;
                }
                if(node.left != null) {
                    node.left.val = siblingsSum;
                }
                if(node.right != null) {
                    node.right.val = siblingsSum;
                }
                queueSize--;
            }
            nextLevelSum = currentLevelSum;
        }
        return root;
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        output = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        if(root != null) {
            updateLeftBoundary(root);
            updateLeaves(root);
            updateRightBoundary(root);
            output.remove(output.size() - 1);
        }
        return output;
    }

    private void updateLeftBoundary(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) {
            return;
        }
        output.add(root.val);
        if(root.left != null) {
            updateLeftBoundary(root.left);
        } else {
            updateLeftBoundary(root.right);
        }
    }

    private void updateLeaves(TreeNode root) {
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null) {
            output.add(root.val);
        }
        updateLeaves(root.left);
        updateLeaves(root.right);
    }

    private void updateRightBoundary(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) {
            return;
        }

        if(root.right != null) {
            updateRightBoundary(root.right);
        } else {
            updateRightBoundary(root.left);
        }
        output.add(root.val);
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        head1 = new ListNode(4);
        head1.next = new ListNode(2);
        head = head1;
        return isSubPathSolve(head, root);
    }

    public boolean isSubPathSolve(ListNode head, TreeNode root) {
        if(head == null) {
            return true;
        }
        if(root == null) {
            return false;
        }
        if(root.val == head.val) {
            return isSubPath(head.next, root.left) || isSubPath(head.next, root.right);
        }
        return isSubPath(head1, root.left) || isSubPath(head1, root.right);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        prepareTriplets(root, 0, 0);
        List<Triplet> sortedList = triplets.stream()
                .sorted(Comparator.comparingInt((Triplet t) -> t.col)
                        .thenComparingInt(t -> t.row)
                        .thenComparingInt(t -> t.val))
                .toList();

        List<List<Integer>> output = new ArrayList<>();
        for(Triplet triplet : sortedList) {

        }
        return output;
    }

    private void prepareTriplets(TreeNode root, int col, int row) {
        if(root == null) {
            return;
        }
        triplets.add(new Triplet(col, row, root.val));
        prepareTriplets(root.left, col - 1, row + 1);
        prepareTriplets(root.right, col + 1, row + 1);
    }
    private TreeNode getStartNode(TreeNode root, int start) {
        if(root == null || root.val == start) {
            return root;
        }
        TreeNode left = getStartNode(root.left, start);
        if(left != null) {
            return left;
        }
        return getStartNode(root.right, start);
    }

    public Result findDistance2(TreeNode root, int p, int q, int depth) {
        if(root == null) {
            return new Result(root, -1, 0);
        }

        if(root.val == p || root.val == q) {
            return new Result(root, depth, 0);
        }

        Result left = findDistance2(root.left, p, q, depth + 1);
        Result right = findDistance2(root.right, p, q, depth + 1);

        if(left.LCA != null && right.LCA != null) {
            return new Result(root, depth, left.depth + right.depth - 2 * depth);
        }

        if(left.LCA != null) {
            return new Result(left.LCA, left.depth, left.distance);
        }

        if(right.LCA != null) {
            return new Result(right.LCA, right.depth, right.distance);
        }


        return new Result(null, -1, 0);
    }

    public int findDistance(TreeNode root, int p, int q) {
        int distance = 0;
        TreeNode lca = getMeLCA(root, p, q);
        if(lca.val == p) {
            distance = getMeDistance(lca, q) - 1;
        } else if(lca.val == q) {
            distance = getMeDistance(lca, p) - 1;
        } else {
            distance = getMeDistance(lca, p) + getMeDistance(lca, q) - 2;
        }
        return distance;
    }

    private TreeNode getMeLCA(TreeNode root, int p, int q) {
        if(root == null) {
            return null;
        }
        if(root.val == p || root.val == q) {
            return root;
        }

        TreeNode left = getMeLCA(root.left, p, q);
        TreeNode right = getMeLCA(root.right, p, q);

        if(left != null && right != null) {
            return root;
        }

        if(left != null) {
            return left;
        }
        return right;
    }

    private int getMeDistance(TreeNode root, int num) {
        if(root == null) {
            return 0;
        }

        if(root.val == num) {
            return 1;
        }

        int left = getMeDistance(root.left, num);
        int right = getMeDistance(root.right, num);

        if(left != 0) {
            return 1 + left;
        }

        if(right != 0) {
            return 1 + right;
        }

        return 0;
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        return distanceKNodes(root, target, k, getParents(root));
    }

    public List<Integer> distanceKNodes(TreeNode root, TreeNode target, int k, Map<TreeNode, TreeNode> parentsMap) {
        Map<TreeNode, TreeNode> map = getParents(root);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.add(target);
        visited.add(target);
        List<Integer> output = new ArrayList<>();
        while(!queue.isEmpty() && k > 0) {
            int queueSize = queue.size();
            while(queueSize > 0) {
                TreeNode node = queue.poll();
                TreeNode left = node.left;
                TreeNode right = node.right;
                if(left != null && !visited.contains(left)) {
                    queue.add(left);
                    visited.add(left);
                }
                if(right != null && !visited.contains(right)) {
                    queue.add(right);
                    visited.add(right);
                }
                if(parentsMap.get(node) != null && !visited.contains(parentsMap.get(node))) {
                    queue.add(parentsMap.get(node));
                    visited.add(parentsMap.get(node));
                }
                queueSize--;
            }
            k--;
        }
        while(!queue.isEmpty()) {
            output.add(queue.poll().val);
        }
        return output;
    }

    private Map<TreeNode, TreeNode> getParents(TreeNode root) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int queueSize = queue.size();
            while(queueSize > 0) {
                TreeNode node = queue.poll();
                TreeNode left = node.left;
                TreeNode right = node.right;
                if(left != null) {
                    queue.add(left);
                    map.put(left, node);
                }
                if(right != null) {
                    queue.add(right);
                    map.put(right, node);
                }
                queueSize--;
            }
        }
        return map;
    }

    public int countHighestScoreNodes(int[] parents) {
        TreeNode[] nodes = getNodes(parents);
        prepareTree(parents, nodes);
        computeSizes(nodes[0]);
        return noOfNodesWithMaxScore(nodes, parents);
    }

    private int noOfNodesWithMaxScore(TreeNode[] nodes, int[] parents) {
        long maxScore = 0;
        int noOfNodesWithMaxScore = 0;
        for(int i = 0;i < parents.length; i++) {
            long leftSize = (nodes[i].left == null) ? 0 : nodes[i].left.size;
            long rightSize = (nodes[i].right == null) ? 0 : nodes[i].right.size;
            long parentSize = nodes[0].size - leftSize - rightSize - 1;

            long score = 1;
            if (leftSize > 0) score *= leftSize;
            if (rightSize > 0) score *= rightSize;
            if (parentSize > 0) score *= parentSize;
            if (score > maxScore) {
                maxScore = score;
                noOfNodesWithMaxScore = 1;
            } else if (score == maxScore) {
                noOfNodesWithMaxScore++;
            }
        }
        return noOfNodesWithMaxScore;
    }

    private int computeSizes(TreeNode root) {
        if(root == null) {
            return 0;
        }

        if(root.left == null && root.right == null) {
            root.size = 1;
            return 1;
        }

        int leftSize = computeSizes(root.left);
        int rightSize = computeSizes(root.right);
        root.size = 1 + leftSize + rightSize;
        return root.size;
    }

    private void prepareTree(int[] parents, TreeNode[] nodes) {
        for(int i = 1;i < parents.length; i++) {
            if(nodes[parents[i]].left == null) {
                nodes[parents[i]].left = nodes[i];
            } else {
                nodes[parents[i]].right = nodes[i];
            }

        }
    }
    private TreeNode[] getNodes(int[] parents) {
        TreeNode[] nodes = new TreeNode[parents.length];
        for(int i = 0; i < parents.length; i++) {
            nodes[i] = new TreeNode(i);
        }
        return nodes;
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = getSet(to_delete);
        List<TreeNode> output = new ArrayList<>();
        if (!set.contains(root.val)) {
            output.add(root);
        }
        delNodesSolve(output, root, set);
        return output;
    }

    public TreeNode delNodesSolve(List<TreeNode> output, TreeNode root, Set<Integer> set) {
        if(root == null) {
            return null;
        }

        root.left = delNodesSolve(output, root.left, set);
        root.right = delNodesSolve(output, root.right, set);

        if(set.contains(root.val)) {
            if(root.left != null) {
                output.add(root.left);
            }
            if(root.right != null) {
                output.add(root.right);
            }
            return null;
        }
        return root;
    }


    private Set<Integer> getSet(int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for(int i : to_delete) {
            set.add(i);
        }
        return set;
    }

    public Map<Integer, Integer> countPairsSolve2(TreeNode root, int distance) {
        if(root == null) {
            return new HashMap<>();
        }

        if(root.left == null && root.right == null) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(1, 1);
            return map;
        }

        Map<Integer, Integer> left = countPairsSolve2(root.left, distance);
        Map<Integer, Integer> right = countPairsSolve2(root.right, distance);
        Map<Integer, Integer> output = new HashMap<>();

        for(int x : right.keySet()) {
            output.put(x + 1, right.get(x) + output.getOrDefault(x + 1, 0));
        }

        for(int x : left.keySet()) {
            output.put(x + 1, left.get(x) + output.getOrDefault(x + 1, 0));
        }

        for(int x : left.keySet()) {
            for(int y : right.keySet()) {
                if(x + y <= distance) {
                    count += left.get(x) * right.get(y);
                }
            }
        }

        return output;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        pfound = false;
        qfound = false;
        TreeNode lca = lowestCommonAncestorSolve(root, p, q);
        return (pfound && qfound) ? lca : null;
    }

    public TreeNode lowestCommonAncestorSolve(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        }
        if(root.val == p.val) {
            pfound = true;
            return root;
        }

        if(root.val == q.val) {
            qfound = true;
            return root;
        }


        TreeNode leftLCA = lowestCommonAncestorSolve(root.left, p, q);
        TreeNode rightLCA = lowestCommonAncestorSolve(root.right, p, q);

        if (leftLCA != null && rightLCA != null) {
            return root;
        }

        return (leftLCA != null) ? leftLCA : rightLCA;
    }

//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        Triplet triplet = lowestCommonAncestorSolve(root, p, q);
//        return triplet.LCA;
//    }
//
//    public Triplet lowestCommonAncestorSolve(TreeNode root, TreeNode p, TreeNode q) {
//        if(root == null) {
//            return new Triplet(null, false, false);
//        }
//
//        if(root.val == p.val) {
//            return new Triplet(root, true, false);
//        }
//
//        if(root.val == q.val) {
//            return new Triplet(root, false, true);
//        }
//
//        Triplet leftLCATriplet = lowestCommonAncestorSolve(root.left, p, q);
//        Triplet rightLCATriplet = lowestCommonAncestorSolve(root.right, p, q);
//        if(leftLCATriplet.LCA != null && rightLCATriplet.LCA  != null) {
//            return new Triplet(root,
//                    leftLCATriplet.pfound || rightLCATriplet.pfound,
//                    leftLCATriplet.qfound || rightLCATriplet.qfound
//            );
//        }
//        if(leftLCATriplet.LCA != null && leftLCATriplet.pfound && leftLCATriplet.qfound) {
//            return new Triplet(leftLCATriplet.LCA,
//                    leftLCATriplet.pfound,
//                    leftLCATriplet.qfound
//            );
//        }
//
//        if(rightLCATriplet.LCA != null && rightLCATriplet.pfound && rightLCATriplet.qfound) {
//            return new Triplet(rightLCATriplet.LCA,
//                    rightLCATriplet.pfound,
//                    rightLCATriplet.qfound
//            );
//        }
//
//        return new Triplet(null, false, false);
//    }

    public void inorder(TreeNode root) {
        if(root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }
    public TreeNode construct(Integer[] nodes) {
        if(nodes.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int index = 1;
        while(index < nodes.length && !queue.isEmpty()) {
            int size = queue.size();
            while(index < nodes.length && size > 0) {
                TreeNode node = queue.poll();
                if(node == null) {
                    index++;
                } else {
                    if(nodes[index] != null) {
                        node.left = new TreeNode(nodes[index]);
                        queue.add(node.left);
                    }
                    index++;
                    if(index < nodes.length) {
                        if(nodes[index] != null) {
                            node.right = new TreeNode(nodes[index]);
                            queue.add(node.right);
                        }
                        index++;
                    }
                }
                size--;
            }
        }
        return root;
    }
}
