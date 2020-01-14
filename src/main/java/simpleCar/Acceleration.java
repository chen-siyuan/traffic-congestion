/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

/**
 *
 * @author chensiyuan
 */
public class Acceleration {
    
    private double magnitude;
    private double orientation;
    
    public Acceleration(double magnitude, double orientation) {
        
        this.magnitude = magnitude;
        this.orientation = orientation;
        
    }

    public double getMagnitude() {
        return magnitude;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public double getXMagnitude() {
        return magnitude * Math.cos(orientation);
    }

    public double getYMagnitude() {
        return magnitude * Math.sin(orientation);
    }
    
}
