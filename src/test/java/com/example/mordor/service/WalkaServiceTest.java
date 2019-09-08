package com.example.mordor.service;

import com.example.mordor.model.postacie.Istota;
import com.example.mordor.model.postacie.TypPostaciEnum;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class WalkaServiceTest {

    @Test
    public void funkcjaSprawdzaDodanieWybranejPostaciDoKorpoWinnerList() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        WalkaService ws = new WalkaService();
        TypPostaciEnum typDodawanejPostaci = TypPostaciEnum.RECEPCJONISTKA;
        //when
        ws.dodajPostacDoKorpoWinnerList(tps,typDodawanejPostaci, ws.korpoWinnerList);
        //then
        Assert.assertTrue(ws.korpoWinnerList.size()==1);
        Assert.assertTrue(ws.korpoWinnerList.get(0).getTypPostaci().equals(typDodawanejPostaci));
    }

    @Test
    public void funkcjaSprawdzaUsuwanieWybranejPostaciZKorpoWinnerList() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        WalkaService ws = new WalkaService();
        List<Istota> lista = tps.stworzWieleDowolnychPostaci(4);
        String idPostaciUsuwanej = lista.get(0).getIdPostaci();
        Istota postacUsuwana = lista.get(0);
        ws.korpoWinnerList.addAll(lista);
        //when
        ws.usunWybranaPostacZKorpoWinnerList(idPostaciUsuwanej, ws.korpoWinnerList);
        //then
        Assert.assertFalse(ws.korpoWinnerList.contains(postacUsuwana));
    }

    @Test
    public void funkcjaSprawdzaCzyszczenieKorpoWinnerList() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        WalkaService ws = new WalkaService();
        List<Istota> lista = tps.stworzWieleDowolnychPostaci(4);
        ws.korpoWinnerList.addAll(lista);
        //when
        ws.usunWszystkichZKorpoWinnerList(ws.korpoWinnerList);
        //then
        Assert.assertTrue(ws.korpoWinnerList.isEmpty());
    }

    @Test
    public void funkcjaSprawdzaCzyGlobalKorpoWinnerCzysciKorpoWinnerList() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        WalkaService ws = new WalkaService();
        List<Istota> lista = tps.stworzWieleDowolnychPostaci(4);
        //when
        Istota zwyciezca = ws.globalKorpoWinner(lista);
        //then
        Assert.assertNotNull(lista);
        Assert.assertNotNull(zwyciezca);
        Assert.assertTrue(1 == lista.size());
    }

    @Test
    public void funkcjaSprawdzaGlobalKorpoWinner() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        WalkaService ws = new WalkaService();
        List<Istota> lista = tps.stworzWieleDowolnychPostaci(4);
        //when
        Istota zwyciezca = ws.globalKorpoWinner(lista);
        //then
        Assert.assertNotNull(lista);
        Assert.assertNotNull(zwyciezca);
        Assert.assertTrue(zwyciezca.getPunktyKorpoZycia() > 0);
    }

    @Test
    public void funkcjaSprawdzaGlobalKorpoFight() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        WalkaService ws = new WalkaService();
        Integer ileSezonow = 2;
        Integer ileWalczakowSezon = 2;
        //when
        List<Istota> zwyciezca = ws.globalKorpoFight(tps, ileSezonow, ileWalczakowSezon);
        //then
        Assert.assertNotNull(zwyciezca);
        Assert.assertNotNull(ws.korpoWinnerList);
        Assert.assertTrue(ws.korpoWinnerList.size() == ileSezonow);
    }

    @Test
    public void funkcjaSprawdzaKorpoWinner() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        WalkaService ws = new WalkaService();
        List<Istota> lista = tps.stworzWieleDowolnychPostaci(4);
        //when
        Istota zwyciezca = ws.korpoWinner(lista);
        //then
        Assert.assertNotNull(lista);
        Assert.assertNotNull(zwyciezca);
        Assert.assertTrue(zwyciezca.getPunktyKorpoZycia() > 0);
        Assert.assertTrue(zwyciezca.getIloscUnikow() == 1);
        Assert.assertTrue(zwyciezca.getIloscAtakow() == 1);
    }

    @Test
    public void funkcjaSprawdzaWalkaOstatniZywy() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        WalkaService ws = new WalkaService();
        List<Istota> lista = tps.stworzWieleDowolnychPostaci(4);
        //when
        Istota zwyciezca = ws.walkaOstatniZywy(lista);
        //then
        Assert.assertNotNull(lista);
        Assert.assertNotNull(zwyciezca);
        Assert.assertTrue(zwyciezca.getPunktyKorpoZycia() > 0);

    }

    @Test
    public void funkcjaSprawdzaLosowanieWalczaka() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        WalkaService ws = new WalkaService();
        List<Istota> lista = tps.stworzWieleDowolnychPostaci(4);
        //when
        Istota wylosowany = ws.losowanieWalczaka(lista);
        //then
        Assert.assertNotNull(wylosowany);
        Assert.assertFalse(lista.contains(wylosowany));
        Assert.assertFalse(lista.size() == 4);
    }

    @Test
    public void funkcjaSprawdzaWalkeParyWalczakow() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        WalkaService ws = new WalkaService();
        Istota atakujacy = tps.stworzDowolnaPostac();
        Istota ofiara = tps.stworzDowolnaPostac();
        //when
        Istota zwyciezca = ws.walkaParyWalczakow(atakujacy, ofiara);
        //then
        Assert.assertNotNull(atakujacy);
        Assert.assertNotNull(ofiara);
        Assert.assertTrue(atakujacy.getPunktyKorpoZycia() >= 0);
        Assert.assertTrue(ofiara.getPunktyKorpoZycia() >= 0);
        Assert.assertNotNull(zwyciezca);
        Assert.assertTrue(zwyciezca.getPunktyKorpoZycia() >= 8);
        Assert.assertTrue(zwyciezca.getPunktyDoswiadczenia() >= 10);
        Assert.assertTrue(zwyciezca.getWygraneWalki() >= 1);
        Assert.assertTrue(zwyciezca.getWygraneWalki() >= 1);
    }

    @Test
    public void funkcjaSprawdzaRundeWalki() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        WalkaService ws = new WalkaService();
        Istota atakujacy = tps.stworzDowolnaPostac();
        Istota ofiara = tps.stworzDowolnaPostac();
        //when
        ws.rundaWalki(atakujacy, ofiara);
        //then
        Assert.assertNotNull(atakujacy);
        Assert.assertNotNull(ofiara);
        Assert.assertTrue(atakujacy.getIdPostaci() != ofiara.getIdPostaci());
        Assert.assertTrue(atakujacy.getIloscAtakow() >= 0);
        Assert.assertTrue(atakujacy.getPunktyKorpoZycia() >= 0);
        Assert.assertTrue(ofiara.getIloscAtakow() >= 0);
        Assert.assertTrue(ofiara.getPunktyKorpoZycia() >= 0);
        Assert.assertTrue(atakujacy.getIloscUnikow() == 1);
        Assert.assertTrue(ofiara.getIloscUnikow() == 1);
    }

    @Test
    public void funkcjaSprawdzadecydujacaRundeWalki() {
        //given
        TworzeniePostaciService tps = new TworzeniePostaciService();
        WalkaService ws = new WalkaService();
        Istota atakujacy = tps.stworzDowolnaPostac();
        Istota ofiara = tps.stworzDowolnaPostac();
        //when
        ws.decydujacaRundaWalkiPunktySzczescie(atakujacy, ofiara);
        //then
        Assert.assertNotNull(atakujacy);
        Assert.assertNotNull(ofiara);
        Assert.assertTrue(atakujacy.getIdPostaci() != ofiara.getIdPostaci());
        if (atakujacy.getPunktyKorpoZycia() == 0) {
            Assert.assertTrue(ofiara.getPunktyKorpoZycia() > 0);
        } else {
            Assert.assertTrue(ofiara.getPunktyKorpoZycia() == 0);
        }
        Assert.assertTrue(atakujacy.getPunktyKorpoZycia() != ofiara.getPunktyKorpoZycia());
    }
}