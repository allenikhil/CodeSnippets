package com.java.linkedlist;

public class MainClass {
	public static void main(String[] args) {
		LinkedList list=new LinkedList();
		list.insert(20);
		list.insert(23);
		list.insert(40);
		list.insertAtStart(50);
		list.insertAt(2,10);
		list.show();
		list.deleteAt(3);
		list.show();
		System.out.println(list.size());
		
	}

}
