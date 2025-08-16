import java.util.*;
import java.util.stream.Collectors;

// Employee Model
class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + department + " | " + salary;
    }
}

// Employee Manager
class EmployeeManager {
    private List<Employee> employeeList = new ArrayList<>();

    // CREATE
    public void addEmployee(Employee e) {
        employeeList.add(e);
    }

    // READ
    public void viewEmployees() {
        employeeList.forEach(System.out::println);
    }

    // UPDATE
    public void updateEmployee(int id, String newName, String newDept, double newSalary) {
        employeeList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .ifPresent(e -> {
                    e.setName(newName);
                    e.setDepartment(newDept);
                    e.setSalary(newSalary);
                });
    }

    // DELETE
    public void deleteEmployee(int id) {
        employeeList.removeIf(e -> e.getId() == id);
    }

    // SORT by Salary
    public void sortBySalary() {
        employeeList.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .forEach(System.out::println);
    }

    // FILTER by Department
    public void filterByDepartment(String dept) {
        List<Employee> filtered = employeeList.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(dept))
                .collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }

    // HIGHEST Salary
    public void highestSalary() {
        employeeList.stream()
                .reduce((e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2)
                .ifPresent(System.out::println);
    }

    // AVERAGE Salary
    public void averageSalary() {
        double avg = employeeList.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.println("Average Salary = " + avg);
    }
}

// Main Class
public class EmployeeSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeManager manager = new EmployeeManager();

        while (true) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Sort by Salary");
            System.out.println("6. Filter by Department");
            System.out.println("7. Highest Salary");
            System.out.println("8. Average Salary");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double sal = sc.nextDouble();
                    manager.addEmployee(new Employee(id, name, dept, sal));
                    break;

                case 2:
                    manager.viewEmployees();
                    break;

                case 3:
                    System.out.print("Enter ID to Update: ");
                    int uid = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Department: ");
                    String newDept = sc.nextLine();
                    System.out.print("Enter New Salary: ");
                    double newSal = sc.nextDouble();
                    manager.updateEmployee(uid, newName, newDept, newSal);
                    break;

                case 4:
                    System.out.print("Enter ID to Delete: ");
                    int did = sc.nextInt();
                    manager.deleteEmployee(did);
                    break;

                case 5:
                    manager.sortBySalary();
                    break;

                case 6:
                    System.out.print("Enter Department to Filter: ");
                    String fdept = sc.nextLine();
                    manager.filterByDepartment(fdept);
                    break;

                case 7:
                    manager.highestSalary();
                    break;

                case 8:
                    manager.averageSalary();
                    break;

                case 9:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
