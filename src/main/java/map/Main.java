/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 7.1,
        lastModified = "04/02/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Main {
    
    public static final String ASSETS_ADDRESS = "assets/";
    public static final String OUTPUT_ADDRESS = "output/";
    
    public static final Color BOARD_COLOR = new Color(214, 232, 202);
    
    public static final Color SLOW_VEHICLE_COLOR = new Color(255, 0, 0);
    public static final Color FAST_VEHICLE_COLOR = new Color(0, 0, 255);
    
    public static final double PIXELS_PER_METER = 80. / 3;
    public static final double MILLISECONDS_PER_SECOND = 1000; //use this to change display render speed

    public static final double PANEL_WIDTH = 1000. / PIXELS_PER_METER; //meter
    public static final double PANEL_HEIGHT = 600. / PIXELS_PER_METER; //meter
    public static final double FRAME_WIDTH = PANEL_WIDTH;
    public static final double FRAME_HEIGHT = PANEL_HEIGHT;
    public static final double INTERVAL = 0.01;//second
    public static final double THRESHOLD = 0.5;
    
    /**
     * 
     * @param vehiclesMap a HashMap object specifying the number of cars and buses wanted
     * @return an ArrayList object containing the specified number of cars and buses
     */
    public static ArrayList<Vehicle> getVehiclesList(HashMap vehiclesMap) {
        
        ArrayList<Vehicle> vehiclesList = new ArrayList<Vehicle>();
        
        vehiclesList.addAll(Car.getCarsList((int)vehiclesMap.get("car")));
        vehiclesList.addAll(Bus.getBusesList((int)vehiclesMap.get("bus")));
        
        return vehiclesList;
    }
    
    public static ArrayList<Obstacle> getObstaclesList(HashMap obstaclesMap) {
        
        ArrayList<Obstacle> obstaclesList = new ArrayList<Obstacle>();
        
        obstaclesList.addAll(Pedestrian.getPedestriansList((int)obstaclesMap.get("pedestrian")));
        
        return obstaclesList;
    }
    
    public static void main(String[] args) {
        
        Crossroad crossRoad = new Crossroad(new Position(10, 10), 10);
        
        System.out.println(crossRoad.getLanes().get(1).getPosition().getXPosition());
        System.out.println(crossRoad.getLanes().get(1).getPosition().getYPosition());
        
        System.out.println(crossRoad.getLanes().get(1).getSize().getWidth());
        
//        HashMap vehiclesMap = new HashMap();
//        
//        vehiclesMap.put("car", 1);
//        vehiclesMap.put("bus", 0);
//        
//        ArrayList<Vehicle> vehiclesList = getVehiclesList(vehiclesMap);
        
//        vehiclesList.get(0).setPosition(new Position(50 / PIXELS_PER_METER, 250 / PIXELS_PER_METER)); // 0
//        vehiclesList.get(0).setVelocity(new Velocity(10, Math.PI * 0 / 2));
        
//        vehiclesList.get(0).setPosition(new Position(350 / PIXELS_PER_METER, 50 / PIXELS_PER_METER)); // 1
//        vehiclesList.get(0).setVelocity(new Velocity(10, Math.PI * 1 / 2));
        
//        vehiclesList.get(0).setPosition(new Position(950 / PIXELS_PER_METER, 350 / PIXELS_PER_METER)); // 2
//        vehiclesList.get(0).setVelocity(new Velocity(10, Math.PI * 2 / 2));
        
//        vehiclesList.get(0).setPosition(new Position(250 / PIXELS_PER_METER, 550 / PIXELS_PER_METER)); // 3
//        vehiclesList.get(0).setVelocity(new Velocity(10, Math.PI * 3 / 2));
        
//        HashMap obstaclesMap = new HashMap();
//        
//        obstaclesMap.put("pedestrian", 1);
//        
//        ArrayList<Obstacle> obstaclesList = getObstaclesList(obstaclesMap);
//        
//        obstaclesList.get(0).setPosition(new Position(20, 10));
//        obstaclesList.get(0).setVelocity(new Velocity(5, Math.PI * 3 / 2));
//        obstaclesList.get(0).setPath(new Path(true,
//                new Position(20, 10),
//                new Position(17, 10),
//                new Position(17, 2),
//                new Position(10, 2),
//                new Position(10, 12),
//                new Position(15, 12),
//                new Position(15, 7),
//                new Position(20, 7)));
        
//        EventQueue.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//
//                Frame frame = new Frame(false, 250);
//                
//                frame.addVehicles(vehiclesList);
////                frame.addObstacles(obstaclesList);
//                
//                frame.initUI();
//                frame.setVisible(true);
//
//            }
//            
//        });

    }
    
}
