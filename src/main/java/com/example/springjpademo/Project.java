package com.example.springjpademo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            inverseJoinColumns = @JoinColumn(name = "employee_id"),
            joinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Employee> employees;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
