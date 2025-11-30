class IniSerializer:
    def __init__(self, inp):
        self.inp = inp

    def to_ini_string(self, value):
        """конвертация в подходящий формат для ini"""
        if isinstance(value, list):
            parts = [str(v) for v in value]
            return ", ".join(parts)
        return str(value)

    def serialize(self):
        ini_lines = []
        
        day = self.inp.get('day', '')
        ini_lines.append("[General]")
        ini_lines.append(f"day_of_week = {day}")
        
        ini_lines.append("")
        
        schedule = self.inp.get('schedule', [])

        for index, lesson in enumerate(schedule, 1):
            section_name = f"Lesson_{index}"
            ini_lines.append(f"[{section_name}]")
            for key, value in lesson.items():
                ini_value = self.to_ini_string(value)
                ini_lines.append(f"{key} = {ini_value}")
                
            ini_lines.append("")

        return "\n".join(ini_lines).strip()