import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillCalculatorApp extends JFrame {
    JRadioButton waterBillRadio;
    JRadioButton electricBillRadio;
    JTextField lastMeterField;
    JTextField currentMeterField;
    JTextField unitAmountField;
    JTextField resultField;
    JTextField totalRentalField;
    JComboBox<String> roomTypeComboBox;
    JButton calculateButton;
    JButton resetButton;
    JProgressBar progressBar;

    public BillCalculatorApp() {
        setTitle("Bill Calculator");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


        JLabel selectBillLabel = new JLabel("Select Bills:");
        selectBillLabel.setBounds(20, 20, 100, 20);
        add(selectBillLabel);
        waterBillRadio = new JRadioButton("Water Bill");
        waterBillRadio.setBounds(120, 20, 100, 20);
        add(waterBillRadio);
        electricBillRadio = new JRadioButton("Electric Bill");
        electricBillRadio.setBounds(220, 20, 100, 20);
        add(electricBillRadio);
        ButtonGroup billGroup = new ButtonGroup();
        billGroup.add(waterBillRadio);
        billGroup.add(electricBillRadio);


        JLabel lastMeterLabel = new JLabel("Last Meter:");
        lastMeterLabel.setBounds(20, 60, 100, 20);
        add(lastMeterLabel);
        lastMeterField = new JTextField();
        lastMeterField.setBounds(120, 60, 100, 20);
        add(lastMeterField);


        JLabel currentMeterLabel = new JLabel("Current Meter:");
        currentMeterLabel.setBounds(20, 100, 100, 20);
        add(currentMeterLabel);
        currentMeterField = new JTextField();
        currentMeterField.setBounds(120, 100, 100, 20);
        add(currentMeterField);

        JLabel unitAmountLabel = new JLabel("Unit Amount:");
        unitAmountLabel.setBounds(20, 140, 100, 20);
        add(unitAmountLabel);
        unitAmountField = new JTextField("Waiting");
        unitAmountField.setBounds(120, 140, 100, 20);
        unitAmountField.setEditable(false);
        add(unitAmountField);

        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeLabel.setBounds(240, 60, 100, 20);
        add(roomTypeLabel);
        roomTypeComboBox = new JComboBox<>(new String[]{"Please Select", "Single", "Double"});
        roomTypeComboBox.setBounds(320, 60, 100, 20);
        add(roomTypeComboBox);

        JLabel totalRentalLabel = new JLabel("Total Rental:");
        totalRentalLabel.setBounds(240, 100, 100, 20);
        add(totalRentalLabel);
        totalRentalField = new JTextField();
        totalRentalField.setBounds(320, 100, 100, 20);
        totalRentalField.setEditable(false);
        add(totalRentalField);

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setBounds(20, 180, 100, 20);
        add(resultLabel);
        resultField = new JTextField();
        resultField.setBounds(120, 180, 100, 20);
        resultField.setEditable(false);
        add(resultField);

        calculateButton = new JButton("Calculate Bill");
        calculateButton.setBounds(20, 220, 120, 30);
        add(calculateButton);
        resetButton = new JButton("Reset");
        resetButton.setBounds(160, 220, 100, 30);
        add(resetButton);

        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(20, 270, 400, 20);
        progressBar.setValue(0);
        add(progressBar);


        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int lastMeter = Integer.parseInt(lastMeterField.getText());
                    int currentMeter = Integer.parseInt(currentMeterField.getText());

                    if (currentMeter <= lastMeter) {

                        JOptionPane.showMessageDialog(
                            null,
                            "Error: Current Meter must be higher than Last Meter.",
                            "Invalid Input",
                            JOptionPane.WARNING_MESSAGE

                        );

                        return;

                    }

                    int unitConsumed = currentMeter - lastMeter;
                    int unitAmount = 0;

                    if (waterBillRadio.isSelected()) {
                        unitAmount = unitConsumed * 5; 
                    } else if (electricBillRadio.isSelected()) {
                        unitAmount = unitConsumed * 6; 
                    } else {

                        JOptionPane.showMessageDialog(

                            null,
                            "Please select a bill type.",
                            "Error",

                            JOptionPane.ERROR_MESSAGE

                        );

                        return;

                    }
                    String roomType = (String) roomTypeComboBox.getSelectedItem();
                    int roomCost = 0;
                    if ("Single".equals(roomType)) {
                        roomCost = 1500; 
                        progressBar.setValue(100); 
                    } else if ("Double".equals(roomType)) {
                        roomCost = 2000; 
                        progressBar.setValue(100); 
                    } else {
                        progressBar.setValue(50); 
                    }

                    int totalAmount = unitAmount + roomCost;
                    unitAmountField.setText(String.valueOf(unitConsumed));
                    resultField.setText(String.valueOf(unitAmount));
                    totalRentalField.setText(String.valueOf(totalAmount));

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                        null,
                        "Please enter valid numbers for meters.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE

                    );

                }

            }

        });


        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                waterBillRadio.setSelected(false);
                electricBillRadio.setSelected(false);
                lastMeterField.setText("");
                currentMeterField.setText("");
                unitAmountField.setText("Waiting");
                resultField.setText("");
                roomTypeComboBox.setSelectedIndex(0);
                totalRentalField.setText("");
                progressBar.setValue(0); 
            }

        });

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            BillCalculatorApp app = new BillCalculatorApp();
            app.setVisible(true);

        });

    }

} 