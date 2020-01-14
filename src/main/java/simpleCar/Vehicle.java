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
public abstract class Vehicle extends Body {
    
    // Constructors
    
    public Vehicle(Size size, Position position, Velocity velocity) {
        super(size, position, velocity);
    }

    public abstract Acceleration getAcceleration();
    
    // Auxillary methods
    
    public String toString() {
        return String.format("Vehicle:\tSize: %.2f * %.2f;\tPos: (%.2f, %.2f);\tVelocity: %.2f at %.2f.",
                this.getWidth(), this.getHeight(),
                this.getXPosition(), this.getYPosition(),
                this.getSpeed(), this.getOrientation());
    }
    
    // Actual Methods

    // t is in real time
    
    public void passTime(double t) {
        
        double xSpeed = getVelocity().getXSpeed() + getAcceleration().getXMagnitude() * t;
        double ySpeed = getVelocity().getYSpeed() + getAcceleration().getYMagnitude() * t;
        double speed = Math.sqrt(Math.pow(xSpeed, 2) + Math.pow(ySpeed, 2));
        double orientation = Math.atan2(ySpeed, xSpeed);

        Velocity newVelocity = new Velocity(speed, orientation);
        setVelocity(newVelocity);
        
        Position newPos = new Position(getPosition().getXPosition() + getVelocity().getXSpeed() * t,
                getPosition().getYPosition() + getVelocity().getYSpeed() * t);
        
        setPosition(newPos);
        
    }

}
