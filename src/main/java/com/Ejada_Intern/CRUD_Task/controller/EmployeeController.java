package com.Ejada_Intern.CRUD_Task.controller;

import com.Ejada_Intern.CRUD_Task.model.Employee;
import com.Ejada_Intern.CRUD_Task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.saveEmployee(employee);
        Map<String, Object> response = new HashMap<>();
        if (createdEmployee != null) {
            response.put("message", "Employee created successfully");
            response.put("employee", createdEmployee);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.put("message", "Failed to create employee");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEmployees() {
        Iterable<Employee> employees = employeeService.getAllEmployees();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employees retrieved successfully");
        response.put("employees", employees);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        Map<String, Object> response = new HashMap<>();
        if (employee != null) {
            response.put("message", "Employee retrieved successfully");
            response.put("employee", employee);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("message", "Employee not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        Map<String, Object> response = new HashMap<>();
        if (updatedEmployee != null) {
            response.put("message", "Employee updated successfully");
            response.put("employee", updatedEmployee);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("message", "Employee not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable Long id) {
        boolean deleted = employeeService.deleteEmployeeById(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("message", "Employee deleted successfully");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            response.put("message", "Employee not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
