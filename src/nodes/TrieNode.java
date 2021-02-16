package nodes;

import java.util.*;

public class TrieNode {
	public boolean isWord;
	private String text;
	public HashMap<Character, TrieNode> children;
	
	public TrieNode() {
		children = new HashMap<>();
		isWord = false;
		text = "";
	}
	public TrieNode(String text) {
		this();
		this.text = text;
	}
	

	public TrieNode getChild(Character c) {
		return children.get(c);
	}
	
	public TrieNode insert(Character c) {
		if (children.containsKey(c)) {
			return null;
		}
		TrieNode next = new TrieNode(text + c.toString());
		children.put(c, next);
		return next;
	}
	
	public String getText() { return text; }
	
	public void setEndOfWord(boolean b) { this.isWord = b; }
	
	public boolean endsWord() { return isWord; }
	
	public Set<Character> getValidNextCharacters() {
		return children.keySet();
	}
	
	
	
	
	
}
