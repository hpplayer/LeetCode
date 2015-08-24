class Solution(object):
    def missingNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        result = len(nums) #help get the nth number
        for i, num in enumerate(nums):
            result ^= num
            result ^= i
            
        return result