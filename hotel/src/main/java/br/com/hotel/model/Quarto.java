package br.com.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quarto")
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuarto;
    @Column(nullable = false, columnDefinition = "varchar(16100)")
    private String listaReservas;
    @Column(nullable = false)
    private String numero;
    private Boolean statusReserva;

    public Long getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(Long idQuarto) {
        this.idQuarto = idQuarto;
    }

    public String getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(String listaReservas) {
        this.listaReservas = listaReservas;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Boolean getStatus() {
        return statusReserva;
    }

    public void setStatus(Boolean status) {
        this.statusReserva = status;
    }

}
