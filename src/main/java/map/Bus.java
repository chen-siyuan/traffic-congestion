/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.awt.Color;
import java.util.ArrayList;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 3.1,
        lastModified = "04/03/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Bus extends Vehicle {
    
    public static final Color COLOR = new Color(84, 184, 96);
    public static final double MAX_VELOCITY_MAGNITUDE = 30;
    public static final double MAX_ACCELERATION_MAGNITUDE = 5;
    public static final double BOUNDING_BOX_FACTOR_ALONG = 1.5;
    public static final double BOUNDING_BOX_FACTOR_ACROSS = 1.5;
    
    public static final double ALONG = 9;
    public static final double ACROSS = 2.5;
    public static final double XPOS = 0;
    public static final double YPOS = 0;
    public static final double SPEED = 20;
    public static final double ORIENTATION = Math.toRadians(45);
    
    public static Bus[] getBusesArray(int numBuses) {
        
        Bus[] buses = new Bus[numBuses];
        
        for(int i=0; i < numBuses; i++) {
            buses[i] = new Bus(new Position(Math.random() * Main.PANEL_ALONG, Math.random() * Main.FRAME_ACROSS),
                    new Velocity(Math.random() * MAX_VELOCITY_MAGNITUDE, Math.random() * 2 * Math.PI));
        }
        
        return buses;
    }
    
    public static ArrayList<Bus> getBusesList(int numBuses) {
        
        ArrayList<Bus> buses = new ArrayList<Bus>();
        
        for(int i=0; i < numBuses; i++) {
            buses.add(new Bus(new Position(Math.random() * Main.PANEL_ALONG, Math.random() * Main.FRAME_ACROSS),
                    new Velocity(Math.random() * MAX_VELOCITY_MAGNITUDE, Math.random() * 2 * Math.PI)));
        }
        
        return buses;
    }
    
    public Bus() {
        this(new Size(ALONG, ACROSS), new Position(XPOS, YPOS), new Velocity(SPEED, ORIENTATION));
    }
    
    public Bus(Position position, Velocity velocity) {
        this(new Size(ALONG, ACROSS), position, velocity);
    }
    
    /**
     * The Bus class inherits the car class and is parallel to the Car class
     * 
     * @param size the size of the bus
     * @param position the initial position of the bus
     * @param velocity the initial velocity of the bus
     */
    private Bus(Size size, Position position, Velocity velocity) {
        super(size, position, velocity);
    }
    
    public Color getColor() {
        
        double slowColorWeight;
        double fastColorWeight = this.getVelocity().getMagnitude() / MAX_VELOCITY_MAGNITUDE;
        
        if(fastColorWeight > 1) {
            fastColorWeight = 1;
        } else if(fastColorWeight < 0) {
            fastColorWeight = 0;
        }
        
        slowColorWeight = 1 - fastColorWeight;
        
        return new Color((int)Math.round(Main.SLOW_VEHICLE_COLOR.getRed() * slowColorWeight + Main.FAST_VEHICLE_COLOR.getRed() * fastColorWeight),
                (int)Math.round(Main.SLOW_VEHICLE_COLOR.getGreen() * slowColorWeight + Main.FAST_VEHICLE_COLOR.getGreen() * fastColorWeight),
                (int)Math.round(Main.SLOW_VEHICLE_COLOR.getBlue() * slowColorWeight + Main.FAST_VEHICLE_COLOR.getBlue() * fastColorWeight));
    }

    public String toString() {
        return String.format("Bus:\tSize: %.2f * %.2f;\tPos: (%.2f, %.2f);\tVelocity: %.2f at %.2f.",
                this.getSize().getAlong(), this.getSize().getAcross(),
                this.getPosition().getXPosition(), this.getPosition().getYPosition(),
                this.getVelocity().getMagnitude(), this.getVelocity().getOrientation());
    }

    public Acceleration getAcceleration() {
        return new Acceleration(MAX_ACCELERATION_MAGNITUDE, getVelocity().getOrientation());
    }
    
    public Size getBoundingBoxSize() {
        return new Size(getSize().getAlong() * BOUNDING_BOX_FACTOR_ALONG,
                getSize().getAcross() * BOUNDING_BOX_FACTOR_ACROSS);
    }
    
}
