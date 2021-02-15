package com;

import java.util.*;

import nodes.ListNode;

public class LinkedListQ<E> extends AbstractList<E> {
	ListNode<E> head;
	ListNode<E> tail;
	int size;
	
	public LinkedListQ() {
		this.size = 0;
		this.head = new ListNode<E>();
		this.tail = new ListNode<E>();
		this.head.next = this.tail;
		this.tail.prev = this.head;
	}
	
	
	public boolean addLast(E data) {
		if (data == null) throw new NullPointerException("no data");
		ListNode<E> nodeToAdd = new ListNode<>(data);
		ListNode<E> prevNode = tail.prev;
		
		prevNode.next = nodeToAdd;
		nodeToAdd.prev = prevNode;
		
		nodeToAdd.next = tail;
		tail.prev = nodeToAdd;
		
		size++;
		return true;
	}
	public boolean addFirst(E data) {
		if (data == null) throw new NullPointerException("no data");
		ListNode<E> nodeToAdd = new ListNode<>(data);
		ListNode<E> nextNode = head.next;
		
		nodeToAdd.next = nextNode;
		nodeToAdd.prev = head;
		
		nextNode.prev = nodeToAdd;
		head.next = nodeToAdd;
		
		size++;		
		return true;
	}
	public void add(int index, E data) {
		if (data == null) throw new NullPointerException("no data");
		if (index < 0 || index > size -1) throw new IndexOutOfBoundsException("Invalid index");
		ListNode<E> target;
		ListNode<E> nodeToAdd = new ListNode<>(data);
		
		target = searchNode(index);
		
		nodeToAdd.next = target;
		nodeToAdd.prev = target.prev;
		
		target.prev.next = nodeToAdd;
		target.prev = nodeToAdd;
		
		size++;
	}
	
	public E remove(int index) {
		if (index < 0 || index > size -1) throw new IndexOutOfBoundsException("Invalid index");
		
		ListNode<E> target = searchNode(index);
		
		target.next.prev = target.prev;
		target.prev.next = target.next;
		
		size--;
		return target.data;
	}
	
	public E set(int index, E data) {
		if (index < 0 || index > size -1) throw new IndexOutOfBoundsException("Invalid index");
		if (data == null) throw new NullPointerException("no data");
		
		ListNode<E> target = searchNode(index);
		target.data = data;
		
		return target.data;
	}
	
	// Basic linear search
	private ListNode<E> searchNode(int index) {
		if (index < 0 || index > size -1) throw new IndexOutOfBoundsException("Invalid index");
		
		ListNode<E> target = head;
		if (target.data == null) target = target.next;
		for(int i = 0; i < index; i++) {
			target = target.next;
		}
		return target;
	}
	
	
	@Override
	public E get(int index) {
		if (index < 0 || index > size -1) throw new IndexOutOfBoundsException("Invalid index");
		
		ListNode<E> target;
		target = searchNode(index);
		
		return target.data;
	}

	@Override
	public int size() {
		return size;
	}
	
	
	
	public static void main(String[] args) {
		LinkedListQ<String> lst = new LinkedListQ<>();
		lst.addFirst("123");
		lst.addFirst("qwe");
		
		lst.addLast("zxc");
		lst.addLast("uio");
		
		lst.add(2, "ooop");
		
		System.out.println(lst);
		
		System.out.println(lst.get(1));
		System.out.println(lst.remove(1));
		
		System.out.println(lst);
		
		lst.set(1, "pppppp");
		
		System.out.println(lst);
	}
	
}
