package map;

import control.Controller;
import control.Slider;

import java.awt.*;
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

    public Frame(boolean record, int frameNumber) {
        this.record = record;
        board = new Board(record, frameNumber);
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

        setTitle("Simulation");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if(record) {
            setPreferredSize(new Dimension(
                    (int)Math.round(Main.PANEL_ALONG * Main.PIXELS_PER_METER),
                    (int)Math.round(Main.PANEL_ACROSS * Main.PIXELS_PER_METER)));
        } else {
            setPreferredSize(new Dimension(
                    (int)Math.round(Main.FRAME_ALONG * Main.PIXELS_PER_METER),
                    (int)Math.round(Main.FRAME_ACROSS * Main.PIXELS_PER_METER)));
        }

        setResizable(false);

//        add(board);

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 10;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(board, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(new Slider(), gridBagConstraints);

        pack();

    }

}
