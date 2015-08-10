class Solution:
    # @param {integer[]} nums
    # @return {integer[]}
    def productExceptSelf(self, nums):
        result = [1] * len(nums) #Thanks to python, we can initialize array's default value to 1 when creating it
        
        #first loop, from left to right, we can use the result from prev loop
        for i in range(1, len(nums)):
            result[i] = result[i-1] * nums[i-1]
            
        #second loop, from right to left, we can't use the result from prev loop
        #but we need a temp variable to record product from right
        temp = 1
        for i in range(len(nums)-2, -1, -1):
            temp *= nums[i+1]
            result[i] *= temp
            
        return result
    
sol = Solution()
nums = [0,4,0]
print(sol.productExceptSelf(nums))