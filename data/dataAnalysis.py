##Final Analytics
from dataReader import *

def analyze():

    analyzeData = dataReader('TrafficData', 'nycData', 'NYCTrafficVolumeCounts.csv')

    successGroup = analyzeData.query("""
select * from nycData where [8:00-9:00AM] < '3000' 
""")

    failGroup = analyzeData.query("""
select * from nycData where [8:00-9:00AM] > '3000'
""")

    successGroupTotal = 0
    failGroupTotal = 0

    fig = plt.figure()
    fig.subplots_adjust(top = 0.8)
    ax1 = fig.add_subplot(211)
    ax1.set_ylabel("# of Cars")
    ax1.set_xlabel("Day/Street")
    ax1.set_title("Traffic Counts vs. Model Speed")
    
    for i in successGroup['8:00-9:00AM']:
        successGroupTotal += 1
        print(successGroupTotal)
        ax1.scatter(successGroupTotal, i)
   
    for i in failGroup['8:00-9:00AM']:
        failGroupTotal += 1
        ax1.scatter((successGroupTotal + failGroupTotal), i)
        
    plt.show()
    
    print(successGroupTotal)
    print(failGroupTotal)
    total = successGroupTotal + failGroupTotal

    successRate = (successGroupTotal/total)*100
    print(successRate)
    
##    successGroup2 = successGroup.drop(columns = ['index', 'Segment ID', 'Roadway Name', 'From', 'To', 'Direction', 'Date'])
##    successGroup3 = successGroup2.groupby('ID')
##
##    averagesSuccess = averages(successGroup3)
    

##def averages(df):
##    dfMean = df.mean()
##    keys  = []
##    for i in dfMean.keys():
##        keys.append(i)
##    
##    listValues = []
##    ##Each Street
##    for i in range(0, len(dfMean)):
##        ##Each time
##        ##Start at 1 to exclude ID in calculation
##        for j in range(1, len(keys)):
##            listValues.append(dfMean.loc[i,j])
##
##    return listValues


analyze()  
