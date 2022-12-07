package com.example.EmployeeCrudApplication.service.implementation;
import com.example.EmployeeCrudApplication.exception.RestException;
import com.example.EmployeeCrudApplication.model.Department;
import com.example.EmployeeCrudApplication.repository.DepartmentRepository;
import com.example.EmployeeCrudApplication.model.DepartmentPage;
import com.example.EmployeeCrudApplication.service.DepartmentService;
import com.example.EmployeeCrudApplication.util.PageBuilderForDepartment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final PageBuilderForDepartment pageBuilder;
    @Override
    public void createDepartment(Department department) {
        int count = departmentRepository.countOfDepartments(department.getDpName());
        if(count > 0){
            throw new RestException("This department already exists!", HttpStatus.BAD_REQUEST);
        }
        this.departmentRepository.saveDepartment(department.getDpName());

    }

    @Override
    public void updateDepartment(Department department, int dpId) {
        this.departmentRepository.updateDepartment(department, dpId);

    }

    @Override
    public void deleteDepartment(Department department, int dpId) {
        this.departmentRepository.deleteDepartment(department, dpId);

    }

    @Override
    public Department getDepartment(int dpId) {
        Optional<Department> optionalDepartment = this.departmentRepository.getDepartment(dpId);

        if(optionalDepartment.isEmpty()){
            throw new RestException("Department with id: "+ dpId + " does not found!", HttpStatus.NOT_FOUND);
        }
        return optionalDepartment.get();
    }

    @Override
    public DepartmentPage getAllDpPage(int page) {
        List<Department> allDp = departmentRepository.getAllDepartments();


        return pageBuilder.buildPage(allDp, page);
    }

    @Override
    public List<Department> getAllDp() {
        return this.departmentRepository.getAllDepartments();
    }
}
