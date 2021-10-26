package map;

@ClassPreamble (
    author = "Daniel Chen",
    date = "01/14/2020",
    currentRevision = 2.1,
    lastModified = "05/09/2020",
    lastModifiedBy = "Daniel Chen"
)
public class Acceleration {

  /**
   *
   * @param xComponent the xComponent
   * @param yComponent the yComponent
   * @return the Acceleration vector from the two components
   */
  public static Acceleration accelerationFromComponents(double xComponent, double yComponent) {
    return new Acceleration(Velocity.magnitudeFromComponents(xComponent, yComponent),
        Velocity.orientationFromComponents(xComponent, yComponent));
  }

  /**
   *
   * @param acceleration1 the first Acceleration vector
   * @param acceleration2 the second Acceleration vector
   * @return the sum of the two Acceleration vectors
   */
  public static Acceleration combineAccelerations(Acceleration acceleration1, Acceleration acceleration2) {
    return accelerationFromComponents(acceleration1.getXMagnitude() + acceleration2.getXMagnitude(),
        acceleration1.getYMagnitude() + acceleration2.getYMagnitude());
  }

  private double magnitude;
  private double orientation;

  /**
   * This class is used as a basis for other classes
   *
   * @param magnitude the length of the acceleration vector of the Body object
   * @param orientation the direction of the acceleration vector of the Body object,
   * starts from positive x and rotates counterclockwise
   */
  public Acceleration(double magnitude, double orientation) {

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
