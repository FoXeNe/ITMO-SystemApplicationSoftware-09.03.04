import re
import unittest

# students.spam@yandex.ru => yandex.ru
# example@example => Fail!
# example@example.com => example.com

def main(text):
    pattern = r"(?<=@)\w+\.\w+"

    match = re.search(pattern, text)
    if match:
        return match.group(0)
    else:
        return "Fail!"

class task2_test(unittest.TestCase):
    def test1(self):
        text = "students.spam@yandex.ru"
        self.assertEqual(main(text), "yandex.ru")
    
    def test2(self):
        text = "example@example"
        self.assertEqual(main(text), "Fail!")
    
    def test3(self):
        text = "example@example.com"
        self.assertEqual(main(text), "example.com")

if __name__ == "__main__":
    unittest.main()