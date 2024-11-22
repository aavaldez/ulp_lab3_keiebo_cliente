package com.a2valdez.keiebo.modelo;

import java.io.Serializable;

public class Pago implements Serializable {

    private int id;
    private int numero;
    private Reunion contrato;
    private double importe;
    private String fecha;

    public Pago() {}

    public Pago(int id, int numero, Reunion contrato, double importe, String fechaDePago) {
        this.id = id;
        this.numero = numero;
        this.contrato = contrato;
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
        return contrato;
    }

    public void setContrato(Reunion contrato) {
        this.contrato = contrato;
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
