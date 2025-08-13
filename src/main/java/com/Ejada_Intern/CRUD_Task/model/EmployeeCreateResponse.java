package com.Ejada_Intern.CRUD_Task.model;

public class EmployeeCreateResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String department;
    private String createdAt;

    public EmployeeCreateResponse(Long id, String name, String email, String phone, String department, String createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getDepartment() { return department; }
    public String getCreatedAt() { return createdAt; }
}

