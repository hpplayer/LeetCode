# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None
"""
recursive way
"""
class Solution:
    # @param {ListNode} head
    # @return {ListNode}
    def reverseList(self, head):
        if head is None or head.next is None:
        # or just "if not head or not head.next:"
            return head
        newHead = self.reverseList(head.next)
        head.next.next = head #form a loop now
        head.next = None #cut the original pointer
        return newHead