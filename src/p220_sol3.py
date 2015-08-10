class Solution:
    # @param {integer[]} nums
    # @param {integer} k
    # @param {integer} t
    # @return {boolean}
    def containsNearbyAlmostDuplicate(self, nums, k, t):
        if t < 0 or k < 1:
            return False
        dict = {}
        for i, v in enumerate(nums):
            if i > k:
                del dict[(nums[i-k-1]  + 2 **31) / (t+1)]
            temp = v + 2 ** 31
            bucket = temp / (t+1)
            if (bucket in dict or (bucket-1 in dict and dict[bucket -1] + t >= v) or (bucket+1 in dict and dict[bucket + 1] - t <= v)):
                return True
            
            dict[bucket] = v
        
        return False