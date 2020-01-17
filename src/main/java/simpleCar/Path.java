/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.util.ArrayList;
import java.util.Arrays;

@ClassPreamble (
        author = "William Wu",
        date = "01/16/2020",
        currentRevision = 3.1,
        lastModified = "01/17/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Path {
    
    public static final Position END_OF_PATH = new Position(-1, -1); //this need to be discussed
    
    private ArrayList<Position> positions;
    private int currentCount;
    private boolean loop;
    
    public Path(Position... args) {
        this(false, args);
    }
    
    public Path(boolean loop, Position... args) {
        this(loop, (ArrayList)Arrays.asList(args));
    }
    
    public Path(ArrayList<Position> positions) {
        this(false, positions);
    }
    
    /**
     * A Path is an object that tracks the path of an Obstacle
     * 
     * @param loop whether to loop the list of positions or not
     * @param positions array list of positions
    **/
    public Path(boolean loop, ArrayList<Position> positions) {
        
        this.loop = loop;
        this.positions = positions;
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
