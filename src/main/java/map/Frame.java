package map;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ClassPreamble (
    author = "Daniel Chen",
    date = "01/14/2020",
    currentRevision = 5.1,
    lastModified = "05/09/2020",
    lastModifiedBy = "Daniel Chen"
)
public class Frame extends JFrame {

  private final DisplayPanel displayPanel;
  private final ControlPanel controlPanel;
  private final boolean record;

  public static double factor = 1.;

  public Frame(Crossroad crossroad, boolean record, int frameNumber) {
    this.record = record;
    displayPanel = new DisplayPanel(crossroad, record, frameNumber);
    controlPanel = new ControlPanel();
  }

  public void addObstacle(Obstacle obstacle) {
    displayPanel.addObstacle(obstacle);
  }

  public void addObstacles(Obstacle[] obstacles) {
    displayPanel.addObstacles(obstacles);
  }

  public void addObstacles(ArrayList<Obstacle> obstacles) {
    displayPanel.addObstacles(obstacles);
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

    setLayout(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();

    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 10;
    gridBagConstraints.weighty = 1;
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    add(displayPanel, gridBagConstraints);

    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1;
    gridBagConstraints.weighty = 1;
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    add(controlPanel, gridBagConstraints);

    pack();

  }

}
