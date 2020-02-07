/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.util.ArrayList;

@ClassPreamble (
        author = "Drew Kirk",
        date = "01/17/2020",
        currentRevision = 1.1,
        lastModified = "01/17/2020",
        lastModifiedBy = "Drew Kirk"
)
public class Road {
    
    public int[] road = new int[5];
    
    public void drawRoad(int laneNum,int length, boolean direction, int startPosX, int startPosY) {
        
        if(direction == true) {
            
           this.road[0] = startPosX;
           this.road[1] = this.road[0] + 10 * laneNum;
           this.road[2] = startPosY;
           this.road[3] = this.road[2] + length;
           this.road[4] = 1;
           this.road[5] = laneNum;
           
        }
        
        if(direction == true) {
            
           this.road[0] = startPosX;
           this.road[1] = this.road[0] + length;
           this.road[2] = startPosY;
           this.road[3] = this.road[2] + 10 * laneNum;
           this.road[4] = 2;
           this.road[5] = laneNum;
           
        }
        
    }
    
    public boolean inRoad(Body body) {
        
        boolean inRoad = true;
        
        ArrayList<Position> cornerPositions = new ArrayList<Position>();
        for(int i = 0; i < 4; i++){
            
        }
        
        if(this.road[0] > Body.getCornerPositions(body.getSize(), body.getPosition(), body.getVelocity()).get(0) || xPos > this.road[1]) {
            inRoad = false;
        }
        
        if(this.road[2] > yPos || yPos > this.road[3]) {
            inRoad = false;
        }
        
        return inRoad;
    }
    
    public int inLane(int xPos, int yPos) {
        
        if(this.road[4] == 1) {
            
            for(int i = 0; i < this.road[5]; i++) {
                
                if(this.road[0] + 10 * i > xPos || xPos > this.road[0] + 10 * i + 1) {
                    // dosomething
                }
                
            }
            
        } 
        
        return 1;
    }

}
