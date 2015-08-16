class Solution:
    # @param {integer[]} nums
    # @return {integer}
    def longestConsecutive(self, nums):
        hs = {}
        result = 0
        for num in nums:
            if num in hs:
                continue
            left = 0 if num - 1 not in hs else hs[num-1]
            right = 0 if num + 1 not in hs else hs[num+1]
            sum = left + 1 + right
            hs[num] = sum
            result = max(result, sum)
            #update tail and head 
            hs[num-left] = sum
            hs[num + right] = sum
            
        return result
            