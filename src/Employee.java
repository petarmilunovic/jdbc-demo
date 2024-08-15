import java.util.ArrayList;

public class Employee {

    public String name, address;
    public int id, age, salary;
    public static ArrayList<Employee> employeeList;

    public Employee(int ID, String name, int age, String address, int salary) {
        this.name = name;
        this.address = address;
        this.id = ID;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", address: " + address + ", age: " +
                age + ", salary: " + salary;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

}
