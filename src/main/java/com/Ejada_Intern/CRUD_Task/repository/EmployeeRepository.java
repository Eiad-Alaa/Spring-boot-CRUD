package com.Ejada_Intern.CRUD_Task.repository;

import com.Ejada_Intern.CRUD_Task.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
