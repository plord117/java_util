package com.cxc.linkedList;

/**
 * @author : chi xuchong
 */
@SuppressWarnings("UtilityClassCanBeSingleton")
public class ListPartition {

    /**
     * 根据pivot的值 将链表排序为 &lt;pivot区域 =pivot区域 &gt;pivot区域三个区域
     *
     * @param head  链表的头节点
     * @param pivot 分割链表的值
     * @return 排好序的链表头节点
     */
    public static Node listPartition(Node head, int pivot) {
        // base case
        if (head == null || head.next == null) {
            return head;
        }
        // 初始化三个区域
        Node lessHead = null;
        Node lessTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node moreHead = null;
        Node moreTail = null;

        // 指针
        Node p = head;

        // 遍历链表，将节点添加到对应的区域中
        while (p != null) {
            if (p.val < pivot) {
                if (lessHead == null) {
                    lessHead = p;
                    lessTail = p;
                } else {
                    lessTail.next = p;
                    lessTail = lessTail.next;
                }
            } else if (p.val == pivot) {
                // 如果和pivot相等，添加到equal区域
                if (equalHead == null) {
                    equalHead = p;
                    equalTail = p;
                } else {
                    equalTail.next = p;
                    equalTail = equalTail.next;
                }
            } else if (p.val > pivot) {
                // 如果大于pivot，添加到more区域
                if (moreHead == null) {
                    moreHead = p;
                    moreTail = p;
                } else {
                    moreTail.next = p;
                    moreTail = moreTail.next;
                }
            }
            p = p.next;
        }
        // 三个的终点置null
        if (lessTail != null) {
            lessTail.next = null;
        }
        if (equalTail != null) {
            equalTail.next = null;
        }
        if (moreTail != null) {
            moreTail.next = null;
        }

        // 从左到右连接三个区域
        // 三种至少有1中
        if (lessHead == null && equalHead == null) {
            // 只有more区域
            return moreHead;
        }
        if (lessHead == null && moreHead == null) {
            // 只有equal区域
            return equalHead;
        }
        if (equalHead == null && moreHead == null) {
            // 只有less区域
            return lessHead;
        }
        // 三种中有两种
        if (lessHead == null) {
            // 只有equal区域和more区域
            equalTail.next = moreHead;
            return equalHead;
        }
        if (equalHead == null) {
            // 只有less区域和more区域
            lessTail.next = moreHead;
            return lessHead;
        }
        if (moreHead == null) {
            // 只有less区域和equal区域
            lessTail.next = equalHead;
            return lessHead;
        }
        // 三种中有三种
        lessTail.next = equalHead;
        equalTail.next = moreHead;
        return lessHead;
    }


    public static void main(String[] args) {
        LinkedListTester.testWithBiConsumer(ListPartition::listPartition, ListPartition::tester);
    }

    /**
     * 检验器 测试 listPartition 返回的结果是否正确
     *
     * @param head  listPartition 返回链表的头节点
     * @param pivot listPartition中用到的pivot值
     * @return 是否按照pivot将链表按照  &lt;pivot区域 =pivot区域 &gt;pivot区域三个区域 的顺序排列
     */
    public static boolean tester(Node head, int pivot) {
        Node less = null;
        Node equal = null;
        Node more = null;

        Node p = head;
        // 判断第一个元素的所示的范围
        if (p.val < pivot) {
            less = p;
        } else if (p.val == pivot) {
            equal = p;
        } else {
            more = p;
        }
        while (p != null) {
            if (more != null) {
                // 后面的都应该是>区域的
                if (p.val < pivot) {
                    return false;
                }
            } else if (equal != null) {
                // 后面应该是=区或 >区 不可能是<区
                if (p.val < pivot) {
                    return false;
                }
                if (p.val > pivot) {
                    equal = null;
                    more = p;
                    continue;
                }
            } else if (less != null) {

                if (p.val == pivot) {
                    less = null;
                    equal = p;
                    continue;
                } else if (p.val > pivot) {
                    less = null;
                    more = p;
                    continue;
                }
            }
            p = p.next;
        }
        return true;
    }
}
