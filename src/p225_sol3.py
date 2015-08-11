import collections
class Stack:
    # initialize your data structure here.
    def __init__(self):
        self.q = collections.deque()

    # @param x, an integer
    # @return nothing
    def push(self, x):
        self.q.append(x) #append value to the deque
        for _ in range(len(self.q) - 1):
            self.q.append(self.q.pop())

    # @return nothing
    def pop(self):
        self.q.pop()

    # @return an integer
    def top(self):
        return self.q[0]

    # @return an boolean
    def empty(self):
        return not len(self.q)
    


deq = collections.deque()
deq.append(1)
deq.append(2)
print(deq.pop())

self = Stack()
print(self.empty())