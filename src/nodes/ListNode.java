package nodes;

public class ListNode<E> {
	public ListNode<E> prev;
	public ListNode<E> next;
	public E data;
	
	public ListNode() {
		this.data = null;
		this.prev = null;
		this.next = null;
	}
	public ListNode(E data) {
		this.data = data;
		this.prev = null;
		this.next = null;
	}
}
