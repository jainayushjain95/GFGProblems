package binary.trees;

public class Node {

	int data;
	Node left;
	Node right;
	
	Node() {}
	
	Node(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}
	
	
	
}
