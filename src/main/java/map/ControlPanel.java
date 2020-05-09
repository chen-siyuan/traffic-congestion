package map;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

@ClassPreamble(
        author = "William Wu",
        date = "04/23/2020",
        currentRevision = 3.1,
        lastModified = "05/09/2020",
        lastModifiedBy = "Daniel Chen"
)
public class ControlPanel extends JPanel {
    
    private final JSlider slider;
    private final JTextField textField;

    private int rawSpeed;

    public ControlPanel() {
        
        slider = new JSlider(JSlider.VERTICAL, -40 ,40, 0);
        textField = new JTextField("0");
        JLabel label = new JLabel("PLAY SPEED");

        slider.setMajorTickSpacing(1);

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
            rawSpeed = slider.getValue();
            textField.setText(String.format("%.2f", convertToPlaySpeed(rawSpeed)));
            Frame.factor = convertToPlaySpeed(rawSpeed);
        });

        textField.addActionListener(event -> {
            rawSpeed = convertToRawSpeed(checkInputRawSpeed(textField.getText()));
            slider.setValue(rawSpeed);
            textField.setText(String.format("%.2f", convertToPlaySpeed(rawSpeed)));
            Frame.factor = convertToPlaySpeed(rawSpeed);
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

    /**
     *
     * @param rawSpeed the numerical value from the slider
     * @return the actual play speed factor
     */
    public double convertToPlaySpeed(int rawSpeed) {
        return Math.pow(10, rawSpeed / 40.);
    }

    /**
     *
     * @param playSpeed the actual play speed factor entered
     * @return the numerical value displayed on the slider
     */
    public int convertToRawSpeed(double playSpeed) {

        if(playSpeed > 10.) return 40;
        if(playSpeed < 0.1) return -40;

        return (int)Math.round(Math.log10(playSpeed) * 40);
    }

    /**
     *
     * @param string the inputted play speed factor string
     * @return 1 if the string is not a valid value
     */
    public double checkInputRawSpeed(String string) {

        try {
            return Double.parseDouble(string);
        } catch(NumberFormatException exception) {
            return 1.;
        }

    }

}
