package com.Ejada_Intern.CRUD_Task.controller;

import com.Ejada_Intern.CRUD_Task.model.Employee;
import com.Ejada_Intern.CRUD_Task.model.EmployeeCreateResponse;
import com.Ejada_Intern.CRUD_Task.model.EmployeeGetResponse;
import com.Ejada_Intern.CRUD_Task.model.EmployeeRequest;
import com.Ejada_Intern.CRUD_Task.model.EmployeeUpdateResponse;
import com.Ejada_Intern.CRUD_Task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        // Convert EmployeeRequest to Employee
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhone(employeeRequest.getPhone());
        employee.setDepartment(employeeRequest.getDepartment());
        EmployeeCreateResponse createdEmployee = employeeService.saveEmployee(employee);
        if (createdEmployee != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Failed to create employee");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        Iterable<EmployeeGetResponse> employees = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        EmployeeGetResponse employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Employee not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhone(employeeRequest.getPhone());
        employee.setDepartment(employeeRequest.getDepartment());
        EmployeeUpdateResponse updatedEmployee = employeeService.updateEmployee(id, employee);
        if (updatedEmployee != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Employee not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        boolean deleted = employeeService.deleteEmployeeById(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "Employee not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
