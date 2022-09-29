package org.troyka.clientejaxrs.models;

import jakarta.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
public class Curso {
    private Long id;
    private String name;
    private String descripcion;
    private Instructor instructor;
    private Double duracion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", instructor='" + instructor.getId() + '\'' + "," + instructor.getNombre() +
                ", duracion=" + duracion +
                '}';
    }
}
