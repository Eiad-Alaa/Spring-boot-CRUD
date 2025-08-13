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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    @Operation(summary = "Create a new employee", description = "Creates a new employee and returns the created employee DTO.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Employee created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeCreateResponse.class))),
        @ApiResponse(responseCode = "400", description = "Failed to create employee or validation error",
            content = @Content(mediaType = "application/json"))
    })
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

    @GetMapping("/employees")
    @Operation(summary = "Get all employees", description = "Returns a list of all employees.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employees retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeGetResponse.class)))
    })
    public ResponseEntity<?> getAllEmployees() {
        Iterable<EmployeeGetResponse> employees = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @GetMapping("/employees/{id}")
    @Operation(summary = "Get employee by ID", description = "Returns a single employee DTO by ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeGetResponse.class))),
        @ApiResponse(responseCode = "404", description = "Employee not found",
            content = @Content(mediaType = "application/json"))
    })
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

    @PutMapping("/employees/{id}")
    @Operation(summary = "Update employee", description = "Updates an employee and returns the updated employee DTO.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeUpdateResponse.class))),
        @ApiResponse(responseCode = "404", description = "Employee not found",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Validation error",
            content = @Content(mediaType = "application/json"))
    })
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

    @DeleteMapping("/employees/{id}")
    @Operation(summary = "Delete employee", description = "Deletes an employee by ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Employee not found")
    })
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
