package com.example.EmployeeCrudApplication.repository.implementation;

import com.example.EmployeeCrudApplication.exception.RestException;
import com.example.EmployeeCrudApplication.model.Department;
import com.example.EmployeeCrudApplication.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String SQL_GET_ALL_DEPARTMENTS = "SELECT * FROM departments";
    private final String SQL_SAVE_DEPARTMENT = "INSERT INTO departments(dpName) VALUES(:dpName)";
    private final String SQL_DELETE_DEPARTMENT = "DELETE FROM departments WHERE dpId = :dpId";
    private final String SQL_SELECT_DEPARTMENT = "SELECT * FROM departments WHERE dpId = :dpId";
    private final String SQL_SELECT_COUNT_BY_NAME = "select count(d.dpID)\n" +
            "from departments d\n" +
            "where d.dpName = :dpName";
    @Override
    public List<Department> getAllDepartments() {
        return jdbcTemplate.query(SQL_GET_ALL_DEPARTMENTS, new BeanPropertyRowMapper<>(Department.class));
    }

    @Override
    public Optional<Department> getDepartment(int dpId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("dpId", dpId);
        return jdbcTemplate.query(SQL_SELECT_DEPARTMENT, parameterSource, new BeanPropertyRowMapper<>(Department.class))
                .stream()
                .findFirst();
    }

    @Override
    public void deleteDepartment(Department department, int dpId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("dpId", dpId);
        jdbcTemplate.update(SQL_DELETE_DEPARTMENT, parameterSource);


    }

    @Override
    public void updateDepartment(Department department, int dpId) {

    }

    @Override
    public void saveDepartment(String dpName) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("dpName", dpName);
         jdbcTemplate.update(SQL_SAVE_DEPARTMENT, parameterSource);

    }

    @Override
    public Integer countOfDepartments(String dpName) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("dpName", dpName);
        return jdbcTemplate.queryForList(SQL_SELECT_COUNT_BY_NAME, parameterSource, Integer.class).stream()
                .findFirst()
                .orElseThrow(() -> new RestException("", HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
