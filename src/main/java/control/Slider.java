package control;

import map.ClassPreamble;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

@ClassPreamble(
        author = "William Wu",
        date = "04/23/2020",
        currentRevision = 1,
        lastModified = "04/23/2020",
        lastModifiedBy = "William Wu"
)
public class Slider extends JPanel {
    
    private final JSlider slider;
    private final JTextField textField;
    private JPanel panel;
    
    private double playSpeed;

    public Slider() {
        
        slider = new JSlider(JSlider.VERTICAL, -40 ,40, 0);
        textField = new JTextField("0");
        JLabel label = new JLabel("PLAY SPEED");

        slider.setMajorTickSpacing(1);
//        slider.setPaintTicks(true);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();

        labelTable.put(-40, new JLabel("00.10"));
        labelTable.put(-30, new JLabel("00.18"));
        labelTable.put(-20, new JLabel("00.32"));
        labelTable.put(-10, new JLabel("00.56"));
        labelTable.put(0, new JLabel("01.00"));
        labelTable.put(10, new JLabel("01.78"));
        labelTable.put(20, new JLabel("03.16"));
        labelTable.put(30, new JLabel("05.62"));
        labelTable.put(40, new JLabel("10.00"));

        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);

        slider.addChangeListener(event -> {
            playSpeed = slider.getValue();
            textField.setText(Double.toString(Math.random()));
        });

        textField.addActionListener(event -> {
            playSpeed = Integer.parseInt(textField.getText());
            slider.setValue(3);
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(label, gridBagConstraints);
        
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(textField, gridBagConstraints);
        
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(slider, gridBagConstraints);

    }
    
}
