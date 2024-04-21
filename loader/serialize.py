
dictionary = {
    '`': "0",
    'K': "11",
    'S': "100",
    'Q': "1010",
    '0': "10110",
    '1': "10111",
}

with open("serialize.in") as fi:
    txt = fi.read()

with open("serialize.out", "w") as fo:
    text = txt[:]
    while text:
        *text, char = text
        fo.write(dictionary.get(char, ""))

with open("inverse.out", "w") as fo:
    text = txt[:]
    while text:
        char, *text = text
        fo.write(dictionary.get(char, ""))
