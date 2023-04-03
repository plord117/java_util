package com.cxc.linkedList;


import java.security.SecureRandom;
import java.util.function.*;


/**
 * LinkedListTester 是用于测试链表相关算法的测试类。
 *
 * @author : chi xuchong
 */
public class LinkedListTester {

    /**
     * 随机测试次数，用于对数器测试。
     */
    private static final int TEST_TIME = 5_000_000;
    /**
     * 随机生成的链表长度上限（非负整数），用于对数器测试。
     */
    private static final int MAX_SIZE = 100;
    /**
     * 随机生成的链表元素绝对值上限（非负整数），用于对数器测试。
     */
    private static final int MAX_VALUE = 100;
    /**
     * 生成随机链表时，小规模测试的链表长度，用于对数器测试。
     */
    private static final int SMALL_TEST_SIZE = 10;
    /**
     * 生成随机链表时，链表元素的最小值，用于对数器测试。
     */
    private static final int SMALL_TEST_VALUE = 10;


    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 入两个 Consumer 函数（对象原地更改，无输出），其中 linkListMethod 为想要测试的函数，comparator 为确定没有问题的函数.
     *
     * @param linkListMethod 想要测试的函数
     * @param comparator     确定没有问题的函数
     */
    public static void testWithConsumer(Consumer<Node> linkListMethod, Consumer<Node> comparator) {
        boolean succeed = true;
        for (int i = 0; i < TEST_TIME; i++) {
            Node linkedList1 = generateRandomLinkedList(MAX_SIZE, MAX_VALUE);
            Node linkedList2 = copyLinkedList(linkedList1);

            linkListMethod.accept(linkedList1);
            comparator.accept(linkedList2);
            if (isNotEqual(linkedList1, linkedList2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        if (!succeed) {
            while (true) {
                Node linkedList1 = generateRandomLinkedList(SMALL_TEST_SIZE, SMALL_TEST_VALUE);
                Node linkedList2 = copyLinkedList(linkedList1);
                Node backup = copyLinkedList(linkedList1);

                linkListMethod.accept(linkedList1);
                comparator.accept(linkedList2);
                if (isNotEqual(linkedList1, linkedList2)) {
                    System.out.println("backup = ");
                    printLinkedList(backup);
                    System.out.println("your method = ");
                    printLinkedList(linkedList1);
                    System.out.println("comparator = ");
                    printLinkedList(linkedList2);
                    break;
                }
            }
        }
    }

    /**
     * 生成随机链表，链表长度为 [0, maxSize] 的随机整数，链表元素为 [-maxValue, maxValue] 范围内的随机整数。
     *
     * @param maxSize  随机生成的链表长度上限（非负整数）
     * @param maxValue 随机生成的链表元素绝对值上限（非负整数）
     * @return 随机生成的链表头节点
     * @throws IllegalArgumentException 如果 maxSize 参数为负数
     */
    public static Node generateRandomLinkedList(int maxSize, int maxValue) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize必须为正整数");
        }
        int length = RANDOM.nextInt(maxSize) + 1;
        Node dummy = new Node(0);
        Node cur = dummy;
        for (int i = 0; i < length; i++) {
            int value = RANDOM.nextInt(2 * maxValue + 1) - maxValue;
            Node node = new Node(value);
            cur.next = node;
            cur = node;
        }
        return dummy.next;
    }

    private static Node copyLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        Node dummy = new Node(0);
        Node cur = head;
        Node newCur = dummy;
        while (cur != null) {
            newCur.next = new Node(cur.val);
            newCur = newCur.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * 比较两链表是否不相等
     *
     * @param linkedList1 链表1
     * @param linkedList2 链表2
     * @return 是否不相等
     */
    private static boolean isNotEqual(Node linkedList1, Node linkedList2) {
        Node cur1 = linkedList1;
        Node cur2 = linkedList2;
        while (cur1 != null && cur2 != null) {
            if (cur1.val != cur2.val) {
                return true;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1 != null || cur2 != null;
    }

    /**
     * 打印链表
     *
     * @param head 链表的头节点
     */
    public static void printLinkedList(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.val);
            if (cur.next != null) {
                System.out.print(" -> ");
            }
            cur = cur.next;
        }
        System.out.println();
    }

    /**
     * linkListMethod是要验证的方法 Consumer 结果为原地修改无返回值， tester是验证函数，验证当前方法的正确性
     *
     * @param linkListMethod 要验证的方法 Consumer 结果为原地修改无返回值
     * @param tester         验证函数，验证当前方法的正确性
     */
    public static void testWithConsumer(Consumer<Node> linkListMethod, Predicate<Node> tester) {
        boolean succeed = true;
        for (int i = 0; i < TEST_TIME; i++) {
            Node linkedList = generateRandomLinkedList(MAX_SIZE, MAX_VALUE);

            linkListMethod.accept(linkedList);
            if (!tester.test(linkedList)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        if (!succeed) {
            while (true) {
                Node linkedList = generateRandomLinkedList(SMALL_TEST_SIZE, SMALL_TEST_VALUE);
                Node backup = copyLinkedList(linkedList);
                linkListMethod.accept(linkedList);
                if (!tester.test(linkedList)) {
                    System.out.println("backup = ");
                    printLinkedList(backup);
                    System.out.println("your method = ");
                    printLinkedList(linkedList);
                    break;
                }
            }
        }
    }


    /**
     * linkListMethod是要验证的方法 Consumer 结果为原地修改无返回值， tester是验证函数，验证当前方法的正确性
     *
     * @param linkListMethod 要验证的方法 Consumer 结果为原地修改无返回值
     * @param tester         验证函数，验证当前方法的正确性
     */
    public static void testWithConsumer(Consumer<Node> linkListMethod, BiPredicate<Node, Integer> tester) {
        boolean succeed = true;
        for (int i = 0; i < TEST_TIME; i++) {
            Node linkedList = generateRandomLinkedList(MAX_SIZE, MAX_VALUE);
            int anInt = RANDOM.nextInt(2 * MAX_VALUE) - MAX_VALUE;
            linkListMethod.accept(linkedList);
            if (!tester.test(linkedList, anInt)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        if (!succeed) {
            while (true) {
                Node linkedList = generateRandomLinkedList(SMALL_TEST_SIZE, SMALL_TEST_VALUE);
                Node backup = copyLinkedList(linkedList);
                int anInt = RANDOM.nextInt(2 * SMALL_TEST_VALUE) - SMALL_TEST_VALUE;
                linkListMethod.accept(linkedList);
                if (!tester.test(linkedList, anInt)) {
                    System.out.println("backup = ");
                    printLinkedList(backup);
                    System.out.println("your method = ");
                    printLinkedList(linkedList);
                    break;
                }
            }
        }
    }


    public static void testWithBiConsumer(BiConsumer<Node, Integer> linkListMethod, BiPredicate<Node, Integer> tester) {
        boolean succeed = true;
        for (int i = 0; i < TEST_TIME; i++) {
            Node linkedList = generateRandomLinkedList(MAX_SIZE, MAX_VALUE);
            int anInt = RANDOM.nextInt(2 * MAX_VALUE) - MAX_VALUE;
            linkListMethod.accept(linkedList, anInt);
            if (!tester.test(linkedList, anInt)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        if (!succeed) {
            while (true) {
                Node linkedList = generateRandomLinkedList(SMALL_TEST_SIZE, SMALL_TEST_VALUE);
                Node backup = copyLinkedList(linkedList);
                int anInt = RANDOM.nextInt(2 * SMALL_TEST_VALUE) - SMALL_TEST_VALUE;
                linkListMethod.accept(linkedList, anInt);
                if (!tester.test(linkedList, anInt)) {
                    System.out.println("backup = ");
                    printLinkedList(backup);
                    System.out.println("your method = ");
                    printLinkedList(linkedList);
                    break;
                }
            }
        }
    }


    /**
     * linkListMethod是要验证的方法 UnaryOperator 非原地修改 有返回值， tester是验证函数，验证当前方法的正确性
     *
     * @param linkListMethod 要验证的方法 UnaryOperator 非原地修改 有返回值
     * @param tester         tester是验证函数，验证当前方法的正确性
     */
    public static void testWithUnaryOperator(UnaryOperator<Node> linkListMethod, Predicate<Node> tester) {
        boolean succeed = true;
        for (int i = 0; i < TEST_TIME; i++) {
            Node linkedList = generateRandomLinkedList(MAX_SIZE, MAX_VALUE);

            Node res = linkListMethod.apply(linkedList);
            if (!tester.test(res)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        if (!succeed) {
            while (true) {
                Node linkedList = generateRandomLinkedList(SMALL_TEST_SIZE, SMALL_TEST_VALUE);
                Node backup = copyLinkedList(linkedList);
                Node res = linkListMethod.apply(linkedList);
                if (!tester.test(res)) {
                    System.out.println("backup = ");
                    printLinkedList(backup);
                    System.out.println("your method = ");
                    printLinkedList(linkedList);
                    break;
                }
            }
        }
    }

    private static boolean isNotEqual(boolean res1, boolean res2) {
        return res1 == res2;
    }

    /**
     * 对数器 两个链表方法都返回boolean值， 其中 linkListMethod 为要验证的方法 comparator为正确的方法
     *
     * @param linkListMethod linkListMethod 为要验证的方法
     * @param comparator     comparator为正确的方法
     */
    public static void testWithPredicate(Predicate<Node> linkListMethod, Predicate<Node> comparator) {
        boolean succeed = true;
        for (int i = 0; i < TEST_TIME; i++) {
            Node linkedList1 = generateRandomLinkedList(MAX_SIZE, MAX_VALUE);
            Node linkedList2 = copyLinkedList(linkedList1);

            if (linkListMethod.test(linkedList1) != comparator.test(linkedList2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        if (!succeed) {
            while (true) {
                Node linkedList1 = generateRandomLinkedList(SMALL_TEST_SIZE, SMALL_TEST_VALUE);
                Node linkedList2 = copyLinkedList(linkedList1);
                Node backup = copyLinkedList(linkedList1);

                if (linkListMethod.test(linkedList1) != comparator.test(linkedList2)) {
                    System.out.println("backup = ");
                    printLinkedList(backup);
                    System.out.println("your method = ");
                    printLinkedList(linkedList1);
                    System.out.println("comparator = ");
                    printLinkedList(linkedList2);
                    break;
                }
            }
        }
    }


}

