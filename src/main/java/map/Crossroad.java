/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;
import java.util.Collections;

@ClassPreamble (
        author = "Daniel Chen",
        date = "02/25/2020",
        currentRevision = 6,
        lastModified = "04/10/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Crossroad {
    
    public static final double RANGE_OF_INTEREST = 10.;
    
    private double laneWidth;
    private Position position;
    private ArrayList<Lane> lanes;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Integer> states; // -1 for turning, 0, 1, 2, 3 for the lanes they are in
    
    public Crossroad(Position position, double laneWidth) {
        
        this.position = position;
        this.laneWidth = laneWidth;
        
        lanes = new ArrayList<Lane>();
        
        lanes.add(new Lane(new Size(Main.FRAME_ALONG, laneWidth),
                new Position(0.5 * Main.FRAME_ALONG, position.getYPosition() + 0.5 * laneWidth),
                Math.PI * 0 / 2, this));
        lanes.add(new Lane(new Size(Main.FRAME_ACROSS, laneWidth),
                new Position(position.getXPosition() - 0.5 * laneWidth, 0.5 * Main.FRAME_ACROSS),
                Math.PI * 1 / 2, this));
        lanes.add(new Lane(new Size(Main.FRAME_ALONG, laneWidth),
                new Position(0.5 * Main.FRAME_ALONG, position.getYPosition() - 0.5 * laneWidth),
                Math.PI * 2 / 2, this));
        lanes.add(new Lane(new Size(Main.FRAME_ACROSS, laneWidth),
                new Position(position.getXPosition() + 0.5 * laneWidth, 0.5 * Main.FRAME_ACROSS),
                Math.PI * 3 / 2, this));
        
        vehicles = new ArrayList<Vehicle>();
        obstacles = new ArrayList<Obstacle>();
        states = new ArrayList<Integer>();
        
    }
    
    public double getLaneWidth() {
        return laneWidth;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public ArrayList<Lane> getLanes() {
        return lanes;
    }
    
    public void setLaneWidth(double laneWidth) {
        this.laneWidth = laneWidth;
    }
    
    public void setPosition(Position position) {
        this.position = position;
    }
    
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
    
    /**
     * Create a new Car in this Crossroad with origin and destination set. The origin could not be the same as the destination
     * 
     * @param origin
     * @param destination 
     */
    public void addCar(int origin, int destination) {
        
        if(origin == destination) {
            System.out.println("ERROR: ORIGIN IS THE SAME AS THE DESTINATION.");
            System.exit(0);
        }
        
        Car car = new Car();
        car.getVelocity().setMagnitude(10);
        
        switch(origin) {
            
            case 0:
                car.setPosition(new Position(Main.FRAME_ALONG, position.getYPosition() - laneWidth * 0.5));
                car.getVelocity().setOrientation(Math.PI * 2 / 2);
                break;
            case 1:
                car.setPosition(new Position(position.getXPosition() + laneWidth * 0.5, Main.FRAME_ACROSS));
                car.getVelocity().setOrientation(Math.PI * 3 / 2);
                break;
            case 2:
                car.setPosition(new Position(0, position.getYPosition() + laneWidth * 0.5));
                car.getVelocity().setOrientation(Math.PI * 0 / 2);
                break;
            case 3:
                car.setPosition(new Position(position.getXPosition() - laneWidth * 0.5, 0));
                car.getVelocity().setOrientation(Math.PI * 1 / 2);
                break;
                
        }
        
        car.setOrigin(origin);
        car.setDestination(destination);
        car.setCrossroad(this);
        
        vehicles.add(car);
        states.add(Integer.valueOf((origin < 2) ? (origin + 2) : (origin - 2)));
        
    }
    
    public ArrayList<Vehicle> getVehicles() {
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
     * @param vehicle
     * @return the turning acceleration based on the vehicle, should be a combination of tangential and angular acceleration
     */
    public Acceleration getAccelerationTurningFor(Vehicle vehicle) {
        
        if((vehicle.getOrigin() + vehicle.getDestination()) % 2 == 0) {
            return new Acceleration(0, 0);
        }
        
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
    
    /**
     * 
     * @param vehicle
     * @return the acceleration for the vehicle driving on a straight lane; need to adjust based on other cars and obstacles
     */
    public Acceleration getAccelerationStraightFor(Vehicle vehicle) {
        
        int laneNum = states.get(vehicles.indexOf(vehicle));
        boolean isAlong = laneNum % 2 == 0; // true: horizontal, false: vertical
        boolean centerOccupied = false;
        double thisPosition = 0;
        
        ArrayList<Double> positions = new ArrayList<Double>();
        
        if(isAlong) {
            
            for(int i=0; i < states.size(); i++) {
                
                if(states.get(i) == laneNum) {
                    positions.add(vehicles.get(i).getPosition().getXPosition());
                }
                
                centerOccupied = centerOccupied || states.get(i) == -1;
                
            }
            
            thisPosition = vehicle.getPosition().getXPosition();
            
        } else {
            
            for(int i=0; i < states.size(); i++) {
                
                if(states.get(i) == laneNum) {
                    positions.add(vehicles.get(i).getPosition().getYPosition());
                }
                
                centerOccupied = centerOccupied || states.get(i) == -1;
                
            }
            
            thisPosition = vehicle.getPosition().getYPosition();
            
        }
        
        if(centerOccupied) {
            
            if(isAlong) {
                
                positions.add(position.getXPosition() - laneWidth);
                positions.add(position.getXPosition() + laneWidth);
                
            } else {
                
                positions.add(position.getYPosition() - laneWidth);
                positions.add(position.getYPosition() + laneWidth);
                
            }
            
        }
        
        Collections.sort(positions);
        
        int index = positions.indexOf(thisPosition);
        
        double frontGap;
        double backGap;
        
        if(laneNum > 1) {
            
            frontGap = thisPosition - (index > 0 ? positions.get(index - 1) : Double.MIN_VALUE);
            backGap = (index < positions.size() -1 ? positions.get(index + 1) : Double.MAX_VALUE) - thisPosition;
            
        } else {
            
            frontGap = (index < positions.size() -1 ? positions.get(index + 1) : Double.MAX_VALUE) - thisPosition;
            backGap = thisPosition - (index > 0 ? positions.get(index - 1) : Double.MIN_VALUE);
            
        }
        
        // If there is no vehicle in front then accelerate if possible
        
        if(frontGap > RANGE_OF_INTEREST) {
            
            if(vehicle.getClass().getName() == "map.Car") {
                
                if(vehicle.getVelocity().getMagnitude() < Car.MAX_VELOCITY_MAGNITUDE) {
                    return new Acceleration(Car.MAX_ACCELERATION_MAGNITUDE, vehicle.getVelocity().getOrientation());
                } else {
                    return new Acceleration(0, 0);
                }
                
            }
            
        }
        
        // Given that there is a vehicle in front, decelerate if there is no vehicle at back and if possible
        
        if(backGap > RANGE_OF_INTEREST) {
            
            if(vehicle.getClass().getName() == "map.Car") {
                
                if(vehicle.getVelocity().getMagnitude() > 0) {
                    return new Acceleration(-Car.MAX_ACCELERATION_MAGNITUDE, vehicle.getVelocity().getOrientation());
                } else {
                    return new Acceleration(0, 0);
                }
                
            }
            
        }
        
        // Given that there are vehicles both in front and at back, balance
        
        return new Acceleration(0, 0);
        
//        System.out.print(frontPosition);
//        System.out.print(" ");
//        System.out.println(backPosition);
//        
//        return new Acceleration(0, 0);
    }
    
    /**
     * 
     * @param vehicle
     * @return the acceleration for the vehicle in this crossroad
     */
    public Acceleration getAccelerationFor(Vehicle vehicle) {
        
        int index = vehicles.indexOf(vehicle);
        
        if(inCenter(vehicle)) {
            
            states.set(index, Integer.valueOf(-1));
            return this.getAccelerationTurningFor(vehicle);
            
        }
        
        if(states.get(vehicles.indexOf(vehicle)) == -1) {
            states.set(index, Integer.valueOf(vehicle.getDestination()));
        }
        
        return getAccelerationStraightFor(vehicle);
    }
    
}
