class Solution:
    # @param {string} s
    # @return {integer}
    """
    # python's conditional expression is "a if C else b" and can't be used as:
    [a for i in items if C else b]
    # the right form is:
    [a if C else b for i in items]
    # even though there is a valid form:
    [a for i in items if C]
    # but that isn't the same as that is how you filter by C, so, this is a valid combination:
    [a if tC else b for i in items if fC]
    """
    def romanToInt(self, s):
        hs = {"I":1, "V":5, "X":10, "L":50, "C":100, "D":500, "M":1000}
        prev = 0
        total = 0
        for c in s:
            curr = hs.get(c)
            total += (curr - 2 * prev) if curr > prev else curr
            prev = curr
        return total
