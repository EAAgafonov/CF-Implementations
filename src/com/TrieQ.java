package com;

import java.util.*;
import nodes.TrieNode;

public class TrieQ {
	private TrieNode root;
	private int size;
	
	public TrieQ() {
		root = new TrieNode();
		size = 0;
	}

	
	//boolean contains
	// add word
	//delete
	//countWords
	
	public boolean insertWord(String word) {
		String wordToAdd = word.toLowerCase();
		if(isWord(wordToAdd))
			return false;
		HashMap<Character, TrieNode> children = root.children;
		for (int i=0; i<wordToAdd.length(); i++) {
			char c = wordToAdd.charAt(i);
			TrieNode temp;
			if (children.containsKey(c)) {
				temp = children.get(c);
			} else {
				temp = new TrieNode(""+(c));
				children.put(c, temp);
			}
			
			children = temp.children;
			if (i == wordToAdd.length()-1) {
				temp.isWord = true;
				size++;
			}
		}
		return true;
	}
	
	public boolean isWord(String word) {
		TrieNode temp = searchNode(word.toLowerCase());
		if(temp != null && temp.isWord) {
			return true;
		} else {
			return false;
		}
	}
	
	public TrieNode searchNode(String str) {
		HashMap<Character, TrieNode> children = root.children;
		TrieNode t = null;
		for (int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if (children.containsKey(c)) {
				t = children.get(c);
				children = t.children;
			} else {
				return null;
			}
		}
		return t;
	}
	
	public int size() {
		return size;
	}
	
	public void printTrie() {
		printNode(root);
	}
	private void printNode(TrieNode curr) {
		if(curr == null) {
			return;
		}
		System.out.print(curr.getText());
		if (curr.isWord) {
			System.out.println();
		}
		
		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters()) {
			next = curr.getChild(c);
			printNode(next);
		}
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		TrieQ qwe = new TrieQ();
		qwe.insertWord("qwe");
		qwe.insertWord("aaa");
		qwe.insertWord("again");
		qwe.insertWord("finish");
		qwe.insertWord("finishes");
		
		qwe.printTrie();
		
	}

}
