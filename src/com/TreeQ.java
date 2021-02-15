package com;

import java.util.*;

import nodes.TreeNode;

//Binary tree
public class TreeQ<E extends Comparable<? super E>> {
	TreeNode<E> root;
	
	public TreeQ() {
		root = new TreeNode<>();
	}
	
	public boolean addIterate(E data) {
		TreeNode<E> curr = root;
		int compare;
		
		if (curr.getData() == null) {
			curr.setData(data);
			return true;
		} 
		
		compare = data.compareTo(curr.getData());
		while (compare < 0 && curr.getLeftChild() != null ||
				compare > 0 && curr.getRightChild() != null) {
			
			if (compare < 0) {
				curr = curr.getLeftChild();
			} else {
				curr = curr.getRightChild();
			}
			compare = data.compareTo(curr.getData());	
		}
		
		if (compare < 0) {
			curr.addLeftChild(data, curr);
		} else if (compare > 0) {
			curr.addRightChild(data, curr);
		} else {
			//already exists
			return false;
		}
		
		return true;
	}
	
	private TreeNode<E> addRecurcion (TreeNode<E> curr, E data) {
		int compare = data.compareTo(curr.getData());
		if (compare < 0) {
			if (curr.getLeftChild() == null) {
				curr.addLeftChild(data, curr);
				return curr;
			}
			curr = addRecurcion(curr.getLeftChild(), data);

		} else if (compare > 0) {
			if (curr.getRightChild() == null) {
				curr.addRightChild(data, curr);
				return curr;
			}
			curr = addRecurcion(curr.getRightChild(), data);
		} 	
		return curr;
	}
	public void addRec(E data) {
		TreeNode<E> curr = root;
		if (curr.getData() == null) {
			curr.setData(data);
		} else {
			addRecurcion(curr, data);
		}
	}
	
	public boolean contains(E toFind) {
		TreeNode<E> currNode = root;
		int comp;
		while (currNode != null) {
			comp = toFind.compareTo(currNode.getData());
			if (comp < 0) {
				currNode = currNode.getLeftChild();
			} else if (comp > 0) {
				currNode = currNode.getRightChild();
			} else {
				//found
				return true;
			}
		}
		return false;
	}
	
	

	private TreeNode<E> deleteRecursive(TreeNode<E> curr, E data) {
		return null;
	}
	public void delete(E data) {
		
	}
	
	//add different searches
	
	
	
	
	
	
	
	
/*	
	
	//depth first traversal
	private void preOrder(TreeNode<E> node) {
		if (node != null) {
			node.visit();
			preOrder(node.getLeftChild());
			preOrder(node.getRightChild());
		}
	}
	public void preOrder() {
		this.preOrder(root);
	}
	
	
	//breath First Traversal
	private void levelOrder(TreeNode<E> node) {
		Queue<TreeNode<E>> q = new LinkedList<>();
		q.add(root);
		
		while (!q.isEmpty()) {
			TreeNode<E> currNode = q.remove();
			if (currNode != null) {
				currNode.visit();
				q.add(currNode.getLeftChild());
				q.add(currNode.getRightChild());
			}
		}
		
	}
*/
/*
	public TreeNode<E> searchNode(E val) {
		TreeNode<E> target = root;
		
		
		
		return target;
	}
*/	

	
	
	
	public static void main(String[] args) {
		TreeQ<String> qwe = new TreeQ<>();
		qwe.addIterate("qwe");
		qwe.addIterate("asd");
		qwe.addIterate("zxc");
		
		qwe.addRec("wert");
		
		System.out.println(qwe.contains("wert"));
		
		
	}

}
