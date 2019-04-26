package com.example.mordor.service;

import com.example.mordor.model.postacie.Istota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class WalkaService {

    @Autowired
    private TworzeniePostaciService tps;

    public Istota globalKorpoWinner(List<Istota> korpoWinnerList) {
        Istota globalnyWinner = korpoWinner(korpoWinnerList);
        korpoWinnerList.removeAll(korpoWinnerList);
        korpoWinnerList.add(globalnyWinner);
        System.out.println("Postac " + globalnyWinner + " wygrala walkę o światową dominację w korporacji");
        return globalnyWinner;
    }

    public List<Istota> korpoWinnerList = new ArrayList<>();

    public void globalKorpoFight(Integer ileSezonow, Integer ileWalczakowSezon) {
        for (int i = 0; i > ileSezonow; i++) {
            List<Istota> listaWalczakow = tps.stworzWieleDowolnychPostaci(ileWalczakowSezon);
            Istota korpoZw = korpoWinner(listaWalczakow);
            korpoWinnerList.add(korpoZw);
            System.out.println("Postac " + korpoZw + " zakawalifikowała się do globalnej walki o dominację w korporacji ");
        }
        System.out.println("Aktualna lista korporacyjnych szczurów zakwalifikowanych do globalnej walki o dominację w korporacji to: " + korpoWinnerList);
    }

    public Istota korpoWinner(List<Istota> listaWalczakow) {
        Istota zwyciezca = walkaOstatniZywy(listaWalczakow);
        System.out.println("Postac " + zwyciezca + " wygrała w walce o dominacje w korporacji");
        return zwyciezca;
    }

    public Istota walkaOstatniZywy(List<Istota> listaWalczakow) {
        if (listaWalczakow.isEmpty()) {
            System.out.println("Biurowiec jest pusty");
            return null;
        } else {
            if (listaWalczakow.size() <= 1) {
                return listaWalczakow.get(0);
            } else {
                Istota pierwszy = losowanieWalczaka(listaWalczakow);
                Istota drugi = losowanieWalczaka(listaWalczakow);
                listaWalczakow.add(walkaParyWalczakow(pierwszy, drugi));
                return walkaOstatniZywy(listaWalczakow);
            }
        }
    }

    public Istota losowanieWalczaka(List<Istota> listaWalczakow) {
        Integer indexWalczak = TworzeniePostaciService.losuj(0, listaWalczakow.size() - 1);
        Istota walczak = listaWalczakow.get(indexWalczak);
        listaWalczakow.remove(walczak);
        return walczak;
    }

    public Istota walkaParyWalczakow(Istota atakujacy, Istota ofiara) {
        Integer rundaWalki = 0;
        while (atakujacy.getPunktyKorpoZycia() > 0 && ofiara.getPunktyKorpoZycia() > 0) {
            rundaWalki += 1;
            System.out.println("To jest " + rundaWalki + " runda walki");
            rundaWalki(atakujacy, ofiara);
        }

        Istota zwyciezca = atakujacy.getPunktyKorpoZycia() > 0 ? atakujacy : ofiara;
        System.out.println("Zwycięzcą jest " + zwyciezca.getName() + " zwyciężając po " + rundaWalki + " rundach walki");
        return zwyciezca;
        //LICZNIK RUND
        //DOŚWIADCZENIE
    }

    public void rundaWalki(Istota atakujacy, Istota ofiara) {
        if (atakujacy.getIdPostaci().equals(ofiara.getIdPostaci())) {
            System.out.println("Postać " + atakujacy.getName() + " nie może sam siebie zaatakować");
        } else {
            while (atakujacy.getIloscAtakow() > 0 && ofiara.getIloscAtakow() > 0) {
                if (atakujacy.getPunktyKorpoZycia() > 0 && ofiara.getPunktyKorpoZycia() > 0) {
                    atakujacy.atak(ofiara);
                    //LICZNIK WALK - ATAKUJACY
                } else {
                    System.out.println("Jedna z postaci zrezygnowała z pracyy, runda walki nie jest możliwa");
                }
                if (ofiara.getPunktyKorpoZycia() > 0) {
                    ofiara.atak(atakujacy);
                    //LICZNIK WALK - OFIARA
                } else {
                    System.out.println("Postać " + ofiara.getName() + " zrezygnowała z pracy przed końcem rundy walki");
                }
            }
        }
        atakujacy.resetiloscAtakowUnikow();
        ofiara.resetiloscAtakowUnikow();
    }
}