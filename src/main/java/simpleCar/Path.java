/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traffic;

import java.util.ArrayList;

@ClassPreamble (
        author = "William Wu",
        date = "01/16/2020",
        currentRevision = 1,
        lastModified = "01/16/2020",
        lastModifiedBy = "William Wu"
)

public class Path {
    
    private ArrayList<double[]> pathPoints = new ArrayList<>();
    private final double[] STAY = {-1, -1};
    private int currentPoint = -1;
    private boolean loop = false;
        
    public Path(boolean loop, ArrayList<double[]> pathPoints){
        this.pathPoints = pathPoints;
        this.loop = loop;
    }
    
    public Path(boolean loop){
        this.loop = loop;
    }
    
    public Path(){
        this(false);
    }
    
    public void setPath(ArrayList<double[]> pathPoints){
        this.pathPoints = pathPoints;
    }
    
    public ArrayList<double[]> getPath(){
        return this.pathPoints;
    }
    
    public boolean getLoop(){
        return this.loop;
    }
    
    public void setLoop(boolean loop){
        this.loop = loop;
    }
    
    public double[] getNextPt(){
        currentPoint++;
        if(loop){
            currentPoint %= this.pathPoints.size();
        }
        else{
            if(currentPoint >= this.pathPoints.size()){
                return this.STAY;
            }
        }
        return this.pathPoints.get(currentPoint);
    }
}
