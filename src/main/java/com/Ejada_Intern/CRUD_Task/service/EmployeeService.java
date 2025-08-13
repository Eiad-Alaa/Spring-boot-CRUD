package com.Ejada_Intern.CRUD_Task.service;

import com.Ejada_Intern.CRUD_Task.model.Employee;
import com.Ejada_Intern.CRUD_Task.model.EmployeeCreateResponse;
import com.Ejada_Intern.CRUD_Task.model.EmployeeGetResponse;
import com.Ejada_Intern.CRUD_Task.model.EmployeeMapper;
import com.Ejada_Intern.CRUD_Task.model.EmployeeUpdateResponse;
import com.Ejada_Intern.CRUD_Task.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeCreateResponse saveEmployee(Employee employee) {
        Employee saved = employeeRepository.save(employee);
        return EmployeeMapper.toCreateResponse(saved);
    }

    public Iterable<EmployeeGetResponse> getAllEmployees() {
        Iterable<Employee> employees = employeeRepository.findAll();
        java.util.List<EmployeeGetResponse> dtos = new java.util.ArrayList<>();
        for (Employee emp : employees) {
            dtos.add(EmployeeMapper.toGetResponse(emp));
        }
        return dtos;
    }

    public EmployeeGetResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return employee != null ? EmployeeMapper.toGetResponse(employee) : null;
    }

    public EmployeeUpdateResponse updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee == null) {
            return null;
        }
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setDepartment(employee.getDepartment());
        Employee updated = employeeRepository.save(existingEmployee);
        return EmployeeMapper.toUpdateResponse(updated);
    }

    public boolean deleteEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) {
            return false;
        }
        employeeRepository.deleteById(id);
        return true;
    }
}
