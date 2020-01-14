/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 1,
        lastModified = "01/14/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Board extends JPanel implements Runnable {
    
    public static final Color COLOR = new Color(235, 209, 195);

    private Thread animator;
    private double interval;
    private ArrayList<Vehicle> vehicles;
    
    /**
     * @param interval number of real life seconds each frame stays
     */
    public Board(double interval) {

        setBackground(COLOR);
        setPreferredSize(new Dimension((int)Math.round(Main.PANEL_WIDTH * Main.PIXELS_PER_METER),
                (int)Math.round(Main.PANEL_HEIGHT * Main.PIXELS_PER_METER)));
        
        this.interval = interval;
        vehicles = new ArrayList<Vehicle>();

    }
    
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
    
    public void addVehicles(Vehicle[] vehicles) {
        for(Vehicle vehicle: vehicles) {
            this.vehicles.add(vehicle);
        }
    }
    
    public void addVehicles(ArrayList<Vehicle> vehicles) {
        for(Vehicle vehicle: vehicles) {
            this.vehicles.add(vehicle);
        }
    }
    
    public void passTime(double time) {
        vehicles.forEach((vehicle) -> vehicle.passTime(time));
    }
    
    @Override
    public void addNotify() {
        
        super.addNotify();

        animator = new Thread(this);
        animator.start();

    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        
        super.paintComponent(graphics);

        vehicles.forEach((vehicle) -> drawVehicle(graphics, vehicle));

    }
    
    /**
     * 
     * @param graphics the graphics object to draw vehicles on
     * @param vehicle the vehicle to draw
     */
    private void drawVehicle(Graphics graphics, Vehicle vehicle) {
        
        Graphics2D graphics2D = (Graphics2D)graphics;
        
        AffineTransform originalTransform = graphics2D.getTransform();
        
        graphics2D.setColor(vehicle.getColor());
        
        graphics2D.rotate(vehicle.getVelocity().getOrientation(),
                (int)Math.round(vehicle.getPosition().getXPosition() * Main.PIXELS_PER_METER),
                (int)Math.round(vehicle.getPosition().getYPosition() * Main.PIXELS_PER_METER));
        
        graphics2D.fillRect(
                (int)Math.round(vehicle.getPosition().getXPosition() * Main.PIXELS_PER_METER)
                        - (int)Math.round(vehicle.getSize().getWidth() * Main.PIXELS_PER_METER / 2),
                (int)Math.round(vehicle.getPosition().getYPosition() * Main.PIXELS_PER_METER
                        - (int)Math.round(vehicle.getSize().getHeight() * Main.PIXELS_PER_METER / 2)),
                (int)Math.round(vehicle.getSize().getWidth() * Main.PIXELS_PER_METER),
                (int)Math.round(vehicle.getSize().getHeight() * Main.PIXELS_PER_METER));
        
        graphics2D.setTransform(originalTransform);
        Toolkit.getDefaultToolkit().sync();

    }
    
    @Override
    public void run() {
        
        long startTime, timeDifference, correctedInterval;

        startTime = System.currentTimeMillis();

        while(true) {

            passTime(interval);
            repaint();
            
            timeDifference = System.currentTimeMillis() - startTime;
            correctedInterval = (int)Math.round(interval * Main.MILLISECONDS_PER_SECOND) - timeDifference;

            // I don't think this would happen
            if(correctedInterval < 0) {
                
                System.out.printf("ERROR Board Sleep: %d.\n", correctedInterval);
//                System.exit(0);
                correctedInterval = 1;
                
            }

            try {
                Thread.sleep(correctedInterval);
            } catch (InterruptedException e) {
                
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                
                JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
                
            }
            
            startTime = System.currentTimeMillis();
            
        }
    }
    
}
