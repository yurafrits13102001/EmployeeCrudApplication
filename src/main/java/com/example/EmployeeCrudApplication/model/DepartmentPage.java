package com.example.EmployeeCrudApplication.model;

import lombok.Getter;

import java.util.List;
@Getter
public class DepartmentPage {
    private List<Department> outputDepartments;
    private List<Integer> pageIndexes;
    private int countOfPages;


    public DepartmentPage(List<Department> outputDepartments, List<Integer> pageIndexes, int countOfPages) {
        this.outputDepartments = outputDepartments;
        this.pageIndexes = pageIndexes;
        this.countOfPages = countOfPages;
    }




}
