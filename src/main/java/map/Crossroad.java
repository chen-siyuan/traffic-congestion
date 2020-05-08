package map;

import java.util.ArrayList;
import java.util.Collections;

@ClassPreamble (
        author = "Daniel Chen",
        date = "02/25/2020",
        currentRevision = 11,
        lastModified = "05/07/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Crossroad {

    public static final double RANGE_OF_SPAWN = 12.;
    public static final double RANGE_OF_INTEREST = 8.;
    public static final double RANGE_OF_BUFFER = 100.;
    
    private final double laneWidth;
    private final Position position;
    private final Lane[] lanes;
    private final ArrayList<Vehicle> vehicles;
    private final ArrayList<Obstacle> obstacles;
    private final ArrayList<Integer> states; // -1 pre-turn 0 turning 1 post-turn
    private final int[] spawns;

    public Crossroad(Position position, double laneWidth) {
        
        this.position = position;
        this.laneWidth = laneWidth;

        lanes = new Lane[4];

        lanes[0] = new Lane(new Size(Main.FRAME_ALONG + RANGE_OF_BUFFER * 2, laneWidth),
                new Position(0.5 * Main.FRAME_ALONG, position.getYPosition() + 0.5 * laneWidth),
                Math.PI * 0 / 2, this);
        lanes[1] = new Lane(new Size(Main.FRAME_ACROSS + RANGE_OF_BUFFER * 2, laneWidth),
                new Position(position.getXPosition() - 0.5 * laneWidth, 0.5 * Main.FRAME_ACROSS),
                Math.PI * 1 / 2, this);
        lanes[2] = new Lane(new Size(Main.FRAME_ALONG + RANGE_OF_BUFFER * 2, laneWidth),
                new Position(0.5 * Main.FRAME_ALONG, position.getYPosition() - 0.5 * laneWidth),
                Math.PI * 2 / 2, this);
        lanes[3] = new Lane(new Size(Main.FRAME_ACROSS + RANGE_OF_BUFFER * 2, laneWidth),
                new Position(position.getXPosition() + 0.5 * laneWidth, 0.5 * Main.FRAME_ACROSS),
                Math.PI * 3 / 2, this);

        vehicles = new ArrayList<>();
        obstacles = new ArrayList<>();
        states = new ArrayList<>();

        spawns = new int[4];

    }
    
    public Position getPosition() {
        return position;
    }
    
    public Position getSpawnPosition(int origin) {

        double xPosition = 0;
        double yPosition = 0;

        switch(origin) {
            case 0:
                xPosition = Main.FRAME_ALONG + spawns[0]++ * RANGE_OF_SPAWN;
                yPosition = position.getYPosition() - laneWidth * 0.5;
                break;
            case 1:
                xPosition = position.getXPosition() + laneWidth * 0.5;
                yPosition = Main.FRAME_ACROSS + spawns[1]++ * RANGE_OF_SPAWN;
                break;
            case 2:
                xPosition = - spawns[2]++ * RANGE_OF_SPAWN;
                yPosition = position.getYPosition() + laneWidth * 0.5;
                break;
            case 3:
                xPosition = position.getXPosition() - laneWidth * 0.5;
                yPosition = - spawns[3]++ * RANGE_OF_SPAWN;
                break;
        }

        return new Position(xPosition, yPosition);
    }

    /**
     * Create a new Car in this Crossroad with origin and destination set. The origin could not be the same as the destination
     * 
     * @param origin 0, 1, 2, or 3
     * @param destination 0, 1, 2, or 3
     */
    public void spawnCar(int origin, int destination) {
        
        if(origin == destination) destination = (origin + 2) % 4;

        Car car = new Car(getSpawnPosition(origin),
                new Velocity(5 + 10 * Math.random(), Math.PI * ((origin + 2) % 4) / 2));

        car.setOrigin(origin);
        car.setDestination(destination);
        car.setCrossroad(this);
        
        vehicles.add(car);
        states.add(-1);

    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public boolean inBorder(Vehicle vehicle) {
        return vehicle.getPosition().getXPosition() >= 0
                && vehicle.getPosition().getXPosition() <= Main.PANEL_ALONG
                && vehicle.getPosition().getYPosition() >= 0
                && vehicle.getPosition().getYPosition() <= Main.PANEL_ACROSS;
    }

    public boolean isPresent(int index) {
        return states.get(index) != 1 || inBorder(vehicles.get(index));
    }

    public ArrayList<Vehicle> getPresentVehicles() {

        int pointer = 0;

        while(pointer < vehicles.size()) if(!isPresent(pointer++)) {
            vehicles.remove(--pointer);
            states.remove(pointer);
        }

        return vehicles;
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }
    
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
    
    /**
     * 
     * @param position the position of the point
     * @return whether or not the position of the point is in the center of the crossroad
     */
    public boolean inCenter(Position position) {
        
        return (this.position.getXPosition() - this.laneWidth <= position.getXPosition())
                && (this.position.getXPosition() + this.laneWidth >= position.getXPosition())
                && (this.position.getYPosition() - this.laneWidth <= position.getYPosition())
                && (this.position.getYPosition() + this.laneWidth >= position.getYPosition());
        
    }
    
    public boolean inCenter(int index) {
        return inCenter(vehicles.get(index).getPosition());
    }
    
    public Acceleration getAccelerationTurningFor(int index) {

        Vehicle vehicle = vehicles.get(index);
        
        if((vehicle.getOrigin() + vehicle.getDestination()) % 2 == 0) return new Acceleration(0, 0);

        if((vehicle.getDestination() - vehicle.getOrigin() - 1) % 4 == 0) {
            return new Acceleration(Math.pow(vehicle.getVelocity().getMagnitude(), 2) / (laneWidth * 1.5),
                    vehicle.getVelocity().getOrientation() - Math.PI / 2);
        }
        
        if((vehicle.getDestination() - vehicle.getOrigin() + 1) % 4 == 0) {
            return new Acceleration(Math.pow(vehicle.getVelocity().getMagnitude(), 2) / (laneWidth * 0.5),
                    vehicle.getVelocity().getOrientation() + Math.PI / 2);
        }
        
        return null;
    }

    public int getLaneNum(int index) {

        if(states.get(index) == 1) return vehicles.get(index).getDestination();
        if(states.get(index) == -1) return (vehicles.get(index).getOrigin() + 2) % 4;

        return -1;
    }

    public Acceleration getAccelerationStraightFor(int indey) {
        
        int laneNum = getLaneNum(indey);
        boolean isAlong = laneNum % 2 == 0; // true: horizontal, false: vertical
        Vehicle vehicle = vehicles.get(indey);

        ArrayList<Double> allPositions = new ArrayList<>();

        for(int i = 0; i < states.size(); i++)
            if(getLaneNum(i) == laneNum || lanes[laneNum].inRange(vehicles.get(i), false))
                allPositions.add(vehicles.get(i).getPosition().getPosition(isAlong));

        double absolutePosition = vehicle.getPosition().getPosition(isAlong);

        Collections.sort(allPositions);

        int relativeIndex = allPositions.indexOf(absolutePosition);

        double frontGap;
        double backGap;
        
        if(laneNum > 1) {
            frontGap = absolutePosition - (relativeIndex > 0 ? allPositions.get(relativeIndex - 1) : -Double.MAX_VALUE);
            backGap = (relativeIndex < allPositions.size() -1 ? allPositions.get(relativeIndex + 1) : Double.MAX_VALUE / 2) - absolutePosition;
        } else {
            frontGap = (relativeIndex < allPositions.size() -1 ? allPositions.get(relativeIndex + 1) : Double.MAX_VALUE / 2) - absolutePosition;
            backGap = absolutePosition - (relativeIndex > 0 ? allPositions.get(relativeIndex - 1) : -Double.MAX_VALUE / 2);
        }

        if("map.Car".equals(vehicle.getClass().getName())) {

            // If there is no vehicle in front then accelerate to max speed if possible

            if(frontGap > RANGE_OF_INTEREST) {
                if (vehicle.getVelocity().getMagnitude() < Car.MAX_VELOCITY_MAGNITUDE) {
                    return new Acceleration(Car.MAX_ACCELERATION_MAGNITUDE, vehicle.getVelocity().getOrientation());
                } else {
                    return new Acceleration(0, 0);
                }
            }

            // Given that there is a vehicle in front, decelerate if there is no vehicle at back and if possible

            if(backGap > RANGE_OF_INTEREST) {
                if(vehicle.getVelocity().getMagnitude() > Car.MAX_ACCELERATION_MAGNITUDE * Main.INTERVAL) {
                    return new Acceleration(Car.MAX_ACCELERATION_MAGNITUDE, Math.PI + vehicle.getVelocity().getOrientation());
                } else {
                    return new Acceleration(0, 0);
                }
            }

            // Given that there are vehicles both in front and at back, balance

            if(backGap > frontGap) {
                if(vehicle.getVelocity().getMagnitude() > Car.MAX_ACCELERATION_MAGNITUDE * Main.INTERVAL) {
                    return new Acceleration(Car.MAX_ACCELERATION_MAGNITUDE, Math.PI + vehicle.getVelocity().getOrientation());
                } else {
                    return new Acceleration(0, 0);
                }
            } else {
                if (vehicle.getVelocity().getMagnitude() < Car.MAX_VELOCITY_MAGNITUDE) {
                    return new Acceleration(Car.MAX_ACCELERATION_MAGNITUDE, vehicle.getVelocity().getOrientation());
                } else {
                    return new Acceleration(0, 0);
                }
            }

        }

        return null;
    }
    
    public Acceleration getAccelerationFor(int index) {
        
        if(inCenter(index)) {
            states.set(index, 0);
            return this.getAccelerationTurningFor(index);
        }
        
        if(states.get(index) == 0) states.set(index, 1);

        return getAccelerationStraightFor(index);
    }

    //TEMPORARY

    public Acceleration getAccelerationFor(Vehicle vehicle) {
        return getAccelerationFor(vehicles.indexOf(vehicle));
    }
    
}
