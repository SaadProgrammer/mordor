package com.example.mordor;

import com.example.mordor.model.postacie.Istota;
import com.example.mordor.model.postacie.TypPostaciEnum;
import com.example.mordor.service.TworzeniePostaciService;
import com.example.mordor.service.WalkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@EnableAutoConfiguration
@SpringBootConfiguration
@SpringBootApplication

public class MordorApplication {

    public static void main(String[] args) {

        TworzeniePostaciService tps = new TworzeniePostaciService();
        List<Istota> lista = tps.stworzWieleDowolnychPostaci(2);

        WalkaService ws = new WalkaService();
        ws.walkaOstatniZywy(lista);

       /* ws.globalKorpoFight(1, 4);
        ws.globalKorpoWinner(ws.korpoWinnerList);*/
    }

}
