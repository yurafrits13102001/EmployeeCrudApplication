package com.example.EmployeeCrudApplication.service;

import com.example.EmployeeCrudApplication.model.Department;

import com.example.EmployeeCrudApplication.model.DepartmentPage;

import java.util.List;

public interface DepartmentService {

    void createDepartment(Department department);
    void updateDepartment(Department department, int dpId);
    void deleteDepartment(Department department, int dpId);
    Department getDepartment(int dpId);
    DepartmentPage getAllDpPage(int page);
    List<Department> getAllDp();
}
