
dictionary = {
    '`': "0",
    'K': "11",
    'S': "100",
    'Q': "1010",
    '0': "10110",
    '1': "10111",
}

with open("serialize.in") as fi:
    text = fi.read()

with open("serialize.out", "w") as fo:
    while len(text) > 1:
        char = text[-1]
        text = text[:-1]
        fo.write(dictionary.get(char, ""))
