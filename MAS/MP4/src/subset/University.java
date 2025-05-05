package subset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class University {
    private String name;
    private final List<Student> students = new ArrayList<>();
    private final List<Student> honorStudents = new ArrayList<>();

    public University(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("University name cannot be empty");
        }
        this.name = name;
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        if (!students.contains(student)) {
            students.add(student);
            student.addUniversity(this);
        }
    }

    public void removeStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        // Check if the student is an honor student
        if (honorStudents.contains(student)) {
            // Remove from honor students first
            removeHonorStudent(student);
        }

        if (students.remove(student)) {
            student.removeUniversity(this);
        }
    }

    public List<Student> getHonorStudents() {
        return Collections.unmodifiableList(honorStudents);
    }

    public void addHonorStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        // Subset constraint - a student must be in the students list
        if (!students.contains(student)) {
            throw new IllegalArgumentException(
                    "Cannot add honor student. The student must be enrolled in the university first.");
        }

        if (!honorStudents.contains(student)) {
            honorStudents.add(student);
            student.addHonorUniversity(this);
        }
    }

    public void removeHonorStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        if (honorStudents.remove(student)) {
            student.removeHonorUniversity(this);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
