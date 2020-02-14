/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

@ClassPreamble (
        author = "William Wu",
        date = "01/16/2020",
        currentRevision = 4,
        lastModified = "02/13/2020",
        lastModifiedBy = "William Wu"
)
public abstract class Obstacle extends Body {
    
    protected Path path;
        
     /**
     * The Obstacle class inherits the Body class and is parallel to the Vehicle class
     * 
     * @param size the size of the Obstacle
     * @param position the initial position of the Obstacle
     * @param velocity the initial velocity of the Obstacle
     * @param path the path which the Obstacle follows
     */
    public Obstacle(Size size, Position position, Velocity velocity, Path path) {
        super(size, position, velocity);
        this.path = path;
    }
    
    @Override
    public String toString() {
        return String.format("Obstacle:\tSize: %.2f * %.2f;\tPos: (%.2f, %.2f);\tVelocity: %.2f at %.2f.",
                this.getSize().getWidth(), this.getSize().getHeight(),
                this.getPosition().getXPosition(), this.getPosition().getYPosition(),
                this.getVelocity().getMagnitude(), this.getVelocity().getOrientation());
    }
    
}
