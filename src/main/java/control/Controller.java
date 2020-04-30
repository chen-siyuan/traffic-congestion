/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import map.ClassPreamble;

@ClassPreamble (
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
public class Controller extends JPanel {
    
    JSlider ASSlider;
    JTextField amountTF, maxSpeedTF, minSpeedTF;
    JButton startButton, pauseButton, resetButton;
    JLabel amountLabel, maxSpeedLabel, minSpeedLabel, ASLabel, ASFixedLabel;
    JPanel controlPanel, ASPanel;
    
    public int amount, maxSpeed, minSpeed, AS;
    private boolean start=false, pause=false, reset=false;
    
    public Controller (int amount, int maxSpeed, int minSpeed, int AS) {
        
        this.amount = amount;
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
        this.AS = AS;
        
        ASSlider = new JSlider(JSlider.VERTICAL, -3, 12, this.AS);
        ASSlider.setMajorTickSpacing(1);
        ASSlider.setPaintTicks(true);
        
        amountTF = new JTextField("" + amount);
        maxSpeedTF = new JTextField("" + maxSpeed);
        minSpeedTF = new JTextField("" + minSpeed);
        
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        resetButton = new JButton("Reset");
        
        Start s = new Start();
        Pause p = new Pause();
        Reset r = new Reset();
        startButton.addActionListener(s);
        pauseButton.addActionListener(p);
        resetButton.addActionListener(r);
        
        amountLabel = new JLabel("Amount: ");
        maxSpeedLabel = new JLabel("Max Speed kph: ");
        minSpeedLabel = new JLabel("Min Speed kph: ");
        ASFixedLabel = new JLabel("Animation Speed: ");
        ASLabel = new JLabel("" + (1 + 0.25 * this.AS));
        
        this.setLayout(new GridLayout(1,2));
        
        controlPanel = new JPanel();
        ASPanel = new JPanel();
        
        this.add(controlPanel);
        this.add(ASPanel);
        
        
        controlPanel.setLayout(new GridBagLayout());
        ASPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(amountLabel, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        controlPanel.add(amountTF, gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        controlPanel.add(maxSpeedLabel, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 3;
        controlPanel.add(maxSpeedTF, gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        controlPanel.add(minSpeedLabel, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 5;
        controlPanel.add(minSpeedTF, gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 6;
        controlPanel.add(startButton, gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 7;
        controlPanel.add(pauseButton, gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 8;
        controlPanel.add(resetButton, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        ASPanel.add(ASFixedLabel, gbc);
        
        gbc.fill = GridBagConstraints.CENTER;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        ASPanel.add(ASLabel, gbc);
        
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 1;
        gbc.weighty = 3;
        gbc.gridx = 0;
        gbc.gridy = 2;
        ASPanel.add(ASSlider, gbc);
    }
    
    public int[] getData() {
        try{this.amount = Integer.parseInt(amountTF.getText());}
        catch(Exception e) {amountTF.setText("15000");}
        try{this.maxSpeed = Integer.parseInt(maxSpeedTF.getText());}
        catch(Exception e) {maxSpeedTF.setText("70");}
        try{this.minSpeed = Integer.parseInt(minSpeedTF.getText());}
        catch(Exception e) {minSpeedTF.setText("5");}
        int[] temp = {amount, maxSpeed, minSpeed};
        System.out.println(amount);
        return temp;
    }
    
    public boolean[] getButtons() {
        boolean[] temp = {start, pause, reset};
        return temp;
    }
    
    public void setStart() {
        start = false;
    }
    
    public void setPause() {
        pause = false;
    }
    
    public void setReset() {
        reset = false;
    }
    
    public void setAll() {
        start = false;
        pause = false;
        reset = false;
    }
    
    public int getAS() {
        AS = ASSlider.getValue();
        ASLabel.setText("" + (1 + 0.25 * AS));
        return AS;
    }
    
    public class Start implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            start = true;
        }
    }
    
    public class Pause implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            pause = true;
        }
    }
    
    public class Reset implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            reset = true;
        }
    }
}


