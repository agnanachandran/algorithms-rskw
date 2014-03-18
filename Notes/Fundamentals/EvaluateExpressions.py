# Evaluates fully-parenthesized expressions delimited by spaces, e.g. the input "( ( 5 + 3 ) - 0.5 ) yields the result 7.5
import fileinput

ops = []
vals = []
line = raw_input()
for s in line.split:
    if s == '(':
        pass
    elif s == '+' or s == '-' or s == '*' or s == '/' or s == 'sqrt':
        ops.append(s)
    elif s == ')':
        op = ops.pop()
        v = vals.pop()
        if op == '+': v = vals.pop() + v
        elif op == '-': v = vals.pop() - v
        elif op == '*': v = vals.pop() * v
        elif op == '/': v = vals.pop()/v
        elif op == 'sqrt': v = math.sqrt(v)
        vals.append(v)
    else:
        vals.append(float(s))
print vals.pop()
