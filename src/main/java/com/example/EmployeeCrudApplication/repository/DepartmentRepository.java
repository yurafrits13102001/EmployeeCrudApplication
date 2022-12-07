package com.example.EmployeeCrudApplication.repository;

import com.example.EmployeeCrudApplication.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {

    List<Department> getAllDepartments();
    Optional<Department> getDepartment(int dpId);
    void deleteDepartment(Department department, int dpId);
    void updateDepartment(Department department, int dpId);
    void saveDepartment(String dpName);
    Integer countOfDepartments(String dpName);



}
