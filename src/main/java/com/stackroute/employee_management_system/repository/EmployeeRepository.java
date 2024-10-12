package com.stackroute.employee_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.employee_management_system.model.Employee;
import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastName(String lastName);
    List<Employee> findByDepartment(String department);
    List<Employee> findBySalaryGreaterThan(Double salary);
}

