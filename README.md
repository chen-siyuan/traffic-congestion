# AT Comp Sci Project

This project is for the 2019-2020 Advanced Topics in Computer Science course at Tabor Academy. Four students seek to find a method to mitigate traffic congestion.

## Team Members & Responsibilities

### Daniel Chen (programmer & team captain)

Daniel is responsible for the collision detection, the collision prediction, and the signaling mechanism.

For the collision detection mechanism, Daniel is going to use coordinate and vector geometry to determine the positions of the bounding rectangles and whether or not they overlap. Once that is implemented, Daniel could achieve the collision prediction mechanism through detecting collision of two larger surrounding boxes. Regarding the signaling mechanism, Daniel is going to implement a signal system that will spread across the board.

### William Wu (programmer)

  - Programming the Path finding and Obstacle type classes
  - Creating the graphics and textures for the objects

### Drew Kirk (programmer)

  - Programing the Road class and Lane detection mechanism
  
### Sam Monaghan (researcher and data collector)

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
