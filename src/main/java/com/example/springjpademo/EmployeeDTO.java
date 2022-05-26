package com.example.springjpademo;

public class EmployeeDTO {
    private String ilRuolo;
    private String name;

    public EmployeeDTO(String ilRuolo, String name) {
        this.ilRuolo = ilRuolo;
        this.name = name;
    }

    public String getIlRuolo() {
        return ilRuolo;
    }

    public void setIlRuolo(String ilRuolo) {
        this.ilRuolo = ilRuolo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
