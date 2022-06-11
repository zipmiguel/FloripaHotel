package br.com.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoQuarto")
public class TipoQuarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoQuarto;
    @Column(nullable = false)
    private String tipoQuarto;
    @Column(nullable = false)
    private int quantidadeCamaSolteiro;
    @Column(nullable = false)
    private int quantidadeCamaCasal;
    @Column(nullable = false)
    private int numeroPessoas;
    
    public Long getIdTipoQuarto() {
        return idTipoQuarto;
    }
    public void setIdTipoQuarto(Long idTipoQuarto) {
        this.idTipoQuarto = idTipoQuarto;
    }
    public String getTipoQuarto() {
        return tipoQuarto;
    }
    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }
    public int getQuantidadeCamaSolteiro() {
        return quantidadeCamaSolteiro;
    }
    public void setQuantidadeCamaSolteiro(int quantidadeCamaSolteiro) {
        this.quantidadeCamaSolteiro = quantidadeCamaSolteiro;
    }
    public int getQuantidadeCamaCasal() {
        return quantidadeCamaCasal;
    }
    public void setQuantidadeCamaCasal(int quantidadeCamaCasal) {
        this.quantidadeCamaCasal = quantidadeCamaCasal;
    }
    public int getNumeroPessoas() {
        return numeroPessoas;
    }
    public void setNumeroPessoas(int numeroPessoas) {
        this.numeroPessoas = numeroPessoas;
    }
}
