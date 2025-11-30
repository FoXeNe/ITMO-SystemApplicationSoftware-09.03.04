import unittest


pszh = r"""
                                                               #5&1
$&#^!)   dP""b8 ! # T       #9g&2   f   h     b         c     b    nc k   p
w    g  dP   `"  %№"      @*     &8 !   1    5 $       / \    %   @ d /   o
#    !  Yb        @       &^     )s g4&8$   %   1     ^   4   d  %  | %?4/2
d    a  @#   !)  o4a  .o. 7#     -1 %   #  #     g   +g<?6&$  n #   1 &   n 
(    !   YboodP h @ % `"'   f%s#6   #   ) @       ! 0       d 1&    v i   ^
                                                                ййй
пппппп   сссссс  ж ж ж       оооо   н   н     л         а     й    йй н   н
п    п  сс   сс   жжж      оо    оо н   н    л л       а а    й   й й н   н
п    п  сс         ж       оо    оо ннннн   л   л     а   а   й  й  й ннннн
п    п  сс   сс   жжж  .o. оо    оо н   н  л     л   ааааааа  й й   й н   н
п    п   сссссс  ж ж ж `"'   оооо   н   н л       л а       а йй    й н   н
"""

def task1():
    load = unittest.TestLoader()
    test = load.loadTestsFromName("Informatics_Lab3_Task1")
    run = unittest.TextTestRunner()
    res = run.run(test)

def task2():
    loader = unittest.TestLoader()
    test = loader.loadTestsFromName("Informatics_Lab3_Task2")
    run = unittest.TextTestRunner()
    res = run.run(test)

def task3():
    loader = unittest.TestLoader()
    test = loader.loadTestsFromName("Informatics_Lab3_Task3")
    run = unittest.TextTestRunner()
    res = run.run(test)


def menu():
    print(pszh + "\n" + "1 - task1" + "\n" + "2 - task2" + "\n" + "3 - task3")

    inp = int(input("Выберите задание:"))

    match inp:
        case 1:
            task1()
        case 2:
            task2()
        case 3:
            task3()


if __name__ == "__main__":
    while True:
        menu()