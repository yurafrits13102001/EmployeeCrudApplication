package com.example.EmployeeCrudApplication.service;

import com.example.EmployeeCrudApplication.model.Employee;

import com.example.EmployeeCrudApplication.model.EmployeePage;

public interface EmployeeService {
    void createEmployee(Employee employee);
    void updateEmployee(Employee employee, Long empId);
    void deleteEmployee(Employee employee, Long empId);
    Employee getEmployee(Long empId);
    EmployeePage getAllEmp(int page);
    EmployeePage search(String empName, Integer empAge, String empDate, int page);
   EmployeePage searchByKeyword(String keyword, int page);

}

