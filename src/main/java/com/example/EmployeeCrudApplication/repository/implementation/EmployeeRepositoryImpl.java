package com.example.EmployeeCrudApplication.repository.implementation;

import com.example.EmployeeCrudApplication.model.Employee;
import com.example.EmployeeCrudApplication.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getAllEmployees() {
        String SQL_GET_ALL_EMPLOYEES = "SELECT e.empID AS empId, e.empName AS empName,e.empActive AS empActive, e.empAge AS empAge, e.date AS date, d.dpName AS empDepartment FROM employees e JOIN departments d ON e.emp_dpID = d.dpID";
        return jdbcTemplate.query(SQL_GET_ALL_EMPLOYEES, new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public Optional<Employee> getEmployee(Long empId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("empId", empId);
        String SQL_GET_EMPLOYEE_BY_ID = "SELECT e.empID AS empId, e.empName AS empName,e.empActive AS empActive, e.empAge AS empAge, e.date AS date, d.dpName AS empDepartment FROM employees e JOIN departments d ON e.emp_dpID = d.dpID WHERE e.empID = :empId";
        return jdbcTemplate.query(SQL_GET_EMPLOYEE_BY_ID, parameterSource, new BeanPropertyRowMapper<>(Employee.class))
                .stream()
                .findFirst();
    }

    @Override
    public void deleteEmployee(Employee employee, Long empId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("empId", empId);
        String SQL_DELETE_EMPLOYEE_BY_ID = "DELETE  FROM employees  WHERE empID = :empId";
        jdbcTemplate.update(SQL_DELETE_EMPLOYEE_BY_ID, parameterSource);

    }

    @Override
    public void updateEmployee(String empName, String empActive, Long emp_dpID, Long empId, int empAge, String date) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("empId", empId);
        parameterSource.addValue("empName", empName);
        parameterSource.addValue("empActive", empActive);
        parameterSource.addValue("empAge", empAge);
        parameterSource.addValue("emp_dpID", emp_dpID);
        parameterSource.addValue("date", date);

        String SQL_UPDATE_EMPLOYEE = "UPDATE  employees SET empName = :empName, empActive = :empActive, empAge = :empAge, date = :date, emp_dpID = :emp_dpID  WHERE empID = :empId";
        jdbcTemplate.update(SQL_UPDATE_EMPLOYEE, parameterSource);

    }

    @Override
    public void saveEmployee(String empName, String empActive, int empAge, String date, Long emp_dpID) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("empName", empName);
        parameterSource.addValue("empActive", empActive);
        parameterSource.addValue("empAge", empAge);
        parameterSource.addValue("date", date);
        parameterSource.addValue("emp_dpID", emp_dpID);

        String SQL_SAVE_EMPLOYEE = "INSERT INTO employees(empName, empActive, empAge, date, emp_dpID) VALUES (:empName, :empActive, :empAge, :date, :emp_dpID)";
        jdbcTemplate.update(SQL_SAVE_EMPLOYEE, parameterSource);

    }

    @Override
    public List<Employee> getEmployeesByName(String empName) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("empName", empName);
        String SQL_SELECT_BY_NAME = "SELECT e.empID AS empId, e.empName AS empName,e.empActive AS empActive, e.empAge AS empAge, e.date AS date, d.dpName AS empDepartment FROM employees e JOIN departments d ON e.emp_dpID = d.dpID WHERE e.empName = :empName";
        return jdbcTemplate.query(SQL_SELECT_BY_NAME, parameterSource, new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public List<Employee> getEmployeesByAge(Integer empAge) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("empAge", empAge);
        String SQL_SELECT_BY_AGE = "SELECT e.empID AS empId, e.empName AS empName, e.empActive AS empActive, e.empAge AS empAge, e.date AS date, d.dpName AS empDepartment FROM employees e JOIN departments d ON e.emp_dpID = d.dpID WHERE e.empAge = :empAge";
        return jdbcTemplate.query(SQL_SELECT_BY_AGE, parameterSource, new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public List<Employee> getEmployeeByDate(String empDate) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("empDate", empDate);
        String SQL_SELECT_BY_DATE = "SELECT e.empID AS empId, e.empName AS empName, e.empActive AS empActive, e.empAge AS empAge, e.date AS date, d.dpName AS empDepartment FROM employees e JOIN departments d ON e.emp_dpID = d.dpID WHERE e.date = :empDate";
        return jdbcTemplate.query(SQL_SELECT_BY_DATE, parameterSource, new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public List<Employee> getEmployeesByAgeAndNameAndDate(String empName, Integer empAge, String empDate) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("empAge", empAge);
        parameterSource.addValue("empName", empName);
        parameterSource.addValue("empDate", empDate);
        String SQL_SELECT_BY_AGE = "SELECT e.empID AS empId, e.empName AS empName, e.empActive AS empActive, e.empAge AS empAge, e.date AS date, d.dpName AS empDepartment FROM employees e JOIN departments d ON e.emp_dpID = d.dpID WHERE e.empAge = :empAge AND e.empName = :empName AND e.date = :empDate";
        return jdbcTemplate.query(SQL_SELECT_BY_AGE, parameterSource, new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public List<Employee> getEmployeesByKeyword(String keyword) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("keyword", keyword);
        String SQL_SELECT_BY_KEYWORD = "SELECT e.empID AS empId, e.empName AS empName, e.empActive AS empActive, e.empAge AS empAge, e.date AS date, d.dpName AS empDepartment FROM employees e JOIN departments d ON e.emp_dpID = d.dpID WHERE   e.empName LIKE :keyword or e.date LIKE :keyword or e.empAge LIKE :keyword";
        return jdbcTemplate.query(SQL_SELECT_BY_KEYWORD, parameterSource, new BeanPropertyRowMapper<>(Employee.class));
    }
}