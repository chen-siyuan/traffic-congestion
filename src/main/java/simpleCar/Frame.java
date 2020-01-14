/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author chensiyuan
 */
public class Frame extends JFrame {
    
    // Parameters
    
    private double interval; // in real life
    private Board board;
    
    // Constructor
    
    public Frame(double interval) {
        this.interval = interval;
        board = new Board(interval);
    }
    
    // Getter
    
    public Board getBoard() {
        return board;
    }
    
    public void addVehicle(Vehicle vehicle) {
        board.addVehicle(vehicle);
    }
    
    public void addVehicles(Vehicle[] vehicles) {
        board.addVehicles(vehicles);
    }
    
    public void addVehicles(ArrayList<Vehicle> vehicles) {
        board.addVehicles(vehicles);
    }
    
    public void initUI() {
        
        add(board);
        
        setPreferredSize(new Dimension((int)Math.round(Main.FRAME_WIDTH * Main.PIXELS_PER_METER),
                (int)Math.round(Main.FRAME_HEIGHT * Main.PIXELS_PER_METER)));
        setResizable(false);
        pack();
        
        setTitle("Simple Car");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
