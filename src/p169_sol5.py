class Solution:
    # @param {integer[]} nums
    # @return {integer}
    def majorityElement(self, nums):
        result = 0
        for i in range(32):
            ones = zeros = 0
            for j in range(len(nums)):
                if (nums[j] & (1<<i)) != 0:
                     ones += 1
                else:
                    zeros += 1
            #there is nearly no overflow problem in python, so when we nearly manually set the int when it reachs the boundary of 32 bit
            #if the first bit in int is 1, it means this int is negative, so we will let 1<<31 then minus the result to get its 
            #negative version, i.e. get the positive version of result, then negates it
            #else just result |= 1<<i
            if ones > zeros:
                if i == 31:
                    result = -((1<<31) - result)
                else:
                    result |= (1 << i)
        return result