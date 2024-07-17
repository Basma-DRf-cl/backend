package com.Mamda.Mamda.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<BienInfo> bienInfos;

    public Employee() {
    }

    public Employee(int id, String lastName, String firstName, String email) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public List<BienInfo> getBienInfos() {
        return bienInfos;
    }

    public void setBienInfos(List<BienInfo> bienInfos) {
        this.bienInfos = bienInfos;
}
}
