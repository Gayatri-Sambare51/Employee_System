package com.stackroute.employee_management_system.service;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.employee_management_system.model.Employee;
import com.stackroute.employee_management_system.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (!existingEmployee.isPresent()) {
            // Returning null if the employee is not found
            return null;
        }
        employee.setId(id);
        return employeeRepository.save(employee);
	}

	@Override
	public boolean deleteEmployee(Long id) {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            employeeRepository.delete(employeeOptional.get());
            return true; // Employee was found and deleted
        }
        return false; // Employee not found
	}

	@Override
	public List<Employee> findEmployeesByLastName(String lastName) {
		return employeeRepository.findByLastName(lastName);
	}

	@Override
	public List<Employee> findEmployeesByDepartment(String department) {
		 return employeeRepository.findByDepartment(department);
	}

	@Override
	public List<Employee> findEmployeesWithSalaryGreaterThan(Double salary) {
		return employeeRepository.findBySalaryGreaterThan(salary);
	}

}
