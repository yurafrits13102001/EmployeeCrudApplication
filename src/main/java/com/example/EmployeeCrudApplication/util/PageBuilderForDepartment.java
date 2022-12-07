package com.example.EmployeeCrudApplication.util;

import com.example.EmployeeCrudApplication.exception.RestException;
import com.example.EmployeeCrudApplication.model.Department;
import com.example.EmployeeCrudApplication.model.DepartmentPage;
import com.example.EmployeeCrudApplication.model.Employee;
import com.example.EmployeeCrudApplication.model.EmployeePage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PageBuilderForDepartment {
    private static final int LIMIT = 4;

    public DepartmentPage buildPage(List<Department> allDepartments, int page) {
        int endIndex = page * LIMIT;
        int startIndex = endIndex - LIMIT;
        int countOfAllDepartments = allDepartments.size();
        if (endIndex > countOfAllDepartments) {
            endIndex = countOfAllDepartments;
        }

        List<Department> outputDepartments = new ArrayList<>();

        for (int i = startIndex; i < endIndex; i++) {
            outputDepartments.add(allDepartments.get(i));
        }
        int countOfPages = (int) Math.ceil(countOfAllDepartments / (double) LIMIT);

        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= countOfPages; i++) {
            pages.add(i);
        }
        if(outputDepartments.isEmpty()){
            throw new RestException("Page: " + page + " not found!", HttpStatus.NOT_FOUND);
        }
        return new DepartmentPage(outputDepartments, pages, countOfPages);
    }
}

