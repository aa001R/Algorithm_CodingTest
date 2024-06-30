def explode(string, bomb):
    bomb_size = len(bomb)
    if len(string) < bomb_size:
        return string
    stack = []
    for i in range(len(string)):
        stack.append(string[i])
        if stack[-1] == bomb[-1]:
            if "".join(stack[-bomb_size:]) == bomb:
                del stack[-bomb_size:]
    if stack:
        return "".join(stack)
    return "FRULA"
            
# 데이터 입력
sentence = input()
bomb = input()
print(explode(sentence, bomb))