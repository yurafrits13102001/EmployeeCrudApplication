package com.example.EmployeeCrudApplication.service.implementation;
import com.example.EmployeeCrudApplication.repository.EmployeeRepository;
import com.example.EmployeeCrudApplication.exception.RestException;
import com.example.EmployeeCrudApplication.model.Employee;
import com.example.EmployeeCrudApplication.model.EmployeePage;
import com.example.EmployeeCrudApplication.service.EmployeeService;
import com.example.EmployeeCrudApplication.util.PageBuilderForEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PageBuilderForEmployee pageBuilder;
    @Override
    public void createEmployee(Employee employee) {
        if(employee.getEmpAge() <= 17){
            throw new RestException("The age of the employee must not be less than 18 years!!!", HttpStatus.BAD_REQUEST);
        }
        this.employeeRepository.saveEmployee(employee.getEmpName(), employee.getEmpActive(), employee.getEmpAge(), employee.getDate(), employee.getEmp_dpID());


    }

    @Override
    public void updateEmployee(Employee employee, Long empId) {
        this.employeeRepository.updateEmployee(employee.getEmpName(),employee.getEmpActive(), employee.getEmp_dpID(), empId, employee.getEmpAge(), employee.getDate());


    }

    @Override
    public void deleteEmployee(Employee employee, Long empId) {
        this.employeeRepository.deleteEmployee(employee, empId);

    }

    @Override
    public Employee getEmployee(Long empId) {
        Optional<Employee> optionalEmployee = this.employeeRepository.getEmployee(empId);
        if(optionalEmployee.isEmpty()){
            throw new RestException("Employee with id: " + empId + " not found!", HttpStatus.NOT_FOUND);
        }
        return optionalEmployee.get();
    }

    @Override
    public EmployeePage getAllEmp(int page) {
        List<Employee> allEmp = employeeRepository.getAllEmployees();

        return pageBuilder.buildPage(allEmp, page);




    }



    @Override
    public EmployeePage search(String empName, Integer empAge, String empDate, int page) {
        if(empAge == null && empDate == null) {
            List<Employee> allEmp = employeeRepository.getEmployeesByName(empName);

            if (allEmp.isEmpty()) {
                throw new RestException("Employees with name: " + empName + " not found!", HttpStatus.NOT_FOUND);
            }


            return pageBuilder.buildPage(allEmp, page);
        }
        if((empName == null || empName.isEmpty()) && (empDate == null || empDate.isEmpty())){

            List<Employee> allEmp = employeeRepository.getEmployeesByAge(empAge);


            if (allEmp.isEmpty()) {
                throw new RestException("Employees with age: " + empAge + " not found!", HttpStatus.NOT_FOUND);
            }


            return pageBuilder.buildPage(allEmp, page);

        }
        if(empAge == null && (empName == null || empName.isEmpty())){

            List<Employee> allEmp = employeeRepository.getEmployeeByDate(empDate);

            if (allEmp.isEmpty()) {
                throw new RestException("Employees with date of joining: " + empDate + " not found!", HttpStatus.NOT_FOUND);
            }

            return pageBuilder.buildPage(allEmp, page);
        }
        List<Employee> allEmp = employeeRepository.getEmployeesByAgeAndNameAndDate(empName, empAge, empDate);


        if (allEmp.isEmpty()) {
            throw new RestException("Employees with age and name: " + empAge + " and  " + empName + " not found!", HttpStatus.NOT_FOUND);
        }



        return pageBuilder.buildPage(allEmp, page);


    }

    @Override
    public EmployeePage searchByKeyword(String keyword, int page) {
        List<Employee> allEmp = employeeRepository.getEmployeesByKeyword(keyword);
        return pageBuilder.buildPage(allEmp, page);
    }


//    @Override
//    public EmployeePage searchByAge(Integer empAge, int page) {
//        List<Employee> allEmp = employeeRepository.getEmployeesByAge(empAge);
//        if(allEmp.isEmpty()){
//            throw new RestException("Employees with age: " + empAge + " not found!", HttpStatus.NOT_FOUND);
//        }
//
//
//
//        return pageBuilder.buildPage(allEmp, page);
//    }
}
