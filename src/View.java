import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private JFrame frame;
    private JTextArea parcelArea;
    private JTextArea customerQueueArea;
    private JTextArea logArea;
    private JTextArea currentParcelArea;

    // Buttons as instance variables
    private JButton loadButton;
    private JButton processButton;
    private JButton displayLogButton;
    private JButton viewStatisticsButton;

    public View() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Main frame
        frame = new JFrame("Depot Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(900, 700);

        // Panels
        JPanel mainPanel = new JPanel(new GridLayout(1, 4));

        // Parcels Panel
        JPanel parcelPanel = new JPanel(new BorderLayout());
        parcelPanel.setBorder(BorderFactory.createTitledBorder("Parcels in Depot"));
        parcelArea = new JTextArea();
        parcelArea.setEditable(false);
        parcelPanel.add(new JScrollPane(parcelArea), BorderLayout.CENTER);

        // Customer Queue Panel
        JPanel customerQueuePanel = new JPanel(new BorderLayout());
        customerQueuePanel.setBorder(BorderFactory.createTitledBorder("Customer Queue"));
        customerQueueArea = new JTextArea();
        customerQueueArea.setEditable(false);
        customerQueuePanel.add(new JScrollPane(customerQueueArea), BorderLayout.CENTER);

        // Logs Panel
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(BorderFactory.createTitledBorder("Logs"));
        logArea = new JTextArea();
        logArea.setEditable(false);
        logPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);

        // Current Parcel Panel (Visual Feedback)
        JPanel currentParcelPanel = new JPanel(new BorderLayout());
        currentParcelPanel.setBorder(BorderFactory.createTitledBorder("Current Parcel"));
        currentParcelArea = new JTextArea();
        currentParcelArea.setEditable(false);
        currentParcelPanel.add(new JScrollPane(currentParcelArea), BorderLayout.CENTER);

        // Add panels to the main panel
        mainPanel.add(parcelPanel);
        mainPanel.add(customerQueuePanel);
        mainPanel.add(logPanel);
        mainPanel.add(currentParcelPanel);

        // Buttons
        JPanel buttonPanel = new JPanel();
        loadButton = new JButton("Load Data");
        processButton = new JButton("Process Next Customer");
        displayLogButton = new JButton("Display Logs");
        viewStatisticsButton = new JButton("View Statistics");

        buttonPanel.add(loadButton);
        buttonPanel.add(processButton);
        buttonPanel.add(displayLogButton);
        buttonPanel.add(viewStatisticsButton);

        // Add main panel and button panel to frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);
    }

    // Getters for GUI components
    public JTextArea getParcelArea() {
        return parcelArea;
    }

    public JTextArea getCustomerQueueArea() {
        return customerQueueArea;
    }

    public JTextArea getLogArea() {
        return logArea;
    }

    public JTextArea getCurrentParcelArea() {
        return currentParcelArea;
    }

    public void addLoadButtonListener(ActionListener listener) {
        loadButton.addActionListener(listener);
    }

    public void addProcessButtonListener(ActionListener listener) {
        processButton.addActionListener(listener);
    }

    public void addDisplayLogButtonListener(ActionListener listener) {
        displayLogButton.addActionListener(listener);
    }

    public void addViewStatisticsButtonListener(ActionListener listener) {
        viewStatisticsButton.addActionListener(listener);
    }
}
