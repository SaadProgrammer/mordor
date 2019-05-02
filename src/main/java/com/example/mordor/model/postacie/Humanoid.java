package com.example.mordor.model.postacie;

import com.example.mordor.service.TworzeniePostaciService;

public abstract class Humanoid extends Istota implements FunkcjeHumanoida {

    public Humanoid(Integer sila, Integer wytrzymalosc, Integer spryt, Integer szybkosc, TypPostaciEnum typPostaci, Integer punktyKorpoZycia) {
        super(sila, wytrzymalosc, spryt, szybkosc, typPostaci, punktyKorpoZycia);
    }

    @Override
    public void zepchnijZeSchodow(Istota ofiara) {
        if (getIloscAtakow() != 0) {
            if (getSila() >= TworzeniePostaciService.losuj(5, 8)) {
                System.out.println("Postać " + getName() + " będzie próbowała zepchnąć ze schodów " + ofiara.getName());
                ofiara.unik(this, TworzeniePostaciService.losuj(RodzajeAtakowObrazniaEnum.ZEPCHNIJ_ZE_SCHODOW.getMinWart(),RodzajeAtakowObrazniaEnum.ZEPCHNIJ_ZE_SCHODOW.getMaxWart()));
                redukcjaIloscAtakow();
            } else {
                System.out.println("Postaci " + getName() + " nie udało się zepchnąć ze schodów " + ofiara.getName() + " bo nie miała wymaganej siły");
                redukcjaIloscAtakow();
                ofiara.redukcjaIloscUnikow();
            }
        } else {
            System.out.println("Postac " + getName() + " nie ma ataku");
        }
    }

    @Override
    public void atak(Istota ofiara) {
        Integer indexAtaku = TworzeniePostaciService.losuj(0, 100) + doswiadczenieDoAtaku();
        if (indexAtaku <= RodzajeAtakowEnum.DONIES.getMaxWart()) {
            System.out.println("Postać " + getName() + " wybrała donosicielstwo");
            donies(ofiara);
        } else if (indexAtaku >= RodzajeAtakowEnum.OBLEJ_KAWA.getMinWart() && indexAtaku <= RodzajeAtakowEnum.OBLEJ_KAWA.getMaxWart()) {
            System.out.println("Postać " + getName() + " wybrała oblewanie kawą");
            oblejKawa(ofiara);
        } else if (indexAtaku >= RodzajeAtakowEnum.ZEPCHNIJ_ZE_SCHODOW.getMinWart()) {
            System.out.println("Postać " + getName() + " wybrała zepchnięcie ze schodów");
            zepchnijZeSchodow(ofiara);
        } else {
            this.redukcjaIloscAtakow();
            ofiara.redukcjaIloscUnikow();
            System.out.println("Postać " + getName() + " nie może wybrać rzadnego ataku");
        }
    }
}