package com.example.mordor.controller;

import com.example.mordor.model.postacie.Istota;
import com.example.mordor.service.TworzeniePostaciService;
import com.example.mordor.service.WalkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MordorController {

    @Autowired
    private TworzeniePostaciService tps;

    @Autowired
    private WalkaService ws;

    @RequestMapping(method = GET, path = "/symulacjaKorpoWinnerDowolnePostacie", produces = MediaType.APPLICATION_JSON_VALUE)
    public Istota symulacjaKorpoWinner(@RequestParam(value = "iluWalczacych") Integer iluWalczacych) {
        List<Istota> listaWalczacych = tps.stworzWieleDowolnychPostaci(iluWalczacych);
        return ws.korpoWinner(listaWalczacych);
    }

    @RequestMapping(method = GET, path = "/symulacjaGlobalKorpoWinnerDowolnePostacie", produces = MediaType.APPLICATION_JSON_VALUE)
    public Istota symulacjaGlobalKorpoWinner(@RequestParam(value = "ileSezonow") Integer ileSezonow, @RequestParam(value = "iluWalczacyhNaSezon") Integer iluWalczacychNaSezon) {
        return ws.globalKorpoWinner(ws.globalKorpoFight(tps, ileSezonow, iluWalczacychNaSezon));
    }

}
