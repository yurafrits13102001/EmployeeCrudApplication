package com.example.EmployeeCrudApplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long empId;
    @Pattern(regexp = "[A-Za-z]{1,25}", message = "The name must contain more than 2 characters and less than 25")
    private String empName;
    private String empActive;
    private String empDepartment;
    private Long emp_dpID;
    private Integer empAge;
    private String date;

}
