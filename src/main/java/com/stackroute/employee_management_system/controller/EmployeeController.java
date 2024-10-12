package com.stackroute.employee_management_system.controller;

import com.stackroute.employee_management_system.model.Employee;
import com.stackroute.employee_management_system.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return new ResponseEntity<>("Employee added successfully!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        if (employeeService.updateEmployee(id, employee) != null) {
            return new ResponseEntity<>("Employee updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found!", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id)) {
            return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Employee not found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<?> findEmployeesByLastName(@PathVariable String lastName) {
        List<Employee> employees = employeeService.findEmployeesByLastName(lastName);
        if (employees.isEmpty()) {
            return new ResponseEntity<>("No employees found with last name: " + lastName, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<?> findEmployeesByDepartment(@PathVariable String department) {
        List<Employee> employees = employeeService.findEmployeesByDepartment(department);
        if (employees.isEmpty()) {
            return new ResponseEntity<>("No employees found in department: " + department, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/salary/{amount}")
    public ResponseEntity<?> findEmployeesWithSalaryGreaterThan(@PathVariable Double amount) {
        List<Employee> employees = employeeService.findEmployeesWithSalaryGreaterThan(amount);
        if (employees.isEmpty()) {
            return new ResponseEntity<>("No employees found with salary greater than: " + amount, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}