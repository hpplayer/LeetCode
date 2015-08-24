class Solution(object):
    def missingNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        sum, sum2 = 0, 0
        for num in range(0, len(nums)+1):
            sum += num
        for num in nums:
            sum2 += num
        return sum - sum2