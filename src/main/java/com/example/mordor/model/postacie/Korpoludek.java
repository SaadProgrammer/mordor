package com.example.mordor.model.postacie;

import com.example.mordor.service.TworzeniePostaciService;

/**
 * Created by Saad on 2019-02-24.
 */


public class Korpoludek extends Humanoid {

    public Korpoludek() {
        super(TworzeniePostaciService.losuj(4, 7), TworzeniePostaciService.losuj(6, 9), TworzeniePostaciService.losuj(7, 10), TworzeniePostaciService.losuj(4, 7), TypPostaciEnum.KORPOLUDEK, 11);
    }

}