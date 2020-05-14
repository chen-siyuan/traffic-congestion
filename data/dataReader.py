import sqlite3
import pandas as pd
import matplotlib.pyplot as plt
import numbers
import numpy

class dataReader():

    def __init__(self, databaseName, tableName, fileName):

        self.tableName = tableName
        self.fileName = fileName
        self.databaseName = databaseName

        self.conn = sqlite3.connect(self.databaseName)
        self.cursor = self.conn.cursor()

        self.df = pd.read_csv(self.fileName)
        self.df.to_sql(self.tableName, self.conn, if_exists = 'replace')

    def query(self, query):
        return pd.read_sql_query(query, self.conn)

    def queryRoadWay(self, road):
        return pd.read_sql_query("select * from " + self.tableName + " where [Roadway Name] = " + road, self.conn)

    def queryStreet(self,road, fromStreet, toStreet):
        return pd.read_sql_query(
"select * from " + self.tableName + " where([Roadway Name] = "+ road + ") and where ([From] = " + fromStreet + ") and ([To] = "+ toStreet  + ")", self.conn
             )

    def queryGroup(self, contains):
        return pd.read_sql_query(
"select * from " + self.tableName + " where [Roadway Name] like " + contains + "" , self.conn
            )
        
    def extract(self, df):
        df2 = df.drop(columns = ['index','ID', 'Segment ID', 'Roadway Name', 'From', 'To', 'Direction', 'Date'])
        self.keys = df2.keys()
        return df2
    
    
    def scatter(self, df):
        dfMean = df.mean()
        k = 1
        for i in self.keys:
            plt.scatter(k, dfMean[i])
            k+=1

        plt.show()

    def createList(self, df):
        dfMean = df.mean()
        listValues = []
        for i in self.keys:
            listValues.append(dfMean[i])

        return listValues

    def maxAverageValue(self, listVals):
        listVals.sort(reverse = True)
        ##highest third
        totalSum = 0
        for i in range(0, 7):
            totalSum += listVals[i]
        average = totalSum/8
        return average
        

    def createGroup(self, df):
        dfGrouped = df.groupby('Roadway Name')
        dfMean = dfGrouped.mean()
        return dfMean

    
    
        

        
 
        
