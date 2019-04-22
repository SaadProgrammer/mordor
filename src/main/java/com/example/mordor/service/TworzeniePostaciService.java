package com.example.mordor.service;

import com.example.mordor.model.postacie.*;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Created by Saad on 2019-02-24.
 */

@Service

public class TworzeniePostaciService {

    public static int losuj(int minimum, int maximum) {
        Random rn = new Random();
        return rn.nextInt(maximum - minimum + 1) + minimum;
    }

    Istota stworzPostac(TypPostaciEnum typPostaci) {
        Istota nowaPostac;
        if (typPostaci.equals(TypPostaciEnum.KORPOLUDEK)) {
            nowaPostac = new Korpoludek();
        } else if (typPostaci.equals(TypPostaciEnum.MANAGER)) {
            nowaPostac = new Manager();
        } else if (typPostaci.equals(TypPostaciEnum.OCHRONIARZ)) {
            nowaPostac = new Ochroniarz();
        } else if (typPostaci.equals(TypPostaciEnum.PANKANAPKA)) {
            nowaPostac = new PanKanapka();
        } else if (typPostaci.equals(TypPostaciEnum.RECEPCJONISTKA)) {
            nowaPostac = new Recepcjonistka();
        } else if (typPostaci.equals(TypPostaciEnum.SPRZATACZKA)) {
            nowaPostac = new Sprzataczka();
        } else if (typPostaci.equals(TypPostaciEnum.PREZES)) {
            nowaPostac = new Prezes();
        } else

        {
            nowaPostac = null;
        }
        if (Objects.nonNull(nowaPostac))

        {
            nowaPostac.setName(typPostaci.name());
            nowaPostac.setIdPostaci(UUID.randomUUID().toString());
            System.out.println("Nowa postac to: " + nowaPostac.toString());
        }
        return nowaPostac;
    }

    Istota stworzDowolnaPostac() {
        Istota nowaPostac;
        List<TypPostaciEnum> listaTypowPostaci = Arrays.asList(TypPostaciEnum.values());
        Integer indexTwrzonejPostaci = losuj(0, listaTypowPostaci.size());
        TypPostaciEnum dowolnaPostac = listaTypowPostaci.get(indexTwrzonejPostaci);
        nowaPostac = stworzPostac(dowolnaPostac);
        return nowaPostac;
    }

    public List<Istota> stworzWieleDowolnychPostaci(Integer ile) {
        List<Istota> listaPostaci = new ArrayList<>();
        for (int i = 0; i < ile; i++) {
            Istota nowaPostac = stworzDowolnaPostac();
            listaPostaci.add(nowaPostac);
        }
        return listaPostaci;
    }

    public List<Istota> stworzWieleOkreslonychPostaci(Integer ile, TypPostaciEnum typPostaci) {
        List<Istota> listaPostaci = new ArrayList<>();
        for (int i = 0; i < ile; i++) {
            Istota nowaPostac = stworzPostac(typPostaci);
            listaPostaci.add(nowaPostac);
        }
        return listaPostaci;
    }
}


