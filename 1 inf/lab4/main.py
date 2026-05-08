import time

from task1.ron_desirializer import RonDesirializer, Token
from task2.ini_serializer import IniSerializer
from task3.ini_serializer_lib import LibSerializer
from task4.xml_serializer import XmlSerializer

file = open("input.ron")
text = file.read() 

# task1 ron => binary
print("task1")
tokens = Token.tokenize(text)
deserializer = RonDesirializer(tokens)
parsed = deserializer.parse()
print(parsed)
print("\n")

# task2 binary => ini
print("task2")
serializer = IniSerializer(parsed)
ini = serializer.serialize()
print(ini)
print("\n")

# task3
print("task3")
serializer_instance = LibSerializer()
serializer_instance.serializer(parsed)
print("вывод в файле")
print("\n")

# task4
print("task4")
serializer = XmlSerializer(parsed)
xml_output = serializer.serialize()
print(xml_output)
print("\n")

# task5
print("task5")

N = 100
# RON => INI
start_time = time.perf_counter()
for i in range(N):
    tokens = Token.tokenize(text)
    deserializer = RonDesirializer(tokens)
    parsed_data = deserializer.parse()
    # Сериализация
    serializer = IniSerializer(parsed_data)
    i = serializer.serialize()
time_ini = time.perf_counter() - start_time
print(time_ini)


# RON => INI LIB
start_time = time.perf_counter()
for i in range(N):
    tokens = Token.tokenize(text)
    deserializer = RonDesirializer(tokens)
    parsed_data = deserializer.parse()
    serializer_instance.serializer(parsed_data)
time_ini_lib = time.perf_counter() - start_time
print(time_ini_lib)


# RON => XML
start_time = time.perf_counter()
for i in range(N):
    tokens = Token.tokenize(text)
    deserializer = RonDesirializer(tokens)
    parsed_data = deserializer.parse()
    serializer = XmlSerializer(parsed_data)
    i = serializer.serialize()
time_xml = time.perf_counter() - start_time
print(time_xml)