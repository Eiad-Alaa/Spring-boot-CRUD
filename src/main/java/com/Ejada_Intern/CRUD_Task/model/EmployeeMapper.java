package com.Ejada_Intern.CRUD_Task.model;

public class EmployeeMapper {

    public static EmployeeCreateResponse toCreateResponse(Employee employee) {
        return new EmployeeCreateResponse(
            employee.getId(),
            employee.getName(),
            employee.getEmail(),
            employee.getPhone(),
            employee.getDepartment(),
            employee.getCreatedAt()
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
            employee.getUpdatedAt()
        );
    }
}
