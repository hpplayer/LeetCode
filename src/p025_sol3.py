# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param {ListNode} head
    # @param {integer} k
    # @return {ListNode}
    def reverseKGroup(self, head, k):
        if not head or k <= 1:#k=1 means keep list as it is
            return head
        dummy = ListNode(0)
        dummy.next = head
        curr = dummy
        while curr:
            curr.next = self.reverseList(curr.next, k)
            for _ in range(k): #move curr k nodes away, i.e. to next group
                if not curr:
                    break
                curr = curr.next
        return dummy.next
    
    def reverseList(self, head, k):
        ptr = head
        for _ in range(k):
            if not ptr:
                return head
            ptr = ptr.next #after while loop ptr should be k nodes away from head
        """
        since we are reversing the list, so the first node will become last after the while loop,
        which means first node will points to the first node in the next group
        which is k nodes away from the first node in this group
        """           
        prev = ptr 
        curr = head
        while curr != ptr:
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        return prev