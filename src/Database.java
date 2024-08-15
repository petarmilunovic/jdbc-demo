import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static Connection conn = null;

    /**
     * methods for connecting and disconnecting from the database
     */

    public static void connect() throws SQLException {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/name_of_your_db", "username", "password");
    }
    public static void disconnect() throws SQLException {
        conn.close();
    }

    /**
     * methods for manipulating data
     */

    public static void addEmployee() throws SQLException {

        // checking if any field is empty
        if (UserInterface.tfEmployeeName.getText().isEmpty() ||
                UserInterface.tfEmployeeAddress.getText().isEmpty() ||
                UserInterface.tfEmployeeAge.getText().isEmpty() ||
                UserInterface.tfEmployeeSalary.getText().isEmpty()) {

            JOptionPane.showMessageDialog(UserInterface.addEmployeeDialog, "Check if all fields are populated!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            /* text fields for age and salary ignore any text (only accept digits), however there
               is a way for user to input text into the fields by copying it from somewhere else and pasting it!
               below ensures that fields truly have only digits in them */

            if (UserInterface.tfEmployeeAge.getText().matches("\\d+") &&
                    UserInterface.tfEmployeeSalary.getText().matches("\\d+")) {

                String name = UserInterface.tfEmployeeName.getText();
                int age = Integer.parseInt(UserInterface.tfEmployeeAge.getText());
                String address = UserInterface.tfEmployeeAddress.getText();
                int salary = Integer.parseInt(UserInterface.tfEmployeeSalary.getText());

                // adding employee to the database
                PreparedStatement ps = conn.prepareStatement("INSERT INTO employee VALUES (null, ?, ?, ?, ?)");
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setString(3, address);
                ps.setInt(4, salary);
                ps.execute();

                JOptionPane.showMessageDialog(UserInterface.addEmployeeDialog, "Employee has successfully been added to the database!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                // returning to main menu
                UserInterface.addEmployeeDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(UserInterface.addEmployeeDialog, "Please make sure that fields for age and salary contain only numbers!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void updateEmployeeData() throws SQLException {

        // checking if any field is empty
        if (UserInterface.tfEmployeeID.getText().isEmpty() ||
                UserInterface.tfEmployeeName.getText().isEmpty() ||
                UserInterface.tfEmployeeAddress.getText().isEmpty() ||
                UserInterface.tfEmployeeAge.getText().isEmpty() ||
                UserInterface.tfEmployeeSalary.getText().isEmpty()) {

            JOptionPane.showMessageDialog(UserInterface.updateEmployeeDialog, "Check if all fields are populated!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {

           /* text fields for ID, age and salary ignore any text (only accept digits), however there
               is a way for user to input text into the fields by copying it from somewhere else and pasting it!
               below ensures that fields truly have only digits in them */

            if (UserInterface.tfEmployeeID.getText().matches("\\d+") &&
                    UserInterface.tfEmployeeAge.getText().matches("\\d+") &&
                    UserInterface.tfEmployeeSalary.getText().matches("\\d+")) {

                int id = Integer.parseInt(UserInterface.tfEmployeeID.getText());
                // checking if employee exists or not
                if (employeeValidation(id)) {

                    String updatedEmployeeName = UserInterface.tfEmployeeName.getText();
                    int updatedEmployeeAge = Integer.parseInt(UserInterface.tfEmployeeAge.getText());
                    String updatedEmployeeAddress = UserInterface.tfEmployeeAddress.getText();
                    int updatedEmployeeSalary = Integer.parseInt(UserInterface.tfEmployeeSalary.getText());

                    // updating employee data
                    PreparedStatement ps = conn.prepareStatement( "UPDATE employee SET name = ?, " +
                            "age = ?, " + "address = ?, " + "salary = ? WHERE id = " + id);
                    ps.setString(1, updatedEmployeeName);
                    ps.setInt(2, updatedEmployeeAge);
                    ps.setString(3, updatedEmployeeAddress);
                    ps.setInt(4, updatedEmployeeSalary);
                    ps.execute();

                    JOptionPane.showMessageDialog(UserInterface.updateEmployeeDialog, "Data has been successfully updated for the employee with ID " + id + "!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    // returning to main menu
                    UserInterface.updateEmployeeDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(UserInterface.updateEmployeeDialog, "Employee with ID " + id + " does not exist!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(UserInterface.updateEmployeeDialog, "Please make sure that fields for ID, age and salary contain only numbers!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void deleteEmployee () throws SQLException {

        // checking if ID field is empty
        if (UserInterface.tfEmployeeID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(UserInterface.deleteEmployeeDialog, "Check if the ID field is populated!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            /* field for ID ignores any text (only accept digits), however there
               is a way for user to input text into the field by copying it from somewhere else and pasting it!
               below ensures that field truly has only digits in it */

            if (UserInterface.tfEmployeeID.getText().matches("\\d+")) {

                int id = Integer.parseInt(UserInterface.tfEmployeeID.getText());
                // checking if employee exists or not
                if (employeeValidation(id)) {

                    // deleting employee
                    PreparedStatement ps = conn.prepareStatement("DELETE FROM employee WHERE id = ?");
                    ps.setInt(1, id);
                    ps.execute();

                    JOptionPane.showMessageDialog(UserInterface.deleteEmployeeDialog, "Employee with ID " + id + " has successfully been deleted from the database!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);

                    // returning to main menu
                    UserInterface.deleteEmployeeDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(UserInterface.deleteEmployeeDialog, "Employee with ID " + id + " does not exist!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(UserInterface.deleteEmployeeDialog, "Please make sure that field for ID contains only numbers!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void loadAllEmployees() throws SQLException {

        Employee.employeeList = new ArrayList<>();

        Statement st = conn.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT * FROM employee");
        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age= resultSet.getInt("age");
            String address = resultSet.getString("address");
            int salary = resultSet.getInt("salary");

            // loading all employees from the database into the list
            Employee.employeeList.add(new Employee(id, name, age, address, salary));
        }
    }

    // checking if user ID exists in the database
    public static boolean employeeValidation(int ID) throws SQLException {

        Statement st = conn.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT * FROM employee WHERE id = '" + ID + "'");

        return resultSet.next();
    }


}
