package com.example.andriodconcept.WebServices;

import com.example.andriodconcept.WebServices.EmployeePOJO;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EmployeesPOJO {
    @SerializedName("employees")
    private ArrayList<EmployeePOJO> employees;

    public ArrayList<EmployeePOJO> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<EmployeePOJO> employees) {
        this.employees = employees;
    }
}
