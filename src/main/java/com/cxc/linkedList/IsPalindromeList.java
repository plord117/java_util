package com.cxc.linkedList;

/**
 * @author : chi xuchong
 */
public class IsPalindromeList {
    static Node left2;

    public static boolean isPalindromeCheckerWithReversal(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 1. 快慢指针找中点
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 2. 如果是奇数在移动一个
        if (fast != null) {
            slow = slow.next;
        }
        // 3 从slow开始翻转列表 并左右开始比较
        Node left = head;
        // 右侧是从slow开始翻转的列表
        Node right = reverse(slow);
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    private static Node reverse(Node head) {
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            // 先记录好下一个
            Node next = cur.next;
            // 当前的往前指
            cur.next = pre;
            // pre 往后移动
            pre = cur;
            // cur 往后移动
            cur = next;
        }
        // 最后 cur取 null 此时 pre是翻转链表的头
        return pre;
    }

    public static boolean isPalindromeCheckerWithStack(Node head) {
        left2 = head;
        return traverse(head);
    }

    // 利用递归，倒序遍历单链表
    private static boolean traverse(Node right) {
        if (right == null) {
            return true;
        }
        boolean res = traverse(right.next);
        // 后序遍历代码
        res = res && (right.val == left2.val);
        left2 = left2.next;
        return res;
    }

    public static void main(String[] args) {
        LinkedListTester.testWithPredicate(IsPalindromeList::isPalindromeCheckerWithReversal,
                                           IsPalindromeList::isPalindromeCheckerWithStack);
    }

}
