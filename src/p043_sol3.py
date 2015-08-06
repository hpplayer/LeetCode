class Solution:
    # @param {string} num1
    # @param {string} num2
    # @return {string}
    def multiply(self, num1, num2):
        sum = [0] * (len(num1) + len(num2))
        
        #reverse string in python
        num1 = num1[::-1]
        num2 = num2[::-1]
        
        for i in range(len(num1)):
            for j in range(len(num2)):
                sum[i+j] += int(num1[i]) * int(num2[j])
        
        result = ""
        
        for i in range(len(sum)):
            mod = sum[i] % 10
            carry = int(sum[i] / 10)
            if i < len(sum) - 1:
                sum[i+1] += carry
            result = str(mod) + result
        
        #remove 0s in front, dont forget result[0] == "0" not 0, char not int
        while len(result) > 1 and result[0] == "0":
            result = result[1:]
            
        return result
    
sol = Solution()
print(sol.multiply("0", "0"))