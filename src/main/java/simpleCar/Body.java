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

    /**
     * The Body class describes all objects that have a size, a position, and a velocity
     * The classes directly inheriting it are the Obstacle class and the Vehicle class
     * 
     * @param size the Size object of the Body object
     * @param position the Position object of the Body object
     * @param velocity the Velocity object of the Body object
     */
    public Body(Size size, Position position, Velocity velocity) {
        
        this.size = size;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = getAcceleration();
        
    }
    
    /**
     * Each of the inheriting classes have different ways of getting its acceleration
     * 
     * @return the Acceleration object
     */
    public abstract Acceleration getAcceleration();
    
    public Size getSize() {
        return size;
    }

    public Position getPosition() {
        return position;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public abstract Color getColor();

    public void setSize(Size size) {
        this.size = size;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public abstract String toString();
    
    /**
     * 
     * @param time in real life units
     */
    public abstract void passTime(double time);

}
