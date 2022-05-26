package com.example.springjpademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/demo")
public class DemoRestController {

    private final EmployeeService employeeService;

    @Autowired
    public DemoRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("CIAO");
    }

    @GetMapping("employee")
    public ResponseEntity<Collection<EmployeeDTO>> listEmployees() {
        Collection<EmployeeDTO> result = employeeService.listEmployeesOfProject();
        return ResponseEntity.ok(result);
    }

//
//    @Autowired
//    public void setEmployeeService(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }
}
