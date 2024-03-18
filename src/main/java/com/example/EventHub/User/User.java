package com.example.EventHub.User;

import com.example.EventHub.Role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.UniqueElements;


@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Username cannot be empty!")
    @UniqueElements(message = "Username already exist! ")
    private String username;
    @UniqueElements(message = "Email already exist!")
    @NotEmpty(message = "Please enter email!")
    private String email;
    @NotEmpty(message = "Please enter name!")
    private String firstName;
    @NotEmpty(message = "Please enter surname!")
    private String lastName;
    @NotEmpty(message = "Please enter password!")
    private String password;
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @NotEmpty
    private boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
