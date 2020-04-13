/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 3.4,
        lastModified = "04/13/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Frame extends JFrame {
    
    private final Board board;
    private final boolean record;
    private final int frameNumber;
    
    public Frame(boolean record, int frameNumber) {

        this.record = record;
        this.frameNumber = frameNumber;
        
        board = new Board(record, frameNumber);
        
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
    
    public void addObstacle(Obstacle obstacle) {
        board.addObstacle(obstacle);
    }
    
    public void addObstacles(Obstacle[] obstacles) {
        board.addObstacles(obstacles);
    }
    
    public void addObstacles(ArrayList<Obstacle> obstacles) {
        board.addObstacles(obstacles);
    }
    
    public void initUI() {
        
        add(board);
        
        setPreferredSize(new Dimension((int)Math.round(Main.FRAME_ALONG * Main.PIXELS_PER_METER),
                (int)Math.round(Main.FRAME_ACROSS * Main.PIXELS_PER_METER)));
        setResizable(false);
        pack();
        
        setTitle("Simulation");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
