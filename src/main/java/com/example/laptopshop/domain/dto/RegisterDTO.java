package com.example.laptopshop.domain.dto;

import com.example.laptopshop.service.validator.RegisterChecked;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@RegisterChecked
public class RegisterDTO {

    @Size(min = 2, message = "FirstName cannot be empty")
    private String firstName;
    private String lastName;
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @Size(min = 2, message = "Passwowd cannot be empty")
    private String password;
    private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
