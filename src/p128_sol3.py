class Solution:
    # @param {integer[]} nums
    # @return {integer}
    def longestConsecutive(self, nums):
        hs = set(nums)
        result = 0
        for num in nums:
            #only start new search if we have not touched the num -1 before
            if num -1 not in hs:
                m = num + 1
                while m in hs:
                    m += 1
                result = max(result, m-num)
        return result