package com.example.EmployeeCrudApplication.controller;
import com.example.EmployeeCrudApplication.exception.RestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.util.NestedServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        NestedServletException nestedServletException = (NestedServletException)request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if(!(nestedServletException.getCause() instanceof RestException restException)){
            return "error-500";
        }
        model.addAttribute("errorMessage", restException.getMessage());

        return "custom-error";
    }
}
