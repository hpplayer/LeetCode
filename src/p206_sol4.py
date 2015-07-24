# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param {ListNode} head
    # @return {ListNode}
    def reverseList(self, head):
        if head is None: return head # or just "if not head: return head"
        curr = head
        prev = None
        while curr != None: # or just "while curr:"
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        return prev
            