package com.example.appchamcong.domain;

public class Person_Timekeeping {
    public int Id;
    public String workName;
    public String dateSalary;
    public String method;
    public int accountEmployee;
    public String htcc;

    public Person_Timekeeping() {
    }

    public Person_Timekeeping(int id, String workName, String dateSalary, String method, int accountEmployee, String htcc) {
        Id = id;
        this.workName = workName;
        this.dateSalary = dateSalary;
        this.method = method;
        this.accountEmployee = accountEmployee;
        this.htcc = htcc;
    }

    public Person_Timekeeping(int id, String workName, String dateSalary) {
        Id = id;
        this.workName = workName;
        this.dateSalary = dateSalary;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getDateSalary() {
        return dateSalary;
    }

    public void setDateSalary(String dateSalary) {
        this.dateSalary = dateSalary;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getAccountEmployee() {
        return accountEmployee;
    }

    public void setAccountEmployee(int accountEmployee) {
        this.accountEmployee = accountEmployee;
    }

    public String getHtcc() {
        return htcc;
    }

    public void setHtcc(String htcc) {
        this.htcc = htcc;
    }
}
