package com.stackroute.employee_management_system.service;



import com.stackroute.employee_management_system.model.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    boolean deleteEmployee(Long id);
    List<Employee> findEmployeesByLastName(String lastName);
    List<Employee> findEmployeesByDepartment(String department);
    List<Employee> findEmployeesWithSalaryGreaterThan(Double salary);
}
