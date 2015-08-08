class Solution:
    # @param {integer[]} nums
    # @param {integer} k
    # @return {boolean}
    """
    if I use "v not in hs.keys()", the OJ will return LTE error, why?
    since "v not in hs" will do the hasing search while "v not in hs.keys()" will do the linear search which will
    be much slower
    """
    def containsNearbyDuplicate(self, nums, k):
        hs = {}
        for i, v in enumerate(nums):
            if v not in hs:
                hs[v] = i
            else:
                if (i - hs[v]) <= k:
                    return True
                hs[v] = i
        return False
    

sol = Solution()
print(sol.containsNearbyDuplicate([1,0,1,1], 1))