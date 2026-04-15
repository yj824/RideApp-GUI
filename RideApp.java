import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RideApp extends JFrame {

    private JTextField riderNameField;

    private JTextField pickupStreetField;
    private JTextField pickupCityField;
    private JComboBox<String> pickupStateBox;
    private JTextField pickupZipField;

    private JTextField dropoffStreetField;
    private JTextField dropoffCityField;
    private JComboBox<String> dropoffStateBox;
    private JTextField dropoffZipField;

    private JCheckBox expressCheckBox;
    private JRadioButton passengers1to2;
    private JRadioButton passengers3to4;
    private JRadioButton passengers5to7;

    private JButton requestRideButton;
    private JButton clearButton;
    private JButton assignRideButton;
    private JTextArea rideQueueArea;

    private List<Ride> rides = new ArrayList<>();
    private NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance(Locale.US);

    private static final String[] STATES = {
            "AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA",
            "HI","IA","ID","IL","IN","KS","KY","LA","MA","MD",
            "ME","MI","MN","MO","MS","MT","NC","ND","NE","NH",
            "NJ","NM","NV","NY","OH","OK","OR","PA","RI","SC",
            "SD","TN","TX","UT","VA","VT","WA","WI","WV","WY"
    };

    private static final String[] DRIVERS = {
            "Amina","Ben","Carlos","Dana","Ethan",
            "Fatima","George","Hannah","Ivan","Jill"
    };

    public RideApp() {
        super("Ride App");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        setJMenuBar(buildMenuBar());
        add(buildMainPanel(), BorderLayout.CENTER);
        add(buildQueuePanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        updateAssignRideButtonEnabled();
    }

    private JMenuBar buildMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        bar.add(fileMenu);
        return bar;
    }

    private JPanel buildMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(buildRiderPanel());
        panel.add(Box.createVerticalStrut(10));
        panel.add(buildPickupPanel());
        panel.add(Box.createVerticalStrut(10));
        panel.add(buildDropoffPanel());
        panel.add(Box.createVerticalStrut(10));
        panel.add(buildRideInfoPanel());

        return panel;
    }

    private JPanel buildRiderPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(""));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;

        panel.add(new JLabel("Rider name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        riderNameField = new JTextField(20);
        panel.add(riderNameField, gbc);

        return panel;
    }

    private JPanel buildPickupPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Pickup Address"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Street address:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pickupStreetField = new JTextField(25);
        panel.add(pickupStreetField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("City:"), gbc);

        gbc.gridx = 1;
        pickupCityField = new JTextField(15);
        panel.add(pickupCityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("State:"), gbc);

        gbc.gridx = 1;
        pickupStateBox = new JComboBox<>(STATES);
        panel.add(pickupStateBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Zip code:"), gbc);

        gbc.gridx = 1;
        pickupZipField = new JTextField(10);
        panel.add(pickupZipField, gbc);

        return panel;
    }

    private JPanel buildDropoffPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dropoff Address"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Street address:"), gbc);

        gbc.gridx = 1;
        dropoffStreetField = new JTextField(25);
        panel.add(dropoffStreetField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("City:"), gbc);

        gbc.gridx = 1;
        dropoffCityField = new JTextField(15);
        panel.add(dropoffCityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("State:"), gbc);

        gbc.gridx = 1;
        dropoffStateBox = new JComboBox<>(STATES);
        panel.add(dropoffStateBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Zip code:"), gbc);

        gbc.gridx = 1;
        dropoffZipField = new JTextField(10);
        panel.add(dropoffZipField, gbc);

        return panel;
    }

    private JPanel buildRideInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Ride Info"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        expressCheckBox = new JCheckBox("Express?");
        panel.add(expressCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("# of passengers:"), gbc);

        ButtonGroup group = new ButtonGroup();
        passengers1to2 = new JRadioButton("1-2", true);
        passengers3to4 = new JRadioButton("3-4");
        passengers5to7 = new JRadioButton("5-7");

        group.add(passengers1to2);
        group.add(passengers3to4);
        group.add(passengers5to7);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        radioPanel.add(passengers1to2);
        radioPanel.add(passengers3to4);
        radioPanel.add(passengers5to7);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(radioPanel, gbc);

        requestRideButton = new JButton("Request ride");
        clearButton = new JButton("Clear");

        requestRideButton.addActionListener(e -> handleRequestRide());
        clearButton.addActionListener(e -> clearForm());

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        buttonsPanel.add(requestRideButton);
        buttonsPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(buttonsPanel, gbc);

        return panel;
    }

    private JPanel buildQueuePanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JPanel top = new JPanel(new BorderLayout());
        top.add(new JLabel("Ride Queue:"), BorderLayout.WEST);

        assignRideButton = new JButton("Assign Ride");
        assignRideButton.addActionListener(e -> showAssignRideDialog());
        top.add(assignRideButton, BorderLayout.EAST);

        rideQueueArea = new JTextArea(6, 50);
        rideQueueArea.setEditable(false);
        rideQueueArea.setLineWrap(true);
        rideQueueArea.setWrapStyleWord(true);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(rideQueueArea), BorderLayout.CENTER);

        return panel;
    }

    private void handleRequestRide() {
        if (!isInputValid()) {
            JOptionPane.showMessageDialog(this, "Invalid input",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String riderName = riderNameField.getText().trim();

        Address pickup = new Address(
                pickupStreetField.getText().trim(),
                pickupCityField.getText().trim(),
                (String) pickupStateBox.getSelectedItem(),
                Integer.parseInt(pickupZipField.getText().trim())
        );

        Address dropoff = new Address(
                dropoffStreetField.getText().trim(),
                dropoffCityField.getText().trim(),
                (String) dropoffStateBox.getSelectedItem(),
                Integer.parseInt(dropoffZipField.getText().trim())
        );

        boolean express = expressCheckBox.isSelected();
        String passengerRange;
        if (passengers3to4.isSelected()) {
            passengerRange = "3-4";
        } else if (passengers5to7.isSelected()) {
            passengerRange = "5-7";
        } else {
            passengerRange = "1-2";
        }

        double price = calculatePrice(pickup.getZip(), dropoff.getZip(), express);

        Ride ride = new Ride(riderName, pickup, dropoff, express,
                passengerRange, price);

        rides.add(ride);
        refreshRideQueueDisplay();
        updateAssignRideButtonEnabled();
    }

    private boolean isInputValid() {
        if (isBlank(riderNameField) ||
                isBlank(pickupStreetField) ||
                isBlank(pickupCityField) ||
                isBlank(pickupZipField) ||
                isBlank(dropoffStreetField) ||
                isBlank(dropoffCityField) ||
                isBlank(dropoffZipField)) {
            return false;
        }

        try {
            int pickupZip = Integer.parseInt(pickupZipField.getText().trim());
            int dropoffZip = Integer.parseInt(dropoffZipField.getText().trim());
            if (pickupZip <= 0 || dropoffZip <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private boolean isBlank(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    private double calculatePrice(int pickupZip, int dropoffZip, boolean express) {
        double base = 20.0;
        double diff = Math.abs(pickupZip - dropoffZip);
        double total = base + diff;
        if (express) {
            total = total * 1.10;
        }
        return total;
    }

    private void refreshRideQueueDisplay() {
        StringBuilder sb = new StringBuilder();
        for (Ride r : rides) {
            sb.append(r.getQueueSummary(currencyFormat)).append("\n");
        }
        rideQueueArea.setText(sb.toString());
    }

    private void clearForm() {
        riderNameField.setText("");

        pickupStreetField.setText("");
        pickupCityField.setText("");
        pickupStateBox.setSelectedIndex(0);
        pickupZipField.setText("");

        dropoffStreetField.setText("");
        dropoffCityField.setText("");
        dropoffStateBox.setSelectedIndex(0);
        dropoffZipField.setText("");

        expressCheckBox.setSelected(false);
        passengers1to2.setSelected(true);
    }

    private void updateAssignRideButtonEnabled() {
        boolean hasUnassigned = rides.stream().anyMatch(r -> !r.isAssigned());
        assignRideButton.setEnabled(hasUnassigned);
    }

    private void showAssignRideDialog() {
        List<Ride> unassigned = new ArrayList<>();
        for (Ride r : rides) {
            if (!r.isAssigned()) {
                unassigned.add(r);
            }
        }

        if (unassigned.isEmpty()) {
            updateAssignRideButtonEnabled();
            return;
        }

        JComboBox<String> driverBox = new JComboBox<>(DRIVERS);
        JComboBox<Ride> rideBox = new JComboBox<>(
                unassigned.toArray(new Ride[0]));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Driver:"), gbc);

        gbc.gridx = 1;
        panel.add(driverBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Ride:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(rideBox, gbc);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Assign Ride",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String driver = (String) driverBox.getSelectedItem();
            Ride selectedRide = (Ride) rideBox.getSelectedItem();
            if (selectedRide != null && driver != null) {
                selectedRide.setDriver(driver);
                refreshRideQueueDisplay();
                updateAssignRideButtonEnabled();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RideApp().setVisible(true));
    }
}
