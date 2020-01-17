/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.awt.Color;
import java.util.ArrayList;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 4,
        lastModified = "01/16/2020",
        lastModifiedBy = "Daniel Chen"
)
public abstract class Body {
    
    /**
     * returns the four corners of the rectangle described by the parameters
     * 
     * @param size the Size of the rectangle
     * @param position Position of the center of the rectangle
     * @param velocity defines the orientation of the rectangle
     * @return the four Position objects of the rectangle described
     */
    public static ArrayList<Position> getCornerPositions(Size size, Position position, Velocity velocity) {
        
        ArrayList<Position> cornerPositions = new ArrayList<Position>();
        
        double halfDiagonal = Math.sqrt(Math.pow(size.getWidth(), 2) + Math.pow(size.getHeight(), 2)) / 2;
        double angle1 = velocity.getOrientation() + Math.atan2(size.getHeight(), size.getWidth());
        double angle2 = velocity.getOrientation() - Math.atan2(size.getHeight(), size.getWidth());
        
//        System.out.println(halfDiagonal);
//        System.out.println(angle / Math.PI);
        
        cornerPositions.add(new Position(
                position.getXPosition() + halfDiagonal * Math.cos(angle1),
                position.getYPosition() + halfDiagonal * Math.sin(angle1)));
        
        cornerPositions.add(new Position(
                position.getXPosition() - halfDiagonal * Math.cos(angle1),
                position.getYPosition() - halfDiagonal * Math.sin(angle1)));
        
        cornerPositions.add(new Position(
                position.getXPosition() + halfDiagonal * Math.cos(angle2),
                position.getYPosition() + halfDiagonal * Math.sin(angle2)));
        
        cornerPositions.add(new Position(
                position.getXPosition() - halfDiagonal * Math.cos(angle2),
                position.getYPosition() - halfDiagonal * Math.sin(angle2)));
        
        return cornerPositions;
    }
    
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
    public abstract void passTime();
    
}
