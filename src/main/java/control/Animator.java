/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import map.ClassPreamble;

@ClassPreamble(
    author = "William Wu",
    date = "04/23/2020",
    currentRevision = 2,
    lastModified = "04/30/2020",
    lastModifiedBy = "William Wu"
)

/**
 *
 * @author williamw
 */
public class Animator extends JPanel{

  public int amount, maxSpeed, minSpeed, AS;
  private int VELOCITY;
  double realAS;
  private int counter = 0;
  private boolean paused = false;
  ArrayList<int[]> allCir = new ArrayList<>();

  ImageIcon bg = new ImageIcon("assets/Background_final.png");

  public Animator(int amount, int maxSpeed, int minSpeed, int AS) {

    this.amount = amount;
    this.maxSpeed = maxSpeed;
    this.minSpeed = minSpeed;
    this.AS = AS;
    this.realAS = 1 + 0.25 * AS;
    this.VELOCITY = (maxSpeed + minSpeed) / 2;

  }

  public void getData(int amount, int maxSpeed, int minSpeed){
    this.amount = amount;
    this.maxSpeed = maxSpeed;
    this.minSpeed = minSpeed;
    this.VELOCITY = (maxSpeed + minSpeed) / 2;
    System.out.println(amount);
    System.out.println(this.amount);
  }

  public void getAS(int AS){
    this.AS = AS;
    realAS = 1 + 0.25 * this.AS;
  }

  public void pause() {
    paused = true;
  }

  public void unPause() {
    paused = false;
  }

  public void reset(int[] input) {
    allCir = new ArrayList<>();
    this.getData(input[0], input[1], input[2]);
  }

  public void paintComponent (Graphics g) {
    super.paintComponent(g);
    this.setBackground(Color.BLACK);
    bg.paintIcon(this, g, 0, 0);
    for (int i = 0; i < allCir.size(); i++) {
      g.setColor(new Color(255, 130, 0));
      g.fillOval(allCir.get(i)[0], allCir.get(i)[1], 40, 40);
    }
  }

  public void update() {
    if(paused) {
      realAS = 0;
    }
    for (int i = 0; i < allCir.size(); i++) {
      int[] temp = allCir.get(i);
      temp[0] += temp[2] * VELOCITY * realAS;
      temp[1] += temp[3] * VELOCITY * realAS;
      allCir.set(i, temp);
    }

    repaint();

    double delay = 60.0 / ((((double)amount / 60.0) / 60.0) * realAS);
    if (realAS == 0) {
      counter = 0;
    }
    System.out.println(amount);
//        System.out.println(delay);
//        System.out.println(counter);
    if (counter > delay) {
      Spawner s = new Spawner();
      s.spawn();
      counter = 0;
    }
    else {
      counter++;
    }
  }

  class Spawner {

    private int[][] spawnPoints = {{220, 0}, {320, 600}, {0, 330}, {1000, 230}};

    public void spawn(){
      int randInt = (int)(Math.random()*4);
      int x = spawnPoints[randInt][0];
      int y = spawnPoints[randInt][1];
      int velox, veloy;
      switch (randInt) {
        case 0:
          velox = 0;
          veloy = 1;
          break;
        case 1:
          velox = 0;
          veloy = -1;
          break;
        case 2:
          velox = 1;
          veloy = 0;
          break;
        default:
          velox = -1;
          veloy = 0;
          break;
      }

      int[] newCir = {x, y, velox, veloy};
      allCir.add(newCir);
    }
  }
}

