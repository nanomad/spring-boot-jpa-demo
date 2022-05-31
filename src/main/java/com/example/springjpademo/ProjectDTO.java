package com.example.springjpademo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectDTO {
    private String name;
    private List<EmployeeDTO> employees;
    private String customerName;

    public ProjectDTO(Project project) {

        this.name = project.getName();
        this.employees = project.getEmployees().stream().map(x -> {
            EmployeeDTO dto = new EmployeeDTO(x.getIlRuolo(), x.getName());
            return dto;
        }).collect(Collectors.toList());
        this.customerName = project.getCustomer().getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
