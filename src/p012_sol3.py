class Solution:
    # @param {integer} num
    # @return {string}
    def intToRoman(self, num):
        keys = [1000, 900, 500, 400, 100, 90, 50,40, 10, 9, 5, 4, 1]
        values = ["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]
        i = 0
        result = ""
        while num > 0:
            times = int(num/keys[i])
            for _ in range(times):
                result += values[i]
            num -= times * keys[i]
            i += 1
        return result