package indi.zmx.leetcode;

public class Solution {
    ListNode nthToLast(ListNode head, int n) {
        // write your code here
        if (head == null || n < 1) {
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        for (int j = 0; j < n - 1; j++) {
            if (p2 == null) {
                return null;
            }
            p2 = p2.next;
        }
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode node1 = new ListNode(3);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(1);
		ListNode node4 = new ListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = null;
		Solution s = new Solution();
		ListNode p = s.nthToLast(node1, 2);
//		while (node1 != null) {
//			System.out.println(node1.val);
//			node1 = node1.next;
//		}
		System.out.println(p.val);
	}
}
