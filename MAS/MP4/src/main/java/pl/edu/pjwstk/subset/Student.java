package pl.edu.pjwstk.subset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
    private String name;
    private List<University> universities = new ArrayList<>();
    private List<University> honorUniversities = new ArrayList<>();

    public Student(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        this.name = name;
    }

    public List<University> getUniversities() {
        return Collections.unmodifiableList(universities);
    }

    public void addUniversity(University university) {
        if (university == null) {
            throw new IllegalArgumentException("University cannot be null");
        }

        if (!universities.contains(university)) {
            universities.add(university);
            university.addStudent(this);
        }
    }

    public void removeUniversity(University university) {
        if (university == null) {
            throw new IllegalArgumentException("University cannot be null");
        }

        if (honorUniversities.contains(university)) {
            removeHonorUniversity(university);
        }

        if (universities.remove(university)) {
            university.removeStudent(this);
        }
    }

    public List<University> getHonorUniversities() {
        return Collections.unmodifiableList(honorUniversities);
    }

    public void addHonorUniversity(University university) {
        if (university == null) {
            throw new IllegalArgumentException("University cannot be null");
        }

        if (!universities.contains(university)) {
            throw new IllegalArgumentException(
                    "Cannot add honor university. The student must be enrolled in the university first.");
        }

        if (!honorUniversities.contains(university)) {
            honorUniversities.add(university);
            university.addHonorStudent(this);
        }
    }

    public void removeHonorUniversity(University university) {
        if (university == null) {
            throw new IllegalArgumentException("University cannot be null");
        }

        if (honorUniversities.remove(university)) {
            university.removeHonorStudent(this);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
