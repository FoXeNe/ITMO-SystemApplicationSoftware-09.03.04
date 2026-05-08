import pandas as pd
import tabulate

file = 'InfLab5Stadnikov.xlsx'
start = 6
end = 25

def join_row(row):
    return "".join(row)

data = pd.read_excel(file, header=None, skiprows=3, nrows=12).rename(columns={0: 'A', 1: 'B', 2: 'C', 3: 'D', 4: 'E'})

data['Bin'] = data.iloc[:, start:end].astype(str).apply(join_row, axis=1)

data_res = data[['A', 'B', 'C', 'E', 'Bin']]

print(tabulate.tabulate(data_res))
