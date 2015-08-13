# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param {ListNode} head
    # @return {boolean}
    def findMid(self, head):
        fast = slow = head
        while fast and fast.next:
            fast = fast.next.next
            slow = slow.next
        return slow
        
    def reverseList(self, head):
        prev, curr = None, head
        while curr:
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        return prev
        
    def isPalindrome(self, head):
        mid = self.findMid(head)
        newhead = self.reverseList(mid)
        while head != mid:
            if head.val != newhead.val:
                return False
            head = head.next
            newhead = newhead.next
        return True
        