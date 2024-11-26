package com.a2valdez.keiebo.modelo;

import java.io.Serializable;

public class Tarea implements Serializable {

    private int id;
    private int numero;
    private Reunion reunion;
    private double importe;
    private String fecha;

    public Tarea() {}

    public Tarea(int id, int numero, Reunion reunion, double importe, String fechaDePago) {
        this.id = id;
        this.numero = numero;
        this.reunion = reunion;
        this.importe = importe;
        this.fecha = fechaDePago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Reunion getContrato() {
        return reunion;
    }

    public void setContrato(Reunion reunion) {
        this.reunion = reunion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fechaDePago) {
        this.fecha = fechaDePago;
    }
}
