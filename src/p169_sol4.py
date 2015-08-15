class Solution:
    # @param {integer[]} nums
    # @return {integer}
    def majorityElement(self, nums):
        count, candidate = 0, 0;
        for num in nums:
            if count == 0:
                count = 1
                candidate = num
            elif num == candidate:
                count += 1
                if count > len(nums)//2:
                    return candidate
            else:
                count -= 1
        
        return candidate
        