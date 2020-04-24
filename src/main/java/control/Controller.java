package control;

import map.ClassPreamble;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@ClassPreamble (
        author = "William Wu",
        date = "04/23/2020",
        currentRevision = 1,
        lastModified = "04/23/2020",
        lastModifiedBy = "William Wu"
)
public class Controller extends JPanel {
    
    JSlider amountSlider, TSSlider;
    JTextField amountTF, TSTF;
    JLabel amountLabel, TSLabel;
    JPanel amounts, TSs;
    
    public int amount, TS;
    
    public Controller () {
        
        amountSlider = new JSlider(JSlider.VERTICAL, 0 ,90000, 15000);
        amountSlider.setMajorTickSpacing(5000);
        amountSlider.setPaintTicks(true);
        TSSlider = new JSlider(JSlider.VERTICAL, -15, 100, 0);
        TSSlider.setMajorTickSpacing(5);
        TSSlider.setPaintTicks(true);
        
        amountTF = new JTextField("15000");
        TSTF = new JTextField("20");
        
        amountLabel = new JLabel("Amount: ");
        TSLabel = new JLabel("Tick Speed: ");
        
        event e = new event();
        amountSlider.addChangeListener(e);
        TSSlider.addChangeListener(e);
        
        action a = new action();
        amountTF.addActionListener(a);
        TSTF.addActionListener(a);
        
        this.setLayout(new GridLayout(1,2));
        
        amounts = new JPanel();
        TSs = new JPanel();
        
        this.add(amounts);
        this.add(TSs);
        
        
        amounts.setLayout(new GridBagLayout());
        TSs.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        amounts.add(amountLabel, gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipadx = 50;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        amounts.add(amountTF, gbc);
        
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipadx = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        amounts.add(amountSlider, gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        TSs.add(TSLabel, gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipadx = 50;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        TSs.add(TSTF, gbc);
        
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipadx = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        TSs.add(TSSlider, gbc);
    }
    
    public int getAmount() {
        return amount;
    }
    
    public int getTS() {
        return TS;
    }
    
    class event implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            amount = amountSlider.getValue();
            TS = TSSlider.getValue();
            
            amountTF.setText("" + amount);
            TSTF.setText("" + (TS + 20));
        }
    }
    
    public class action implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            amount = Integer.parseInt(amountTF.getText());
            TS = Integer.parseInt(TSTF.getText()) - 20;
            
            amountSlider.setValue(amount);
            TSSlider.setValue(TS);
        }
    }
    
    
}


