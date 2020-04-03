/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;
import java.util.Arrays;

@ClassPreamble (
        author = "William Wu",
        date = "01/16/2020",
        currentRevision = 4.2,
        lastModified = "02/14/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Path {
    
    public static final Position END_OF_PATH = new Position(-1, -1);
    
    private ArrayList<Position> positions;
    private int currentCount;
    private boolean loop;
    
    public Path(Position... args) {
        this(false, args);
    }
    
    public Path(boolean loop, Position... args) {
        this(loop, new ArrayList<Position>(Arrays.asList(args)));
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
    
    public void setPositions(Position... args) {
        this.positions = (ArrayList)Arrays.asList(args);
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
     * @return the position of the currentCount, does not change currentCount
     */
    public Position getCurrentPosition() {
        return positions.get(standardize(currentCount));
    }
    
    /**
     * 
     * @return the position of the currentCount+1, does not change currentCount
     * if loop is true, this would get the starting Position at the end of each loop
     */
    public Position getNextPosition() {
        return positions.get(standardize(currentCount + 1));
    }
    
    /**
     * increases the currentCount by 1
     * if loop, reset currentCount at the end of the loop
     */
    public void incrementCount() {
        currentCount = standardize(currentCount + 1);
    }
    
    public int standardize(int count) {
        
        if(loop) {
            count %= positions.size();
        } else {
            if(count >= positions.size()) {
                count = positions.size() - 1;
            }
        }
        
        return count;
    }
}
