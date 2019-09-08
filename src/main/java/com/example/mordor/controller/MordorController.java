package com.example.mordor.controller;

import com.example.mordor.model.postacie.Istota;
import com.example.mordor.model.postacie.TypPostaciEnum;
import com.example.mordor.service.TworzeniePostaciService;
import com.example.mordor.service.WalkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class MordorController {

    @Autowired
    private TworzeniePostaciService tps;

    @Autowired
    private WalkaService ws;

    @RequestMapping(method = GET, path = "/symulacjaWalkiWybranychPostaci", produces = MediaType.APPLICATION_JSON_VALUE)
    public Istota symulacjaKorpoWinner(@RequestParam(value = "postac1") TypPostaciEnum postac1, @RequestParam(value = "postac2") TypPostaciEnum postac2) {
        List<Istota> lista1 = tps.stworzWieleOkreslonychPostaci(1, postac1);
        List<Istota> lista2 = tps.stworzWieleOkreslonychPostaci(1, postac2);
        List<Istota> listaWybranychPostaci = new ArrayList<>();
        listaWybranychPostaci.addAll(lista1);
        listaWybranychPostaci.addAll(lista2);
        return ws.korpoWinner(listaWybranychPostaci);
    }

    @RequestMapping(method = GET, path = "/walkaDowolnychPostaciIDodanieDoKorpoWinnerListZwyciezcowPoszczegolnychSezonow", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Istota> listaZwyciezcowPoszczegolnychSezonow(@RequestParam(value = "ileSezonow") Integer ileSezonow, @RequestParam(value = "iluWalczacyhNaSezon") Integer iluWalczacychNaSezon) {
        List<Istota> aktualizacjaKorpoWinnerList = ws.globalKorpoFight(tps, ileSezonow, iluWalczacychNaSezon);
        ws.korpoWinnerList.addAll(aktualizacjaKorpoWinnerList);
        return ws.korpoWinnerList;
    }

    @RequestMapping(method = GET, path = "/walkaNajlepszychZebranychWKorpoWinnerList", produces = MediaType.APPLICATION_JSON_VALUE)
    public Istota walkaNajlepszychKorpoWinnerList() {
        return ws.globalKorpoWinner(ws.korpoWinnerList);
    }

    @RequestMapping(method = GET, path = "/pokazKorpoWinnerList")
    public List<Istota> pokazKorpoWinnerList() {
        return ws.korpoWinnerList;
    }

    @RequestMapping(method = POST, path = "/dodajWybranaPostacDoKorpoWinnerList")
    public void dodajWybranaPostacDoKorpoWinnerList(@RequestParam(value = "wybranaPostac") TypPostaciEnum wybranaPostac) {
        ws.dodajPostacDoKorpoWinnerList(tps, wybranaPostac, ws.korpoWinnerList);
    }

    @RequestMapping(method = DELETE, path = "/usunWybranaPostacZKorpoWinnerList", produces = MediaType.APPLICATION_JSON_VALUE)
    public void usunWybranaPostacZKorpoWinnerList(@RequestParam(value = "idPostaci") String idPostaci) {
        ws.usunWybranaPostacZKorpoWinnerList(idPostaci, ws.korpoWinnerList);
    }

    @RequestMapping(method = DELETE, path = "/usunZawartoscKorpoWinnerList", produces = MediaType.APPLICATION_JSON_VALUE)
    public void usunZawartoscKorpoWinnerList() {
        ws.usunWszystkichZKorpoWinnerList(ws.korpoWinnerList);
    }
}
