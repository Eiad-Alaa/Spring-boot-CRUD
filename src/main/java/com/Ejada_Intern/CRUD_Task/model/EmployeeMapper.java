package com.Ejada_Intern.CRUD_Task.model;

import java.time.format.DateTimeFormatter;

public class EmployeeMapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public static EmployeeCreateResponse toCreateResponse(Employee employee) {
        return new EmployeeCreateResponse(
            employee.getId(),
            employee.getName(),
            employee.getEmail(),
            employee.getPhone(),
            employee.getDepartment(),
            employee.getCreatedAt() != null ? employee.getCreatedAt().format(formatter) : null
        );
    }

    public static EmployeeGetResponse toGetResponse(Employee employee) {
        return new EmployeeGetResponse(
            employee.getId(),
            employee.getName(),
            employee.getEmail(),
            employee.getPhone(),
            employee.getDepartment()
        );
    }

    public static EmployeeUpdateResponse toUpdateResponse(Employee employee) {
        return new EmployeeUpdateResponse(
            employee.getId(),
            employee.getName(),
            employee.getEmail(),
            employee.getPhone(),
            employee.getDepartment(),
            employee.getUpdatedAt() != null ? employee.getUpdatedAt().format(formatter) : null
        );
    }
}

