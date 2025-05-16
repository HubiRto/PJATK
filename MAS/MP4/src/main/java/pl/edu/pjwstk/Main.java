package pl.edu.pjwstk;

import pl.edu.pjwstk._static.User;
import pl.edu.pjwstk.bag.Customer;
import pl.edu.pjwstk.bag.Product;
import pl.edu.pjwstk.bag.Purchase;
import pl.edu.pjwstk.my.Event;
import pl.edu.pjwstk.my.Participant;
import pl.edu.pjwstk.ordered.Book;
import pl.edu.pjwstk.ordered.Library;
import pl.edu.pjwstk.subset.Student;
import pl.edu.pjwstk.subset.University;
import pl.edu.pjwstk.unique.Vehicle;
import pl.edu.pjwstk.xor.Company;
import pl.edu.pjwstk.xor.Employee;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        {
            // Static
            User user1 = new User("john.doe@example.com");
            System.out.println("User 1 created with email: " + user1.getEmail());

            try {
                User user2 = new User("invalid-email");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            try {
                user1.setEmail("another-invalid");
            } catch (IllegalArgumentException e) {
                System.out.println("Error when updating: " + e.getMessage());
            }
        }
        {
            // Dynamic
            pl.edu.pjwstk.dynamic.Product laptop = new pl.edu.pjwstk.dynamic.Product("Laptop XYZ", 1000.0);
            System.out.println("Product created: " + laptop.getName() + ", price: $" + laptop.getPrice());

            laptop.setPrice(1150.0);
            System.out.println("Price updated to: $" + laptop.getPrice());

            try {
                laptop.setPrice(1495.0);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            laptop.setPrice(1000.0);
            System.out.println("Price decreased to: $" + laptop.getPrice());
        }
        {
            // Unique
            Vehicle car1 = new Vehicle("Toyota", "Corolla", "ABC123");
            Vehicle car2 = new Vehicle("Honda", "Civic", "XYZ789");

            System.out.println("Created vehicles:");
            for (Vehicle v : Vehicle.getExtent()) {
                System.out.println(v);
            }

            try {
                Vehicle car3 = new Vehicle("Ford", "Focus", "ABC123");
            } catch (IllegalArgumentException e) {
                System.out.println("Error creating vehicle: " + e.getMessage());
            }

            try {
                car2.setRegistrationNumber("ABC123");
            } catch (IllegalArgumentException e) {
                System.out.println("Error updating vehicle: " + e.getMessage());
            }

            car1.setRegistrationNumber("DEF456");
            System.out.println("Updated car1: " + car1);

            car2.setRegistrationNumber("ABC123");
            System.out.println("Updated car2: " + car2);
        }
        {
            // Subset
            University mit = new University("MIT");
            University harvard = new University("Harvard");

            Student john = new Student("John");
            Student mary = new Student("Mary");
            Student peter = new Student("Peter");

            mit.addStudent(john);
            mit.addStudent(mary);
            harvard.addStudent(peter);
            harvard.addStudent(mary);

            System.out.println("Initial enrollments:");
            System.out.println("MIT students: " + mit.getStudents());
            System.out.println("Harvard students: " + harvard.getStudents());

            System.out.println("\nAdding honor students:");
            mit.addHonorStudent(john);
            harvard.addHonorStudent(peter);

            System.out.println("MIT honor students: " + mit.getHonorStudents());
            System.out.println("Harvard honor students: " + harvard.getHonorStudents());

            try {
                mit.addHonorStudent(peter);
            } catch (IllegalArgumentException e) {
                System.out.println("\nError: " + e.getMessage());
            }

            System.out.println("\nEnrolling Peter in MIT:");
            mit.addStudent(peter);
            System.out.println("MIT students: " + mit.getStudents());

            mit.addHonorStudent(peter);
            System.out.println("MIT honor students: " + mit.getHonorStudents());

            System.out.println("\nRemoving Mary from Harvard:");
            harvard.removeStudent(mary);
            System.out.println("Harvard students: " + harvard.getStudents());
        }
        {
            // Ordered
            Library centralLibrary = new Library("Central Library");

            Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
            Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 1960);
            Book book3 = new Book("1984", "George Orwell", 1949);
            Book book4 = new Book("Pride and Prejudice", "Jane Austen", 1813);
            Book book5 = new Book("The Catcher in the Rye", "J.D. Salinger", 1951);

            centralLibrary.addBook(book1);
            centralLibrary.addBook(book2);
            centralLibrary.addBook(book3);
            centralLibrary.addBook(book4);
            centralLibrary.addBook(book5);

            System.out.println("Library books ordered by publication year:");
            for (Book book : centralLibrary.getBooks()) {
                System.out.println(book);
            }

            Book newBook = new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997);
            centralLibrary.addBook(newBook);

            System.out.println("\nUpdated library books (still ordered):");
            for (Book book : centralLibrary.getBooks()) {
                System.out.println(book);
            }

            System.out.println("\nChanging publication year of 1984:");
            book3.setPublicationYear(1948);

            System.out.println("\nBooks after year correction (still ordered):");
            for (Book book : centralLibrary.getBooks()) {
                System.out.println(book);
            }
        }
        {
            // Bag
            Customer john = new Customer("John Doe", "john@example.com");

            Product coffee = new Product("Coffee", 3.50);
            Product tea = new Product("Tea", 2.75);

            Purchase purchase1 = new Purchase(john, coffee, 1);
            System.out.println("First purchase: " + purchase1);

            Purchase purchase2 = new Purchase(john, coffee, 2);
            System.out.println("Second purchase (same product): " + purchase2);

            Purchase purchase3 = new Purchase(john, tea, 1);
            System.out.println("Third purchase (different product): " + purchase3);

            System.out.println("\nAll purchases for " + john.getName() + ":");
            for (Purchase purchase : john.getPurchases()) {
                System.out.println(" - " + purchase.getQuantity() + " " +
                        purchase.getProduct().getName() + " at " + purchase.getPurchaseTime());
            }

            System.out.println("\nAll purchases of " + coffee.getName() + ":");
            for (Purchase purchase : coffee.getPurchases()) {
                System.out.println(" - " + purchase.getQuantity() + " purchased by " +
                        purchase.getCustomer().getName() + " at " + purchase.getPurchaseTime());
            }
        }
        {
            // XOR
            Company techCorp = new Company("TechCorp");
            Company consultingFirm = new Company("ConsultingFirm");

            Employee alice = new Employee("Alice Smith", "alice@example.com");
            Employee bob = new Employee("Bob Johnson", "bob@example.com");
            Employee charlie = new Employee("Charlie Brown", "charlie@example.com");

            System.out.println("Hiring employees:");
            techCorp.addFullTimeEmployee(alice);
            consultingFirm.addPartTimeEmployee(bob);

            System.out.println("Full-time employees at TechCorp: " + techCorp.getFullTimeEmployees());
            System.out.println("Part-time employees at ConsultingFirm: " + consultingFirm.getPartTimeEmployees());

            System.out.println("\nTrying to add Alice as part-time at ConsultingFirm:");
            try {
                consultingFirm.addPartTimeEmployee(alice);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("\nTrying to add Bob as full-time at TechCorp:");
            try {
                techCorp.addFullTimeEmployee(bob);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("\nAdding Charlie as part-time at TechCorp:");
            techCorp.addPartTimeEmployee(charlie);
            System.out.println("Part-time employees at TechCorp: " + techCorp.getPartTimeEmployees());

            System.out.println("\nChanging Bob from part-time at ConsultingFirm to full-time at TechCorp:");
            consultingFirm.removePartTimeEmployee(bob);
            techCorp.addFullTimeEmployee(bob);

            System.out.println("Full-time employees at TechCorp: " + techCorp.getFullTimeEmployees());
            System.out.println("Part-time employees at ConsultingFirm: " + consultingFirm.getPartTimeEmployees());
        }
        {
            // My
            System.out.println("Creating events:");
            Event conference = new Event(
                    "Tech Conference",
                    "Annual technology conference",
                    LocalDate.of(2023, 6, 15),
                    LocalDate.of(2023, 6, 17)
            );

            Event workshop = new Event(
                    "Java Workshop",
                    "Workshop on Java programming",
                    LocalDate.of(2023, 7, 10),
                    LocalDate.of(2023, 7, 12)
            );

            System.out.println("Created: " + conference);
            System.out.println("Created: " + workshop);

            try {
                Event invalidEvent = new Event(
                        "Invalid Event",
                        "Event with invalid dates",
                        LocalDate.of(2023, 8, 15),
                        LocalDate.of(2023, 8, 10)
                );
            } catch (IllegalArgumentException e) {
                System.out.println("\nError creating event: " + e.getMessage());
            }

            Participant alice = new Participant("Alice Smith", "alice@example.com");
            Participant bob = new Participant("Bob Johnson", "bob@example.com");

            alice.addUnavailabilityPeriod(
                    LocalDate.of(2023, 6, 1),
                    LocalDate.of(2023, 6, 10)
            );

            bob.addUnavailabilityPeriod(
                    LocalDate.of(2023, 7, 5),
                    LocalDate.of(2023, 7, 15)
            );

            System.out.println("\nParticipant Unavailability:");
            System.out.println(alice.getName() + ": " + alice.getUnavailabilityPeriods());
            System.out.println(bob.getName() + ": " + bob.getUnavailabilityPeriods());

            System.out.println("\nAdding participants to events:");
            conference.addParticipant(alice);
            conference.addParticipant(bob);
            System.out.println("Conference participants: " + conference.getParticipants());

            try {
                workshop.addParticipant(bob);
            } catch (IllegalArgumentException e) {
                System.out.println("Error adding Bob to workshop: " + e.getMessage());
            }

            workshop.addParticipant(alice);
            System.out.println("Workshop participants: " + workshop.getParticipants());

            try {
                conference.setEndDate(LocalDate.of(2023, 6, 10));
            } catch (IllegalArgumentException e) {
                System.out.println("\nError updating conference: " + e.getMessage());
            }

            conference.setEndDate(LocalDate.of(2023, 6, 18));
            System.out.println("Updated conference: " + conference);
        }
    }
}