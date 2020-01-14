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
public class Velocity {
    
    private double speed;
    private double orientation;
    
    public Velocity(double speed, double orientation) {
        this.speed = speed;
        this.orientation = orientation;
    }

    public double getSpeed() {
        return speed;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public double getXSpeed() {
        return speed * Math.cos(orientation);
    }

    public double getYSpeed() {
        return speed * Math.sin(orientation);
    }
    
}
