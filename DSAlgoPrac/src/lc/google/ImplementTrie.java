package lc.google;

public class ImplementTrie {

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("abd");
//		trie.insert("ab");
	}

}



class TrieNode {
	TrieNode[] array;
	boolean endsWord;
	
	public TrieNode() {
		this(new TrieNode[26], false);
	}
	
	public TrieNode(TrieNode[] array, boolean endsWord) {
		super();
		this.array = array;
		this.endsWord = endsWord;
	}
	
	public boolean containsKey(char ch) {
		return array[ch - 'a'] != null;
	}
	
	public void put(char ch) {
		this.array[ch - 'a'] = new TrieNode();
	}
	
	public TrieNode get(char ch) {
		return this.array[ch - 'a'];
	}
	
}

class Trie {
	
	private TrieNode root;
	
    public Trie() {
    	root = new TrieNode();
    }
    
    
    public void insert(String word) {
        TrieNode node = root;
    	for(int i = 0;i < word.length(); i++) {
        	char ch = word.charAt(i);
        	if(!node.containsKey(ch)) {
        		node.array[ch - 'a'] = new TrieNode();
        	}
        	node = node.array[ch - 'a'];
    	}
    	node.endsWord = true;
    }
    
    public boolean search(String word) {
    	TrieNode node = root;
    	for(int i = 0;i < word.length(); i++) {
    		char ch = word.charAt(i);
    		if(!node.containsKey(ch)) {
    			return false;
    		} else {
    			node = node.array[ch - 'a'];
    		}
    	}
        return node.endsWord;
    }
    
    public boolean startsWith(String prefix) {
    	TrieNode node = root;
    	for(int i = 0;i < prefix.length(); i++) {
    		char ch = prefix.charAt(i);
    		if(!node.containsKey(ch)) {
    			return false;
    		} else {
    			node = node.array[ch - 'a'];
    		}
    	}
        return true;
    }
}
