package com.example.vistaraemployeemanager.model.em;

public class Employee {
    private int id;
    private String name, password, email, country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isNameEmptyOrNull() {
        return isStrNullOrEmpty(name);
    }

    public boolean isPasswordEmptyOrNull() {
        return isStrNullOrEmpty(password);
    }

    public boolean isEmailEmptyOrNull() {
        return isStrNullOrEmpty(email);
    }

    public boolean isCountryEmptyOrNull() {
        return isStrNullOrEmpty(country);
    }

    private boolean isStrNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "Id: " + id + "> Name: " + name;
    }
}
