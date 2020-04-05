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
        currentRevision = 6,
        lastModified = "04/05/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Car extends Vehicle {
    
    public static final Color COLOR = new Color(227, 86, 77);
    public static final double MAX_VELOCITY_MAGNITUDE = 60;
    public static final double MAX_ACCELERATION_MAGNITUDE = 8;
    public static final double BOUNDING_BOX_FACTOR_ALONG = 1.5;
    public static final double BOUNDING_BOX_FACTOR_ACROSS = 1.5;
    
    public static final double ALONG = 4;
    public static final double ACROSS = 1.8;
    public static final double XPOS = 0;
    public static final double YPOS = 0;
    public static final double SPEED = 20;
    public static final double ORIENTATION = Math.toRadians(45);

    public static Car[] getCarsArray(int numCars) {
        
        Car[] cars = new Car[numCars];
        
        for(int i=0; i < numCars; i++) {
            cars[i] = new Car(new Position(Math.random() * Main.PANEL_ALONG, Math.random() * Main.FRAME_ACROSS),
                    new Velocity(Math.random() * MAX_VELOCITY_MAGNITUDE / 2, Math.random() * 2 * Math.PI));
        }
        
        return cars;
    }
    
    public static ArrayList<Car> getCarsList(int numCars) {
        
        ArrayList<Car> cars = new ArrayList<Car>();
        
        for(int i=0; i < numCars; i++) {
            cars.add(new Car(new Position(Math.random() * Main.PANEL_ALONG, Math.random() * Main.FRAME_ACROSS),
                    new Velocity(Math.random() * MAX_VELOCITY_MAGNITUDE / 2, Math.random() * 2 * Math.PI)));
        }
        
        return cars;
    }
    
    public Car() {
        this(new Size(ALONG, ACROSS), new Position(XPOS, YPOS), new Velocity(SPEED, ORIENTATION));
    }
    
    public Car(Position position, Velocity velocity) {
        this(new Size(ALONG, ACROSS), position, velocity);
    }
    
    /**
     * The Car class inherits the Vehicle class
     * 
     * @param size the size of the car
     * @param position the initial position of the car
     * @param velocity the initial velocity of the car
     */
    public Car(Size size, Position position, Velocity velocity) {
        super(size, position, velocity);
    }
    
    /**
     * uses weighted average to determine the color, calculated based on speed
     * 
     * @return the Color object used by Board to paint the cars
     */
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
        return String.format("Car:\tSize: %.2f * %.2f;\tPos: (%.2f, %.2f);\tVelocity: %.2f at %.2f.",
                this.getSize().getAlong(), this.getSize().getAcross(),
                this.getPosition().getXPosition(), this.getPosition().getYPosition(),
                this.getVelocity().getMagnitude(), this.getVelocity().getOrientation());
    }

    public Acceleration getAcceleration() {
        
        if(this.getCrossroad() == null) {
            return new Acceleration(0, 0);
        } else {
            return this.getCrossroad().getAccelerationFor(this);
        }
        
    }
    
    public Size getBoundingBoxSize() {
        return new Size(getSize().getAlong() * BOUNDING_BOX_FACTOR_ALONG,
                getSize().getAcross() * BOUNDING_BOX_FACTOR_ACROSS);
    }

}
