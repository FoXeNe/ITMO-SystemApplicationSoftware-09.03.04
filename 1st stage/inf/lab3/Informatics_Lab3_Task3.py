import re
import unittest
    

class task3_test(unittest.TestCase):
    text = str(input("введите текст:"))
    def test1(self):
        flag = False
        if not re.search(r"\w{5}", self.text):
            self.fail("Your password must be at least 5 characters.")
    
    def test2(self):
        if not re.search(r"[0-9]", self.text):
            self.fail("Your password must include a number.")
    
    def test3(self):
        if not re.search(r"[A-Z]", self.text):
            self.fail("Your password must include an uppercase letter.")
    
    def test4(self):
        if not re.search(r"\W", self.text):
            self.fail("Your password must include a special character.")
    
    def test5(self):
        mas = re.findall(r"\d", self.text)
        sum = 0
        for i in range(len(mas)):
            sum += int(mas[i])
        if sum != 25:
            self.fail("The digits in your password must add up to 25.")
    
    def test6(self):
        if not re.search(r"(january|febuary|march|april|may|june|july|august|september|october|november|december)", self.text):
            self.fail("Your password must include a month of the year.")

if __name__ == "__main__":
    unittest.main()