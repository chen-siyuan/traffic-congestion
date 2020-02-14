/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 3.1,
        lastModified = "02/14/2020",
        lastModifiedBy = "William Wu"
)
public class Position {
    
    public static double distanceBetween(Position position1, Position position2) {
        return Math.sqrt(Math.pow(position1.getXPosition() - position2.getXPosition(), 2)
                + Math.pow(position1.getYPosition() - position2.getYPosition(), 2));
    }
    
    private double xPosition;
    private double yPosition;
    
    /**
     * This class is used as a basis for other classes
     * 
     * @param xPosition the horizontal position of the Body object
     * @param yPosition the vertical position of the Body object
     */
    public Position(double xPosition, double yPosition) {
        
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        
    }

    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }
    
    /**
     * Calculates the distance from this Position to the otherPosition
     * 
     * @param otherPosition the other point
     * @return the distance between the two points
     */
    public double distanceTo(Position otherPosition) {
        return Math.sqrt(Math.pow(xPosition - otherPosition.getXPosition(), 2)
                + Math.pow(yPosition - otherPosition.getYPosition(), 2));
    }
    
    /**
     * Calculates the distance from this Position to the line connecting otherPosition1 and otherPosition2
     * 
     * @param otherPosition1 one point on the line
     * @param otherPosition2 the other point on the line
     * @return the distance between this point and the line
     */
    public double distanceTo(Position otherPosition1, Position otherPosition2) {
        
        double crossProduct = Math.abs(
                (xPosition - otherPosition1.getXPosition()) * (otherPosition2.getYPosition() - otherPosition1.getYPosition())
                - (yPosition - otherPosition1.getYPosition()) * (otherPosition2.getXPosition() - otherPosition1.getXPosition()));
        
        double baseLength = Position.distanceBetween(otherPosition1, otherPosition2);
        
        if(baseLength < Main.THRESHOLD) {
            return distanceTo(otherPosition1);
        }
        
        return crossProduct / baseLength;
    }
    
    public boolean onTheLine(Position startOfLine, Position endOfLine) {
        if(distanceTo(startOfLine, endOfLine) <= Main.THRESHOLD) {
            return true;
        } else {
            return false;
        }
    }
    
}
