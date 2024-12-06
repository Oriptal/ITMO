import time

def parse(path: str) -> list[str]:
    with open(path, 'r', encoding="UTF-8") as file:
        res = file.readlines()
    return res


def get_tag(code: str):
    for i in range(len(code)):
        if code[i] == '>':
            return code[:i+1]
    raise ValueError("В одной из строк отсутствует тег!")


def get_attributes(tag: str) -> dict[str, str]:
    res = dict()
    tag = tag[:-1]
    for attr in tag.split(' ')[1:]:
        key,value = attr.split('=')
        res[key] = value
    return res


def calc_tabs(open_tags: list[str])->str:
    return '\t' * len(open_tags)


def get_text(code: str)->str:
    return (code.split('>')[1]).split('<')[0]

class Converter:
    def __init__(self):
        self.format = dict()

    def print_format(self, form: str):
        for line in self.format[form]:
            print(line)

    def get_info(self, path: str, file_format: str = "XML") -> None:
        code = parse(path)
        open_tags = ['{']
        close_tags = []
        json_code = ['{']
        self.format[file_format] = code
        for line in code:
            line = line.strip()
            tag = get_tag(line)
            if tag[0] != '<':
                raise ValueError("В одной из строк отсутствует тег!")
            if tag[1] == '/':  # Тег закрывающийся
                if open_tags[-1] == tag[2:-1]:
                    open_tags.pop()
                    if json_code[-1][-1] == ',':
                        json_code[-1] = json_code[-1][:-1]
                    json_code.append(calc_tabs(open_tags) + '},')
                else:
                    raise ValueError("Плохой XML файл")
            else:
                attrs = get_attributes(tag)
                open_tag = (tag.split(' ')[0])[1:]
                if open_tag[-1] == '>':
                    open_tag = open_tag[:-1]
                if tag == line:  # Тег исключительно открывающийся
                    json_code.append(f'{calc_tabs(open_tags)}"{open_tag}": ' + '{')
                    open_tags.append(open_tag)
                    for k in attrs.keys():
                        json_code.append(f'{calc_tabs(open_tags)}"_{k}": {attrs[k]},')
                else:
                    text = get_text(line)
                    if len(attrs) == 0:
                        json_code.append(f'{calc_tabs(open_tags)}"{open_tag}": "{text}",')
                    else:
                        json_code.append(f'{calc_tabs(open_tags)}"{open_tag}": ' + '{')
                        open_tags.append(open_tag)
                        for k in attrs.keys():
                            json_code.append(f'{calc_tabs(open_tags)}"_{k}": {attrs[k]},')
                        json_code.append(f'{calc_tabs(open_tags)}"__text": "{text}"')
                        open_tags.pop()
                        json_code.append(calc_tabs(open_tags) + '},')
        if json_code[-1][-1] == ',':
            json_code[-1] = json_code[-1][:-1]
        open_tags.pop()
        json_code.append('}')
        self.format["JSON"] = json_code


def main():
    path = input("Введите путь до конвертируемого файла\n")
    conv = Converter()
    t = time.time()
    for i in range(1000):
        conv.get_info(path)
        # conv.print_format("JSON")
    print(f">>> Время выполнения по 1000 запускам: {round((time.time() - t), 3)} мс")

if __name__ == "__main__":
    main()