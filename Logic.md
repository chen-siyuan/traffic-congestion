# Logic of the Self-driving cars

## On a straight road
The car should detect other cars and objects with in a certain radius.
At all times, the car will try to manage equal distance between the car in the front and the car in the back.
When no car is detected in the front or back, the distance will be calculated as the edge of the detection circle.
//Interaction with obstacles is yet to be filled

## At an intersection
Each car will have a priority factor when entering an intersection.
Cars will dodge the cars with higher priority factor than themselves. 
Priority factor is determined as this:
  Right-turning cars > Straight-going cars > Left-turning cars
This rule priority factor is based upon the driving-on-the-right-side-of-the-road  convention. 
//Interaction with obstacles is yet to be filled
