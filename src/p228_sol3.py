class Solution:
    # @param {integer[]} nums
    # @return {string[]}
    def summaryRanges(self, nums):
        result = []
        i = 0
        while i  < len(nums):
            m = 0
            s = str(nums[i])
            while i + m + 1 < len(nums) and (nums[i+m] + 1 == nums[i+m+1]):
                m += 1
            if m != 0:
                s += '->' + str(nums[i+m])
            result.append(s)
            i+= m + 1
            
        return result