# Advanced Topics in Computer Science Project

This repository is for the 2019-2020 *Advanced Topics in Computer Science* course at Tabor Academy, Marion, MA. The team, consisting of one junior and three seniors, seeks to find a method to mitigate traffic congestion.

## To-Do List

1. Fully transition passTime() from Vehicle-oriented to Crossroad-oriented.
2. Standardize post-transition velocity of vehicles.
3. Change the turning mechanism so that only one car is turning at any given moment (In consideration).
4. Add in tangential acceleration so that there won't be cars turning extremely slowly (Should be solved by #3).
5. Prevent cars from occasionally driving backwards (Should be solved by #2 and #3).
6. Change the spawning mechanism into a spawning stream.

## Version Control

Introductions and revisions of mechanisms should be recorded as large version updates. Stylistic edits and comments should be recorded as small version updates.

## Team Members & Individual Responsibilities

### Siyuan "Daniel" Chen - Team Captain, Programmer

Daniel has the following responsibilities:

- Developing the signaling mechanism for vehicles and obstacles.
- Developing methods that require coordinate and vector geometry, e.g., inRange(), getOrientationBetween().
- Developing the GUI, e.g., Frame, Board.
- Developing major classes and components, e.g., Vehicle, Lane.
- Maintaining the structure of the program through good use of the four fundamental classes, i.e., Size, Position, Velocity, Acceleration.
- Editing teammates' programs and maintaining a coherent style and reasonable progress.

### Yifan "William" Wu - Programmer

William has the following responsibilities:

- Designing graphics, e.g., images for vehicles and obstacles.
- Designing and Developing GUI interface for demonstration, e.g., Slider, Textfield.
- Developing major classes and components, e.g., Obstacle.
- Determining the signaling mechanism for vehicles and obstacles.
- Testing existing mechanisms through extreme case analysis.
- Revising and expanding classes and methods.
- Providing valuable, constructive criticism to teammates.

### Andrew "Drew" Kirk - Programmer

Drew has the following responsibilities:

- Developing classes and components.
- Determining the signaling mechanism for vehicles and obstacles.
- Revising and expanding classes and methods.
- Producing presentations and proofs of concept.
- Analyzing viability of team plans and goals.
- Providing valuable, constructive criticism to teammates.

### Sam Monaghan - Researcher, Data Analyst

Sam has the following responsibilities:

- Gathering relevant data regarding traffic congestion.
- Cleansing and organizing data to determine key parameters, e.g., average velocity, average number of cars per hour.
- Analyzing data gathered and producing visualizations through Python.
- Comparing preexisting traffic conditions with conditions illustrated by the simulation produced.
- Integrating data collected into the simulation when needed.
- Providing valuable, constructive criticism to teammates.

## Style guide

Always use full spelling when possible.

```java
// Use
public static double orientationBetween(Position position1, Position position2) {
  return Math.atan2(
    (position2.getYPosition() - position1.getYPosition()),
    (position2.getXPosition() - position1.getXPosition()));
}
// Instead of
public static double orttBetween(Position p1, Position p2) {
  return Math.atan2(
    (p2.getYPos() - p1.getYPos()),
    (p2.getXPos() - p1.getXPos()));
}
```

Do not put a space between condition sentences and conditions; however, do put a space between conditions and curly brackets.

```java
// Use
if(this.getPosition().getYPosition() > 100) {
  return new Acceleration(0, 0);
} else {

  if(this.getPosition().getXPosition() < 200) {
    return new Acceleration(6.25, getVelocity().getOrientation() + Math.PI / 2);
  } else {
    return new Acceleration(0, 0);
  }

}
// Instead of
if (this.getPosition().getYPosition() > 100) {
  return new Acceleration(0, 0);
} else {

  if (this.getPosition().getXPosition() < 200){
    return new Acceleration(6.25, getVelocity().getOrientation() + Math.PI / 2);
  } else {
    return new Acceleration(0, 0);
  }

}
```

Use the four fundamental Classes

- *Size*
- *Position*
- *Velocity*
- *Acceleration*

## Instructions for Output as Mp4

When creating a *Frame* object, pass in attribute *record* as true and a valid *frameNumber*. The images would be created in the folder *output*. Use the following command line to create a video file from the images using *ffmpeg*.

```cmd
ffmpeg -framerate {*FRAME RATE*} -i TRAFFIC_CONGESTION_FRAME_%0{*NUMBER OF DIGITS*}d.png {*OUTPUT FILE NAME*}.mp4
```
