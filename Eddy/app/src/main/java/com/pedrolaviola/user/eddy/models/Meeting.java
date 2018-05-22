package com.pedrolaviola.user.eddy.models;

/**
 * Created by User on 30/10/2017.
 */

public class Meeting {
    private String data;
    private boolean marcada;

    public Meeting() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isMarcada() {
        return marcada;
    }

    public void setMarcada(boolean marcada) {
        this.marcada = marcada;
    }
}
