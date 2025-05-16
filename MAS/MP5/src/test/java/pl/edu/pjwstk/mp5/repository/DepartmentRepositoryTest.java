package pl.edu.pjwstk.mp5.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.edu.pjwstk.mp5.entity.Department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testRequiredRepositories() {
        assertNotNull(departmentRepository);
    }

    @Test
    public void testFetchDepartments() {
        Iterable<Department> all = departmentRepository.findAll();
        for (Department department : all) {
            System.out.println(department.toString());
        }
    }

    @Test
    public void testSaveDepartment() {
        Department newDept = Department.builder().name("Dept HR").location("Warsaw").budget(1_000_000).build();
        departmentRepository.save(newDept);
        assertEquals(1, departmentRepository.count());
    }
}