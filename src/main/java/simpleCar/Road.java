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
        currentRevision = 3,
        lastModified = "02/21/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Road {
    
    public Road() {
        
    }
    
//    public int[] road = new int[5];
//    
//    private Size size;
//    private Position position;
//    private double orientation;
//    private ArrayList<Lane> lanes;
//    
//    /**
//     * 
//     * @param size the dimensions of the road; width represents length, height represents width
//     * @param position the position of the center of the road
//     * @param orientation the direction of the road
//     * @param numLanes the number of lanes of the road
//     */
//    public Road(Size size, Position position, double orientation, int numLanes) {
//        
//        this.size = size;
//        this.position = position;
//        this.orientation = orientation;
//        
//        this.lanes = new ArrayList<Lane>();
//        
//        double laneWidth = size.getHeight() / numLanes;
//        
//        double xIncrement = Math.cos(orientation + Math.PI / 2) * laneWidth;
//        double yIncrement = Math.sin(orientation + Math.PI / 2) * laneWidth;
//        
//        double xEndPoint = position.getXPosition() - xIncrement * (numLanes - 1) / 2;
//        double yEndPoint = position.getYPosition() - yIncrement * (numLanes - 1) / 2;
//        
//        for(int i=0; i < numLanes; i++) {
//            
//            lanes.add(new Lane(
//                    new Size(size.getWidth(), size.getHeight() / numLanes),
//                    new Position(xEndPoint + xIncrement * i, yEndPoint + yIncrement * i),
//                    orientation));
//            
//        }
//        
//    }
//    
//    public Size getSize() {
//        return size;
//    }
//    
//    public Position getPosition() {
//        return position;
//    }
//    
//    public double getOrientation() {
//        return orientation;
//    }
//    
//    public ArrayList<Lane> getLanes() {
//        return lanes;
//    }
//    
//    public int getNumLanes() {
//        return lanes.size();
//    }
//    
//    public boolean contains(Position otherPosition) {
//        
//        for(Lane lane: lanes) {
//            
//            if(lane.contains(otherPosition)) {
//                return true;
//            }
//            
//        }
//        
//        return false;
//    }
//    
////    public static int whichLane(int[] road) {
////        
////        int[] carPos = new int[]{12,13,22,28};
////        ArrayList<Integer> cornerPositions = new ArrayList<Integer>();
////        for(int i = 0; i < 4; i++){
////            cornerPositions.add(carPos[i]);  
////        }
////        for(int i = 0; i < (road[5] - 1); i++){
////            if(road[4] == 2){
////            if((road[2] + 10 * i) < carPos[2] && road[2] + 10 * (i+1) > carPos[3] && 
////                road[0] < carPos[0] && road[1] > carPos[1]) {
////                        return i;
////            }
////            }
////        else{
////            if((road[0] + 10 * i) < carPos[0] && road[0] + 10 * (i+1) > carPos[1] && 
////                road[2] < carPos[2] && road[3] > carPos[3]) {
////                        return i;   
////                }
////        }
////        }
////        return -1;
////    }

}
