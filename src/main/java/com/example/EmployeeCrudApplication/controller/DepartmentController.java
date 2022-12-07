package com.example.EmployeeCrudApplication.controller;
import com.example.EmployeeCrudApplication.model.Department;
import com.example.EmployeeCrudApplication.model.DepartmentPage;
import com.example.EmployeeCrudApplication.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping()
    public String getAllDp(Model model, @RequestParam (value="page", defaultValue = "1") int page){
        DepartmentPage departmentPage = departmentService.getAllDpPage(page);
        model.addAttribute("departments", departmentPage.getOutputDepartments());
        model.addAttribute("pages", departmentPage.getPageIndexes());
        model.addAttribute("currentPage", page);
        model.addAttribute("countOfPages", departmentPage.getCountOfPages());
        return "get-all-departments";
    }

    @GetMapping("/create")
    public String getCreateDepartment(Model model){
        model.addAttribute("department", new Department());
        return "create-department";
    }

    @PostMapping()
    public String creatingDepartment(Department department ){
        departmentService.createDepartment(department);
        return "redirect:/department";
    }

    @GetMapping("/{id}/delete")
    public String getDeleteDepartment(Model model, @PathVariable("id") int id){
        model.addAttribute("department", departmentService.getDepartment(id));
        return "delete-department";
    }

    @PostMapping("/{id}")
    public String deleteDepartment(@ModelAttribute Department department, @PathVariable("id") int id){
        departmentService.deleteDepartment(department, id);
        return "redirect:/department";

    }
}
