package com.vitaly.farmacia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Usuario {
    @Id
    private Long id;
    private String nombre;
    private String correo;
    private String password;
}
