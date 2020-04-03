/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 1,
        lastModified = "01/14/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Size {
    
    private double width;
    private double height;

    /**
     * This class is used as a basis for other classes
     * 
     * @param width the width of the bounding box of The body object
     * @param height the height of the bounding box of the Body object
     */
    public Size(double width, double height) {
        
        this.width = width;
        this.height = height;
        
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    
}
