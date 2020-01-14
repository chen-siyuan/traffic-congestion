/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author chensiyuan
 */
public class Car extends Vehicle {
    
    public static final Color COLOR = new Color(227, 86, 77);
    public static final double MAX_SPEED = 60;
    public static final double MAX_ACCELERATION = 8;
    
    // all real time
    public static final double WIDTH = 4.5;
    public static final double HEIGHT = 1.8;
    public static final double XPOS = 0;
    public static final double YPOS = 0;
    public static final double SPEED = 20;
    public static final double ORIENTATION = Math.toRadians(45);

    // Generators
    
    public static Car[] getCarsArray(int numCars) {
        
        Car[] cars = new Car[numCars];
        
        for(int i=0; i < numCars; i++) {
            cars[i] = new Car(new Position(Math.random() * Main.PANEL_WIDTH, Math.random() * Main.FRAME_HEIGHT),
                    new Velocity(Math.random() * MAX_SPEED / 2, Math.random() * 2 * Math.PI));
        }
        
        return cars;
    }
    
    public static ArrayList<Car> getCarsList(int numCars) {
        
        ArrayList<Car> cars = new ArrayList<Car>();
        
        for(int i=0; i < numCars; i++) {
            cars.add(new Car(new Position(Math.random() * Main.PANEL_WIDTH, Math.random() * Main.FRAME_HEIGHT),
                    new Velocity(Math.random() * MAX_SPEED / 2, Math.random() * 2 * Math.PI)));
        }
        
        return cars;
    }
    
    // Constructors
    
    public Car() {
        this(new Size(WIDTH, HEIGHT), new Position(XPOS, YPOS), new Velocity(SPEED, ORIENTATION));
    }
    
    public Car(Position p, Velocity initV) {
        this(new Size(WIDTH, HEIGHT), p, initV);
    }
    
    private Car(Size s, Position p, Velocity initV) {
        super(s, p, initV);
    }
    
    // Methods

    public Color getColor() {
        return COLOR;
    }

    public String toString() {
        
        return String.format("Car:\tSize: %.2f * %.2f;\tPos: (%.2f, %.2f);\tVelocity: %.2f at %.2f.",
                this.getWidth(), this.getHeight(),
                this.getXPosition(), this.getYPosition(),
                this.getSpeed(), this.getOrientation());
    }

    public Acceleration getAcceleration() {
        return new Acceleration(MAX_ACCELERATION, getVelocity().getOrientation());
    }

}
