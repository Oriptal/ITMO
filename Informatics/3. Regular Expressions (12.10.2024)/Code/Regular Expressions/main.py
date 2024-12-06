import re
from itertools import count


def count_smile(s: str)->int:
    regex = r';<\)'
    return len(re.findall(regex, s))


def delete_repeating_words(s: str)->str:
    regex = r'\b(\w+)\b(?:\s+\1)+'
    return re.sub(regex, r'\1', s, flags=re.IGNORECASE)

def find_one_vowel_words(s: str)->list[str]:
    vowels = 'аяеёоиуюэы'
    non_vowels = 'йцкнгшщзхъждлрпвфчсмтьб'
    regex = fr"\b[{non_vowels}]*([{vowels}])(?:[{non_vowels}]*\1*)*\b"
    answer = []
    for sub in re.split(r'[-.;:\s|,|\*|\n]', s):
        if re.fullmatch(regex, sub, flags=re.IGNORECASE) is not None:
            answer.append(sub)
    answer.sort(key=lambda a: (len(a), a))
    return answer

with open('input.txt', 'r') as file:
    text = file.read()

split_text = text.split('^')[1:]

for i in range(0, 5):
    print(find_one_vowel_words(split_text[i]))
