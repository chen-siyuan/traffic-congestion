/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 1,
        lastModified = "01/14/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Frame extends JFrame {
    
    private double interval;
    private Board board;
    
    public Frame(double interval) {
        
        this.interval = interval;
        board = new Board(interval);
        
    }
    
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
