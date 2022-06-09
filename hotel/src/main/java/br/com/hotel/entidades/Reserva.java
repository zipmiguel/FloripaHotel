package br.com.hotel.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;
    @Column(nullable = false)
    private Long idHospede;
    @Column(nullable = false)
    private Long idTipoQuarto;
    @Column(nullable = false)
    private Long idFuncionario;
    @Column(nullable = false)
    private Long idDiaria;
    @Column(nullable = false)
    private String metodoPagamento;
    @Column(nullable = false)
    private Double valorPago;
    @Column(nullable = false)
    private String dataEntrada;
    @Column(nullable = false)
    private String dataSaida;
    @Column(nullable = false)
    private String status;

    public Long getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }
    public Long getIdHospede() {
        return idHospede;
    }
    public void setIdHospede(Long idHospede) {
        this.idHospede = idHospede;
    }
    public Long getIdTipoQuarto() {
        return idTipoQuarto;
    }
    public void setIdTipoQuarto(Long idTipoQuarto) {
        this.idTipoQuarto = idTipoQuarto;
    }
    public Long getIdFuncionario() {
        return idFuncionario;
    }
    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
    public Long getIdDiaria() {
        return idDiaria;
    }
    public void setIdDiaria(Long idDiaria) {
        this.idDiaria = idDiaria;
    }
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
    public String getDataEntrada() {
        return dataEntrada;
    }
    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
    public String getDataSaida() {
        return dataSaida;
    }
    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}

