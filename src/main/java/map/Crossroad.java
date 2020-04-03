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
        currentRevision = 2,
        lastModified = "04/02/2020",
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
        
        this.lanes.add(new Lane(new Size(Main.FRAME_WIDTH, laneWidth), new Position(0.5 * Main.FRAME_WIDTH, position.getYPosition() - 0.5 * laneWidth), Math.PI * 0 / 2));
        this.lanes.add(new Lane(new Size(Main.FRAME_HEIGHT, laneWidth), new Position(position.getXPosition() + 0.5 * laneWidth, 0.5 * Main.FRAME_HEIGHT), Math.PI * 1 / 2));
        this.lanes.add(new Lane(new Size(Main.FRAME_WIDTH, laneWidth), new Position(0.5 * Main.FRAME_WIDTH, position.getYPosition() + 0.5 * laneWidth), Math.PI * 2 / 2));
        this.lanes.add(new Lane(new Size(Main.FRAME_HEIGHT, laneWidth), new Position(position.getXPosition() - 0.5 * laneWidth, 0.5 * Main.FRAME_HEIGHT), Math.PI * 3 / 2));
        
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
    
}
