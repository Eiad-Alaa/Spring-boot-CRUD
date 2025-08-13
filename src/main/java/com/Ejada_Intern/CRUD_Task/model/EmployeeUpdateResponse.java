package com.Ejada_Intern.CRUD_Task.model;

public class EmployeeUpdateResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String department;
    private String updatedAt;

    public EmployeeUpdateResponse(Long id, String name, String email, String phone, String department, String updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getDepartment() { return department; }
    public String getUpdatedAt() { return updatedAt; }
}

