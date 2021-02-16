package nodes;

public class TreeNode<E> {
	private TreeNode<E> parent;
	private TreeNode<E> leftChild;
	private TreeNode<E> rightChild;
	private E data;
	

	public TreeNode(E data) {
		this.data = data;
	}

	public TreeNode(E data, TreeNode<E> par) {
		this.data = data;
		this.parent = par;
	}

	
	public TreeNode<E> addLeftChild(E val, TreeNode<E> par) {
		this.leftChild = new TreeNode<E>(val, this);
		this.parent = par;
		return this.leftChild;
	}
	public TreeNode<E> addRightChild(E val, TreeNode<E> par) {
		this.rightChild = new TreeNode<E>(val, this);
		this.parent = par;
		return this.rightChild;
	}
	
	
	public TreeNode<E> getLeftChild() { return leftChild; }
	public TreeNode<E> getRightChild() { return rightChild; }
	
	public E getData() { return data; }
	public void setData(E data) { this.data = data; }
	
	public void delLeft() { this.leftChild = null; }
	public void delRight() { this.rightChild = null; }
	
	public TreeNode<E> getParent() { return parent; }
	
	//does something with a node
	public void visit() {
		System.out.println(data);
	}
	

	
	
	
	
	
	
	
}