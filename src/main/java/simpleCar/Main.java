/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 5.1,
        lastModified = "01/18/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Main {
    
    public static final String ASSETS_ADDRESS = "assets/";
    public static final String OUTPUT_ADDRESS = "output/";
    
    public static final Color BOARD_COLOR = new Color(214, 232, 202);
    
    public static final Color SLOW_VEHICLE_COLOR = new Color(255, 0, 0);
    public static final Color FAST_VEHICLE_COLOR = new Color(0, 0, 255);
    
    public static final double PIXELS_PER_METER = 10;
    public static final double MILLISECONDS_PER_SECOND = 5000; //use this to change display render speed

    public static final double PANEL_WIDTH = 256;//meter
    public static final double PANEL_HEIGHT = 160;//meter
    public static final double FRAME_WIDTH = PANEL_WIDTH;
    public static final double FRAME_HEIGHT = PANEL_HEIGHT;
    public static final double INTERVAL = 0.01;//second
    public static final double THRESHOLD = 0.05;
    
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
    
    public static void main(String[] args) {
        
        HashMap vehiclesMap = new HashMap();
        
        vehiclesMap.put("car", 5);
        vehiclesMap.put("bus", 1);
        
        ArrayList<Vehicle> vehiclesList = getVehiclesList(vehiclesMap);
        
        vehiclesList.get(0).getVelocity().setMagnitude(59);
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                Frame frame = new Frame();
                
                frame.addVehicles(vehiclesList);
                
                frame.initUI();
                frame.setVisible(true);

            }
            
        });

//        Position p1 = new Position(3, 4);
//        Position p2 = new Position(5.5, -1);
//        Position p3 = new Position(4, -3);
//        
//        System.out.println(p1.distanceTo(p2, p3));
        
//        Body.getCornerPositions(
//                new Size(Math.sqrt(90), Math.sqrt(10)),
//                new Position(1, 3),
//                new Velocity(10, 4.39)).forEach(
//                        (position) -> System.out.printf("%f %f\n", position.getXPosition(), position.getYPosition()));

//        BufferedImage carImage = null;
//        
//        try {
//            carImage = ImageIO.read(new File("src/resources/Car.png"));
//        } catch(IOException e) {
//            System.out.println(e.getLocalizedMessage());
//        }
            

    }
    
}
