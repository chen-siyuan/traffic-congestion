/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.awt.Color;
import java.awt.EventQueue;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 8.5,
        lastModified = "04/23/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Main {
    
    public static final String ASSETS_ADDRESS = "assets/";
    public static final String OUTPUT_ADDRESS = "output/";
    
    public static final Color SLOW_VEHICLE_COLOR = new Color(0, 0, 0);
    public static final Color FAST_VEHICLE_COLOR = new Color(255, 255, 255);
    
    public static final double PIXELS_PER_METER = 80. / 3;
    public static final double MILLISECONDS_PER_SECOND = 1000; //use this to change display render speed

    public static final double PANEL_ALONG = 1000. / PIXELS_PER_METER; //meter
    public static final double PANEL_ACROSS = 600. / PIXELS_PER_METER; //meter
    public static final double FRAME_ALONG = PANEL_ALONG;
    public static final double FRAME_ACROSS = PANEL_ACROSS;
    public static final double INTERVAL = 1. / 256;
    public static final double THRESHOLD = 0.5;
    
    public static void main(String[] args) {
        
        Crossroad crossRoad = new Crossroad(new Position(300 / PIXELS_PER_METER, 300 / PIXELS_PER_METER), 100 / PIXELS_PER_METER);
        
        crossRoad.spawnCar(1, 0);
        crossRoad.spawnCar(2, 0);
        crossRoad.spawnCar(3, 0);
        crossRoad.spawnCar(0, 2);
        crossRoad.spawnCar(0, 3);
        crossRoad.spawnCar(1, 2);
        crossRoad.spawnCar(1, 3);
        crossRoad.spawnCar(3, 2);
        crossRoad.spawnCar(0, 1);
        crossRoad.spawnCar(2, 3);
        crossRoad.spawnCar(1, 2);
        crossRoad.spawnCar(0, 1);
        crossRoad.spawnCar(2, 1);
        crossRoad.spawnCar(2, 1);
        crossRoad.spawnCar(1, 2);
        crossRoad.spawnCar(0, 3);
        crossRoad.spawnCar(0, 3);
        crossRoad.spawnCar(0, 3);
//        crossRoad.spawnCar(0, 3);
//        crossRoad.spawnCar(0, 3);
//        crossRoad.spawnCar(0, 3);
//        crossRoad.spawnCar(0, 1);
//        crossRoad.spawnCar(0, 3);
//        crossRoad.spawnCar(0, 2);
//        crossRoad.spawnCar(0, 3);
//        crossRoad.spawnCar(0, 2);
//        crossRoad.spawnCar(0, 1);
//        crossRoad.spawnCar(0, 3);
//        crossRoad.spawnCar(0, 1);

//        ArrayList<Obstacle> obstaclesList = new ArrayList<>();
//
//        obstaclesList.add(new Pedestrian(new Position(400. / PIXELS_PER_METER, 200. / PIXELS_PER_METER),
//                new Velocity(10, Math.PI / 2),
//                null));
//
//        obstaclesList.get(0).setPath(new Path(true,
//                new Position(20, 10),
//                new Position(17, 10),
//                new Position(17, 2),
//                new Position(10, 2),
//                new Position(10, 12),
//                new Position(15, 12),
//                new Position(15, 7),
//                new Position(20, 7)));
        
        EventQueue.invokeLater(() -> {

            Frame frame = new Frame(false, 1048576);

            frame.addVehicles(crossRoad.getVehicles());
//            frame.addObstacles(obstaclesList);

            frame.initUI();
            frame.setVisible(true);

        });

    }
    
}
