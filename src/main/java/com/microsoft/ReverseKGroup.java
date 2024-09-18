package com.microsoft;

/**
 * @author ooo
 * @description K个一组翻转链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * @date 2024/9/18 09:20:37
 */
public class ReverseKGroup {

    public static void main(String[] args) {
        ReverseKGroup r = new ReverseKGroup();
        ListNode l5 = new ListNode(5);
        ListNode l4 = new ListNode(4, l5);
        ListNode l3 = new ListNode(3, l4);
        ListNode l2 = new ListNode(2, l3);
        ListNode l1 = new ListNode(1, l2);

        ListNode node = l1;
        while (node != null){
            System.out.printf("" + node.val + ",");
            node = node.next;
        }
        System.out.println("");

        node = r.reverseKGroup(l1, 5);

        while (node != null){
            System.out.printf("" + node.val + ",");
            node = node.next;
        }
    }

    /**
     * 分类讨论：
     * 1. k = 1：不用翻转直接返回；
     * 2. k = length，简单的翻转链表；
     * 3. k 是 length的约数，可以翻转 length / k 轮，每轮进行翻转即可；
     * 4. k 不是length的约数，可以翻转 length / k 轮，最后剩下的拼接上即可；
     *
     * 那么对于每轮翻转，我们可以使用头插法，剩下的直接拼接到尾部即可
     * 这里我们使用一个dummyHead来保存需要返回的新链表的头部，tail来指向新链表的尾部，每轮进行翻转时，该轮的第一个节点就是新的tail
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 首先检查链表里有多少节点，以确定是否需要翻转，以及需要翻转几组
        int L = 0;
        ListNode cur = head;
        while (cur != null){
            cur = cur.next;
            L++;
        }
        // 不需要翻转，直接返回
        if (L == 1 || L < k){
            return head;
        }

        // 翻转链表使用头插法，dummyHead是个很好的辅助节点，而tail用于指向新的尾节点，即每轮翻转时的第一个节点
        ListNode dummyHead = new ListNode(), tail = dummyHead;
        // count记录当前组已经翻转的节点数量，sum记录所有已经翻转的节点数量
        int count = 0 , sum = 0;
        // cur 作为指针不断移动，遍历整个链表
        cur = head;
        // head 作为dummyHead的代表进行第一轮的翻转后，dummyHead的next就指向了第一组的最后一个节点
        head = dummyHead;

        while (cur != null){
            // 如果已经完成了一组翻转，那么将head移动到当前已经完成翻转的链表的尾部，方便进行下一次翻转
            if (count == k){
                head = tail;
            }

            // 如果当前剩下的节点数量够一次翻转，则使用头插法进行翻转
            if (L - sum >= k){
                // 每组翻转节点的第一个节点作为新链表的尾节点
                tail = cur;

                int t = k;
                while (t > 0){
                    // 保留cur的next
                    ListNode temp = cur.next;
                    // 头插法
                    cur.next = head.next;
                    head.next = cur;
                    // cur指向原来的next
                    cur = temp;
                    t--;
                }
                count = k;
                sum += k;
            }else {// 剩下的节点不够一次翻转，直接插入head后面
                head.next = cur;
                break;
            }
        }

        return dummyHead.next;
    }

    private void headInsert(ListNode head, ListNode cur, int k) {
        while (k > 0){
            cur.next = head.next;
            head.next = cur;
            k--;
        }
    }

    static class ListNode{
        int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
