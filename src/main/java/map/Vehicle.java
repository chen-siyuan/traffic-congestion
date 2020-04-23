/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.awt.*;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 6,
        lastModified = "04/05/2020",
        lastModifiedBy = "Daniel Chen"
)
public abstract class Vehicle extends Body {
    
    private Crossroad crossroad;
    private int origin; // 0, 1, 2, 3
    private int destination; // corresponds to +x, +y, -x, -y direction
    
    /**
     * The Vehicle class inherits the Body class and is parallel to the Obstacle class
     * 
     * @param size the size of the Vehicle
     * @param position the initial position of the Vehicle
     * @param velocity the initial velocity of the Vehicle
     */
    public Vehicle(Size size, Position position, Velocity velocity) {
        super(size, position, velocity);
    }
    
    public void setCrossroad(Crossroad crossroad) {
        this.crossroad = crossroad;
    }
    
    public Crossroad getCrossroad() {
        return crossroad;
    }
    
    public void setOrigin(int origin) {
        this.origin = origin;
    }
    
    public int getOrigin() {
        return origin;
    }
    
    public void setDestination(int destination) {
        this.destination = destination;
    }
    
    public int getDestination() {
        return destination;
    }
    
    public abstract Acceleration getAcceleration();
    
    public abstract Color getColor();
    
    public String toString() {
        return String.format("Vehicle:\tSize: %.2f * %.2f;\tPos: (%.2f, %.2f);\tVelocity: %.2f at %.2f.",
                this.getSize().getAlong(), this.getSize().getAcross(),
                this.getPosition().getXPosition(), this.getPosition().getYPosition(),
                this.getVelocity().getMagnitude(), this.getVelocity().getOrientation());
    }
    
    public void passTime() {

        Velocity velocity = getVelocity();
        Acceleration acceleration = getAcceleration();
        
        double xMagnitude = velocity.getXMagnitude() + acceleration.getXMagnitude() * Main.INTERVAL;
        double yMagnitude = velocity.getYMagnitude() + acceleration.getYMagnitude() * Main.INTERVAL;
        
        double newMagnitude = Math.sqrt(Math.pow(xMagnitude, 2) + Math.pow(yMagnitude, 2));
        double newOrientation = Math.atan2(yMagnitude, xMagnitude);

        newMagnitude = Math.min(newMagnitude, Car.MAX_VELOCITY_MAGNITUDE);

        setVelocity(new Velocity(newMagnitude, newOrientation));
        
        setPosition(new Position(
                getPosition().getXPosition() + getVelocity().getXMagnitude() * Main.INTERVAL,
                getPosition().getYPosition() + getVelocity().getYMagnitude() * Main.INTERVAL));
        
    }
    
}
