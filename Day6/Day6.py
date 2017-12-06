def solve(vals):
    seen = set()

    while tuple(vals) not in seen:
        seen.add(tuple(vals))

        max_index = 0
        for index, val in enumerate(vals):
            if val > vals[max_index]:
                max_index = index

        num = vals[max_index]
        vals[max_index] = 0
        while num > 0:
            max_index += 1
            vals[max_index % len(vals)] = vals[max_index % len(vals)] + 1
            num -= 1
            
    print(len(seen))
    return vals
        





f = open('day6_input.txt', 'r')
values = [int(i) for i in f.readline().split()]
f.close()

solve(solve(values))