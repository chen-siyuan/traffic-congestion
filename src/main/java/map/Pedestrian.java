package map;

import java.awt.Color;
import java.util.ArrayList;

@ClassPreamble (
        author = "William Wu",
        date = "01/16/2020",
        currentRevision = 7,
        lastModified = "04/30/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Pedestrian extends Obstacle {
    
    public static final Color COLOR = new Color(255, 138, 0);
    public static final double BOUNDING_BOX_FACTOR_ALONG = 1.1;
    public static final double BOUNDING_BOX_FACTOR_ACROSS = 1.1;
    
    public static final double ALONG = 1;
    public static final double ACROSS = 1;
    public static final double XPOS = 75;
    public static final double YPOS = 75;
    public static final double SPEED = 15;
    public static final double ORIENTATION = Math.toRadians(45);
    
    public Pedestrian() {
        this(new Size(ALONG, ACROSS), new Position(XPOS, YPOS), new Velocity(SPEED, ORIENTATION), new Path());
    }
    
    public Pedestrian(Position position, Velocity velocity, Path path) {
        this(new Size(ALONG, ACROSS), position, velocity, path);
    }
    
    /**
     * The Pedestrian class inherits the Obstacle class and is parallel to the WildObstacle class and the DrunkDriver class
     * 
     * @param size the size of the pedestrian
     * @param position the initial position of the pedestrian
     * @param velocity the initial velocity of the pedestrian
     * @param path the path that the pedestrian follows
     */
    public Pedestrian(Size size, Position position, Velocity velocity, Path path) {
        super(size, position, velocity, path);
    }
    
    public Color getColor() {
        return COLOR;
    }
    
    public String toString() {
        return String.format("Pedestrian:\tSize: %.2f * %.2f;\tPos: (%.2f, %.2f);\tVelocity: %.2f at %.2f.",
                this.getSize().getAlong(), this.getSize().getAcross(),
                this.getPosition().getXPosition(), this.getPosition().getYPosition(),
                this.getVelocity().getMagnitude(), this.getVelocity().getOrientation());
    }
    
    /**
     * The Pedestrian class shall not have an acceleration attribute
     * 
     * @return null
     */
    public Acceleration getAcceleration() {
        return null;
    }
    
    public Size getBoundingBoxSize() {
        return new Size(getSize().getAlong() * BOUNDING_BOX_FACTOR_ALONG,
                getSize().getAcross() * BOUNDING_BOX_FACTOR_ACROSS);
    }
    
    public void passTime(double factor) {

        if(getPath() == null) return;
        
        if(getPosition().onSegment(this.getPath().getCurrentPosition(), this.getPath().getNextPosition())) {
            
            this.setVelocity(new Velocity(this.getVelocity().getMagnitude(),
                    this.getPath().getCurrentPosition().getOrientationToward(this.getPath().getNextPosition())));
            
            this.setPosition(new Position(getPosition().getXPosition() + this.getVelocity().getXMagnitude() * Main.INTERVAL * factor,
                    getPosition().getYPosition() + this.getVelocity().getYMagnitude() * Main.INTERVAL * factor));
            
        } else {
            
            this.getPath().incrementCount();
            
            setPosition(this.getPath().getCurrentPosition());
            
            passTime(factor);
            
        }

    }
    
}
