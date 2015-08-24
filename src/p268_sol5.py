class Solution(object):
    def missingNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        sum, n= 0,  len(nums)
        for num in nums:
            sum += num
        return (n+1) * (n+0)/2 - sum