class Solution:
    # @param {integer[]} nums
    # @return {integer}
    def majorityElement(self, nums):
        #only has single element
        if len(nums) == 1:
            return nums[0]
        left = self.majorityElement(nums[:len(nums)//2])  #equal to left : mid(not inclusive), where mid is (left+right)/2
        right = self.majorityElement(nums[len(nums)//2:]) #equal to mid+1(inclusive) : right, where mid is (left+right)/2
        
        if left == right:
            return left
        #if count(right) > len(nums)//2 return 1, where we return right
        #if count(right) < len(nums)//2 return 0, where we return left
        #Since the final majority will have occurrences > len(nums)//2, it will be guaranteed to win the competition
        return [left,right][nums.count(right) > len(nums)//2]
        