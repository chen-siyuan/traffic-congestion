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
        currentRevision = 8,
        lastModified = "04/05/2020",
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

    public static final double PANEL_ALONG = 1000. / PIXELS_PER_METER; //meter
    public static final double PANEL_ACROSS = 600. / PIXELS_PER_METER; //meter
    public static final double FRAME_ALONG = PANEL_ALONG;
    public static final double FRAME_ACROSS = PANEL_ACROSS;
    public static final double INTERVAL = 0.001;
    public static final double THRESHOLD = 0.5;
    
    public static ArrayList<Obstacle> getObstaclesList(HashMap obstaclesMap) {
        
        ArrayList<Obstacle> obstaclesList = new ArrayList<Obstacle>();
        
        obstaclesList.addAll(Pedestrian.getPedestriansList((int)obstaclesMap.get("pedestrian")));
        
        return obstaclesList;
    }
    
    public static void main(String[] args) {
        
        Crossroad crossRoad = new Crossroad(new Position(300 / PIXELS_PER_METER, 300 / PIXELS_PER_METER), 100 / PIXELS_PER_METER);
        
        crossRoad.addCar(0, 3);
    
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
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                Frame frame = new Frame(false, 75);
                
                frame.addVehicles(crossRoad.getVehicles());
//                frame.addObstacles(obstaclesList);
                
                frame.initUI();
                frame.setVisible(true);

            }
            
        });

    }
    
}
