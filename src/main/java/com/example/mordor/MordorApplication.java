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
        WalkaService ws = new WalkaService();

        List<Istota> korpoSzczury = tps.stworzWieleDowolnychPostaci(5);
        ws.korpoWinner(korpoSzczury);

        //POWYŻSZE DZIAŁA, ALE JEŚLI CHCE JUŻ ZROBIĆ BARDZIEJ GLOBALNIE Z LISTĄ TO NIE - PONIŻEJ (OBIE FUNKCJE W WALKA SERVICE
        //PROBLEM Z KORPOWINNER LISTĄ

        /*ws.globalKorpoWinner(ws.globalKorpoFight(2,4));*/


    }
}
