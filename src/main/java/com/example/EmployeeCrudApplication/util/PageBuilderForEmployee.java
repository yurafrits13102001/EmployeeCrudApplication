package com.example.EmployeeCrudApplication.util;

import com.example.EmployeeCrudApplication.exception.RestException;
import com.example.EmployeeCrudApplication.model.Employee;
import com.example.EmployeeCrudApplication.model.EmployeePage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PageBuilderForEmployee {

    private static final int LIMIT = 4;

    public EmployeePage buildPage(List<Employee> allEmployees, int page){
        int endIndex = page*LIMIT;
        int startIndex = endIndex - LIMIT;
        int countOfAllEmployees = allEmployees.size();
        if(endIndex > countOfAllEmployees){
            endIndex = countOfAllEmployees;
        }

        List<Employee> outputEmployees = new ArrayList<>();

        for (int i = startIndex; i < endIndex; i++) {
            outputEmployees.add(allEmployees.get(i));
        }
        int countOfPages = (int)Math.ceil(countOfAllEmployees/(double)LIMIT);

        List<Integer> pages = new ArrayList<>();
        for(int i = 1; i <= countOfPages; i++){
            pages.add(i);

        }
        if(outputEmployees.isEmpty()){
            throw new RestException("Page: " + page + " not found!", HttpStatus.NOT_FOUND);
        }

        return new EmployeePage(outputEmployees, pages, countOfPages);
    }

}
