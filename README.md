# AT Comp Sci Project

This project is for the 2019-2020 Advanced Topics in Computer Science course at Tabor Academy. Four students seek to find a method to mitigate traffic congestion.

## Team Members & Responsibilities

### Daniel Chen (programmer & team captain)

**Program the collision detection and prediction mechanism and create the signaling mechanism.**

I would work on implementing a collision detection and prediction mechanism using bounding boxes of multiple sizes and coordinate geometry. I would also draft a mechanism used for communication between cars and implement the signaling method eventually. I would help with the graphic user interface. I would proofread and edit other people’s programs and make sure that our progress is monitored and well-paced.

### William Wu (programmer)

**Program the Path finding and Obstacle type classes and create graphics.**

Unlike the vehicles, obstacles such as pedestrians, drunk drivers, or wild animals benefit from a path finding system based on the fixed coordinates of the map. (Where as cars drive based on an acceleration vector and velocity vector and have no access to the map coordinates) Therefore, I will be programming the conditions each obstacle spawn and despawn (i.e. pedestrians will despawn after crossing a specific street), the behaviors they follow (i.e. a chicken may pause at the middle of the road for a couple of seconds, and a drunk driver may follow a certain type of path that loops). I would also create the graphics and textures of the objects eventually.

### Drew Kirk (programmer)

**Program the road class and lane detection classes.**

I will be programming the roads to be driven on. They will function as a set of rectangular “lanes”. Cars and obstacles will be able to be placed onto the plane of roads. The class will be tested by indicating which lane obstacles and cars are based on receiving their position. By the end of the trimester cars and obstacles (or points to signify them) will be able to be placed onto the map of roads.

### Sam Monaghan (researcher and data collector)

**Data manipulation to display graphs in python, and manipulate data in java for velocity**

We have a data file that displays traffic flow within the various streets of New York City. I will be manipulating that data set in python by grouping the set by street in order to show the various velocities of cars going through the busiest intersections and streets in New York during each time period of the day. Using these graphs, we will be able to compare our model to this control group, which will be formed on the basis of the numbers calculated by manipulating the data.

In addition, we will need to be able to access these numbers through java, so I will also create a java file that reads the .csv file and proceeds to read the file line by line, parsing data into arrays and lists that will be accessed to calculate velocity and traffic flow, and will be used to run our control group model.

## Style guide

- Use the following branch names:
  - *readme-edits* for editing this document
  - *main-structure* for new classes and features
- Always use full spelling when possible
  - *position* instead of *pos*
  - *otherPosition1* and *otherPosition2* instead of *p1* and *p2*
  - *getCarsArray()* instead of *getCarsA()*
- Do not put a space between condition sentences and conditions
  - if(condition) {}
  - while(condition) {}
- Use the four fundamental Classes
  - *Size*
  - *Position*
  - *Velocity*
  - *Acceleration*
- Refer to the magnitude of Velocity as magnitude instead of speed
  - *velocity.getMagnitude()* instead of *velocity.getSpeed()*
- Use same name variables but lowercase for parameters passed
  - *passTime(Time time)* instead of *passTime(Time t)*
  - *Car(Size size, Position position, Velocity velocity)* instead of *Car(Size s, Position p, Velocity v)*
- Changes that does not affect the execution of the code are subversions
  - Style edit or *//comments* can be recorded as *currentRevision = 2* -> *currentRevision = 2.1*

## Instructions

If the *Frame* object has attribute *record* set to *true*, the images would be created under the folder *output*. Use the following command in iTerm to create a video file from the images.

ffmpeg -framerate {*FRAME RATE*} -i TRAFFIC_CONGESTION_FRAME_%0{*NUMBER OF DIGITS*}d.png {*OUTPUT FILE NAME*}.mp4
