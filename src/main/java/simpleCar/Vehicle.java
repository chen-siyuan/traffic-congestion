/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 1,
        lastModified = "01/14/2020",
        lastModifiedBy = "Daniel Chen"
)
public abstract class Vehicle extends Body {
    
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

    public abstract Acceleration getAcceleration();
    
    public String toString() {
        return String.format("Vehicle:\tSize: %.2f * %.2f;\tPos: (%.2f, %.2f);\tVelocity: %.2f at %.2f.",
                this.getSize().getWidth(), this.getSize().getHeight(),
                this.getPosition().getXPosition(), this.getPosition().getYPosition(),
                this.getVelocity().getMagnitude(), this.getVelocity().getOrientation());
    }
    
    public void passTime(double t) {
        
        double xSpeed = getVelocity().getXMagnitude() + getAcceleration().getXMagnitude() * t;
        double ySpeed = getVelocity().getYMagnitude() + getAcceleration().getYMagnitude() * t;
        
        double newSpeed = Math.sqrt(Math.pow(xSpeed, 2) + Math.pow(ySpeed, 2));
        double newOrientation = Math.atan2(ySpeed, xSpeed);

        setVelocity(new Velocity(newSpeed, newOrientation));
        
        setPosition(new Position(
                getPosition().getXPosition() + getVelocity().getXMagnitude() * t,
                getPosition().getYPosition() + getVelocity().getYMagnitude() * t));
        
    }

}
