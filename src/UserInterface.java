import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class UserInterface extends JFrame {

    private JButton btnSubmit;
    public static JTextField tfEmployeeName, tfEmployeeAddress, tfEmployeeAge, tfEmployeeSalary, tfEmployeeID;
    public static JDialog addEmployeeDialog, updateEmployeeDialog, deleteEmployeeDialog, displayEmployeesDialog, searchEmployeesDialog;

    public UserInterface() throws SQLException {

        this.setTitle("JDBC Demo");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setSize(290, 242);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        mainPanel(); // main panel & its components
        Database.connect(); // connecting to the database
        changeBackground(this, new Color(245, 246 ,250)); // changing background color of all components

        this.setVisible(true);
    }

    // all components visible to user are created in this method
    public void mainPanel() {

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        mainPanel.setPreferredSize(new Dimension(250, 202));
        mainPanel.setBorder(BorderFactory.createTitledBorder("Choose one of the options below ↓"));

        // button to add employees to the database
        JButton btnAddEmployee = new JButton("Add employee");
        btnAddEmployee.setPreferredSize(new Dimension(155, 24));
        // listener
        btnAddEmployee.addActionListener(e -> {

            // dialog for adding employees to the database & its components
            addEmployeeDialog = new JDialog(UserInterface.this, "Add employee", true);
            addEmployeeDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            addEmployeeDialog.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 2));
            addEmployeeDialog.setSize(new Dimension(400, 265));
            addEmployeeDialog.setResizable(false);
            addEmployeeDialog.setLocationRelativeTo(UserInterface.this);

            // additional text
            JLabel lbAddText = new JLabel("► Use this form to add a new employee to the database ◄");
            addEmployeeDialog.add(lbAddText);

            // text field for the name
            tfEmployeeName = new JTextField();
            tfEmployeeName.setPreferredSize(new Dimension(255, 43));
            tfEmployeeName.setBorder(BorderFactory.createTitledBorder("Employee name"));
            addEmployeeDialog.add(tfEmployeeName);

            // text field for the address
            tfEmployeeAddress = new JTextField();
            tfEmployeeAddress.setPreferredSize(new Dimension(255, 43));
            tfEmployeeAddress.setBorder(BorderFactory.createTitledBorder("Employee address"));
            addEmployeeDialog.add(tfEmployeeAddress);

            // text field for the age
            tfEmployeeAge = new JTextField();
            tfEmployeeAge.setPreferredSize(new Dimension(255, 43));
            tfEmployeeAge.setBorder(BorderFactory.createTitledBorder("Employee age"));
            // listener used to ignore all text characters
            tfEmployeeAge.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                }
            });
            addEmployeeDialog.add(tfEmployeeAge);

            // text field for the salary
            tfEmployeeSalary = new JTextField();
            tfEmployeeSalary.setPreferredSize(new Dimension(255, 43));
            tfEmployeeSalary.setBorder(BorderFactory.createTitledBorder("Employee salary"));
            // listener used to ignore all text characters
            tfEmployeeSalary.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                }
            });
            addEmployeeDialog.add(tfEmployeeSalary);

            // button which initializes the process of adding an employee to the database
            btnSubmit = new JButton("Submit");
            btnSubmit.setPreferredSize(new Dimension(155, 24));
            btnSubmit.addActionListener(lam -> {
                try {
                    Database.addEmployee(); // adding employee to the database
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(UserInterface.addEmployeeDialog, err.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            addEmployeeDialog.add(btnSubmit);

            // changing background color of all components in this JDialog
            changeBackground(addEmployeeDialog, new Color(245, 246, 250));

            addEmployeeDialog.setVisible(true);
        });
        mainPanel.add(btnAddEmployee);

        // button to update employees data based on ID
        JButton btnUpdateEmployee = new JButton("Update employee");
        btnUpdateEmployee.setPreferredSize(new Dimension(155, 24));
        // listener
        btnUpdateEmployee.addActionListener(e -> {

            // dialog for updating employees data & its components
            updateEmployeeDialog = new JDialog(UserInterface.this, "Update employee", true);
            updateEmployeeDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            updateEmployeeDialog.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 2));
            updateEmployeeDialog.setSize(new Dimension(400, 310));
            updateEmployeeDialog.setResizable(false);
            updateEmployeeDialog.setLocationRelativeTo(UserInterface.this);

            // additional text
            JLabel lbAddText = new JLabel("► Use this form to update data of an employee based on ID ◄");
            updateEmployeeDialog.add(lbAddText);

            // text field for the ID
            tfEmployeeID = new JTextField();
            tfEmployeeID.setPreferredSize(new Dimension(255, 43));
            tfEmployeeID.setBorder(BorderFactory.createTitledBorder("Employee ID"));
            // listener used to ignore all text characters
            tfEmployeeID.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                }
            });
            updateEmployeeDialog.add(tfEmployeeID);

            // text field for the name
            tfEmployeeName = new JTextField();
            tfEmployeeName.setPreferredSize(new Dimension( 255, 43));
            tfEmployeeName.setBorder(BorderFactory.createTitledBorder("Updated name"));
            updateEmployeeDialog.add(tfEmployeeName);

            // text field for the address
            tfEmployeeAddress = new JTextField();
            tfEmployeeAddress.setPreferredSize(new Dimension(255, 43));
            tfEmployeeAddress.setBorder(BorderFactory.createTitledBorder("Updated address"));
            updateEmployeeDialog.add(tfEmployeeAddress);

            // text field for the age
            tfEmployeeAge = new JTextField();
            tfEmployeeAge.setPreferredSize(new Dimension(255, 43));
            tfEmployeeAge.setBorder(BorderFactory.createTitledBorder("Updated age"));
            // listener used to ignore all text characters
            tfEmployeeAge.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                }
            });
            updateEmployeeDialog.add(tfEmployeeAge);

            // text field for the salary
            tfEmployeeSalary = new JTextField();
            tfEmployeeSalary.setPreferredSize(new Dimension(255, 43));
            tfEmployeeSalary.setBorder(BorderFactory.createTitledBorder("Updated salary"));
            // listener used to ignore all text characters
            tfEmployeeSalary.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                }
            });
            updateEmployeeDialog.add(tfEmployeeSalary);

            // button which initializes the process of updating data
            btnSubmit = new JButton("Submit");
            btnSubmit.setPreferredSize(new Dimension(155, 24));
            btnSubmit.addActionListener(lam -> {
                try {
                    Database.updateEmployeeData(); // updating employee data
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(UserInterface.updateEmployeeDialog, err.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            updateEmployeeDialog.add(btnSubmit);

            // changing background color of all components in this JDialog
            changeBackground(updateEmployeeDialog, new Color(245, 246, 250));

            updateEmployeeDialog.setVisible(true);
        });
        mainPanel.add(btnUpdateEmployee);

        // button to delete employees based on ID
        JButton btnDeleteEmployee = new JButton("Delete employee");
        btnDeleteEmployee.setPreferredSize(new Dimension(155, 24));
        // listener
        btnDeleteEmployee.addActionListener(e -> {

            // dialog for deleting employees from the database & its components
            deleteEmployeeDialog = new JDialog(UserInterface.this, "Delete employee", true);
            deleteEmployeeDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            deleteEmployeeDialog.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 2));
            deleteEmployeeDialog.setSize(new Dimension(400, 131));
            deleteEmployeeDialog.setResizable(false);
            deleteEmployeeDialog.setLocationRelativeTo(UserInterface.this);

            // additional text
            JLabel lbAddText = new JLabel("► Use this form to delete an employee based on ID ◄");
            deleteEmployeeDialog.add(lbAddText);

            // text field for the ID
            tfEmployeeID = new JTextField();
            tfEmployeeID.setPreferredSize(new Dimension(255, 43));
            tfEmployeeID.setBorder(BorderFactory.createTitledBorder("Employee ID"));
            // listener used to ignore all text characters
            tfEmployeeID.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                }
            });
            deleteEmployeeDialog.add(tfEmployeeID);

            // button which initializes the process of deleting employee from the database
            btnSubmit = new JButton("Submit");
            btnSubmit.setPreferredSize(new Dimension(155, 24));
            btnSubmit.addActionListener(lam -> {
                try {
                    Database.deleteEmployee(); // deleting employee
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(UserInterface.deleteEmployeeDialog, err.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            deleteEmployeeDialog.add(btnSubmit);

            // changing background color of all components in this JDialog
            changeBackground(deleteEmployeeDialog, new Color(245, 246, 250));

            deleteEmployeeDialog.setVisible(true);
        });
        mainPanel.add(btnDeleteEmployee);

        // button to display all employees from the database
        JButton btnDisplayEmployees = new JButton("Display employees");
        btnDisplayEmployees.setPreferredSize(new Dimension(155, 24));
        // listener
        btnDisplayEmployees.addActionListener(e -> {

            // dialog for displaying employees & its components
            displayEmployeesDialog = new JDialog(UserInterface.this, "Display employees", true);
            displayEmployeesDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            displayEmployeesDialog.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 2));
            displayEmployeesDialog.setSize(new Dimension(1000, 267));
            displayEmployeesDialog.setResizable(false);
            displayEmployeesDialog.setLocationRelativeTo(null);

            // additional text
            JLabel lbAddText = new JLabel("► Use this form to see all the employees ◄");
            displayEmployeesDialog.add(lbAddText);

            // loading all employees from the database into the list
            try {
                Database.loadAllEmployees();
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(UserInterface.displayEmployeesDialog, err.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

            // table which will be populated by the employees data
            DefaultTableModel model = new DefaultTableModel();
            model.setRowCount(0); // clears existing data (if there is any)

            // adding table columns
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Age");
            model.addColumn("Address");
            model.addColumn("Salary");

            // populating table columns
            for (Employee employee : Employee.employeeList) {
                model.addRow(new Object[] {
                        employee.getId(),
                        employee.getName(),
                        employee.getAge(),
                        employee.getAddress(),
                        employee.getSalary()
                });
            }

            JTable table = new JTable(model);
            table.setDefaultEditor(Object.class, null); // this is making all cells non-editable
            table.getTableHeader().setReorderingAllowed(false); // disables column reordering
            table.setFocusable(false);

            // adding table into the scroll pane
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(979, 180));
            displayEmployeesDialog.add(scrollPane);

            // button to exit the dialog
            JButton btnExit = new JButton("Exit");
            btnExit.setPreferredSize(new Dimension(155, 24));
            // returning to main menu
            btnExit.addActionListener(lam -> displayEmployeesDialog.dispose());
            displayEmployeesDialog.add(btnExit);

            // changing background color of all components in this JDialog
            changeBackground(displayEmployeesDialog, new Color(245, 246, 250));

            displayEmployeesDialog.setVisible(true);
        });
        mainPanel.add(btnDisplayEmployees);

        // button to search employees by their name
        JButton btnSearchEmployees = new JButton("Search employees");
        btnSearchEmployees.setPreferredSize(new Dimension(155, 24));
        // listener
        btnSearchEmployees.addActionListener(e -> {

            // dialog for searching employees & its components
            searchEmployeesDialog = new JDialog(UserInterface.this, "Search employees by their name", true);
            searchEmployeesDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            searchEmployeesDialog.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
            searchEmployeesDialog.setSize(new Dimension(1000, 311));
            searchEmployeesDialog.setResizable(false);
            searchEmployeesDialog.setLocationRelativeTo(UserInterface.this);

            // additional text
            JLabel lbAddText = new JLabel("► Use this form to search employees by their name ◄");
            lbAddText.setPreferredSize(new Dimension(1000, 15));
            lbAddText.setHorizontalAlignment(0);
            searchEmployeesDialog.add(lbAddText);

            // loading all employees from the database into the list
            try {
                Database.loadAllEmployees();
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(UserInterface.searchEmployeesDialog, err.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

            // table which will be populated by the employees data
            DefaultTableModel model = new DefaultTableModel();

            // adding table columns
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Age");
            model.addColumn("Address");
            model.addColumn("Salary");

            JTable table = new JTable(model);
            table.setDefaultEditor(Object.class, null); // this is making all cells non-editable
            table.getTableHeader().setReorderingAllowed(false); // disables column reordering
            table.setFocusable(false);

            // text field for the name
            tfEmployeeName = new JTextField();
            tfEmployeeName.setPreferredSize(new Dimension(255, 43));
            tfEmployeeName.setBorder(BorderFactory.createTitledBorder("Employee name"));
            // instead of having to press search button, user can just press enter to search for employees thanks to this listener
            tfEmployeeName.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        model.setRowCount(0); // clears existing data (if there is any)

                        // populating table columns
                        if (tfEmployeeName.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(UserInterface.searchEmployeesDialog, "Employee name field is empty!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else {

                            boolean employeeFound = false;
                            for (Employee employee : Employee.employeeList) {
                                if (employee.getName().toLowerCase().contains(tfEmployeeName.getText().toLowerCase())) {

                                    employeeFound = true;
                                    model.addRow(new Object[] {
                                            employee.getId(),
                                            employee.getName(),
                                            employee.getAge(),
                                            employee.getAddress(),
                                            employee.getSalary()
                                    });
                                }
                            }

                            if (!employeeFound) {
                                model.setRowCount(0); // clears existing data (if there is any)
                                JOptionPane.showMessageDialog(UserInterface.searchEmployeesDialog, "There is no employee with that name!",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            });
            searchEmployeesDialog.add(tfEmployeeName);

            // scroll pane
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(979, 180));
            searchEmployeesDialog.add(scrollPane);

            // button to search employees by their name
            JButton btnSearch = new JButton("Search");
            btnSearch.setPreferredSize(new Dimension(155, 24));
            btnSearch.addActionListener(lam -> {
                model.setRowCount(0); // clears existing data (if there is any)

                // populating table columns
                if (tfEmployeeName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(UserInterface.searchEmployeesDialog, "Employee name field is empty!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    boolean employeeFound = false;
                    for (Employee employee : Employee.employeeList) {
                        if (employee.getName().toLowerCase().contains(tfEmployeeName.getText().toLowerCase())) {

                            employeeFound = true;
                            model.addRow(new Object[] {
                                    employee.getId(),
                                    employee.getName(),
                                    employee.getAge(),
                                    employee.getAddress(),
                                    employee.getSalary()
                            });
                        }
                    }

                    if (!employeeFound) {
                        model.setRowCount(0); // clears existing data (if there is any)
                        JOptionPane.showMessageDialog(UserInterface.searchEmployeesDialog, "There is no employee with that name!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            searchEmployeesDialog.add(btnSearch);

            // button to exit the dialog
            JButton btnExit = new JButton("Exit");
            btnExit.setPreferredSize(new Dimension(155, 24));
            // returning to main menu
            btnExit.addActionListener(lam -> searchEmployeesDialog.dispose());
            searchEmployeesDialog.add(btnExit);

            // changing background color of all components in this JDialog
            changeBackground(searchEmployeesDialog, new Color(245, 246, 250));

            searchEmployeesDialog.setVisible(true);
        });
        mainPanel.add(btnSearchEmployees);

        // button to exit the program
        JButton btnExitProgram = new JButton("Exit the program");
        btnExitProgram.setPreferredSize(new Dimension(155 ,24));
        btnExitProgram.addActionListener(e -> {
            try {
                Database.disconnect(); // disconnecting from the database
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        });
        mainPanel.add(btnExitProgram);

        this.add(mainPanel);
    }

    // changing background color of all components
    public static void changeBackground(Container container, Color color) {
        Component[] components = container.getComponents();

        for (Component component : components) {
            if (component instanceof JComponent) {
                JComponent jComponent = (JComponent) component;
                jComponent.setBackground(color);
            }

            if (component instanceof Container) {
                changeBackground((Container) component, color);
            }
        }
    }

}
