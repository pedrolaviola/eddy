package com.pedrolaviola.user.eddy.models;

/**
 * Created by User on 28/10/2017.
 */

public class Usuario {
    private String codEdi;
    private String apartamento;
    private String nome;

    public Usuario() {
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodEdi() {
        return codEdi;
    }

    public void setCodEdi(String codEdi) {
        this.codEdi = codEdi;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }
}
