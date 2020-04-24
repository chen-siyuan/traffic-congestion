package control;

import map.ClassPreamble;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@ClassPreamble(
        author = "William Wu",
        date = "04/23/2020",
        currentRevision = 1,
        lastModified = "04/23/2020",
        lastModifiedBy = "William Wu"
)
public class Animator extends JPanel{
    
    int amount = 15000;
    int tickSpeed = 20;
    int counter = 0;
    final int SPEED = 20;
    ArrayList<int[]> allCir = new ArrayList<>();
    
    ImageIcon bg = new ImageIcon("assets/Background_final.png");
    
    public Animator() {
    }
    
    public void getData(int a, int ts){
        this.amount = a;
        this.tickSpeed = ts;
    }
    
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.GREEN);
        bg.paintIcon(this, g, 0, 0);
        for (int[] ints : allCir) {
            g.setColor(new Color(255, 130, 0));
            g.fillOval(ints[0], ints[1], 40, 40);
        }
    }

    public void update() {
        for (int i = 0; i < allCir.size(); i++) {
            int[] temp = allCir.get(i);
            temp[0] += temp[2] * SPEED;
            temp[1] += temp[3] * SPEED;
            allCir.set(i, temp);
        }
        
        repaint();
        
        double delay = (3600.0 / (double)amount) * 1000.0;
//        System.out.println(delay);
//        System.out.println(counter);
        if (counter > delay) {
            Spawner s = new Spawner();
            s.spawn();
            counter = 0;
        }
        else {
            counter += tickSpeed;
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

