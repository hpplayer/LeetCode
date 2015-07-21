"""
This solution is interesting, since the way it reverse a string
[begin:end:step]

'hello world"[::-1]
leaving beginning and ending with ::
we specify the step to be -1
so we can get the reverse of "hello world"

if we need get the reverse part of world, then we can do
'hello world'[-1:5:-1] end is exclusive, begin is inclusive, -1 is the step!
"""

class Solution:
    # @param {integer} x
    # @return {boolean}
    def isPalindrome(self, x):
        return str(x) == str(x)[::-1]