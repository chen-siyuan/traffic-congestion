/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 3,
        lastModified = "01/17/2020",
        lastModifiedBy = "Daniel Chen"
)
public abstract class Vehicle extends Body {
    
    public static boolean collided(Vehicle v1, Vehicle v2) {
        
        
        
        return true;
    }
    
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
    
    public abstract Size getBoundingBoxSize();
    
    public String toString() {
        return String.format("Vehicle:\tSize: %.2f * %.2f;\tPos: (%.2f, %.2f);\tVelocity: %.2f at %.2f.",
                this.getSize().getWidth(), this.getSize().getHeight(),
                this.getPosition().getXPosition(), this.getPosition().getYPosition(),
                this.getVelocity().getMagnitude(), this.getVelocity().getOrientation());
    }
    
    public void passTime() {
        
        double xMagnitude = getVelocity().getXMagnitude() + getAcceleration().getXMagnitude() * Main.INTERVAL;
        double yMagnitude = getVelocity().getYMagnitude() + getAcceleration().getYMagnitude() * Main.INTERVAL;
        
        double newMagnitude = Math.sqrt(Math.pow(xMagnitude, 2) + Math.pow(yMagnitude, 2));
        double newOrientation = Math.atan2(yMagnitude, xMagnitude);

        setVelocity(new Velocity(newMagnitude, newOrientation));
        
        setPosition(new Position(
                getPosition().getXPosition() + getVelocity().getXMagnitude() * Main.INTERVAL,
                getPosition().getYPosition() + getVelocity().getYMagnitude() * Main.INTERVAL));
        
    }
    
}
