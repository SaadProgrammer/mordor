package com.example.mordor.model.postacie;

import com.example.mordor.service.TworzeniePostaciService;

public abstract class NadHumanoid extends Istota implements FunkcjeNadHumanoida {

    public NadHumanoid(Integer sila, Integer wytrzymalosc, Integer spryt, Integer szybkosc, TypPostaciEnum typPostaci, Integer punktyKorpoZycia) {
        super(sila, wytrzymalosc, spryt, szybkosc, typPostaci, punktyKorpoZycia);
    }

    @Override
    public void obnizWynagrodzenie(Istota ofiara) {
        if (getIloscAtakow() != 0) {
            if (getSila() >= TworzeniePostaciService.losuj(5, 8)) {
                System.out.println("Postać " + getName() + " będzie próbowała obniżyć wynagrodzenie " + ofiara.getName());
                ofiara.unik(this);
                redukcjaIloscAtakow();
            } else {
                System.out.println("Postaci " + getName() + " nie udało się obniżyć wynagordzenia " + ofiara.getName() + " bo nie miała wymaganej wytrzymałości");
                redukcjaIloscAtakow();
            }
        } else {
            System.out.println("Postac " + getName() + " nie ma ataku");
        }
    }

    @Override
    public void atak (Istota ofiara) {
        Integer indexAtaku = TworzeniePostaciService.losuj(0, 100);
        if (indexAtaku <= RodzajeAtakówEnum.DONIES.getMaxWart()) {
            System.out.println("Postać " + getName() + " wybrała donosicielstwo");
            donies(ofiara);
        } else if (indexAtaku >= RodzajeAtakówEnum.OBLEJ_KAWA.getMinWart() && indexAtaku <= RodzajeAtakówEnum.OBLEJ_KAWA.getMaxWart()) {
            System.out.println("Postać " + getName() + " wybrała oblewanie kawą");
            oblejKawa(ofiara);
        } else if (indexAtaku >= RodzajeAtakówEnum.OBNIZ_WYNAGRODZENIE.getMinWart()) {
            System.out.println("Postać " + getName() + " wybrała obniżenie wynagrodzenia");
            obnizWynagrodzenie(ofiara);
        } else {
            System.out.println("Postać " + getName() + " nie może wybrać rzadnego ataku");

        }
    }
}