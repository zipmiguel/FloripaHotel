package br.com.hotel.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;
    @ManyToOne
    @JoinColumn(name = "id_hospede")
    private Hospede hospede;
    // @ManyToOne
    // @JoinColumn(name = "id_quarto")
    // private Quarto quarto;
    @ManyToOne
    @JoinColumn(name = "id_tipoQuarto")
    private TipoQuarto tipoQuarto;
    // @ManyToOne
    // @JoinColumn(name = "id_funcionario")
    // private Funcionario funcionario;
    // @ManyToOne
    // @JoinColumn(name = "id_diaria")
    // private Diaria diaria;
    @Column(nullable = false)
    private String metodoPagamento;
    @Column(nullable = false)
    private Double valorPago;
    @Column(nullable = false)
    private LocalDate dataEntrada;
    @Column(nullable = false)
    private LocalDate dataSaida;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private Long codigoReserva;
    
    public Long getCodigoReserva() {
        return codigoReserva;
    }
    public void setCodigoReserva(Long codigoReserva) {
        this.codigoReserva = codigoReserva;
    }
    public Long getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }
    public Hospede getHospede() {
        return hospede;
    }
    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }
    // public Funcionario getFuncionario() {
    //     return funcionario;
    // }
    // public void setFuncionario(Funcionario funcionario) {
    //     this.funcionario = funcionario;
    // }
    // public Diaria getDiaria() {
    //     return diaria;
    // }
    // public void setDiaria(Diaria diaria) {
    //     this.diaria = diaria;
    // }
    public String getMetodoPagamento() {
        return metodoPagamento;
    }
    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
    public Double getValorPago() {
        return valorPago;
    }
    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }
    public LocalDate getDataEntrada() {
        return dataEntrada;
    }
    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
    public LocalDate getDataSaida() {
        return dataSaida;
    }
    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    // public Quarto getQuarto() {
    //     return quarto;
    // }
    // public void setQuarto(Quarto quarto) {
    //     this.quarto = quarto;
    // }
    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }
    public void setTipoQuarto(TipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }
}

