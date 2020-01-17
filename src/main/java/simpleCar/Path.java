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
    
    private ArrayList<double[]> path = new ArrayList<double[]>();
    private final double[] STAY = {-1, -1};
    private int currentPoint = -1;
    private boolean loop;
    
    public Path() {
        this(false);
    }
    
    public Path(boolean loop) {
        this.loop = loop;
    }
        
    public Path(ArrayList<double[]> path, boolean loop) {
        
        this.path = path;
        this.loop = loop;
        
    }
    
    public void setPath(ArrayList<double[]> path) {
        this.path = path;
    }
    
    public ArrayList<double[]> getPath() {
        return path;
    }
    
    public boolean getLoop(){
        return loop;
    }
    
    public void setLoop(boolean loop) {
        this.loop = loop;
    }
    
    public double[] getNextPoint() {
        
        currentPoint++;
        
        if(loop) {
            currentPoint %= path.size();
        } else if(currentPoint >= this.path.size()) {
            return STAY;
        }
        
        return path.get(currentPoint);
        
    }
}
