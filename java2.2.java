```java
import java.io.*;
import java.util.*;

class Student implements Serializable {
    int id;
    String name;
    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Employee {
    int id;
    String name;
    double salary;
    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    public String toString() {
        return id + "," + name + "," + salary;
    }
}

public class CombinedProgram {
    static final String EMP_FILE = "employees.txt";

    public static void sumUsingAutoboxing() {
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        System.out.print("Enter number of integers: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Integer num = sc.nextInt();
            sum += num;
        }
        System.out.println("Sum = " + sum);
    }

    public static void serializeDeserializeStudent() {
        try {
            Student s1 = new Student(101, "Rahul");
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.txt"));
            out.writeObject(s1);
            out.close();
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.txt"));
            Student s2 = (Student) in.readObject();
            in.close();
            System.out.println("ID: " + s2.id + ", Name: " + s2.name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addEmployee(Employee emp) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(EMP_FILE, true));
            bw.write(emp.toString());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void viewEmployees() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(EMP_FILE));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void employeeMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Back");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    addEmployee(new Employee(id, name, salary));
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } while (choice != 3);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Sum of Integers (Autoboxing)");
            System.out.println("2. Serialization of Student");
            System.out.println("3. Employee Management System");
            System.out.println("4. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    sumUsingAutoboxing();
                    break;
                case 2:
                    serializeDeserializeStudent();
                    break;
                case 3:
                    employeeMenu();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } while (choice != 4);
    }
}
```
