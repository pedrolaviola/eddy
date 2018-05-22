package com.pedrolaviola.user.eddy.models;

import java.io.Serializable;

/**
 * Created by User on 29/10/2017.
 */

public class Feed implements Serializable{
    private int id;
    private String titulo;
    private String data;
    private String descricao;

    public Feed() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
