/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 4,
        lastModified = "02/18/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Velocity {
    
    public static double magnitudeFromComponents(double xComponent, double yComponent) {
        return Math.sqrt(Math.pow(xComponent, 2) + Math.pow(yComponent, 2));
    }
    
    public static double orientationFromComponents(double xComponent, double yComponent) {
        return Math.atan2(yComponent, xComponent);
    }
    
    public static Velocity velocityFromComponents(double xComponent, double yComponent) {
        return new Velocity(magnitudeFromComponents(xComponent, yComponent),
                orientationFromComponents(xComponent, yComponent));
    }
    
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

    public double getXMagnitude() {
        return magnitude * Math.cos(orientation);
    }

    public double getYMagnitude() {
        return magnitude * Math.sin(orientation);
    }
    
}
