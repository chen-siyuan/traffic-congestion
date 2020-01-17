/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.util.ArrayList;

@ClassPreamble (
        author = "William Wu",
        date = "01/16/2020",
        currentRevision = 2,
        lastModified = "01/16/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Path {
    
    public static final Position END_OF_PATH = new Position(-1, -1); //this need to be discussed
    
    private ArrayList<Position> positions;
    private int currentCount;
    private boolean loop;
    
    public Path(ArrayList<Position> positions) {
        this(positions, false);
    }
    
    public Path(ArrayList<Position> positions, boolean loop) {
        
        this.positions = positions;
        this.loop = loop;
        currentCount = 0;
        
    }
    
    public ArrayList<Position> getPositions() {
        return positions;
    }
    
    public void setPositions(ArrayList<Position> positions) {
        this.positions = positions;
    }
    
    public boolean getLoop(){
        return loop;
    }
    
    public void setLoop(boolean loop) {
        this.loop = loop;
    }
    
    /**
     * 
     * @return the position currentCount points to and then increment currentCount
     * if loop is false this would return the last point, otherwise it checks if resetting is needed
     */
    public Position getNextPosition() {
        
        Position currentPosition;
        
        if(currentCount < positions.size()) {
            currentPosition = positions.get(currentCount);
        } else {
            currentPosition = positions.get(positions.size() - 1);
        }
        
        currentCount++;
        
        if(loop) {
            currentCount %= positions.size();
        }
        
        return currentPosition;
    }
}
