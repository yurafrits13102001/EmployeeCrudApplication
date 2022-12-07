package com.example.EmployeeCrudApplication.mapper;

import com.example.EmployeeCrudApplication.model.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();

        department.setDpId(rs.getInt("dpId"));
        department.setDpName(rs.getString("dpName"));
        return department;
    }
}
