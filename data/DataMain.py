import sqlite3
import pandas as pd
import matplotlib.pyplot as plt
import numbers
import numpy

conn = sqlite3.connect('TrafficData')
cursor = conn.cursor()

df = pd.read_csv('NYCTrafficVolumeCounts.csv')


df.to_sql('nycData', conn, if_exists = 'replace')



dfNycData = pd.read_sql_query("""
select *
from nycData where ([Roadway Name] = 'FDR Drive') and ([From] = 'East 34th Street')

""",conn)


dfExtracted = dfNycData.drop(columns = ['index','ID', 'Segment ID', 'Roadway Name', 'From', 'To', 'Direction', 'Date'])
keys = dfExtracted.keys()
print(keys)

columns = list(dfExtracted)
k = 1

for columnName, columnData in dfExtracted.iteritems():
    ##print(columnData.loc(0))
    plt.scatter(k, columnData.loc(0))
    k+=1
    
plt.show()






