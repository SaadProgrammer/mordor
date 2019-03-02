package com.example.mordor.model.postacie;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Saad on 2019-02-24.
 */

@Getter
@Setter

public abstract class Istota {

    public Istota(Integer sila, Integer wytrzymalosc, Integer spryt, Integer szybkosc) {
        this.sila = sila;
        this.wytrzymalosc = wytrzymalosc;
        this.spryt = spryt;
        this.szybkosc = szybkosc;
    }

    private Integer sila;
    private Integer wytrzymalosc;
    private Integer spryt;
    private Integer szybkosc;
    private String idPostaci;
    private String name;

    @Override
    public String toString() {
        return "Name: " + getName() + " Sila: " + getSila() + " Wytrzymalosc: " + " Spryt: " + getSpryt() + " Szybkosc " + getSzybkosc() + " IdPostaci: " + getIdPostaci();
    }

}
