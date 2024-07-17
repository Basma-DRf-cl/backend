package com.Mamda.Mamda.model;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bienInfo")
@Data
public class BienInfo {
    @Id
    
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int id;
    private String numSerie;
    private Date dateAchat;
    private String categorie;
    private int quantite;
    private String description;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    

}
