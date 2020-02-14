/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 3,
        lastModified = "02/13/2020",
        lastModifiedBy = "William Wu"
)
public class Velocity {
    
    private double magnitude;
    private double orientation;
    
    /**
     * This class is used as a basis for other classes
     * 
     * @param magnitude the length of the velocity vector of the Body object
     * @param orientation the direction of the velocity vector of the Body object
     */
    public Velocity(double magnitude, double orientation) {
        
        this.magnitude = magnitude;
        this.orientation = orientation;
        
    }

    public double getMagnitude() {
        return magnitude;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }
    
    public double CalculateOrientation(Position startOfLine, Position endOfLine) {
        return Math.atan2((endOfLine.getYPosition() - startOfLine.getYPosition()),
                (endOfLine.getXPosition() - startOfLine.getXPosition()));
    }

    public double getXMagnitude() {
        return magnitude * Math.cos(orientation);
    }

    public double getYMagnitude() {
        return magnitude * Math.sin(orientation);
    }
    
}
