package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,String> {
    @Query("select e from Employee e where e.email LIKE %?1%")
    List<Employee> searchByEmail(String keyword);
}
