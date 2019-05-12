package com.example.mordor.service;

import com.example.mordor.model.postacie.Humanoid;
import com.example.mordor.model.postacie.Istota;
import com.example.mordor.model.postacie.TypPostaciEnum;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TworzeniePostaciServiceTest {

    @Test
    public void funkcjaLosowaniaZwracaWartosciZprzedzialuPodanegoWparametrach() {
        //given
        int min = 0;
        int max = 4;
        //when
        int wynik = TworzeniePostaciService.losuj(min, max);
        //then
        Assert.assertTrue(wynik > min || wynik < max);
    }

    @Test
    public void funkcjaStworzPostacZwracaIstote() {
        //given
        TypPostaciEnum typPostaci = TypPostaciEnum.KORPOLUDEK;
        TworzeniePostaciService tps = new TworzeniePostaciService();
        //when
        Istota postac = tps.stworzPostac(typPostaci);
        //then
        Assert.assertNotNull(postac);
        Assert.assertTrue(postac instanceof Istota);
        Assert.assertTrue(postac instanceof Humanoid);
        Assert.assertEquals(TypPostaciEnum.KORPOLUDEK, postac.getTypPostaci());
        Assert.assertNotNull(postac.getSila());
        Assert.assertNotNull(postac.getWytrzymalosc());
        Assert.assertNotNull(postac.getSpryt());
        Assert.assertNotNull(postac.getSzybkosc());
        Assert.assertNotNull(postac.getTypPostaci());
        Assert.assertNotNull(postac.getPunktyKorpoZycia());
    }

    @Test
    public void funkcjaStworzDowolnaPostacZwracaIstoteDowolnegoTypu() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        TypPostaciEnum[] listaPostaciEnum = TypPostaciEnum.values();
        List<TypPostaciEnum> typyPostaci = Arrays.asList(listaPostaciEnum);
        //when
        Istota postac = tps.stworzDowolnaPostac();
        //then
        Assert.assertNotNull(postac);
        Assert.assertTrue(typyPostaci.contains(postac.getTypPostaci()));
    }

    @Test
    public void funkcjaStworzWeleDowolnychPostaciZwracaListeRoznychIstot() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        //when
        List<Istota> listaPostaci = tps.stworzWieleDowolnychPostaci(2);
        //then
        Assert.assertNotNull(listaPostaci);
        Assert.assertTrue(listaPostaci.get(0).getIdPostaci() != listaPostaci.get(1).getIdPostaci());
    }

    @Test
    public void funkcjaStworzWeleOkreslonychPostaciZwracaListeOkreslonychIstot() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        Integer ilePostaci = 4;
        //when
        List<Istota> listaPostaci = tps.stworzWieleOkreslonychPostaci(ilePostaci, TypPostaciEnum.KORPOLUDEK);
        //then
        Assert.assertNotNull(listaPostaci);
        for (int i = 0; i < ilePostaci; i++) {
            Assert.assertEquals(TypPostaciEnum.KORPOLUDEK, listaPostaci.get(i).getTypPostaci());
        }
    }

    @Test
    public void CzyTworzaSieWszystkieTypyPostaci() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        List<Istota> listaPostaci = new ArrayList<>();
        TypPostaciEnum[] tablicaTypowPostaci = TypPostaciEnum.values();
        List<TypPostaciEnum> typyPostaci = Arrays.asList(tablicaTypowPostaci);
        //when
        for (TypPostaciEnum typIstoty : typyPostaci) {
            listaPostaci.add(tps.stworzPostac(typIstoty));
        }
        //then
        for (Istota postac : listaPostaci) {
            Assert.assertTrue(typyPostaci.contains(postac.getTypPostaci()));
        }
        Assert.assertEquals(listaPostaci.size(), typyPostaci.size());
    }
}