package map;

import java.util.ArrayList;
import java.util.Collections;

@ClassPreamble (
        author = "Daniel Chen",
        date = "02/25/2020",
        currentRevision = 10,
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
    private final ArrayList<Integer> states; // -1 preturn 0 turning 1 postturn
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

        double xPosition;
        double yPosition;

        switch(origin) {
            case 0:
                xPosition = Main.FRAME_ALONG + spawns[0]++ * RANGE_OF_SPAWN;
                yPosition = position.getYPosition() - laneWidth * 0.5;
                return new Position(xPosition, yPosition);
            case 1:
                xPosition = position.getXPosition() + laneWidth * 0.5;
                yPosition = Main.FRAME_ACROSS + spawns[1]++ * RANGE_OF_SPAWN;
                return new Position(xPosition, yPosition);
            case 2:
                xPosition = - spawns[2]++ * RANGE_OF_SPAWN;
                yPosition = position.getYPosition() + laneWidth * 0.5;
                return new Position(xPosition, yPosition);
            case 3:
                xPosition = position.getXPosition() - laneWidth * 0.5;
                yPosition = - spawns[3]++ * RANGE_OF_SPAWN;
                return new Position(xPosition, yPosition);
        }

        return null;
    }
    
    /**
     * Create a new Car in this Crossroad with origin and destination set. The origin could not be the same as the destination
     * 
     * @param origin 0, 1, 2, or 3
     * @param destination 0, 1, 2, or 3
     */
    public void spawnCar(int origin, int destination) {
        
        if(origin == destination) destination = (origin + 2) % 4;

        Car car = new Car();
        car.getVelocity().setMagnitude(5 + 10 * Math.random());
        
        switch(origin) {
            
            case 0:
                car.setPosition(getSpawnPosition(0));
                car.getVelocity().setOrientation(Math.PI * 2 / 2);
                break;
            case 1:
                car.setPosition(getSpawnPosition(1));
                car.getVelocity().setOrientation(Math.PI * 3 / 2);
                break;
            case 2:
                car.setPosition(getSpawnPosition(2));
                car.getVelocity().setOrientation(Math.PI * 0 / 2);
                break;
            case 3:
                car.setPosition(getSpawnPosition(3));
                car.getVelocity().setOrientation(Math.PI * 1 / 2);
                break;
                
        }
        
        car.setOrigin(origin);
        car.setDestination(destination);
        car.setCrossroad(this);
        
        vehicles.add(car);
//        states.add((origin < 2) ? (origin + 2) : (origin - 2));
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
    
    /**
     * 
     * @param vehicle the vehicle to be tested
     * @return whether or not the center of the vehicle is in the turning area of the crossroad
     */
    public boolean inCenter(Vehicle vehicle) {
        return inCenter(vehicle.getPosition());
    }
    
    /**
     * 
     * @param vehicle the vehicle
     * @return the turning acceleration based on the vehicle, should be a combination of tangential and angular acceleration
     */
    public Acceleration getAccelerationTurningFor(Vehicle vehicle) {
        
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

    /**
     * 
     * @param vehicle the vehicle
     * @return the acceleration for the vehicle driving on a straight lane; need to adjust based on other cars and obstacles
     */
    public Acceleration getAccelerationStraightFor(Vehicle vehicle) {
        
//        int laneNum = states.get(vehicles.indexOf(vehicle));

        int laneNum = getLaneNum(vehicles.indexOf(vehicle));

        boolean isAlong = laneNum % 2 == 0; // true: horizontal, false: vertical
        double thisPosition;
        
        ArrayList<Double> positions = new ArrayList<Double>();
        
        if(isAlong) {
            
            for(int i = 0; i < states.size(); i++) if(getLaneNum(i) == laneNum || lanes[laneNum].inRange(vehicles.get(i), false))
                positions.add(vehicles.get(i).getPosition().getXPosition());

            thisPosition = vehicle.getPosition().getXPosition();
            
        } else {
            
            for(int i = 0; i < states.size(); i++) if(getLaneNum(i) == laneNum || lanes[laneNum].inRange(vehicles.get(i), false))
                positions.add(vehicles.get(i).getPosition().getYPosition());

            thisPosition = vehicle.getPosition().getYPosition();
            
        }
        
        Collections.sort(positions);

        int index = positions.indexOf(thisPosition);

        double frontGap;
        double backGap;
        
        if(laneNum > 1) {
            frontGap = thisPosition - (index > 0 ? positions.get(index - 1) : -Double.MAX_VALUE);
            backGap = (index < positions.size() -1 ? positions.get(index + 1) : Double.MAX_VALUE / 2) - thisPosition;
        } else {
            frontGap = (index < positions.size() -1 ? positions.get(index + 1) : Double.MAX_VALUE / 2) - thisPosition;
            backGap = thisPosition - (index > 0 ? positions.get(index - 1) : -Double.MAX_VALUE / 2);
        }

//        if(laneNum == 1) System.out.println(frontGap);

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
    
    /**
     * 
     * @param vehicle the vehicle
     * @return the acceleration for the vehicle in this crossroad
     */
    public Acceleration getAccelerationFor(Vehicle vehicle) {
        
        int index = vehicles.indexOf(vehicle);
        
        if(inCenter(vehicle)) {
            states.set(index, 0);
            return this.getAccelerationTurningFor(vehicle);
        }
        
        if(states.get(index) == 0) states.set(index, 1);

        return getAccelerationStraightFor(vehicle);
    }
    
}
