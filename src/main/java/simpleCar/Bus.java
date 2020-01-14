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
public class Bus extends Vehicle {
    
    public static final Color COLOR = new Color(84, 184, 96);
    public static final double MAX_SPEED = 30;
    public static final double MAX_ACCELERATION = 5;
    
    // all real time
    public static final double WIDTH = 9;
    public static final double HEIGHT = 2.5;
    public static final double XPOS = 0;
    public static final double YPOS = 0;
    public static final double SPEED = 20;
    public static final double ORIENTATION = Math.toRadians(45);
    
    // Generators
    
    public static Bus[] getBusesArray(int numBuses) {
        
        Bus[] buses = new Bus[numBuses];
        
        for(int i=0; i < numBuses; i++) {
            buses[i] = new Bus(new Position(Math.random() * Main.PANEL_WIDTH, Math.random() * Main.FRAME_HEIGHT),
                    new Velocity(Math.random() * MAX_SPEED, Math.random() * 2 * Math.PI));
        }
        
        return buses;
    }
    
    public static ArrayList<Bus> getBusesList(int numBuses) {
        
        ArrayList<Bus> buses = new ArrayList<Bus>();
        
        for(int i=0; i < numBuses; i++) {
            buses.add(new Bus(new Position(Math.random() * Main.PANEL_WIDTH, Math.random() * Main.FRAME_HEIGHT),
                    new Velocity(Math.random() * MAX_SPEED, Math.random() * 2 * Math.PI)));
        }
        
        return buses;
    }
    
    // Constructors
    
    public Bus() {
        this(new Size(WIDTH, HEIGHT), new Position(XPOS, YPOS), new Velocity(SPEED, ORIENTATION));
    }
    
    public Bus(Position p, Velocity initV) {
        this(new Size(WIDTH, HEIGHT), p, initV);
    }

    private Bus(Size s, Position p, Velocity initV) {
        super(s, p, initV);
    }
    
    // Methods
    
    public Color getColor() {
        return COLOR;
    }

    public String toString() {
        
        return String.format("Bus:\tSize: %.2f * %.2f;\tPos: (%.2f, %.2f);\tVelocity: %.2f at %.2f.",
                this.getWidth(), this.getHeight(),
                this.getXPosition(), this.getYPosition(),
                this.getSpeed(), this.getOrientation());
    }

    public Acceleration getAcceleration() {
        return new Acceleration(MAX_ACCELERATION, getVelocity().getOrientation());
    }
    
}
