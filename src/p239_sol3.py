import collections
class Solution:
    # @param {integer[]} nums
    # @param {integer} k
    # @return {integer[]}
    def maxSlidingWindow(self, nums, k):
        if not nums or not k:
            return []
        deq = collections.deque()
        result = []
        for i, v in enumerate(nums):
            #if exceed window size we need remove leftmost index
            if deq and deq[0] + k - 1 < i:
                deq.popleft() #notice this popleft(), pop leftmost
            #while loop, remove smaller value's index from tail to head
            while deq and nums[deq[-1]] < v:
                deq.pop() #pop rightmost
                
            #add current value into deq
            deq.append(i)
            #reach window size, we can fill the result array
            if i >= k - 1:
                result.append(nums[deq[0]])
        
        return result
    
sol = Solution()
print(sol.maxSlidingWindow([1], 1))