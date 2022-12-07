package com.example.EmployeeCrudApplication.mapper;

import com.example.EmployeeCrudApplication.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmpId(rs.getLong("empId"));
        employee.setEmpName(rs.getString("empName"));
        employee.setEmpActive(rs.getString("empActive"));
        employee.setEmpDepartment(rs.getString("dpName"));
        return employee;
    }
}
