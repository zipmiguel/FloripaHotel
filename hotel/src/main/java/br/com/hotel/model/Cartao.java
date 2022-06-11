package br.com.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cartao")
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCartao;
    @Column(nullable = false)
    private String bandeira;
    @Column(nullable = false)
    private String numero;
    @Column(nullable = false)
    private String vencimento;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cvv;
    
    public Long getIdCartao() {
        return idCartao;
    }
    public void setIdCartao(Long idCartao) {
        this.idCartao = idCartao;
    }
    public String getBandeira() {
        return bandeira;
    }
    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getVencimento() {
        return vencimento;
    }
    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
