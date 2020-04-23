/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficInterfaceTEST;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import map.ClassPreamble;

@ClassPreamble (
        author = "WilliamWu",
        date = "04/23/2020",
        currentRevision = 1,
        lastModified = "04/23/2020",
        lastModifiedBy = "WilliamWu"
)

/**
 *
 * @author williamw
 */
public class TrafficInterface {
    
    static int tickSpeed = 20;
    static int amount = 15000;
    static Animator ani = new Animator();
    static Controller cont = new Controller();

    /**
     * @param args the command line arguments
     */
    
    
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
            Thread.sleep(tickSpeed);
        }
    }
    
    public static void update() {
        amount = cont.getAmount();
        tickSpeed = cont.getTS() + 20;
        ani.getData(amount, tickSpeed);
        ani.update();
    }
    
}
