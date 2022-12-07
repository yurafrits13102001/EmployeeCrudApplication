package com.example.EmployeeCrudApplication.repository;

import com.example.EmployeeCrudApplication.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployee(Long empId);
    void deleteEmployee(Employee employee, Long empId);
    void updateEmployee(String empName, String empActive, Long emp_dpID, Long empId, int empAge, String date);
    void saveEmployee(String empName, String empActive, int empAge, String date, Long emp_dpID);
    List<Employee> getEmployeesByName(String empName);
    List<Employee> getEmployeesByAge(Integer empAge);
    List<Employee> getEmployeeByDate(String empDate);
    List<Employee> getEmployeesByAgeAndNameAndDate(String empName, Integer empAge, String empDate);
    List<Employee> getEmployeesByKeyword(String keyword);



}
