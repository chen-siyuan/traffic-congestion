/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.util.ArrayList;

@ClassPreamble (
        author = "Daniel Chen",
        date = "02/25/2020",
        currentRevision = 1,
        lastModified = "02/25/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Crossroad {
    
    private double laneWidth;
    private Position position;
    private double orientation;
    private ArrayList<Lane> lanes;
    
    public Crossroad(double laneWidth, Position position, double orientation) {
        
        this.laneWidth = laneWidth;
        this.position = position;
        this.orientation = orientation;
        
        this.lanes = new ArrayList<Lane>();
        
        // Assign lanes
        
    }
    
    public double getLaneWidth() {
        return laneWidth;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public double getOrientation() {
        return orientation;
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
    
    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }
    
}
