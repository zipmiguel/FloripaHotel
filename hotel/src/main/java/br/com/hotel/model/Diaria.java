package br.com.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diaria")
public class Diaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDiara;
    @Column(nullable = false)
    private Double diaUtil;
    @Column(nullable = false)
    private Double fimDeSemana;
    @Column(nullable = false)
    private Double feriado;
    @Column(nullable = false)
    private Double promocional;

    public Long getIdDiara() {
        return idDiara;
    }
    public void setIdDiara(Long idDiara) {
        this.idDiara = idDiara;
    }
    public Double getDiaUtil() {
        return diaUtil;
    }
    public void setDiaUtil(Double diaUtil) {
        this.diaUtil = diaUtil;
    }
    public Double getFimDeSemana() {
        return fimDeSemana;
    }
    public void setFimDeSemana(Double fimDeSemana) {
        this.fimDeSemana = fimDeSemana;
    }
    public Double getFeriado() {
        return feriado;
    }
    public void setFeriado(Double feriado) {
        this.feriado = feriado;
    }
    public Double getPromocional() {
        return promocional;
    }
    public void setPromocional(Double promocional) {
        this.promocional = promocional;
    }
}
