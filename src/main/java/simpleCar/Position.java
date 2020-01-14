/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 1,
        lastModified = "01/14/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Position {
    
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
    
}
