import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager {
    private ParcelMap parcelMap;
    private CustomerQueue customerQueue;
    private Log log;
    private JFrame frame;
    private JTextArea parcelArea;
    private JTextArea customerQueueArea;
    private JTextArea logArea;

    public Manager(ParcelMap parcelMap, CustomerQueue customerQueue) {
        this.parcelMap = parcelMap;
        this.customerQueue = customerQueue;
        this.log = Log.getInstance();
        createAndShowGUI();
    }

    public void initializeData(String parcelsFile, String customersFile) {
        readParcelsFromFile(parcelsFile);
        readCustomersFromFile(customersFile);
    }

    private void readParcelsFromFile(String parcelsFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(parcelsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Reading line: " + line); // Debugging line
                String[] data = line.split(",");
                if (data.length != 6) {
                    System.err.println("Invalid line format: " + line); // Debugging invalid lines
                    continue;
                };

                String id = data[0].trim();
                double weight = Double.parseDouble(data[1].trim());
                int width = Integer.parseInt(data[2].trim());
                int length = Integer.parseInt(data[3].trim());
                int height = Integer.parseInt(data[4].trim());
                int daysInDepot = Integer.parseInt(data[5].trim());
                Parcel parcel = new Parcel(id, weight, width, length, height, daysInDepot, Status.UNCOLLECTED);
                parcelMap.addParcel(parcel);
                System.out.println("Added parcel: " + parcel); // Debugging successful additions
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading parcels file: " + e.getMessage());
        }
    }

    private void readCustomersFromFile(String customersFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(customersFile))) {
            String line;
            int seqNo = 1;
            while ((line = br.readLine()) != null) {
                System.out.println("Reading line: " + line); // Debugging line
                String[] data = line.split(",");
                if (data.length != 2) {
                    System.err.println("Invalid line format: " + line); // Debugging invalid lines
                    continue;
                };

                String name = data[0].trim();
                String parcelID = data[1].trim();
                Customer customer = new Customer(seqNo, name, parcelID);
                customerQueue.addCustomer(customer);
                System.out.println("Added customer: " + customer); // Debugging successful additions
                seqNo++;
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading customers file: " + e.getMessage());
        }
    }

    public void startProcessing() {
        Log log = Log.getInstance();
        Worker worker = new Worker(customerQueue, parcelMap, log);
        while (!customerQueue.isEmpty()) {
            worker.processNextCustomer();
        }
        updateParcelArea(); // Update the GUI after processing
        updateCustomerQueueArea();
        updateLogArea();
    }

    private void createAndShowGUI() {
        // Main frame
        frame = new JFrame("Depot Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600);

        // Panels
        JPanel mainPanel = new JPanel(new GridLayout(1, 3));

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

        // Add panels to the main panel
        mainPanel.add(parcelPanel);
        mainPanel.add(customerQueuePanel);
        mainPanel.add(logPanel);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton loadButton = new JButton("Load Data");
        JButton processButton = new JButton("Process all Customers");
        JButton displayLogButton = new JButton("Display Logs");

        // Add action listeners
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData(); // Calls initializeData internally
            }
        });

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startProcessing(); // Processes all customers in the queue
            }
        });

        displayLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLogArea(); // Displays logs in the log panel
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(loadButton);
        buttonPanel.add(processButton);
        buttonPanel.add(displayLogButton);

        // Add main panel and button panel to frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);
    }

    private void loadData() {
        try {
            String parcelsFile = "src/Parcels.csv"; // Update with actual path
            String customersFile = "src/Custs.csv"; // Update with actual path
            initializeData(parcelsFile, customersFile);
            updateParcelArea();
            updateCustomerQueueArea();
            log.addLog("Data loaded successfully.");
            updateLogArea();
        } catch (Exception e) {
            log.addLog("Error loading data: " + e.getMessage());
            updateLogArea();
        }
    }

    private void updateParcelArea() {
        parcelArea.setText(parcelMap.toString());
    }

    private void updateCustomerQueueArea() {
        customerQueueArea.setText(customerQueue.toString());
    }

    private void updateLogArea() {
        logArea.setText(log.getLogs());
    }


    public static void main(String[] args) {
        ParcelMap parcelMap = new ParcelMap();
        CustomerQueue customerQueue = new CustomerQueue();
        new Manager(parcelMap, customerQueue);
    }
}
