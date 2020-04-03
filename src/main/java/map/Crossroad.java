/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;

@ClassPreamble (
        author = "Daniel Chen",
        date = "02/25/2020",
        currentRevision = 3,
        lastModified = "04/03/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Crossroad {
    
    private double laneWidth;
    private Position position;
    private ArrayList<Lane> lanes;
    
    public Crossroad(Position position, double laneWidth) {
        
        this.position = position;
        this.laneWidth = laneWidth;
        
        this.lanes = new ArrayList<Lane>();
        
        this.lanes.add(new Lane(new Size(Main.FRAME_ALONG, laneWidth), new Position(0.5 * Main.FRAME_ALONG, position.getYPosition() - 0.5 * laneWidth), Math.PI * 0 / 2));
        this.lanes.add(new Lane(new Size(Main.FRAME_ACROSS, laneWidth), new Position(position.getXPosition() + 0.5 * laneWidth, 0.5 * Main.FRAME_ACROSS), Math.PI * 1 / 2));
        this.lanes.add(new Lane(new Size(Main.FRAME_ALONG, laneWidth), new Position(0.5 * Main.FRAME_ALONG, position.getYPosition() + 0.5 * laneWidth), Math.PI * 2 / 2));
        this.lanes.add(new Lane(new Size(Main.FRAME_ACROSS, laneWidth), new Position(position.getXPosition() - 0.5 * laneWidth, 0.5 * Main.FRAME_ACROSS), Math.PI * 3 / 2));
        
    }
    
    public double getLaneWidth() {
        return laneWidth;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public ArrayList<Lane> getLanes() {
        return lanes;
    }
    
    public void setLaneWidth(double laneWidth) {
        this.laneWidth = laneWidth;
    }
    
    public void setPosition(Position position) {
        this.position = position;
    }
    
    /**
     * 
     * @param position the position of the point
     * @return whether or not the position of the point is in the center of the crossroad
     */
    public boolean inCenter(Position position) {
        
        return (this.position.getXPosition() - this.laneWidth <= position.getXPosition())
                && (this.position.getXPosition() + this.laneWidth >= position.getXPosition())
                && (this.position.getYPosition() - this.laneWidth <= position.getYPosition())
                && (this.position.getYPosition() + this.laneWidth >= position.getYPosition());
        
    }
    
    /**
     * 
     * @param vehicle the vehicle to be tested
     * @return whether or not the center of the vehicle is in the turning area of the crossroad
     */
    public boolean inCenter(Vehicle vehicle) {
        return inCenter(vehicle.getPosition());
    }
    
    /**
     * 
     * @param vehicle
     * @return the turning acceleration based on the vehicle, should be a combination of tangential and angular acceleration
     */
    public Acceleration getAcclerationTurning(Vehicle vehicle) {
        return null;
    }
    
    /**
     * 
     * @param vehicle
     * @return the acceleration for the vehicle in this crossroad
     */
    public Acceleration getAccelerationFor(Vehicle vehicle) {
        
//        if(inCenter(vehicle)) {
//            return getAccelerationTurning(vehicle);
//        }
//        
//        assign to lane and get acceleration
//            
        return null;
    }
    
}
