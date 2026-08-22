class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        List<Integer> l = new ArrayList<>();
        while (list1 != null) {
            l.add(list1.val);
            list1 = list1.next;
        }
        while (list2 != null) {
            l.add(list2.val);
            list2 = list2.next;
        }
        Collections.sort(l);
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int value : l) {
            current.next = new ListNode(value);
            current = current.next;
        }
        return dummy.next;
    }
}