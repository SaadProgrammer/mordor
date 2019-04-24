package com.example.mordor.model.postacie;

import com.example.mordor.service.TworzeniePostaciService;

/**
 * Created by Saad on 2019-03-11.
 */
public class Prezes extends NadHumanoid {

    public Prezes() {
        super(TworzeniePostaciService.losuj(6, 9), TworzeniePostaciService.losuj(3, 6), TworzeniePostaciService.losuj(7, 10), TworzeniePostaciService.losuj(2, 5), TypPostaciEnum.PREZES, 12);
    }
}

