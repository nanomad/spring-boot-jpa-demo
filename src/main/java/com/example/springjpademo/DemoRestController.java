package com.example.springjpademo;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoRestController {

    private final EmployeeService employeeService;
    private final ProjectService projectService;

    @Autowired
    public DemoRestController(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("CIAO");
    }

    @GetMapping("employee")
    public ResponseEntity<Collection<Employee>> listEmployees() {
        Collection<Employee> result = employeeService.listEmployeesOfProject();
        return ResponseEntity.ok(result);
    }

    @GetMapping("project")
    public ResponseEntity<Collection<ProjectDTO>> listProjects() {
        Collection<ProjectDTO> result = projectService.findAllProjects();
        return ResponseEntity.ok(result);
    }

    @GetMapping("customer")
    public ResponseEntity<Collection<CustomerDTO>> listCustomers() {
        Collection<CustomerDTO> result = projectService.findCustomerByEmployee("Giovanni");
        return ResponseEntity.ok(result);
    }

    @PostMapping("project")
    public ResponseEntity<Boolean> createProject() {
        projectService.createRandomProject();
        return ResponseEntity.ok(true);
    }

//
//    @Autowired
//    public void setEmployeeService(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }
}
