package com.Ejada_Intern.CRUD_Task.controller;

import com.Ejada_Intern.CRUD_Task.model.*;
import com.Ejada_Intern.CRUD_Task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @Operation(summary = "Create a new employee", description = "Creates a new employee and returns the created employee DTO.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Employee created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeCreateResponse.class))),
        @ApiResponse(responseCode = "400", description = "Failed to create employee or validation error",
            content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = mapToEmployee(employeeRequest);
        EmployeeCreateResponse createdEmployee = employeeService.saveEmployee(employee);
        if (createdEmployee != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
        }
        return errorResponse("Failed to create employee", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    @Operation(summary = "Get all employees", description = "Returns a list of all employees.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employees retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeGetResponse.class)))
    })
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
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
            return ResponseEntity.ok(employee);
        }
        return errorResponse("Employee not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
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
        Employee employee = mapToEmployee(employeeRequest);
        EmployeeUpdateResponse updatedEmployee = employeeService.updateEmployee(id, employee);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        }
        return errorResponse("Employee not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee", description = "Deletes an employee by ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        boolean deleted = employeeService.deleteEmployeeById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return errorResponse("Employee not found", HttpStatus.NOT_FOUND);
    }

    // Helper methods
    private Employee mapToEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setDepartment(request.getDepartment());
        return employee;
    }

    private ResponseEntity<Map<String, Object>> errorResponse(String message, HttpStatus status) {
        Map<String, Object> error = new HashMap<>();
        error.put("message", message);
        return ResponseEntity.status(status).body(error);
    }
}
