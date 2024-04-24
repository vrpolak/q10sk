
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
    """Programs to be consumed as input for interpreter running the loader."""
    text = txt[:]
    while text:
        *text, char = text
        fo.write(dictionary.get(char, ""))

with open("inverse.out", "w") as fo:
    """For defining the loader in interpreter (it happens to read in the opposite order)."""
    text = txt[:]
    while text:
        char, *text = text
        fo.write(dictionary.get(char, ""))
