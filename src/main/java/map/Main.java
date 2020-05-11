package map;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 9.3,
        lastModified = "05/10/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Main {
    
    public static final String ASSETS_ADDRESS = "assets/";
    public static final String INPUT_ADDRESS = "input/";
    public static final String OUTPUT_ADDRESS = "output/";

    public static final String INPUT_FILE_NAME = "input.in";
    
    public static final Color SLOW_VEHICLE_COLOR = new Color(0, 0, 0);
    public static final Color FAST_VEHICLE_COLOR = new Color(255, 255, 255);
    
    public static final double PIXELS_PER_METER = 80. / 3;
    public static final double MILLISECONDS_PER_SECOND = 1000; //use this to change display render speed

    public static final double PANEL_ALONG = 1000. / PIXELS_PER_METER;
    public static final double PANEL_ACROSS = 600. / PIXELS_PER_METER;
    public static final double FRAME_ALONG = 1075. / PIXELS_PER_METER;
    public static final double FRAME_ACROSS = 622. / PIXELS_PER_METER;
    public static final double INTERVAL = 1. / 256;
    public static final double THRESHOLD = 0.5;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(INPUT_ADDRESS + INPUT_FILE_NAME));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int initNumCars = Integer.parseInt(st.nextToken());
        int totalNumCars = Integer.parseInt(st.nextToken());

        br.close();

        Crossroad crossRoad = new Crossroad(new Position(300 / PIXELS_PER_METER, 300 / PIXELS_PER_METER),
                100 / PIXELS_PER_METER, initNumCars, totalNumCars);
        
        for(int i=0; i < initNumCars; i++) crossRoad.spawnVehicle("map.Car");

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

            Frame frame = new Frame(crossRoad, false, 1048576);

//            frame.addObstacles(obstaclesList);

            frame.initUI();
            frame.setVisible(true);

        });

    }

}
