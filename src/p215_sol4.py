import heapq
class Solution(object):
    def findKthLargest(self, nums, k):
        heap = []
        for num in nums:
            heapq.heappush(heap, num)
            if len(heap) > k:
                heapq.heappop(heap)
        """notice, heap[0] is the top!!!!"""
        return heap[0] 
        
        
    def findKthLargest2(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        nums.sort()
        return nums[len(nums) - k]
    
sol = Solution()
nums = [2,1]
print(sol.findKthLargest(nums, 1))