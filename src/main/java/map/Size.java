package map;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 2,
        lastModified = "04/03/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Size {
    
    private double along;
    private double across;

    /**
     * This class is used as a basis for other classes
     * 
     * @param along the primary direction of the bounding box of The body object
     * @param across the secondary direction of the bounding box of the Body object
     */
    public Size(double along, double across) {
        
        this.along = along;
        this.across = across;
        
    }

    public double getAlong() {
        return along;
    }

    public double getAcross() {
        return across;
    }

    public void setAlong(double along) {
        this.along = along;
    }

    public void setAcross(double across) {
        this.across = across;
    }
    
}
