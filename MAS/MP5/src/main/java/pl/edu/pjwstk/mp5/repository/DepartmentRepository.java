package pl.edu.pjwstk.mp5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.mp5.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
