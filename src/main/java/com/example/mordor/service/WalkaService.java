package com.example.mordor.service;

import com.example.mordor.model.postacie.Istota;
import com.example.mordor.model.postacie.TypPostaciEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class WalkaService {

    public List<Istota> korpoWinnerList = new ArrayList<>();

    public void dodajPostacDoKorpoWinnerList(TworzeniePostaciService tps, TypPostaciEnum typWybranejPostaci, List<Istota> korpoWinnerList) {
        Istota wybranaPostac = tps.stworzPostac(typWybranejPostaci);
        korpoWinnerList.add(wybranaPostac);
        System.out.println("Postać " + wybranaPostac.getName() + " została dodana do listy korporacyjnych szczórów zakwalifikowanych do globalnej walki o dominację w korporacji");
    }

    public void usunWybranaPostacZKorpoWinnerList(String idPostaci, List<Istota> korpoWinnerList) {
        for (int i = 0; i < korpoWinnerList.size(); i++) {
            if (korpoWinnerList.get(i).getIdPostaci().equals(idPostaci)) {
                Istota usunPostac = korpoWinnerList.get(i);
                korpoWinnerList.remove(usunPostac);
                System.out.println("Postać " + usunPostac.getName() + " została usunięta z listy korporacyjnych szczórów zakwalifikowanych do globalnej walki o dominację w korporacji");
            }
        }
    }

    public void usunWszystkichZKorpoWinnerList(List<Istota> korpoWinnerList) {
        korpoWinnerList.removeAll(korpoWinnerList);
        System.out.println("Aktualna lista korporacyjnych szczurów zakwalifikowanych do globalnej walki o dominację w korporacji jest pusta");
    }

    public Istota globalKorpoWinner(List<Istota> korpoWinnerList) {
        if (korpoWinnerList.size() <= 1) {
            Istota globalnyWinner = korpoWinnerList.get(0);
            System.out.println("Postać " + globalnyWinner.getName() + " w globalnej walce o światową dominajcę w koporacji nie miała przeciwników i zwyciężyła bez wysiłku. Statystyki: punkty korpo życia: "
                    + globalnyWinner.getPunktyKorpoZycia() + " punkty doświadczenia: " + globalnyWinner.getPunktyDoswiadczenia() + " wygrane walki: " + globalnyWinner.getWygraneWalki() + " ilość ataków " + globalnyWinner.getIloscAtakow()
                    + " ilość uników " + globalnyWinner.getIloscUnikow());
            return globalnyWinner;
        } else {
            Istota globalnyWinner = korpoWinner(korpoWinnerList);
            korpoWinnerList.removeAll(korpoWinnerList);
            korpoWinnerList.add(globalnyWinner);
            System.out.println("Postać " + globalnyWinner.getName() + " wygrala walkę o światową dominację w korporacji. Statystyki: punkty korpo życia: "
                    + globalnyWinner.getPunktyKorpoZycia() + " punkty doświadczenia: " + globalnyWinner.getPunktyDoswiadczenia() + " wygrane walki: " + globalnyWinner.getWygraneWalki() + " ilość ataków " + globalnyWinner.getIloscAtakow()
                    + " ilość uników " + globalnyWinner.getIloscUnikow());
            return globalnyWinner;
        }
    }

    public List<Istota> globalKorpoFight(TworzeniePostaciService tps, Integer ileSezonow, Integer ileWalczakowSezon) {
        List<Istota> korpoWinnerList = new ArrayList<>();
        for (int i = 0; i < ileSezonow; i++) {
            List<Istota> listaWalczakow = tps.stworzWieleDowolnychPostaci(ileWalczakowSezon);
            Istota korpoZw = korpoWinner(listaWalczakow);
            korpoWinnerList.add(korpoZw);
            System.out.println("Postac " + korpoZw.getName() + " zakawalifikowała się do globalnej walki o dominację w korporacji ");
        }
        System.out.println("Aktualna lista korporacyjnych szczurów zakwalifikowanych do globalnej walki o dominację w korporacji to: " + korpoWinnerList);
        return korpoWinnerList;
    }

    public Istota korpoWinner(List<Istota> listaWalczakow) {
        Istota zwyciezca = walkaOstatniZywy(listaWalczakow);
        System.out.println("Postac " + zwyciezca.getName() + " wygrała w walce o dominacje w korporacji. Statystyki: punkty korpo życia: "
                + zwyciezca.getPunktyKorpoZycia() + " punkty doświadczenia: " + zwyciezca.getPunktyDoswiadczenia() + " wygrane walki " + zwyciezca.getWygraneWalki() + " ilość ataków " + zwyciezca.getIloscAtakow()
                + " ilość uników " + zwyciezca.getIloscUnikow());
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
            if (rundaWalki <= 20) {
                System.out.println("To jest " + rundaWalki + " runda walki");
                rundaWalki(atakujacy, ofiara);
            } else {
                if (rundaWalki > 20 && rundaWalki <= 50) {
                    System.out.println("To jest " + rundaWalki
                            + " runda walki. Pracownicy są zmęczeni i z każdą kolejną rundą atakujący traci na szybkości, a atakowany na wytrzymałości");
                    atakujacy.zmeczenieSzybkoscDuzaIloscRundAtakujacy();
                    ofiara.zmeczenieWytrzymaloscDuzaIloscRundOfiara();
                    rundaWalki(atakujacy, ofiara);
                } else {
                    System.out.println("To jest " + rundaWalki + " runda walki - decydująca i ostatnia. Pracownicy są padnięci i o wygranej zadecydują indywidualne umiejętności oraz szczęście");
                    decydujacaRundaWalkiPunktySzczescie(atakujacy, ofiara);
                }
            }
        }

        atakujacy.resetSzybkoscDuzaIloscRundAtakujacy();
        ofiara.resetWytrzymaloscDuzaIloscRundOfiara();

        Istota zwyciezca = atakujacy.getPunktyKorpoZycia() > 0 ? atakujacy : ofiara;
        System.out.println("Zwycięzcą jest " + zwyciezca.getName() + " zwyciężając po " + rundaWalki + " rundach walki");
        Istota przegrany = ofiara.getPunktyKorpoZycia() <= 0 ? ofiara : atakujacy;
        Integer roznicaSila = zwyciezca.getSila() - przegrany.getSila();

        zwyciezca.nowePunktyDoswiadczenia(zwyciezca, roznicaSila);
        zwyciezca.leczenieWyrownaniePunktowKorpoZycia(zwyciezca);
        zwyciezca.licznikWygranychWalk(zwyciezca);

        return zwyciezca;
    }

    public void rundaWalki(Istota atakujacy, Istota ofiara) {
        if (atakujacy.getIdPostaci().equals(ofiara.getIdPostaci())) {
            System.out.println("Postać " + atakujacy.getName() + " nie może sam siebie zaatakować");
        } else {
            while (atakujacy.getIloscAtakow() > 0 && ofiara.getIloscAtakow() > 0) {
                if (atakujacy.getPunktyKorpoZycia() > 0 && ofiara.getPunktyKorpoZycia() > 0) {
                    atakujacy.atak(ofiara);
                } else {
                    atakujacy.redukcjaIloscAtakow();
                    ofiara.redukcjaIloscUnikow();
                    System.out.println("Jedna z postaci zrezygnowała z pracy, runda walki nie jest możliwa");
                }
                if (ofiara.getPunktyKorpoZycia() > 0) {
                    ofiara.atak(atakujacy);
                } else {
                    ofiara.redukcjaIloscAtakow();
                    atakujacy.redukcjaIloscUnikow();
                    System.out.println("Postać " + ofiara.getName() + " zrezygnowała z pracy przed końcem rundy walki");
                }
            }
        }
        atakujacy.resetiloscAtakowUnikow();
        ofiara.resetiloscAtakowUnikow();
    }

    public void decydujacaRundaWalkiPunktySzczescie(Istota atakujacy, Istota ofiara) {
        Integer resztkiMocyAtakujacego = atakujacy.getSila() + atakujacy.getSpryt();
        Integer resztkiMocyOfiary = ofiara.getSila() + ofiara.getSpryt();
        Integer roznicaResztekMocy = resztkiMocyAtakujacego - resztkiMocyOfiary;
        Integer roznicaPunktowKorpoZycia = atakujacy.getPunktyKorpoZycia() - ofiara.getPunktyKorpoZycia();
        Integer wynikOstateczny = roznicaResztekMocy - roznicaPunktowKorpoZycia + TworzeniePostaciService.losuj(-2, 2);
        if (wynikOstateczny <= 0) {
            atakujacy.zmniejszeniePunktowKorpoZycia(atakujacy.getPunktyKorpoZycia());
            System.out.println("Postać " + ofiara.getName() + " pozbawiła korpo życia postać " + atakujacy.getName() + " w decydującej rundzie walki gdzie o wygranej decydowały indywidualne umiejętności i szczęście");
        } else {
            ofiara.zmniejszeniePunktowKorpoZycia(ofiara.getPunktyKorpoZycia());
            System.out.println("Postać " + atakujacy.getName() + " pozbawiła korpo życia postać " + ofiara.getName() + " w decydującej rundzie walki gdzie o wygranej decydowały indywidualne umiejętności i szczęście");
        }
    }
}