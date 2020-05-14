from dataReader import *

import sqlite3
import pandas as pd
import matplotlib.pyplot as plt
import numbers
import numpy


def main():
    ##Parameters = dataBase name, table name, file name
    data = dataReader('TrafficData', 'nycData', 'NYCTrafficVolumeCounts.csv')

###Cross Bronx Expressway
    cbe = data.query("""
select * from nycData where [Roadway Name] = 'Cross Bronx Expressway'

""")
    crossBronxExpressway = data.extract(cbe)

    data.scatter(crossBronxExpressway)
    cbeVals = data.createList(crossBronxExpressway)
    print(cbeVals)

    avgCbeVals = data.maxAverageValue(cbeVals)
    print(avgCbeVals)

    
###    
    e42 = data.query("""
select * from nycData where [Roadway Name] = 'FDR Drive'
and [from] = 'East 42 Street'
and [to] = 'East 43 Street'
""")
    east42 = data.extract(e42)
    data.scatter(east42)
    

main()
