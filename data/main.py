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

###data graph on Cross Bronx Expressway
    data.scatter(crossBronxExpressway, "Cross Bronx Expressway")
    
    cbeVals = data.createList(crossBronxExpressway)

    avgCbe = data.maxAverageValue(cbeVals)
    print("Average of top 1/3 hours for traffic counts\nCross Brox Expressway: ")
    print(avgCbe)

    
###    
    e42 = data.query("""
select * from nycData where [Roadway Name] = 'FDR Drive'
and [from] = 'East 42 Street'
and [to] = 'East 43 Street'
""")
    east42 = data.extract(e42)
    data.scatter(east42, "East 42nd Street")

    east42Vals = data.createList(east42)
    avgEast42 = data.maxAverageValue(east42Vals)
    print("")
    print("Average of the top 1/3 traffic counts\nEast 42nd Street")
    print(avgEast42)
    

main()
