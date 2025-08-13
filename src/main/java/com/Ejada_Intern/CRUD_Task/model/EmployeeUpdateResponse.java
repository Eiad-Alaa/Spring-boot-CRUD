package com.Ejada_Intern.CRUD_Task.model;

import java.time.LocalDateTime;

public class EmployeeUpdateResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String department;
    private LocalDateTime updatedAt;

    public EmployeeUpdateResponse(Long id, String name, String email, String phone, String department, LocalDateTime updatedAt) {
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
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
