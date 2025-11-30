class XmlSerializer:
    def __init__(self, data):
        self.data = data

    def escape(self, value):
        """замена не подходящих символов"""
        return str(value).replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")

    def serialize(self):
        xml_lines = []
        
        xml_lines.append('<?xml version="1.0" encoding="UTF-8"?>')
        
        day = self.data.get('day', '')
        schedule_list = self.data.get('schedule', [])
        
        xml_lines.append(f'<Schedule day="{self.escape(day)}">')
        
        for index, lesson in enumerate(schedule_list, 1):
            
            xml_lines.append(f'  <Lesson id="{index}">')
            
            for key, value in lesson.items():
                
                if isinstance(value, list):
                    value_str = ", ".join(map(str, value))
                else:
                    value_str = str(value)
                
                content = self.escape(value_str)
                xml_lines.append(f'    <{key}>{content}</{key}>')
            
            xml_lines.append('  </Lesson>')

        xml_lines.append('</Schedule>')

        return "\n".join(xml_lines).strip()