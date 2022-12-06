package com.example.digital.models;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@SQLDelete(sql = "UPDATE patito set borrado=true where id = ?")
@Where(clause = "borrado = 0")
public class Patito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "color", nullable = false)
    String color;

    @Column(name = "tamaño", nullable = false)
    String tamaño;

    @Column(name = "precio", nullable = false)
    Double precio;

    @Column(name = "cantidad", nullable = false)
    Integer cantidad;

    @Column(name = "borrado", nullable = false, columnDefinition = "bit default 1")
    Boolean borrado;

    public Patito(Integer id, String color, String tamaño, Double precio, Integer cantidad, Boolean borrado) {
        this.id = id;
        this.color = color;
        this.tamaño = tamaño;
        this.precio = precio;
        this.cantidad = cantidad;
        this.borrado = borrado;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getBorrado(boolean b) {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }
}

