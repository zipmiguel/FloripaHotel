package br.com.hotel.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idCargo;
    @Column(nullable = false)
    private String tipoCargo;
    @Column(nullable = false)
    private Boolean permissao;

    public Long getIdCargo() {
        return idCargo;
    }
    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }
    public String getTipoCargo() {
        return tipoCargo;
    }
    public void setTipoCargo(String tipoCargo) {
        this.tipoCargo = tipoCargo;
    }
    public Boolean getPermissao() {
        return permissao;
    }
    public void setPermissao(Boolean permissao) {
        this.permissao = permissao;
    }
}
