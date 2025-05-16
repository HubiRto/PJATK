package pl.edu.pjwstk.xor;

public class Employee {
    private String name;
    private String email;
    private Company fullTimeCompany;
    private Company partTimeCompany;

    public Employee(String name, String email) {
        setName(name);
        setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Employee name cannot be empty");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Employee email cannot be empty");
        }
        this.email = email;
    }

    public Company getFullTimeCompany() {
        return fullTimeCompany;
    }

    public void setFullTimeCompany(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("Company cannot be null");
        }

        if (this.fullTimeCompany == company) {
            return;
        }

        if (this.partTimeCompany != null && this.partTimeCompany != company) {
            throw new IllegalArgumentException(
                    "Employee is already working part-time at " + this.partTimeCompany.getName() + ". " +
                            "An employee cannot be both full-time and part-time.");
        }

        if (this.fullTimeCompany != null) {
            Company oldCompany = this.fullTimeCompany;
            this.fullTimeCompany = null;
            oldCompany.removeFullTimeEmployee(this);
        }

        this.fullTimeCompany = company;
        company.addFullTimeEmployee(this);
    }

    public void removeFullTimeCompany() {
        this.fullTimeCompany = null;
    }

    public Company getPartTimeCompany() {
        return partTimeCompany;
    }

    public void setPartTimeCompany(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("Company cannot be null");
        }

        if (this.partTimeCompany == company) {
            return;
        }

        if (this.fullTimeCompany != null && this.fullTimeCompany != company) {
            throw new IllegalArgumentException(
                    "Employee is already working full-time at " + this.fullTimeCompany.getName() + ". " +
                            "An employee cannot be both full-time and part-time.");
        }

        if (this.partTimeCompany != null) {
            Company oldCompany = this.partTimeCompany;
            this.partTimeCompany = null;
            oldCompany.removePartTimeEmployee(this);
        }

        this.partTimeCompany = company;
        company.addPartTimeEmployee(this);
    }

    public void removePartTimeCompany() {
        this.partTimeCompany = null;
    }

    @Override
    public String toString() {
        return "Employee(\u001B[32mname\u001B[0m: %s, \u001B[36memail\u001B[0m: %s)".formatted(name, email);
    }
}
