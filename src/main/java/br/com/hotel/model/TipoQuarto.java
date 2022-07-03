package br.com.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
    @Column(nullable = false, columnDefinition = "boolean")
    private boolean StatusTipoQuarto;
    @Lob
    private byte[] imagem;
    

    public boolean getStatusTipoQuarto() {
        return StatusTipoQuarto;
    }
    public void setStatusTipoQuarto(boolean statusTipoQuarto) {
        StatusTipoQuarto = statusTipoQuarto;
    }
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
    public TipoQuarto(String tipoQuarto,int quantidadeCamaSolteiro,int quantidadeCamaCasal,int numeroPessoas){
        this.tipoQuarto = tipoQuarto;
        this.quantidadeCamaSolteiro = quantidadeCamaSolteiro;
        this.quantidadeCamaCasal = quantidadeCamaCasal;
        this.numeroPessoas = numeroPessoas;
        this.StatusTipoQuarto = true;
    }
    public TipoQuarto(String tipoQuarto,int quantidadeCamaSolteiro,int quantidadeCamaCasal){
        this.tipoQuarto = tipoQuarto;
        this.quantidadeCamaSolteiro = quantidadeCamaSolteiro;
        this.quantidadeCamaCasal = quantidadeCamaCasal;
        this.numeroPessoas = (quantidadeCamaCasal*2)+quantidadeCamaSolteiro;
        this.StatusTipoQuarto = true;
    }
    public TipoQuarto(){}

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
