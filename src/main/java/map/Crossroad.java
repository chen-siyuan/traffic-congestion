/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;

@ClassPreamble (
        author = "Daniel Chen",
        date = "02/25/2020",
        currentRevision = 4,
        lastModified = "04/05/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Crossroad {
    
    private double laneWidth;
    private Position position;
    private ArrayList<Lane> lanes;
    private ArrayList<Vehicle> vehicles;
    
    public Crossroad(Position position, double laneWidth) {
        
        this.position = position;
        this.laneWidth = laneWidth;
        
        this.lanes = new ArrayList<Lane>();
        
        this.lanes.add(new Lane(new Size(Main.FRAME_ALONG, laneWidth), new Position(0.5 * Main.FRAME_ALONG, position.getYPosition() + 0.5 * laneWidth), Math.PI * 0 / 2));
        this.lanes.add(new Lane(new Size(Main.FRAME_ACROSS, laneWidth), new Position(position.getXPosition() - 0.5 * laneWidth, 0.5 * Main.FRAME_ACROSS), Math.PI * 1 / 2));
        this.lanes.add(new Lane(new Size(Main.FRAME_ALONG, laneWidth), new Position(0.5 * Main.FRAME_ALONG, position.getYPosition() - 0.5 * laneWidth), Math.PI * 2 / 2));
        this.lanes.add(new Lane(new Size(Main.FRAME_ACROSS, laneWidth), new Position(position.getXPosition() + 0.5 * laneWidth, 0.5 * Main.FRAME_ACROSS), Math.PI * 3 / 2));
        
        this.vehicles = new ArrayList<Vehicle>();
        
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
        car.getVelocity().setMagnitude(20);
        
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
        
    }
    
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
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
    public Acceleration getAccelerationTurning(Vehicle vehicle) {
        
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
     * @return the acceleration for the vehicle in this crossroad
     */
    public Acceleration getAccelerationFor(Vehicle vehicle) {
        
        if(inCenter(vehicle)) {
            return this.getAccelerationTurning(vehicle);
        }
//        
//        assign to lane and get acceleration
//            
        return new Acceleration(0, 0);
    }
    
}
