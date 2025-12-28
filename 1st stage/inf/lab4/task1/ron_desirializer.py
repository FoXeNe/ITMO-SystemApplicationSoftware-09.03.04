class Token:
    def __init__(self, type, value=None):
        self.type = type
        self.value = value

    def tokenize(text):
        """разбивает на токены"""
        tokens, i, n = [], 0, len(text)
        while i < n:
            char = text[i]

            # скип пробелов и переносов
            if char.isspace():
                i += 1
            # разделяшки всякие
            elif char in '(),:[]':
                tokens.append(Token(char))
                i += 1
            # строки
            elif char == '"':
                start = i + 1
                i += 1
                while i < n and text[i] != '"': i += 1
                tokens.append(Token('STRING', text[start:i]))
                i += 1
            # состоит ли число из буков или чисел
            elif char.isalnum():
                start = i
                while i < n and text[i].isalnum(): i += 1
                val = text[start:i]
                tokens.append(Token('NUMBER', int(val)) if val.isdigit() else Token('KEY', val))
            else:
                i += 1
        return tokens


class RonDesirializer:
    def __init__(self, tokens):
        self.tokens = tokens
        self.index = 0

    def look(self):
        """посмотреть на следующий токен"""
        if self.index < len(self.tokens): return self.tokens[self.index]

    def extract(self, _=None):
        """увеличивает индекс и возвращает настоящий токен. так же можно задать нужный токен и найти его"""
        token = self.look()
        self.index += 1
        return token

    def parse_obj(self):
        """парсит()"""
        self.extract()
        result = self.parse_keys()
        self.extract()
        return result

    def parse_mas(self):
        """парсит массив"""
        self.extract()
        values = []
        while self.look().type != ']':
            values.append(self.what_parse())
            if self.look().type == ',': self.extract()
        self.extract()
        return values

    def parse_keys(self):
        """парсит пары ключ:значение"""
        members = {}
        while self.look().type != ')':
            # парсит пару ключ:значение
            key = self.extract().value
            self.extract()
            members[key] = self.what_parse()
            
            if self.look().type == ',': self.extract()
        return members

    def what_parse(self):
        """что парсить"""
        token = self.look()
        if token.type in ('STRING', 'NUMBER'):
            return self.extract().value
        return self.parse_obj() if token.type == '(' else self.parse_mas()

    def parse(self):
        return self.parse_obj()