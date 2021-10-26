package map;

import java.awt.*;

@ClassPreamble (
    author = "Daniel Chen",
    date = "01/14/2020",
    currentRevision = 7,
    lastModified = "05/09/2020",
    lastModifiedBy = "Daniel Chen"
)
public class Car extends Vehicle {

  public static final double MAX_VELOCITY_MAGNITUDE = 15; //meters per second
  public static final double MAX_ACCELERATION_MAGNITUDE = 15;

  public static final double ALONG = 4;
  public static final double ACROSS = 1.8;
  public static final double SPEED = 20;
  public static final double ORIENTATION = Math.toRadians(45);

  public Car() {
    this(new Size(ALONG, ACROSS), new Position(Math.random() * Main.FRAME_ALONG, Math.random() * Main.FRAME_ACROSS), new Velocity(SPEED, ORIENTATION));
  }

  public Car(Position position, Velocity velocity) {
    this(new Size(ALONG, ACROSS), position, velocity);
  }

  /**
   * The Car class inherits the Vehicle class
   *
   * @param size the size of the car
   * @param position the initial position of the car
   * @param velocity the initial velocity of the car
   */
  public Car(Size size, Position position, Velocity velocity) {
    super(size, position, velocity);
  }

  /**
   * uses weighted average to determine the color, calculated based on speed
   *
   * @return the Color object used by Board to paint the cars
   */
  public Color getColor() {

    double slowColorWeight;
    double fastColorWeight = this.getVelocity().getMagnitude() / MAX_VELOCITY_MAGNITUDE;

    if(fastColorWeight > 1) {
      fastColorWeight = 1;
    } else if(fastColorWeight < 0) {
      fastColorWeight = 0;
    }

    slowColorWeight = 1 - fastColorWeight;

    return new Color((int)Math.round(Main.SLOW_VEHICLE_COLOR.getRed() * slowColorWeight + Main.FAST_VEHICLE_COLOR.getRed() * fastColorWeight),
        (int)Math.round(Main.SLOW_VEHICLE_COLOR.getGreen() * slowColorWeight + Main.FAST_VEHICLE_COLOR.getGreen() * fastColorWeight),
        (int)Math.round(Main.SLOW_VEHICLE_COLOR.getBlue() * slowColorWeight + Main.FAST_VEHICLE_COLOR.getBlue() * fastColorWeight));
  }

  public String toString() {
    return String.format("Car:\tSize: %.2f * %.2f;\tPos: (%.2f, %.2f);\tVelocity: %.2f at %.2f.",
        this.getSize().getAlong(), this.getSize().getAcross(),
        this.getPosition().getXPosition(), this.getPosition().getYPosition(),
        this.getVelocity().getMagnitude(), this.getVelocity().getOrientation());
  }

}
