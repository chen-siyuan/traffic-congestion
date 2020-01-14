/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.awt.Color;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 1,
        lastModified = "01/14/2020",
        lastModifiedBy = "Daniel Chen"
)
public abstract class Body {
    
    private Size size;
    private Position position;
    private Velocity velocity;
    private Acceleration acceleration;

    // Constructors
    
    public Body(Size size, Position position, Velocity velocity) {
        
        this.size = size;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = getAcceleration();
        
    }
    
    /**
     * Each class of descending objects have different ways of getting its acceleration
     * 
     * @return the Acceleration object
     */
    public abstract Acceleration getAcceleration();
    
    // Getters

    public Size getSize() {
        return size;
    }

    public double getWidth() {
        return size.getWidth();
    }

    public double getHeight() {
        return size.getHeight();
    }

    public Position getPosition() {
        return position;
    }

    public double getXPosition() {
        return position.getXPosition();
    }

    public double getYPosition() {
        return position.getYPosition();
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public double getSpeed() {
        return velocity.getSpeed();
    }

    public double getOrientation() {
        return velocity.getOrientation();
    }
    
    public abstract Color getColor();

    // Setters

    public void setSize(Size size) {
        this.size = size;
    }

    public void setWidth(double width) {
        this.size.setWidth(width);
    }

    public void setHeight(double height) {
        this.size.setHeight(height);
    }

    public void setPosition(Position pos) {
        this.position = pos;
    }

    public void setXPosition(double xPos) {
        this.position.setXPosition(xPos);
    }

    public void setYPosition(double yPos) {
        this.position.setYPosition(yPos);
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public void setSpeed(double speed) {
        this.velocity.setSpeed(speed);
    }

    public void setOrientation(double orientation) {
        this.velocity.setOrientation(orientation);
    }

    // Auxillary methods
    
    public abstract String toString();
    
    // Actual Methods

    // t is in real time
    
    public abstract void passTime(double t);

}
