package com.weone.demoemployeeportal.repo;

import com.weone.demoemployeeportal.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
