/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.awt.Color;
import java.util.ArrayList;

@ClassPreamble (
        author = "Daniel Chen",
        date = "02/14/2020",
        currentRevision = 3,
        lastModified = "02/21/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Lane {

    public static final Color COLOR = new Color(92, 92, 92);
    
    /**
     * determines which side the range of detection extends toward
     */
    public static boolean LEFT_DETECTION = true;
    
    /**
     * This factor denotes the relative width of the extended portion of the lane that is being used for detection
     * Note: doesn't include its original width; need to add 1 to get total size
     */
    public static final double DETECTION_RANGE_FACTOR = 2.;
    
    private Size size;
    private Position position;
    private double orientation;
    
    public Lane(Size size, Position position, double orientation) {
        
        this.size = size;
        this.position = position;
        this.orientation = orientation;
        
    }
    
    public Size getSize() {
        return size;
    }
    
    /**
     * 
     * @return the size of the complete range of detection, including the lane itself
     */
    public Size getDetectionSize() {
        return new Size(size.getHeight(), size.getWidth() * (1 + DETECTION_RANGE_FACTOR));
    }

    public Position getPosition() {
        return position;
    }
    
    /**
     * 
     * @return the center of the complete range of detection, including the lane itself
     */
    public Position getDetectionPosition() {
        
        double angle = orientation + (LEFT_DETECTION ? -1 : 1) * Math.PI / 2;
        
        return new Position(
                position.getXPosition() + Math.cos(angle) * getDetectionSize().getWidth() * DETECTION_RANGE_FACTOR / 2,
                position.getYPosition() + Math.sin(angle) * getDetectionSize().getWidth() * DETECTION_RANGE_FACTOR / 2);
    }
    
    public ArrayList<Position> getCornerPositions() {
        
        ArrayList<Position> cornerPositions = new ArrayList<Position>();
        
        double halfDiagonal = Math.sqrt(Math.pow(size.getWidth(), 2) + Math.pow(size.getHeight(), 2)) / 2;
        double angle1 = orientation + Math.atan2(size.getHeight(), size.getWidth());
        double angle2 = orientation - Math.atan2(size.getHeight(), size.getWidth());
        
//        System.out.println(halfDiagonal);
//        System.out.println(angle / Math.PI);
        
        cornerPositions.add(new Position(
                position.getXPosition() + halfDiagonal * Math.cos(angle1),
                position.getYPosition() + halfDiagonal * Math.sin(angle1)));
        
        cornerPositions.add(new Position(
                position.getXPosition() - halfDiagonal * Math.cos(angle1),
                position.getYPosition() - halfDiagonal * Math.sin(angle1)));
        
        cornerPositions.add(new Position(
                position.getXPosition() + halfDiagonal * Math.cos(angle2),
                position.getYPosition() + halfDiagonal * Math.sin(angle2)));
        
        cornerPositions.add(new Position(
                position.getXPosition() - halfDiagonal * Math.cos(angle2),
                position.getYPosition() - halfDiagonal * Math.sin(angle2)));
        
        return cornerPositions;
    }
    
    public ArrayList<Position> getDetectionCornerPositions() {
        
        Size detectionSize = getDetectionSize();
        Position detectionPosition = getDetectionPosition();
        
        ArrayList<Position> cornerPositions = new ArrayList<Position>();
        
        double halfDiagonal = Math.sqrt(Math.pow(detectionSize.getWidth(), 2) + Math.pow(detectionSize.getHeight(), 2)) / 2;
        double angle1 = orientation + Math.atan2(detectionSize.getHeight(), detectionSize.getWidth());
        double angle2 = orientation - Math.atan2(detectionSize.getHeight(), detectionSize.getWidth());
        
//        System.out.println(halfDiagonal);
//        System.out.println(angle / Math.PI);
        
        cornerPositions.add(new Position(
                detectionPosition.getXPosition() + halfDiagonal * Math.cos(angle1),
                detectionPosition.getYPosition() + halfDiagonal * Math.sin(angle1)));
        
        cornerPositions.add(new Position(
                detectionPosition.getXPosition() - halfDiagonal * Math.cos(angle1),
                detectionPosition.getYPosition() - halfDiagonal * Math.sin(angle1)));
        
        cornerPositions.add(new Position(
                detectionPosition.getXPosition() + halfDiagonal * Math.cos(angle2),
                detectionPosition.getYPosition() + halfDiagonal * Math.sin(angle2)));
        
        cornerPositions.add(new Position(
                detectionPosition.getXPosition() - halfDiagonal * Math.cos(angle2),
                detectionPosition.getYPosition() - halfDiagonal * Math.sin(angle2)));
        
        return cornerPositions;
    }

    public double getOrientation() {
        return orientation;
    }

    public Color getColor() {
        return COLOR;
    }

    /**
     * 
     * @param otherPosition the position to be tested for
     * @return whether or not the rectangular region of this Lane contains the Position
     */
    public boolean onLane(Position otherPosition) {
        
        ArrayList<Position> cornerPositions = getCornerPositions();
        
        double maxLength = 0.;
        int maxIndex = 0;
        
        for(int i=1; i < 4; i++) {
            
            if(cornerPositions.get(0).distanceTo(cornerPositions.get(i)) > maxLength) {
                maxLength = cornerPositions.get(0).distanceTo(cornerPositions.get(i));
                maxIndex = i;
            }
            
        }
        
        int index0;
        int index1;
        int index2;
        int index3;
        
        if(maxIndex == 1) {
            index0 = 0;
            index1 = 2;
            index2 = 1;
            index3 = 3;
        } else if(maxIndex == 2) {
            index0 = 0;
            index1 = 1;
            index2 = 2;
            index3 = 3;
        } else {
            index0 = 0;
            index1 = 1;
            index2 = 3;
            index3 = 2;
        }
        
        double sumDistances = otherPosition.distanceTo(cornerPositions.get(index0), cornerPositions.get(index1))
                + otherPosition.distanceTo(cornerPositions.get(index1), cornerPositions.get(index2))
                + otherPosition.distanceTo(cornerPositions.get(index2), cornerPositions.get(index3))
                + otherPosition.distanceTo(cornerPositions.get(index3), cornerPositions.get(index0));
        
        return Math.abs(sumDistances - size.getWidth() - size.getHeight()) <= Main.THRESHOLD;
    }
    
    /**
     * 
     * @param body the body to be tested for
     * @param needAll need all points to be onLane to return true
     * @return whether or not the rectangular region of this Lane contains all points or any point of this body
     */
    public boolean onLane(Body body, boolean needAll) {
        
        for(Position otherPosition: body.getCornerPositions()) {
            
            if(onLane(otherPosition) != needAll) {
                return !needAll;
            }
            
        }
        
        return needAll;
    }
    
    /**
     * 
     * @param otherPosition the position to be tested for
     * @return whether or not the position is in the detection range(including the lane itself)
     */
    public boolean inRange(Position otherPosition) {
        
        ArrayList<Position> detectionCornerPositions = getDetectionCornerPositions();
        
        double maxLength = 0.;
        int maxIndex = 0;
        
        for(int i=1; i < 4; i++) {
            
            if(detectionCornerPositions.get(0).distanceTo(detectionCornerPositions.get(i)) > maxLength) {
                maxLength = detectionCornerPositions.get(0).distanceTo(detectionCornerPositions.get(i));
                maxIndex = i;
            }
            
        }
        
        int index0;
        int index1;
        int index2;
        int index3;
        
        if(maxIndex == 1) {
            index0 = 0;
            index1 = 2;
            index2 = 1;
            index3 = 3;
        } else if(maxIndex == 2) {
            index0 = 0;
            index1 = 1;
            index2 = 2;
            index3 = 3;
        } else {
            index0 = 0;
            index1 = 1;
            index2 = 3;
            index3 = 2;
        }
        
        double sumDistances = otherPosition.distanceTo(detectionCornerPositions.get(index0), detectionCornerPositions.get(index1))
                + otherPosition.distanceTo(detectionCornerPositions.get(index1), detectionCornerPositions.get(index2))
                + otherPosition.distanceTo(detectionCornerPositions.get(index2), detectionCornerPositions.get(index3))
                + otherPosition.distanceTo(detectionCornerPositions.get(index3), detectionCornerPositions.get(index0));
        
        return Math.abs(sumDistances - getDetectionSize().getWidth() - getDetectionSize().getHeight()) <= Main.THRESHOLD;
        
    }
    
    /**
     * 
     * @param body the body to be tested for
     * @param needAll
     * @return whether or not the range of detection (including the Lane itself) contains all points or any point of this body
     */
    public boolean inRange(Body body, boolean needAll) {
        
        for(Position otherPosition: body.getCornerPositions()) {
            
            if(inRange(otherPosition) != needAll) {
                return !needAll;
            }
            
        }
        
        return needAll;
    }
    
}
