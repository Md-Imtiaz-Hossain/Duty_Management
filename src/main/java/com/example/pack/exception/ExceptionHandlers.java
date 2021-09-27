package com.example.pack.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(Exception.class)
    public String Exception(Model model){
        model.addAttribute("message", "Exception Occur !!! Try Again Some time After");
        return "error";
    }

    @ExceptionHandler(NullPointerException.class)
    public String NullPointerException(Model model){
        model.addAttribute("message", "NullPointerException Occur !!! Try Again Some time After");
        return "error";
    }


    @ExceptionHandler(EmployeeIdNotFoundException.class)
    public String EmployeeIdNotFoundException(Model model){
        model.addAttribute("message", "EmployeeIdNotFoundException Occur !!! Try Again Some time After");
        return "error";
    }


    @ExceptionHandler(ClientIdNotFoundException.class)
    public String ClientIdNotFoundException(Model model){
        model.addAttribute("message", "ClientIdNotFoundException Occur !!! Try Again Some time After");
        return "error";
    }


    @ExceptionHandler(FileNotFoundException.class)
    public String FileNotFoundException(Model model){
        model.addAttribute("message", "FileNotFoundException Occur !!! Try Again Some time After");
        return "error";
    }



    @ExceptionHandler(EmployeeNotFoundException.class)
    public String EmployeeNotFoundException(Model model){
        model.addAttribute("message", "EmployeeNotFoundException Occur !!! Try Again Some time After");
        return "error";
    }


    @ExceptionHandler(ClientNotFoundException.class)
    public String ClientNotFoundException(Model model){
        model.addAttribute("message", "ClientNotFoundException Occur !!! Try Again Some time After");
        return "error";
    }




}
