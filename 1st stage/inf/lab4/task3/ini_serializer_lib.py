import configparser
import json

class LibSerializer:
    """сериализация в ini при помощи библиотек"""
    def serializer(self, inp, filename='schedule_output.ini'):
        config = configparser.ConfigParser()

        config.add_section('General')
        if 'day' in inp:
            config.set('General', 'day_of_week', str(inp['day']))
        
        schedule = inp.get('schedule', [])

        for index, lesson in enumerate(schedule, 1):
            section_name = f"Lesson_{index}"
            config.add_section(section_name)

            for key, value in lesson.items():
                
                if isinstance(value, list):
                    str_value = ', '.join(map(str, value))
                else:
                    str_value = str(value)
                    
                config.set(section_name, key, str_value) 

        with open(filename, 'w', encoding='utf-8') as configfile:
            config.write(configfile)