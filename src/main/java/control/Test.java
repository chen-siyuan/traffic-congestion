package control;

import javax.swing.*;
import java.awt.*;
import map.ClassPreamble;

@ClassPreamble (
    author = "William Wu",
    date = "04/23/2020",
    currentRevision = 2,
    lastModified = "04/30/2020",
    lastModifiedBy = "William Wu"
)
public class Test {

  static int AS = 0;
  static int amount = 15000;
  static int maxSpeed = 70;
  static int minSpeed = 5;
  static Animator ani = new Animator(amount, maxSpeed, minSpeed, AS);
  static Controller cont = new Controller(amount, maxSpeed, minSpeed, AS);

  public static void main(String[] args) throws InterruptedException {

    JFrame frame = new JFrame("Traffic Test");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1200, 620);
    frame.setResizable(false);

    Container pane = frame.getContentPane();
    pane.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 10;
    gbc.weighty = 1;
    gbc.gridx = 0;
    gbc.gridy = 0;
    frame.add(ani, gbc);
    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.weightx = 0.5;
    gbc.weighty = 1;
    gbc.gridx = 1;
    gbc.gridy = 0;
    frame.add(cont, gbc);

    frame.setVisible(true);

    while(true){
      update();
      Thread.sleep(1000/60);
    }
  }

  public static void update() {
    int[] temp = cont.getData();
    AS = cont.getAS();
    boolean[] tempB = cont.getButtons();
    if (tempB[0]) {
      ani.unPause();
      AS = cont.getAS();
      cont.setAll();
    }
    else if (tempB[1]) {
      ani.pause();
      cont.setAll();
    }
    else if (tempB[2]) {
      ani.reset(temp);
      amount = temp[0];
      maxSpeed = temp[1];
      minSpeed = temp[2];
      cont.setAll();
    }
    else {}
    ani.getData(amount, maxSpeed, minSpeed);
    ani.getAS(AS);
    ani.update();
  }

}
