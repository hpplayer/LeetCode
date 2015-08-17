class Solution:
    # @param {integer[]} nums
    # @return {string[]}
    def summaryRanges(self, nums):
        return [re.sub('->.*>', '->', '->'.join(`n` for i, n in g))
                for _, g in itertools.groupby(enumerate(nums), lambda (i, n): n-i)]
    
"""
Group num in nums by the difference in range, then build the string.
This method can only be used in language that supports groupby function
Since it is a general solution, so not recommended in interview
'*'
Causes the resulting RE to match 0 or more repetitions of the preceding RE, as many repetitions as are possible.
ab* will match ¡®a¡¯, ¡®ab¡¯, or ¡®a¡¯ followed by any number of ¡®b¡¯s.

'.'
(Dot.) In the default mode, this matches any character except a newline.
 If the DOTALL flag has been specified, this matches any character including a newline.
 
 
re.sub(pattern, repl, string, count=0, flags=0)
Return the string obtained by replacing the leftmost non-overlapping occurrences of pattern in string by the replacement repl.
If the pattern isn¡¯t found, string is returned unchanged. repl can be a string or a function; 
if it is a string, any backslash escapes in it are processed.
 That is, \n is converted to a single newline character,
\r is converted to a carriage return, and so forth.
Unknown escapes such as \j are left alone.
 Backreferences, such as \6, are replaced with the substring matched by group 6 in the pattern
 """