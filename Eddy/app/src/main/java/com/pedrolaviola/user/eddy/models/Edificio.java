package com.pedrolaviola.user.eddy.models;

import java.util.List;

/**
 * Created by User on 30/10/2017.
 */

public class Edificio {
    private String id,nomEdi;
    private List<Reserva> reserva;
    private List<Feed> listaFeed;
    private Meeting meeting;
    private int Feedcount;

    public List<Feed> getListaFeed() {
        return listaFeed;
    }

    public void setListaFeed(List<Feed> listaFeed) {
        this.listaFeed = listaFeed;
    }

    public Edificio() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomEdi() {
        return nomEdi;
    }

    public void setNomEdi(String nomEdi) {
        this.nomEdi = nomEdi;
    }

    public List<Reserva> getReserva() {
        return reserva;
    }
    public void setReserva(List<Reserva> reserva) {
        this.reserva = reserva;
    }
    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public int getFeedcount() {
        return Feedcount;
    }

    public void setFeedcount(int feedcount) {
        Feedcount = feedcount;
    }
}
