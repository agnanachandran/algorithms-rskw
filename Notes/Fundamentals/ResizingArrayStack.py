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
        return self

    def next(self):
        if self.current >= self.size():
            raise StopIteration
        else:
            self.current += 1
            return self.items[self.current - 1]

#stack = ResizingArrayStack()
#stack.push(3)
#for x in stack:
    #print x
