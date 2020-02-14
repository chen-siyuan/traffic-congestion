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
        lastModified = "02/14/2020",
        lastModifiedBy = "Drew Kirk"
)
public class Road {
    
    public int[] road = new int[5];
    
    private Size size;
    private Position position;
    private Velocity velocity;
    
    public Road(Size size, Position position, Velocity velocity) {
        
        this.size = size;
        this.position = position;
        this.velocity = velocity;
        
    }
    
    public static int[] drawRoad(int laneNum, int length, boolean direction, int startPosX, int startPosY) {
        int[] road = new int[6];
        if(direction == true) {
           road[0] = startPosX;
           road[1] = road[0] + 10 * laneNum;
           road[2] = startPosY;
           road[3] = road[2] + length;
           road[4] = 1;
           road[5] = laneNum;
           
        }
        
        if(direction == false) {
            
           road[0] = startPosX;
           road[1] = road[0] + length;
           road[2] = startPosY;
           road[3] = road[2] + 10 * laneNum;
           road[4] = 2;
           road[5] = laneNum;
           
        }
      return road;  
    }
    
    public static int whichLane(int[] road) {
        

        int[] carPos = new int[]{12,13,22,28};
        ArrayList<Integer> cornerPositions = new ArrayList<Integer>();
        for(int i = 0; i < 4; i++){
            cornerPositions.add(carPos[i]);  
        }
        for(int i = 0; i < (road[5] - 1); i++){
            if(road[4] == 2){
            if((road[2] + 10 * i) < carPos[2] && road[2] + 10 * (i+1) > carPos[3] && 
                road[0] < carPos[0] && road[1] > carPos[1]) {
                        return i;
            }
            }
        else{
            if((road[0] + 10 * i) < carPos[0] && road[0] + 10 * (i+1) > carPos[1] && 
                road[2] < carPos[2] && road[3] > carPos[3]) {
                        return i;   
                }
        }
        }
        return -1;
    
    
}

}
