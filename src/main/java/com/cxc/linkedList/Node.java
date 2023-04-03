package com.cxc.linkedList;

/**
 * @author : chi xuchong
 */
public class Node {
    public int val;
    public Node next;

    Node(int val) {
        this.val = val;
        this.next = null;
    }

    Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.add(3);
        head.add(4);
        head.add(1);
        head.add(7);
        LinkedListTester.printLinkedList(head);
    }

    public void add(int val) {
        Node newNode = new Node(val);
        Node tail = this;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = newNode;
    }
}
