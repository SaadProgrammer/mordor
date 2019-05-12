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

    private String name;
    private Integer iloscAtakow = 1;
    private Integer iloscUnikow = 1;
    private Integer punktyDoswiadczenia = 0;
    private Integer wygraneWalki = 0;
    private String idPostaci;

    private Integer licznikSzybkosc = 0;
    private Integer licznikWytrzymalosc = 0;

    @Override
    public String toString() {
        return "Name: " + getName() + " Sila: " + getSila() + " Wytrzymalosc: " + getWytrzymalosc() + " Spryt: " + getSpryt() + " Szybkosc: " + getSzybkosc()
                + " Typ postaci: " + getTypPostaci() + " Punkty KorpoZycia: " + getPunktyKorpoZycia() + " Ilość ataków: " + getIloscAtakow()
                + " Ilość uników: " + getIloscUnikow() + " Doswiadczenie: " + getPunktyDoswiadczenia() + " Wygrane walki: " + getWygraneWalki()
                + " IdPostaci: " + getIdPostaci();
    }

    public void zmeczenieSzybkoscDuzaIloscRundAtakujacy() {
        if (getSzybkosc() > 0) {
            setSzybkosc(getSzybkosc() - 1);
            setLicznikSzybkosc(getLicznikSzybkosc() + 1);
        }
    }

    public void zmeczenieWytrzymaloscDuzaIloscRundOfiara() {
        if (getWytrzymalosc() > 0) {
            setWytrzymalosc(getWytrzymalosc() - 1);
            setLicznikWytrzymalosc(getWytrzymalosc() + 1);
        }
    }

    public void resetSzybkoscDuzaIloscRundAtakujacy() {
        setSzybkosc(getSzybkosc() + getLicznikSzybkosc());
        setLicznikSzybkosc(0);
    }

    public void resetWytrzymaloscDuzaIloscRundOfiara() {
        setWytrzymalosc(getWytrzymalosc() + getLicznikWytrzymalosc());
        setLicznikWytrzymalosc(0);
    }

    public int doswiadczenieDoAtaku() {
        int indexAtakuPlus;
        if (getPunktyDoswiadczenia() < 50) {
            indexAtakuPlus = 5;
        } else if (50 <= getPunktyDoswiadczenia() && getPunktyDoswiadczenia() < 180) {
            indexAtakuPlus = 10;
        } else if (180 <= getPunktyDoswiadczenia()) {
            indexAtakuPlus = 15;
        } else {
            indexAtakuPlus = 0;
        }
        return indexAtakuPlus;
    }

    public void licznikWygranychWalk(Istota zwyciezca) {
        zwyciezca.setWygraneWalki(getWygraneWalki() + 1);
    }

    public void nowePunktyDoswiadczenia(Istota zwyciezca, Integer zwyciezcaPrzegranyRoznicaSila) {
        if (zwyciezcaPrzegranyRoznicaSila >= 0) {
            zwyciezca.setPunktyDoswiadczenia(getPunktyDoswiadczenia() + 10);
        } else {
            zwyciezca.setPunktyDoswiadczenia(getPunktyDoswiadczenia() + 30);
        }
    }

    public void leczenieWyrownaniePunktowKorpoZycia(Istota zwyciezca) {
        if (zwyciezca.getPunktyKorpoZycia() < 5) {
            setPunktyKorpoZycia(getPunktyKorpoZycia() + 5);
        } else if (5 <= zwyciezca.getPunktyKorpoZycia() && zwyciezca.getPunktyKorpoZycia() < 9) {
            setPunktyKorpoZycia(getPunktyKorpoZycia() + 3);
        } else {
        }
    }

    public void zmniejszeniePunktowKorpoZycia(Integer punktyDoOdjecia) {
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

    public Integer rezultatAtaku(Istota ofiara, Integer potencjalneObrazeniaZalezneOdRodzajuAtaku) {
        Integer sumaPotencjalnychObrazen = getSila() + potencjalneObrazeniaZalezneOdRodzajuAtaku;
        Integer wynikAtaku = sumaPotencjalnychObrazen - ofiara.getWytrzymalosc();
        if (wynikAtaku > 0) {
            ofiara.zmniejszeniePunktowKorpoZycia(wynikAtaku);
            System.out.println("Postaci " + getName() + " udało się zaatakować. Postać " + ofiara.getName() + " straciła "
                    + wynikAtaku + " punktów korpo życia i ma ich teraz " + ofiara.getPunktyKorpoZycia());
        } else {
            System.out.println("Atak przeprowadzony przez " + getName() + " zakończył się niepowodzeniem ze względu na wytrzymałość " + ofiara.getName());
        }
        return wynikAtaku;
    }

    @Override
    public void donies(Istota ofiara) {
        if (getIloscAtakow() != 0) {
            if (getSpryt() >= TworzeniePostaciService.losuj(3, 6)) {
                System.out.println("Postać " + getName() + " będzie próbowała donieść na " + ofiara.getName());
                ofiara.unik(this, TworzeniePostaciService.losuj(RodzajeAtakowObrazniaEnum.DONIES.getMinWart(), RodzajeAtakowObrazniaEnum.DONIES.getMaxWart()));
                redukcjaIloscAtakow();
            } else {
                System.out.println("Postaci " + getName() + " nie udało się donieść na " + ofiara.getName() + " bo nie wykazała się wymaganym sprytem");
                redukcjaIloscAtakow();
                ofiara.redukcjaIloscUnikow();
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
                ofiara.unik(this, TworzeniePostaciService.losuj(RodzajeAtakowObrazniaEnum.OBLEJ_KAWA.getMinWart(), RodzajeAtakowObrazniaEnum.OBLEJ_KAWA.getMaxWart()));
                redukcjaIloscAtakow();
            } else {
                System.out.println("Postaci " + getName() + " nie udało się oblać kawą " + ofiara.getName() + " bo nie wykazała się wymaganym sprytem");
                redukcjaIloscAtakow();
                ofiara.redukcjaIloscUnikow();
            }
        } else {
            System.out.println("Postac " + getName() + " nie ma ataku");
        }
    }

    @Override
    public void unik(Istota atakujacy, Integer potencjalneObrazeniaZalezneOdRodzajuAtaku) {
        if (getIloscUnikow() != 0) {
            if (getSzybkosc() > atakujacy.getSzybkosc() || getSzybkosc() >= TworzeniePostaciService.losuj(4, 10)) {
                System.out.println("Postaci " + getName() + " udało się uniknąć ataku ze strony " + atakujacy.getName());
                redukcjaIloscUnikow();
            } else {
                System.out.println("Postaci " + getName() + " nie udało się uniknąć ataktu ze strony " + atakujacy.getName());
                redukcjaIloscUnikow();
                atakujacy.rezultatAtaku(this, potencjalneObrazeniaZalezneOdRodzajuAtaku);
            }
        } else {
            System.out.println("Postać " + getName() + " nie ma uników");
        }
    }
}
