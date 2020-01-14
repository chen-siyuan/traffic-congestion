/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author chensiyuan
 */
public class Main {
    
    // all in real life units
    public static final double PIXELS_PER_METER = 5;
    public static final double MILLISECONDS_PER_SECOND = 1000;

    // in real life units
    public static final double PANEL_WIDTH = 100;
    public static final double PANEL_HEIGHT = 100;
    
    // public static final double FRAME_WIDTH = 100;
    // public static final double FRAME_HEIGHT = 100;
    public static final double FRAME_WIDTH = PANEL_WIDTH;
    public static final double FRAME_HEIGHT = PANEL_HEIGHT;

    public static final double INTERVAL = 0.01;
    
    public static ArrayList<Vehicle> getVehiclesList(HashMap vehiclesMap) {
        
        ArrayList<Vehicle> vehiclesList = new ArrayList<Vehicle>();
        
        vehiclesList.addAll(Car.getCarsList((int)vehiclesMap.get("car")));
        vehiclesList.addAll(Bus.getBusesList((int)vehiclesMap.get("bus")));
        
        return vehiclesList;
    }
    
    public static void main(String[] args) {
        
        HashMap vehiclesMap = new HashMap();
        vehiclesMap.put("car", 5);
        vehiclesMap.put("bus", 2);
        
        ArrayList<Vehicle> vehiclesList = getVehiclesList(vehiclesMap);
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                Frame frame = new Frame(INTERVAL);
                
                frame.addVehicles(vehiclesList);
                
                frame.initUI();
                frame.setVisible(true);

            }
            
        });
    }
    
}
