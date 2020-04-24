package control;

import map.ClassPreamble;

import javax.swing.*;
import java.awt.*;

@ClassPreamble (
        author = "William Wu",
        date = "04/23/2020",
        currentRevision = 1.1,
        lastModified = "04/23/2020",
        lastModifiedBy = "Daniel Chen"
)
public class Test {
    
    static int tickSpeed = 20;
    static int amount = 15000;
    static Animator animator = new Animator();
    static Controller controller = new Controller();

    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
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
        frame.add(animator, gbc);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 0.5;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(controller, gbc);
        
        frame.setVisible(true);
        
        while(true){
            update();
            Thread.sleep(tickSpeed);
        }

    }
    
    public static void update() {
        amount = controller.getAmount();
        tickSpeed = controller.getTS() + 20;
        animator.getData(amount, tickSpeed);
        animator.update();
    }
    
}
