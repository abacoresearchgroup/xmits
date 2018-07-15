import pandas as pd

df = pd.read_csv('files/table.csv', sep=';')

df['resposta_a'] = 1 - df['resposta_a']

df['resposta_b'] = df['resposta_b'].apply(lambda x: 0 if x == '1' else x.split('-'))

for index, value in df['resposta_b'].iteritems():
    for i in range(1, 7):
        try:
            tag = 'DC{}'.format(i)
            df.at[index, tag] = int(tag in value)
        except:
            df.at[index, tag] = 0

del df['resposta_b']

df = df.rename(columns={'resposta_a': 'resposta'})

df.to_csv('files/usecase_and_diagram.csv', sep=',', index=False)