package com.example.EmployeeCrudApplication.controller;
import com.example.EmployeeCrudApplication.model.EmployeePage;
import com.example.EmployeeCrudApplication.service.DepartmentService;
import com.example.EmployeeCrudApplication.service.EmployeeService;
import com.example.EmployeeCrudApplication.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor

public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @GetMapping("/employeePage")
    public String getEmployeePage(){

        return "employees-page";
    }

    @GetMapping("/employee")
    public String getAllEmp(Model model, @RequestParam(value="page", defaultValue = "1") int page){
        EmployeePage employeePage = employeeService.getAllEmp(page);
        model.addAttribute("employees", employeePage.getOutputEmployees());
        model.addAttribute("pages", employeePage.getPageIndexes());
        model.addAttribute("currentPage", page);
        model.addAttribute("countOfPages", employeePage.getCountOfPages());
        return "output-all-employees";
    }

    @GetMapping("/employee/create")
    public String getCreateEmployee(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDp());
        return "create-employee";
    }

    @PostMapping("/employee/creating")
    public String createEmployee(Employee employee){
        if(employee.getEmpActive() == null){
            employee.setEmpActive("No");
        }
        employeeService.createEmployee(employee);

        return "redirect:/employee";
    }

    @GetMapping("/employee/{id}/edit")
    public String getEditEmployee(Model model,@PathVariable("id") Long id){
        model.addAttribute("employee", employeeService.getEmployee(id));
        model.addAttribute("departments", departmentService.getAllDp());

        return "edit-employee";
    }

    @PostMapping("/employee/{id}/edit")
    public String editEmployee( @ModelAttribute Employee employee, BindingResult result, @PathVariable("id") Long id){
        if(employee.getEmpActive() == null){
            employee.setEmpActive("No");
        }
        if(result.hasErrors()) {
            return "edit-employee";
        }

        employeeService.updateEmployee(employee, id);


        return "redirect:/employee";
    }

    @GetMapping("/employee/{id}/delete")
    public String getDeleteEmployee(Model model, @PathVariable("id") Long id){
        model.addAttribute("employee", employeeService.getEmployee(id));
        return "delete-employee";
    }

    @PostMapping("/employee/{id}/deleting")
    public String deleteEmp( @ModelAttribute Employee employee, @PathVariable("id") Long id){
        employeeService.deleteEmployee(employee, id);
        return "redirect:/employee";
    }

    @GetMapping("/employee/search")
    public String getEmployeeSearch(){

        return "find-employee-by-parameter";
    }

    @GetMapping("/employee/searching")
    public String employeeSearch(@RequestParam(value = "empName", required = false) String empName,
                                 @RequestParam(value = "empAge", required = false) Integer empAge,
                                 @RequestParam(value = "empDate", required = false) String empDate,
                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                 Model model ){

        EmployeePage employeePage = employeeService.search(empName, empAge, empDate, page);

            model.addAttribute("empName", empName);
            model.addAttribute("empAge", empAge);
            model.addAttribute("empDate", empDate);
            model.addAttribute("employees", employeePage.getOutputEmployees());
            model.addAttribute("pages", employeePage.getPageIndexes());
            model.addAttribute("currentPage", page);
            model.addAttribute("countOfPages", employeePage.getCountOfPages());


        return "output-search-employees";
    }

    @GetMapping("/employee/searchByKeyword")
    public String getEmployeeSearchByAge(){

        return "find-employee-by-keyword";
    }

    @GetMapping("/employee/searchingByKeyword")
    public String employeeSearch(@RequestParam(value = "keyword", required = false) String keyword,
                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                 Model model ){

        EmployeePage employeePage = employeeService.searchByKeyword(keyword, page);

        model.addAttribute("keyword", keyword);
        model.addAttribute("employees", employeePage.getOutputEmployees());
        model.addAttribute("pages", employeePage.getPageIndexes());
        model.addAttribute("currentPage", page);
        model.addAttribute("countOfPages", employeePage.getCountOfPages());


        return "output-search-employees-by-keyword";
    }















//    @GetMapping("/employee/searchingByAge")
//    public String employeeSearchByAge(@RequestParam(value = "keyword") int keyword,
//                                      @RequestParam(value = "page", defaultValue = "1") int page,
//                                      Model model){
//
//
//
//            EmployeePage employeePage = employeeService.searchByAge(keyword, page);
//
//            model.addAttribute("keyword", keyword);
//            model.addAttribute("employees", employeePage.getOutputEmployees());
//            model.addAttribute("pages", employeePage.getPageIndexes());
//            model.addAttribute("currentPage", page);
//            model.addAttribute("countOfPages", employeePage.getCountOfPages());
//
//
//            return "output-search-by-age-employees";
//
//    }
}
