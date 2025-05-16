package pl.edu.pjwstk.xor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {
    private String name;
    private final List<Employee> fullTimeEmployees = new ArrayList<>();
    private final List<Employee> partTimeEmployees = new ArrayList<>();

    public Company(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Company name cannot be empty");
        }
        this.name = name;
    }

    public List<Employee> getFullTimeEmployees() {
        return Collections.unmodifiableList(fullTimeEmployees);
    }

    public void addFullTimeEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        if (partTimeEmployees.contains(employee)) {
            throw new IllegalArgumentException(
                    "Employee " + employee.getName() + " is already a part-time employee. " +
                            "An employee cannot be both full-time and part-time.");
        }

        if (!fullTimeEmployees.contains(employee)) {
            fullTimeEmployees.add(employee);
            employee.setFullTimeCompany(this);
        }
    }

    public void removeFullTimeEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        if (fullTimeEmployees.remove(employee)) {
            employee.removeFullTimeCompany();
        }
    }

    public List<Employee> getPartTimeEmployees() {
        return Collections.unmodifiableList(partTimeEmployees);
    }

    public void addPartTimeEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        if (fullTimeEmployees.contains(employee)) {
            throw new IllegalArgumentException(
                    "Employee " + employee.getName() + " is already a full-time employee. " +
                            "An employee cannot be both full-time and part-time.");
        }

        if (!partTimeEmployees.contains(employee)) {
            partTimeEmployees.add(employee);
            employee.setPartTimeCompany(this);
        }
    }

    public void removePartTimeEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        if (partTimeEmployees.remove(employee)) {
            employee.removePartTimeCompany();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
