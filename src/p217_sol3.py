class Solution:
    # @param {integer[]} nums
    # @return {boolean}
    
    """Simple solution in python, use set() function to convery the array to set, then compare the length"""
    def containsDuplicate(self, nums):
        return len(nums) > len(set(nums))