import re
import unittest

def main(text):
    pattern = r"(\w+)(\s+\1\b)"
    
    return re.sub(pattern ,r"\1", text)

class task1_test(unittest.TestCase):
    def test1(self):
        text = "Довольно распространённая ошибка ошибка – это лишний повтор повтор слова слова. Смешно, не не правда ли? Не нужно портить хор хоровод."
        self.assertEqual(main(text), "Довольно распространённая ошибка – это лишний повтор слова. Смешно, не правда ли? Не нужно портить хор хоровод.")

    def test2(self):
        text = "Да Да, пошли сходим сходим туда"
        self.assertEqual(main(text), "Да, пошли сходим туда")

    def test3(self):
        text = "Он пошел пошел в магазин, вместе с мамой мамой за за мороженным"
        self.assertEqual(main(text), "Он пошел в магазин, вместе с мамой за мороженным")

    def test4(self):
        text = "Я решил, что очень очень хочу наприсать ПСЖ ПСЖ"
        self.assertEqual(main(text), "Я решил, что очень хочу наприсать ПСЖ")

    def test5(self):
        text = "P3116 P3116 лучшая группа группа в ИТМО ИТМО"
        self.assertEqual(main(text), "P3116 лучшая группа в ИТМО")

if __name__ == "__main__":
    unittest.main()