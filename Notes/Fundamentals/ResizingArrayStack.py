class ResizingArrayStack(object):

    def __init__(self):
        self.items = []
        self.current = 0

    def isEmpty(self):
        return len(self.items) == 0
    
    def size(self):
        return len(self.items)

    def push(self, item):
        self.items.append(item)

    def pop(self):
        return self.items.pop()

    def __iter__(self):
        self.current = len(self.items) - 1
        return self

    def next(self):
        if self.current < 0:
            raise StopIteration
        else:
            self.current -= 1
            return self.items[self.current + 1]

if __name__ == '__main__':
    stack = ResizingArrayStack()
    stack.push(5)
    stack.push(2)
    stack.push(-1)
    print stack.pop()
    for st in stack:
        print st
    for st in stack:
        print st
