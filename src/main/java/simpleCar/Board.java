/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleCar;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@ClassPreamble (
        author = "Daniel Chen",
        date = "01/14/2020",
        currentRevision = 4.1,
        lastModified = "01/18/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Board extends JPanel implements Runnable {
    
    public static final String CAR_IMAGE_FILE_NAME = "Car.png";
    
    private Thread animator;
    private ArrayList<Vehicle> vehicles;
    private BufferedImage carImage; // will generalize later
    private boolean record;
    private int frameNumber;
    private int frameCount;
    
    
    public Board(boolean record, int frameNumber) {

        setBackground(Main.BOARD_COLOR);
        setPreferredSize(new Dimension((int)Math.round(Main.PANEL_WIDTH * Main.PIXELS_PER_METER),
                (int)Math.round(Main.PANEL_HEIGHT * Main.PIXELS_PER_METER)));
        
        vehicles = new ArrayList<Vehicle>();
        
        carImage = null;
        
        try {
            carImage = ImageIO.read(new File(Main.ASSETS_ADDRESS + CAR_IMAGE_FILE_NAME));
        } catch(IOException e) {
            
            System.out.println("Car Image Not Found");
            System.exit(0);
            
        }
        
        this.record = record;
        this.frameNumber = frameNumber;
        
        frameCount = 0;

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
    
    public void passTime() {
        vehicles.forEach((vehicle) -> vehicle.passTime());
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
        
        Graphics2D graphics2D = (Graphics2D)graphics;
        
        if(record) {
            
            BufferedImage imageBuffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        
            Graphics2D imageBufferGraphics2D = (Graphics2D)imageBuffer.createGraphics();

            vehicles.forEach((vehicle) -> drawVehicle(imageBufferGraphics2D, vehicle));
        
            graphics2D.drawImage(imageBuffer, 0, 0, this);
        
            File file = new File(Main.OUTPUT_ADDRESS + String.format("TRAFFIC_CONGESTION_FRAME_%5d.png", frameCount));
            
            try {
                ImageIO.write(imageBuffer, "PNG", file);
            } catch (IOException e) {
            }
            
        } else {
            vehicles.forEach((vehicle) -> drawVehicle(graphics2D, vehicle));
        }
        
    }
    
    /**
     * 
     * @param graphics the graphics object to draw vehicles on
     * @param vehicle the vehicle to draw
     */
    private void drawVehicle(Graphics2D graphics2D, Vehicle vehicle) {
        
//        Graphics2D graphics2D = (Graphics2D)graphics;
        
        AffineTransform originalTransform = graphics2D.getTransform();
        
        graphics2D.setColor(vehicle.getColor());
        
        graphics2D.rotate(vehicle.getVelocity().getOrientation(),
                (int)Math.round(vehicle.getPosition().getXPosition() * Main.PIXELS_PER_METER),
                (int)Math.round(vehicle.getPosition().getYPosition() * Main.PIXELS_PER_METER));
        
//        graphics2D.fillRect(
//                (int)Math.round(vehicle.getPosition().getXPosition() * Main.PIXELS_PER_METER)
//                        - (int)Math.round(vehicle.getSize().getWidth() * Main.PIXELS_PER_METER / 2),
//                (int)Math.round(vehicle.getPosition().getYPosition() * Main.PIXELS_PER_METER
//                        - (int)Math.round(vehicle.getSize().getHeight() * Main.PIXELS_PER_METER / 2)),
//                (int)Math.round(vehicle.getSize().getWidth() * Main.PIXELS_PER_METER),
//                (int)Math.round(vehicle.getSize().getHeight() * Main.PIXELS_PER_METER));
        
        graphics2D.drawImage(carImage,
                (int)Math.round(vehicle.getPosition().getXPosition() * Main.PIXELS_PER_METER)
                        - (int)Math.round(vehicle.getSize().getWidth() * Main.PIXELS_PER_METER / 2),
                (int)Math.round(vehicle.getPosition().getYPosition() * Main.PIXELS_PER_METER)
                        - (int)Math.round(vehicle.getSize().getHeight() * Main.PIXELS_PER_METER / 2),
                (int)Math.round(vehicle.getSize().getWidth() * Main.PIXELS_PER_METER),
                (int)Math.round(vehicle.getSize().getHeight() * Main.PIXELS_PER_METER),
                vehicle.getColor(),
                null);
        
        // Color could be passed as arg 5 to specify the color of transparent pixels in the image.
        
        graphics2D.setTransform(originalTransform);
        Toolkit.getDefaultToolkit().sync();

    }
    
    @Override
    public void run() {
        
        long startTime, timeDifference, correctedInterval;

        startTime = System.currentTimeMillis();

        while(true) {

            passTime();
            repaint();
            
            timeDifference = System.currentTimeMillis() - startTime;
            correctedInterval = (int)Math.round(Main.INTERVAL * Main.MILLISECONDS_PER_SECOND) - timeDifference;

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
