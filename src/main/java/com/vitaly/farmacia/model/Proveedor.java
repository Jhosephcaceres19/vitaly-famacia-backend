package com.vitaly.farmacia.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Proveedor {
    @Id
    private Long id;
    @NonNull
    private String nombre;
    private String direccion;
    private String telefono;

}
