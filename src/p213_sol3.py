class Solution:
    # @param {integer[]} nums
    # @return {integer}
    def rob(self, nums):
        if len(nums) == 1:
            return nums[0]
        return max(self.robInrange(0, len(nums)-2, nums), self.robInrange(1, len(nums)-1, nums))
        
    def robInrange(self, start, end, nums):
        prevYes = prevNo = 0
        for i in range(start, end+1):
            temp = prevYes
            prevYes = prevNo + nums[i]
            prevNo = max(prevNo, temp)
        return max(prevYes, prevNo)