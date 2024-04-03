package com.example.EventHub.User;

import com.example.EventHub.Role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

public class UserDTO {

    @NotEmpty(message = "Username cannot be empty!")
    @Min(value = 4, message = "Username can´t be less than 4 characters!")
    @Max(value = 20, message = "Username can´t be more than 20 characters!")
    private String username;
    @NotEmpty(message = "Please enter email!")
    private String email;
    @NotEmpty(message = "Please enter first name!")
    private String firstName;
    @NotEmpty(message = "Please enter last name!")
    private String lastName;
    @NotEmpty(message = "Fill in the password!")
    @Min(value = 6, message = "Password can´t be less than 6 characters!")
    @Max(value = 30, message = "Password can´t be more than 30 characters!")
    private String password;
    @NotEmpty(message = "Please confirm the password!")
    @Min(value = 6, message = "Password can´t be less than 6 characters!")
    @Max(value = 30, message = "Password can´t be more than 30 characters!")
    private String confirmPassword;

    private Role role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
