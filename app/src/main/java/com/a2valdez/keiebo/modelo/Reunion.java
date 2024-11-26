package com.a2valdez.keiebo.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Reunion implements Serializable {

    private int id;
    private String desde;
    private String hasta;
    private double valor;
    private Participante Participante;

    public Reunion() {}
    public Reunion(int id, String fechaInicio, String fechaFin, double valor, Participante Participante) {
        this.id = id;
        this.desde = fechaInicio;
        this.hasta = fechaFin;
        this.valor = valor;
        this.Participante = Participante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


    public Participante getParticipante() {
        return Participante;
    }

    public void setParticipante(Participante Participante) {
        this.Participante = Participante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reunion reunion = (Reunion) o;
        return id == reunion.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
