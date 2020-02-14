/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.awt.Color;
import java.util.ArrayList;

@ClassPreamble (
        author = "William Wu",
        date = "01/16/2020",
        currentRevision = 4,
        lastModified = "02/13/2020",
        lastModifiedBy = "William Wu"
)
public class Pedestrian extends Obstacle {
    
    public static final Color COLOR = new Color(255, 138, 0);
    public static final double MAX_VELOCITY_MAGNITUDE = 12;
    public static final double MAX_ACCELERATION_MAGNITUDE = 9;
    public static final double BOUNDING_BOX_FACTOR_WIDTH = 1.1;
    public static final double BOUNDING_BOX_FACTOR_HEIGHT = 1.1;
    
    public static final double WIDTH = 0.5;
    public static final double HEIGHT = 0.5;
    public static final double XPOS = 0;
    public static final double YPOS = 0;
    public static final double SPEED = 0;
    public static final double ORIENTATION = Math.toRadians(45);
    
    public static Pedestrian[] getPedestriansArray(int numPedestrians) {
        
        Pedestrian[] pedestrians = new Pedestrian[numPedestrians];
        
        for(int i=0; i < numPedestrians; i++) {
            pedestrians[i] = new Pedestrian(new Position(Math.random() * Main.PANEL_WIDTH, Math.random() * Main.FRAME_HEIGHT),
                    new Velocity(Math.random() * MAX_VELOCITY_MAGNITUDE / 2, Math.random() * 2 * Math.PI), 
                    new Path(new Position(Math.random() * Main.PANEL_WIDTH, Math.random() * Main.PANEL_HEIGHT),
                            new Position(Math.random() * Main.PANEL_WIDTH, Math.random() * Main.PANEL_HEIGHT)));
        }
        
        return pedestrians;
    }
    
    public static ArrayList<Pedestrian> getPedestriansList(int numPedestrians) {
        
        ArrayList<Pedestrian> pedestrians = new ArrayList<>();
        
        for(int i=0; i < numPedestrians; i++) {
            pedestrians.add(new Pedestrian(new Position(Math.random() * Main.PANEL_WIDTH, Math.random() * Main.FRAME_HEIGHT),
                    new Velocity(Math.random() * MAX_VELOCITY_MAGNITUDE / 2, Math.random() * 2 * Math.PI), 
                    new Path(new Position(Math.random() * Main.PANEL_WIDTH, Math.random() * Main.PANEL_HEIGHT),
                            new Position(Math.random() * Main.PANEL_WIDTH, Math.random() * Main.PANEL_HEIGHT))));
        }
        
        return pedestrians;
    }
    
    public Pedestrian() {
        this(new Size(WIDTH, HEIGHT), new Position(XPOS, YPOS), new Velocity(SPEED, ORIENTATION), new Path());
    }
    
    public Pedestrian(Position position, Velocity velocity, Path path) {
        this(new Size(WIDTH, HEIGHT), position, velocity, path);
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
    
    @Override
    public Color getColor() {
        return COLOR;
    }
    
    @Override
    public String toString() {
        return String.format("Pedestrian:\tSize: %.2f * %.2f;\tPos: (%.2f, %.2f);\tVelocity: %.2f at %.2f.",
                this.getSize().getWidth(), this.getSize().getHeight(),
                this.getPosition().getXPosition(), this.getPosition().getYPosition(),
                this.getVelocity().getMagnitude(), this.getVelocity().getOrientation());
    }
    
    @Override
    public Acceleration getAcceleration() {
        return new Acceleration(MAX_ACCELERATION_MAGNITUDE, getVelocity().getOrientation());
    }
    
    public Size getBoundingBoxSize() {
        return new Size(getSize().getWidth() * BOUNDING_BOX_FACTOR_WIDTH,
                getSize().getHeight() * BOUNDING_BOX_FACTOR_HEIGHT);
    }
    
    @Override
    public void passTime() { 
        
        if(getPosition().onTheLine(path.getCurrentPosition(), path.getNextPosition())) {
            setVelocity(new Velocity(getVelocity().getMagnitude(),
                    getVelocity().CalculateOrientation(path.getCurrentPosition(), path.getNextPosition())));
            
            double xMagnitude = getVelocity().getXMagnitude() + getAcceleration().getXMagnitude() * Main.INTERVAL;
            double yMagnitude = getVelocity().getYMagnitude() + getAcceleration().getYMagnitude() * Main.INTERVAL;
            
            setPosition(new Position(
                    getPosition().getXPosition() + xMagnitude * Main.INTERVAL, 
                    getPosition().getYPosition() + yMagnitude * Main.INTERVAL));
            
            setVelocity(new Velocity(Math.sqrt(Math.pow(xMagnitude, 2) + Math.pow(yMagnitude, 2)),
                    getVelocity().getOrientation()));
        } else {
            setPosition(path.getNextPosition());
            path.incrementCount();
            
            passTime();
        }
    }
    
}
