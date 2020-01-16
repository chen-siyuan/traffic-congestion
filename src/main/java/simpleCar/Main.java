/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 2,
        lastModified = "01/15/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Main {
    
    public static final double PIXELS_PER_METER = 5;
    public static final double MILLISECONDS_PER_SECOND = 1000;

    public static final double PANEL_WIDTH = 100;//meter
    public static final double PANEL_HEIGHT = 100;//meter
    public static final double FRAME_WIDTH = PANEL_WIDTH;
    public static final double FRAME_HEIGHT = PANEL_HEIGHT;
    public static final double INTERVAL = 0.01;//second
    
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
        
        vehiclesMap.put("car", 1);
        vehiclesMap.put("bus", 1);
        
        ArrayList<Vehicle> vehiclesList = getVehiclesList(vehiclesMap);
        
        vehiclesList.get(0).getVelocity().setOrientation(135 * (Math.PI / 180));
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                Frame frame = new Frame(INTERVAL);
                
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

    }
    
}
