package com.example.mordor.model.postacie;

import com.example.mordor.service.TworzeniePostaciService;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Saad on 2019-02-24.
 */

@Getter
@Setter

public abstract class Istota implements FunkcjeIstoty {

    public Istota(Integer sila, Integer wytrzymalosc, Integer spryt, Integer szybkosc, TypPostaciEnum typPostaci, Integer punktyKorpoZycia) {
        this.sila = sila;
        this.wytrzymalosc = wytrzymalosc;
        this.spryt = spryt;
        this.szybkosc = szybkosc;
        this.typPostaci = typPostaci;
        this.punktyKorpoZycia = punktyKorpoZycia;
    }

    private Integer sila;
    private Integer wytrzymalosc;
    private Integer spryt;
    private Integer szybkosc;
    private TypPostaciEnum typPostaci;
    private Integer punktyKorpoZycia;

    private String idPostaci;
    private String name;
    private Integer iloscAtakow = 1;
    private Integer iloscUnikow = 1;

    @Override
    public String toString() {
        return "Name: " + getName() + " Sila: " + getSila() + " Wytrzymalosc: " + getWytrzymalosc() + " Spryt: " + getSpryt() + " Szybkosc: " + getSzybkosc()
                + " Typ postaci: " + getTypPostaci() + " Punkty KorpoZycia: " + getPunktyKorpoZycia() + " Ilość ataków: " + getIloscAtakow()
                + " Ilość uników: " + getIloscUnikow() + " IdPostaci: " + getIdPostaci();
    }

    public void nowePunktyKorpoZycia(Integer punktyDoOdjecia) {
        setPunktyKorpoZycia(getPunktyKorpoZycia() - punktyDoOdjecia);
        if (getPunktyKorpoZycia() < 0) {
            setPunktyKorpoZycia(0);
        }
    }

    public void redukcjaIloscAtakow() {
        setIloscAtakow((getIloscAtakow() - 1));
    }

    public void resetiloscAtakow() {
        setIloscAtakow(getIloscAtakow() + 1);
    }

    public void redukcjaIloscUnikow() {
        setIloscUnikow((getIloscUnikow() - 1));
    }

    public void resetIloscUnikow() {
        setIloscUnikow(getIloscUnikow() + 1);
    }

    public void resetiloscAtakowUnikow() {
        resetiloscAtakow();
        resetIloscUnikow();
    }

    public Integer rezultatAtaku(Istota ofiara) {
        Integer sumaPotencjalnychObrazen = (getSzybkosc() / 2) + getSila();
        Integer wynikAtaku = sumaPotencjalnychObrazen - ofiara.getWytrzymalosc();
        if (wynikAtaku > 0) {
            ofiara.nowePunktyKorpoZycia(wynikAtaku);
            System.out.println("Postaci " + getName() + " udało się zaatakować. Postać " + ofiara.getName() + " straciła "
                    + wynikAtaku + " punktów korpo życia i ma ich teraz " + ofiara.getPunktyKorpoZycia());
        } else {
            System.out.println("Atak przeprowadzony przez " + getName() + "zakończył się niepowodzeniem");
        }
        return wynikAtaku;
    }

    @Override
    public void donies(Istota ofiara) {
        if (getIloscAtakow() != 0) {
            if (getSpryt() >= TworzeniePostaciService.losuj(3, 6)) {
                System.out.println("Postać " + getName() + " będzie próbowała donieść na " + ofiara.getName());
                ofiara.unik(this);
                redukcjaIloscAtakow();
            } else {
                System.out.println("Postaci " + getName() + " nie udało się donieść na " + ofiara.getName() + " bo nie wykazała się wymaganym sprytem");
                redukcjaIloscAtakow();
            }
        } else {
            System.out.println("Postac " + getName() + " nie ma ataku");
        }
    }

    @Override
    public void oblejKawa(Istota ofiara) {
        if (getIloscAtakow() != 0) {
            if (getSpryt() >= TworzeniePostaciService.losuj(4, 7)) {
                System.out.println("Postać " + getName() + " będzie próbowała oblać kawą " + ofiara.getName());
                ofiara.unik(this);
                redukcjaIloscAtakow();
            } else {
                System.out.println("Postaci " + getName() + " nie udało się oblać kawą " + ofiara.getName() + " bo nie wykazała się wymaganym sprytem");
                redukcjaIloscAtakow();
            }
        } else {
            System.out.println("Postac " + getName() + " nie ma ataku");
        }
    }

    @Override
    public void unik(Istota atakujacy) {
        if (getIloscUnikow() != 0) {
            if (getSzybkosc() >= TworzeniePostaciService.losuj(4, 8)) {
                System.out.println("Postaci " + getName() + " udało się uniknąć ataku ze strony " + atakujacy.getName());
                redukcjaIloscUnikow();
            } else {
                System.out.println("Postaci " + getName() + " nie udało się uniknąć ataktu ze strony " + atakujacy.getName());
                redukcjaIloscUnikow();
                atakujacy.rezultatAtaku(this);
            }
        } else {
            System.out.println("Postać " + getName() + " nie ma uników");
        }
    }
}
