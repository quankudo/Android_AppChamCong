package com.example.appchamcong.domain;

public class Person_Timekeeping {
    private boolean isChuNhom;
    public int id;
    public String workName;
    public String dateSalary;
    public String method;
    public int accountEmployee;
    public String htcc;
    private boolean isGruop;
    public Person_Timekeeping() {
    }

    public Person_Timekeeping(int id, String workName, String dateSalary, String method, int accountEmployee, String htcc) {
        this.id = id;
        this.workName = workName;
        this.dateSalary = dateSalary;
        this.method = method;
        this.accountEmployee = accountEmployee;
        this.htcc = htcc;
    }

    public Person_Timekeeping(int id, String workName, String dateSalary) {
        this.id = id;
        this.workName = workName;
        this.dateSalary = dateSalary;
    }

    public Person_Timekeeping(boolean isChuNhom, int id, String workName, String dateSalary, String method, int accountEmployee, String htcc, boolean isGroup) {
        this.isChuNhom = isChuNhom;
        this.id = id;
        this.workName = workName;
        this.dateSalary = dateSalary;
        this.method = method;
        this.accountEmployee = accountEmployee;
        this.htcc = htcc;
        this.isGruop = isGroup;
    }

    public boolean isChuNhom() {
        return isChuNhom;
    }

    public void setChuNhom(boolean chuNhom) {
        isChuNhom = chuNhom;
    }

    public boolean isGroup() {
        return isGruop;
    }

    public void setGroup(boolean group) {
        isGruop = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
