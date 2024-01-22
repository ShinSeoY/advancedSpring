package com.sandy.advancedSpring.repository.admin;

import com.sandy.advancedSpring.domain.admin.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
