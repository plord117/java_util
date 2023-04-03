package com.cxc.linkedList;

import java.util.HashMap;

/**
 * @author : chi xuchong
 */
public class SpecialLinkedList1 {

    class Node {
        int value;
        Node next;
        Node rand;

        Node(int val) {
            value = val;
        }
    }

    public Node copySpecialLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> special = new HashMap<>();
        special.put(null, null);
        // 遍历第一遍 将老节点存为key 新节点存为value
        Node dummy = new Node(0);
        Node cur = head;
        Node newNode = dummy;
        while (cur != null) {
            Node p = new Node(cur.value);
            newNode.next = p;
            special.put(cur, p);
            newNode = newNode.next;
            cur = cur.next;
        }
        // 重新遍历两个链表
        cur = head;
        newNode = dummy.next;
        while (cur != null) {
            Node curRand = cur.rand;
            newNode.rand = special.get(curRand);
            cur = cur.next;
            newNode = newNode.next;
        }
        return dummy.next;
    }

    public Node copySpecialLinkedList2(Node head) {
        if (head == null) {
            return null;
        }
        // 遍历克隆节点，将当前节点创建在当前节点的后面一位
        Node cur = head;
        while (cur != null) {
            Node curNext = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = curNext;
            cur = cur.next.next;
        }
        // 再次遍历根据老节点的rand 连接后面的新rand
        cur = head;
        while (cur != null) {
            Node curRand = cur.rand;
            // cur.rand可能为null
            if (curRand == null) {
                cur.next.rand = null;
            } else {
                cur.next.rand = curRand.next;
            }
            cur = cur.next.next;
        }
        // 两个链表分离
        Node newDummy = new Node(0);
        newDummy.next = head.next;
        cur = head;
        Node newCurNode = head.next;
        while (newCurNode != null && newCurNode.next != null) {
            cur.next = cur.next.next;
            newCurNode.next = newCurNode.next.next;
            cur = cur.next;
            newCurNode = newCurNode.next;
        }
        if (cur.next != null) {
            cur.next = null;
        }
        return newDummy.next;
    }


    public static void main(String[] args) {

    }
}
